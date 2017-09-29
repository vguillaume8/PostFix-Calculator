// Vinston Guillaume

import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
 
public class EvaluateString
{
   // This function evalutes the expression 
    public static int evaluate(String expression)
    {
         //creates an array
        char[] tokens = expression.toCharArray();
 
         // creates a stack for operands
        Stack<Integer> values = new Stack<Integer>();
 
        // creates a stack for operators
        Stack<Character> ops = new Stack<Character>();
 
        // This for loop reads each character of the expression one at a time
        for (int i = 0; i < tokens.length; i++)
        {
            // Ignores white spaces
            if (tokens[i] == ' ')
                continue;
 
            // pushes to stack if its a number
            if (tokens[i] >= '0' && tokens[i] <= '9')
            {
                StringBuffer sbuf = new StringBuffer();

                // For digits with multiple numbers
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Integer.parseInt(sbuf.toString()));
            }
 
            // Pushes opening parantheses to stack
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
 
            // solve everything before ending parantheses
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }
 
            // identifies whats an operator
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                     tokens[i] == '*' || tokens[i] == '/')
            {
                
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));
 
                // push operators to stack
                ops.push(tokens[i]);
            }
        }
 
        
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
 
        // Return value
        return values.pop();
    }
    //-------------------------------------------------------------------------------
 
    // True if op2 has higher or same precedence as op1
    // otherwise returns false.
    public static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
 
    //-----------------------------------------------------------------------------
    public static int applyOp(char op, int b, int a)
    {
        switch (op)
        {
        case '+':
            return a + b;
        case '-':
            return a - b;
        case '*':
            return a * b;
        case '/':
            if (b == 0)
                throw new
                UnsupportedOperationException("Cannot divide by zero");
            return a / b;
        }
        return 0;
    }
 //----------------------------------------------------------------------------------------------
    
    public static void main(String[] args) throws FileNotFoundException 
    {
        // imports file 
        System.out.println("Hello this is a postfix expression calculator");
        File file = new File("in.dat");

        String line;
     double result;
     
     
     
//-------------------------------------------------------------------------------  
      //Reads file
      Scanner scanner = new Scanner(file);
      
      while(scanner.hasNextLine()){

        line = scanner.nextLine();
        result = EvaluateString.evaluate(line);
        System.out.println("The Value of: " + line+ " is: " + result);
      }
      
      


        
    }
 
}