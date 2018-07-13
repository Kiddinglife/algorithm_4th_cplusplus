#include <iostream>
#include <string>
#include <vector>
#include <stack>
#include <list>
#include <map>
#include <queue>
#include <unordered_map>
#include <unordered_set>
#include <set>
#include <array>
#include <algorithm>
#include <unordered_set>
#include <float.h>
#include <limits.h>
#include <cmath>
#include <limits>
#include <iomanip>
#include <iostream>
#include <type_traits>
#include <numeric>
#include <chrono>

using namespace std;

/** How to use:
 profile_start(code1);
 ...code
 profile_end_ms(code1);
 profile_start(code2);
 ...code
 profile_end_ms(code2);
 profile_ratio(code1, code2);
 */
#define profile_start(id) \
auto start##id = std::chrono::steady_clock::now()
#define profile_end_ms(id) \
auto end##id = std::chrono::steady_clock::now();\
auto elapsed##id = \
std::chrono::duration<double, std::milli>(end##id - start##id).count() ;\
std::cout << #id << ":" << elapsed##id<< "ms, "
#define profile_ratio(id1,id2) \
std::cout << "ratio: " << (elapsed##id1)/(elapsed##id2) << " "

namespace geeksforgeeks
{
  namespace binarytree
  {
    /* A binary tree node has data, pointer to left child
     and a pointer to right child */
    struct node
    {
        int data;
        struct node* left, *right;
        node(int data)
        {
          this->data = data;
          left = right = NULL;
        }
    };
    static node* create_tree()
    {
      struct node *root = new node(1);
      root->left = new node(2);
      root->right = new node(3);
      root->left->left = new node(4);
      root->left->right = new node(5);
      root->left->right->left = new node(6);
      return root;
    }
    namespace recusive_travelsal
    {
      // left right is fixed up we only look at root's positon inorder is in middle
      // postorder is in back preorder is in front
      // https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
      // left right root
      static void postorder(node* root)
      {
        if (!root)
          return;
        postorder(root->left);
        postorder(root->right);
        cout << root->data;
      }
      static void inorder(node* root)
      {
        if (!root)
          return;
        inorder(root->left);
        cout << root->data;
        inorder(root->right);
      }
      static void preorder(node* root)
      {
        if (!root)
          return;
        cout << root->data;
        preorder(root->left);
        preorder(root->right);
      }
      namespace levelorder
      {
        // O(n^2)
        /* Compute the "height" of a tree -- the number of
         nodes along the longest path from the root node
         down to the farthest leaf node.*/
        static int tree_height(node* root)
        {
          if (!root)
            return 0;
          int lh = tree_height(root->left);
          int rh = tree_height(root->right);
          return max(lh, rh) + 1;
        }
        static void print_level(node* root, int level)
        {
          if (!root)
            return;
          if (level == 1)
            cout << root->data;
          else
          {
            print_level(root->left, level - 1);
            print_level(root->right, level - 1);
          }
        }
        static void run(node* root)
        {
          // O(n) + O(n-1) + O(n-2) + .. + O(1) which is O(n^2).
          int h = tree_height(root);
          for (int i = 1; i <= h; i++)
            print_level(root, i);
        }
      }
      static void run()
      {
        ///        1
        ///      /    \
        ///    2      3
        ///  /  \
        /// 4    5
        ///       /\
        ///     6 nil
        cout << "geeksforgeeks\n---> binarytree\n------> recusive_travelsal\n";
        node* root = create_tree();
        cout << "--------> inorder() = ";
        inorder(root);  //426513
        cout << endl;
        cout << "--------> preorder() = ";
        preorder(root); // 124563
        cout << endl;
        cout << "--------> postorder() = ";
        postorder(root); // 465231
        cout << endl;
        cout << "--------> levelorder() = ";
        levelorder::run(root); // 123456
        cout << endl;
      }
    }
    namespace non_recusive_travelsal
    {
      static void preorder(node* root)
      {
        if (root == NULL)
          return;
        stack<node *> nodeStack;
        nodeStack.push(root);
        while (!nodeStack.empty())
        {
          node *node = nodeStack.top();
          printf("%d", node->data);
          nodeStack.pop();
          // Note that right child is pushed first so that left is processed first
          if (node->right)
            nodeStack.push(node->right);
          if (node->left)
            nodeStack.push(node->left);
        }
      }
      static void inorder(node* root)
      {
        //1) Create an empty stack S.
        stack<node*> s;
        //2) Initialize current node as root
        node* cur = root;
        //3) If current is NULL and stack is not empty then
        while (cur || !s.empty())
        {
          //4) Push the current node to S and set current = current->left until current is NULL
          while (cur)
          {
            /* place pointer to a tree node on
             the stack before traversing
             the node's left subtree */
            s.push(cur);
            cur = cur->left;
          }
          /* Current must be NULL at this point */
          //b) Print the popped item, set current = popped_item->right
          cur = s.top();
          //a) Pop the top item from stack.
          s.pop();
          cout << cur->data;
          /* we have visited the node and its
           left subtree.  Now, it's right subtree's turn */
          cur = cur->right;
        }
      }
      static void postorder(node* root)
      {
        //1) Create an empty stack S.
        stack<node*> s;
        //2) Initialize current node as root
        node* cur = root;
        node* rightnode = nullptr;
        //3) If current is NULL and stack is not empty then
        while (cur || !s.empty())
        {
          //4) Push the current node to S and set current = current->left until current is NULL
          while (cur)
          {
            /* place pointer to a tree node on
             the stack before traversing
             the node's left subtree */
            s.push(cur);
            cur = cur->left;
          }
          cur = s.top();
          if (!cur->right || rightnode == cur->right)
          {
            // cur->left must be null plus right is null leaf node just print it and pop it
            //a) Pop the top item from stack.
            //b) Print the popped item, set current = popped_item->right
            s.pop();
            cout << cur->data;
            cur = nullptr;            // this gives us the next node in stack in next iteration
          }
          else
          {
            /* we have visited the node and its
             left subtree.  Now, it's right subtree's turn */
            cur = cur->right;
            rightnode = cur;
          }
        }
      }
      void levelorder(node *root)
      {
        if (!root)
          return;
        queue<node*> q;
        // Enqueue Root and initialize height
        q.push(root);
        while (!q.empty())
        {
          // Print front of queue and remove it from queue
          root = q.front();
          cout << root->data;
          q.pop();
          if (root->left)
            q.push(root->left);
          if (root->right)
            q.push(root->right);
        }
      }

      static void run()
      {
        ///        1
        ///      /    \
        ///    2      3
        ///  /  \
        /// 4    5
        ///       /\
        ///     6 nil
        cout << "------> non_recusive_travelsal\n";
        node* root = create_tree();
        cout << "--------> inorder() = ";
        inorder(root);  //426513
        cout << endl;
        cout << "--------> preorder() = ";
        preorder(root);  // 124563
        cout << endl;
        cout << "--------> postorder() = ";
        postorder(root);  // 465231
        cout << endl;
        cout << "--------> levelorder() = ";
        levelorder(root);  // 123456
        cout << endl;
      }
    }
  }
}

namespace ds_algo_in_c
{
  namespace c1
  {
    namespace s3
    {
      // Figure 1.4 Recursive routine to print an integer
      static void print_num(int64_t v, int depth)
      {
        for (int i = 0; i < depth; i++)
          cout << "  ";
        cout << "print_num " << v << endl;
        if (v >= 10)
          print_num(v / 10, depth + 1);
        for (int i = 0; i < depth; i++)
          cout << "  ";
        cout << "print_num " << v << ": " << v % 10 << endl;
      }
    }
    namespace s5
    {
      namespace t_4_and_5
      {
        // Figure 1.14 Swapping by three copies
        void swap1(double & x, double & y)
        {
          double tmp = x;
          x = y;
          y = tmp;
        }
        void swap2(vector<string> & x, vector<string> & y)
        {
          vector<string> tmp = static_cast<vector<string> &&>(x);
          x = static_cast<vector<string> &&>(y);
          y = static_cast<vector<string> &&>(tmp);
        }

        // Figure 1.15 Swapping by three moves; first with a type cast, second using std::move
        void swap3(vector<string> & x, vector<string> & y)
        {
          vector<string> tmp = std::move(x);
          x = std::move(y);
          y = std::move(tmp);
        }
        void swap4(vector<string> & x, vector<string> & y)
        {
          vector<string> tmp = x;
          x = y;
          y = tmp;
        }

        // Figure 1.12 Two versions to obtain a random item in an array; second version avoids
        // creation of a temporary LargeType object, but only if caller accesses it with a constant reference
        static list<int> f1(const vector<list<int> >& arr)
        {
          // rven thopugh arr[0] is copied to a tempory list<int> and returned to caller
          // but 	c++ compiler looks at value's type just followed by rreturn keyword to recognize it is  lv or rv
          // if caller do not specify std::move(), copy ctor is used. as it is not tempory return value not moved in default
          return arr[0];
        }
        static const list<int>& f2(const vector<list<int> >& arr)
        {
          return arr[0];
        }
        static const list<int>& f4(vector<list<int> > && arr)
        {
          return arr[0];
        }
        // Figure 1.13 Returning of a stack-allocated rvalue in C ++ 11
        static vector<int> f3(const vector<int> & arr)
        {
          // if caller do not specify std::move(), move ctor is used.
          // as it is tempory return value
          vector<int> result(arr.size());
          return result;
        }

        static void run()
        {
          vector<list<int> > vec;
          list<int> lis;
          lis.push_back(12);
          vec.push_back(lis);
          // f4(vec); // compiler error;
          f4(std::move(vec)); // use std::move() cast lv ref to rv ref
          list<int> tmp1 = f1(vec); // 1 copy ctor is used notice the difference from  eg 5
          list<int> tmp3 = std::move(f1(vec)); // 2 move ctor
          list<int> tmp2 = f2(vec); // 3 copy ctor
          const list<int>& tmp4 = f2(vec); // 4 pointer assigment
          tmp4.size();
          vector<int> vec1 { 1, 2, 6, 5 };
          vector<int> sums = f3(vec1); // 5 move ctor is used in C++11 no need to std::move()
        }
      }
      namespace t_6
      {
      /*
       three move swap looks like this:
       template<typename T> void swap(T& t1, T& t2) {
       T temp = std::move(t1); // or T temp(std::move(t1));
       t1 = std::move(t2);
       t2 = std::move(temp);
       }

       IntCell & operator= (IntCell && rhs) // Move assignment
       {
       If swap is implemented as three moves, then we
       would have mutual nonterminating recursion.
       std::swap(storedValue, rhs.storedValue);
       return *this;
       }
       */
      }

      namespace supplyments
      {
        // this is from http://bajamircea.github.io/coding/cpp/2016/04/07/move-forward.html
        // good explainations about std::move() and std::forward()
        struct X
        {
        };
        // overloads
        void fn(X &)
        {
          std::cout << "X &\n";
        }
        void fn(const X &)
        {
          std::cout << "const X &\n";
        }
        void fn(X &&)
        {
          std::cout << "X &&\n";
        }
        template<class T>
        void perfect_forward(T&& a)
        {
          std::cout << "std::forward: ";
          fn(std::forward<T>(a));
        }
        static void run()
        {
          cout << "std::forward and std::move: " << endl;

          X a;
          fn(a);
          // lvalue selects fn(X &)
          // fallbacks on fn(const X &)

          const X b;
          fn(b);
          // const lvalue requires fn(const X &)

          fn(X());
          // rvalue selects fn(X &&)
          // and then on fn(const X &)

          // we can use std::forward() to
          // achieve the above overloadings when calling templated funn,
          // notice that std::forward only works inside template function
          perfect_forward(a);
          perfect_forward(b);
          perfect_forward(X());
          const X& consta = a;
          perfect_forward(consta);
          X& refa = a;
          perfect_forward(refa);
          cout << endl;
        }
      }
    }

    static void exc_1_find_kth_largest_num()
    {
      cout << "exc_1_find_kth_largest_num() --> ";
      vector<int> vec { 15, 2, 7 };
      int k = 2;
      stable_sort(vec.begin(), vec.end());
      cout << vec[vec.size() - k] << endl;
    }

    /*
     hash all words;
     then find if there is matching in all directions
     O(n^2)
     */
    static void exc_2_word_puzzle_v1()
    {
      cout << "exc_2_word_puzzle() --> " << endl;

      vector<vector<string>> letters { { "t", "h", "i", "s", "n", "c" }, { "w", "f", "t", "s", "a", "s" }, { "o", "a",
          "h", "g", "m", "d" }, { "f", "t", "d", "t", "e", "f" }, { "o", "a", "h", "g", "m", "d" }, { "o", "a", "h",
          "g", "m", "d" } };
      vector<string> words { "hello", "jake", "name", "this", "two", "fat", "that", "at", "it" };

      unordered_set<string> hash;
      int minlen = UINT32_MAX;
      for (auto& str : words)
      {
        hash.insert(str);
        if ((int) str.size() < minlen)
          minlen = str.size();
      }
      int cols = letters[0].size();
      int rows = letters.size();
      string letter;
      letter.reserve(max(cols, rows));
      vector<vector<string>> ret(rows);
      for (auto& row : ret)
        row.resize(cols, "+");
      for (int row = 0; row < rows; row++)
      {
        for (int col = 0; col < cols; col++)
        {
          int cur = row;
          // letter.assign(letters[row][col]);
          if (cur + 1 >= minlen)
          {
            for (int i = 0; i < minlen; i++, cur--)
              letter.append(letters[cur][col]);
            for (; cur > 0; cur--)
            {
              if (hash.find(letter) != hash.end())
              {
                int curr = cur;
                cout << letter << ",";
                for (; curr <= row; curr++)
                {
                  ret[curr][col] = letter[curr - row];
                  //cout << ret[curr][col] << " ";
                }
              }
              letter.append(letters[cur][col]);
            }
          }

          letter.clear();
          cur = row;
          if (rows - cur >= minlen)
          {
            for (int i = 0; i < minlen; i++, cur++)
              letter.append(letters[cur][col]);
            for (; cur < rows; cur++)
            {
              if (hash.find(letter) != hash.end())
              {
                int curr = cur - 1;
                cout << letter << ",";
                for (; curr >= row; curr--)
                {
                  ret[curr][col] = letter[curr - row];
                  //cout << ret[curr][col] << " ";
                }
              }
              letter.append(letters[cur][col]);
            }
          }

          letter.clear();
          cur = col;
          if (cur + 1 >= minlen)
          {
            for (int i = 0; i < minlen; i++, cur--)
              letter.append(letters[row][cur]);
            for (; cur > 0; cur--)
            {
              if (hash.find(letter) != hash.end())
              {
                int curr = cur;
                cout << letter << ",";
                for (; curr <= col; curr++)
                {
                  ret[row][curr] = letter[curr - col];
                  //cout << ret[row][curr] << " ";
                }
              }
              letter.append(letters[row][cur]);
            }
          }

          letter.clear();
          cur = col;
          if (cols - cur >= minlen)
          {
            for (int i = 0; i < minlen; i++, cur++)
              letter.append(letters[row][cur]);
            for (; cur < cols; cur++)
            {
              if (hash.find(letter) != hash.end())
              {
                int curr = cur;
                cout << letter << ",";
                for (; curr >= col; curr--)
                {
                  ret[row][curr] = letter[curr - col];
                  //cout << ret[row][curr] << " ";
                }
              }
              letter.append(letters[row][cur]);
            }
          }
        }
      }

      cout << endl;
      for (auto& row : ret)
      {
        for (auto& str : row)
          cout << str;
        cout << endl;
      }
      cout << endl;
    }

    static void print_double(int num)
    {
      if (num >= 10)
        print_double(num / 10);
      cout << (int) num % 10;
    }
    static void exc_3_print_double(double num)
    {
      // to_string(0 is not accurate when you have many decimal points
      cout << "exc_3_print_double (use recurision): ";
      print_double((int) num);
      cout << ".";
      num -= (double) (int) num;
      double diff;
      do
      {
        num *= 10;
        diff = num - (double) ((int) num);
      } while (diff >= (double) 0.1);
      print_double(num);
    }

    static void exc_3_print_double_i(double num)
    {
      cout << "\nexc_3_print_double_i(use std::to_string())" << ": " << to_string(num) << endl;
    }
  }
}

int main(int argnum, char* args[])
{
  ds_algo_in_c::c1::s5::t_4_and_5::run();
  cout << "print_num(): " << endl;
  ds_algo_in_c::c1::s3::print_num(1234, 0);
  cout << endl;
  ds_algo_in_c::c1::s5::supplyments::run();
  ds_algo_in_c::c1::exc_1_find_kth_largest_num();
  profile_start(exc_2_word_puzzle_v1);
  ds_algo_in_c::c1::exc_2_word_puzzle_v1();
  profile_end_ms(exc_2_word_puzzle_v1);
  ds_algo_in_c::c1::exc_3_print_double(12345.123456789);
  ds_algo_in_c::c1::exc_3_print_double_i(12345.123456789);
  cout << endl;
  geeksforgeeks::binarytree::recusive_travelsal::run();
  geeksforgeeks::binarytree::non_recusive_travelsal::run();
  return 0;
}
