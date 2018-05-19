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

/*
	Arithmetic expression evaluation:
	As another example of a stack client, we consider
	a classic example that also demonstrates the utility of generics. Some of the first pro-
	grams that we considered in Section 1.1 involved computing the value of arithmetic
	expressions like this one:
	( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )
*/
void sample_arithmetic_expression_evaluation()
{
	cout << "sample_arithmetic_expression_evaluation:\n";
	stack<string> ops;
	stack<double> vals;
	string exp("( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )");
	string op;
	double val;
	string tmp;
	double v;
	for (char str : exp)
	{
		tmp = str;
		switch (str)
		{
		case '(':
		case ' ':
			// ignore
			break;
		case '+':
		case '-':
		case '*':
		case '/':
			ops.push(tmp);
			break;
		case ')':
			op = ops.top();
			val = vals.top();
			vals.pop();
			ops.pop();
			if (op == "+")
				v = val + vals.top();
			else if (op == "-")
				v = vals.top() - val;
			else if (op == "*")
				v = vals.top() * val;
			else if (op == "/")
				v = vals.top() / val;
			vals.pop();
			vals.push(v);
			break;
		default:
			vals.push(stod(tmp));
			break;
		}
	}
	cout << vals.top() << endl;
}
/*
	1.3.9
	Write a program that takes from standard input an expression without left parentheses
	and prints the equivalent infix expression with the parentheses inserted.
	For example, given the input : 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
	your program should print:     ((1 + 2) * ((3 - 4) * (5 - 6))
	ops: "1"   "2"
	exp:    "+"
	=> meet ")"
	ops: "(1+2)"  "3"   "4"
	exp:            "*"  "-"
	=> meet ")"
	ops: "(1+2)"   "(3-4)"   "5"   "6"
	exp:            "*"         "*"   "-"
	=> meet ")"
	ops: "(1+2)"   "(3-4)"   "(5-6)"
	exp:            "*"         "*"
	=> meet ")"
	ops: "(1+2)"   "((3-4)*(5-6))"
	exp:            "*"
	=> meet last ")"
	ops: "(((1+2)*(3-4)*(5-6)))"
	exp:
	=> done
*/
void exercise_1_3_9()
{
	stack<string> expression;
	stack<string> ops;
	string tmp;
	string ret;
	string input;
	tmp.reserve(1);
	ret.reserve(64);
	input.reserve(256);
	getline(cin, input);
	for (auto itr = input.begin(); itr != input.end(); ++itr)
	{
		if ((*itr == ' '))
			continue;
		tmp = *itr;
		if (tmp == "+" || tmp == "-" || tmp == "*" || tmp == "/")
			ops.push(tmp);
		else if (*itr == ')')
		{
			tmp = expression.top();
			expression.pop();
			ret = "(" + (expression.size() ? expression.top() : "") + (ops.size() ? ops.top() : "") + tmp + ")";
			if (ops.size())
				ops.pop();
			if (expression.size())
				expression.pop();
			expression.push(ret);
		}
		else
			expression.push(tmp);
	}
	while (ops.size())
	{	// this is for this is special case where ops not empty when all str are scanned 	eg. input = "1 + 2)) + 1 + 2)"; 
		tmp = expression.top();
		expression.pop();
		ret = "(" + expression.top() + ops.top() + tmp + ")";
		expression.pop();
		ops.pop();
		expression.push(ret);
	}
	cout << expression.top() << endl;
}
int main(char* args[])
{
	//sample_arithmetic_expression_evaluation();
	exercise_1_3_9();
	return 0;
}