package com.moonpa.calculator;

import java.util.Stack;

/*
拿到算式，假設不填括號。
由左而右，一次讀取一個token
如果是運算元，直接輸出到後序算式（也就是你要算的結果）
如果是運算子，將會push到堆疊，不過要注意
如果堆疊裡面的運算子>=準備放入的運算子的話，裡面的先出來，外面那個再放進去。
如果算式讀完了，把堆疊裡面的運算子依序pop到完
*/

public class CalculatorLogical
{
    private double answer;

    // + and - -> 1, * and / -> 2,other -> 0
    private int priority(char c)
    {
        return c == '+' || c == '-' ? 1 : c == '*' || c == '/' ? 2 : 0;
    }

    private String toPostfix(String infix)
    {
        String postfix = new String();
        Stack<Character> operatorSt = new Stack<Character>();

        if (!infix.isEmpty())
        {
            for (int i = 0; i < infix.length(); i++)
            {
                if (priority(infix.charAt(i)) == 0)
                    postfix += infix.charAt(i);
                else
                {
                    postfix += "_";
                    if (priority(infix.charAt(i)) == 2)
                        operatorSt.push(infix.charAt(i));
                    else
                    {
                        if (operatorSt.empty() || priority(operatorSt.peek()) == 1)
                            operatorSt.push(infix.charAt(i));
                        else
                        {
                            postfix += operatorSt.pop();
                            operatorSt.push(infix.charAt(i));
                        }
                    }
                }
            }
            while (!operatorSt.empty())
            {
                postfix += operatorSt.pop();
            }
        } else
            postfix = "wrong";

        return postfix;
    }

    private double cal(char op, double n1, double n2)
    {
        switch (op)
        {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
            case '/':
                return n1 / n2;
            default:
                throw new ArithmeticException(op + " not defined");
        }
    }
    /*
    利用_將數字隔開以便讀取數字
    ex:1.5*2+3*4.2 -> 1.5_2_*3_4.2*+
    */
    public double getAnswer(String infix)
    {
        Stack<Double> numSt = new Stack<Double>();
        String temp = new String();
        String postfix = toPostfix(infix);

        if (!postfix.equals("wrong"))
        {
           for(int i = 0; i < postfix.length();i++)
           {
               if(postfix.charAt(i) != '+' && postfix.charAt(i) != '-'
                       && postfix.charAt(i) != '*' && postfix.charAt(i) != '/'
                       && postfix.charAt(i) != '_')
               {   temp += postfix.charAt(i); }
               else
               {
                   if(!temp.isEmpty())
                   {
                       numSt.push(Double.parseDouble(temp));
                       temp = "";
                   }
                   if(postfix.charAt(i) == '+' || postfix.charAt(i) == '-'
                           || postfix.charAt(i) == '*' || postfix.charAt(i) == '/')
                   {
                       double n2 = numSt.pop();
                       double n1 = numSt.pop();
                       numSt.push(cal(postfix.charAt(i), n1, n2));
                   }
               }
           }
           answer = numSt.pop();
        }
        return answer;
    }
}