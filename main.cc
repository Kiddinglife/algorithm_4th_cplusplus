#include <iostream>
#include <string>
#include <vector>
#include <stack>
#include <list>
#include <map>
#include <unordered_map>
#include <unordered_set>
#include <set>
#include <array>
using namespace std;

namespace chapter1
{
	namespace section5
	{
		namespace titles_4_5
		{
			// Figure 1.14 Swapping by three copies
			void swap(double & x, double & y)
			{
				double tmp = x;
				x = y;
				y = tmp;
			}
			void swap(vector<string> & x, vector<string> & y)
			{
				vector<string> tmp = static_cast<vector<string> &&>(x);
				x = static_cast<vector<string> &&>(y);
				y = static_cast<vector<string> &&>(tmp);
			}

			// Figure 1.15 Swapping by three moves; first with a type cast, second using std::move
			void swap(vector<string> & x, vector<string> & y)
			{
				vector<string> tmp = std::move(x);
				x = std::move(y);
				y = std::move(tmp);
			}
			void swap(vector<string> & x, vector<string> & y)
			{
				vector<string> tmp = x;
				x = y;
				y = tmp;
			}

			// Figure 1.12 Two versions to obtain a random item in an array; second version avoids
			// creation of a temporary LargeType object, but only if caller accesses it with a constant reference
			static list<int> f1(const vector<list<int>>& arr)
			{
				// rven thopugh arr[0] is copied to a tempory list<int> and returned to caller
				// but 	c++ compiler looks at value's type just followed by rreturn keyword to recognize it is  lv or rv
				// if caller do not specify std::move(), copy ctor is used. as it is not tempory return value not moved in default
				return arr[0];
			}
			static const  list<int>& f2(const vector<list<int>>& arr)
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
				vector<list<int>> vec;
				list<int> lis;
				lis.push_back(12);
				vec.push_back(lis);
				list<int> tmp1 = f1(vec); // 1 copy ctor is used notice the difference from  eg 5
				list<int> tmp3 = std::move(f1(vec)); // 2 move ctor
				list<int> tmp2 = f2(vec); // 3 copy ctor

				const list<int>& tmp4 = f2(vec); // 4 pointer assigment
				vector<int> vec1{ 1,2,6,5 };
				// 5 move ctor is used in C++11 no need to std::move()
				vector<int> sums = f3(vec1);
			}
		}
		namespace titles_6
		{
			// three move swap looks like this:
			//template<typename T> void swap(T& t1, T& t2) {
			//	T temp = std::move(t1); // or T temp(std::move(t1));
			//	t1 = std::move(t2);
			//	t2 = std::move(temp);
			//}

			//IntCell & operator= (IntCell && rhs) // Move assignment
			//{
			// If swap is implemented as three moves, then we
			// would have mutual nonterminating recursion.
			//	std::swap(storedValue, rhs.storedValue);
			//	 return *this;
			// }
		}
	}
}

int main(char* args[])
{
	chapter1::section5::titles_4_5::run();
	return 0;
}