// Christian Ortiz
import java.util.*;
public class Main {


    private static int getOp(String exp, int cur){
        Scanner s = new Scanner(exp.substring(cur));
        s.useDelimiter("[^0-9]");
        return s.nextInt();
    }

    /**
     * The main method handles the calculation logic
     */
    public static void main(String args[]) throws Exception{
        Scanner s = new Scanner(System.in);

        // These two stacks will be used to store previous results when parentheses appear
        Stack<Integer> stack = new Stack();
        Stack<Boolean> signStack = new Stack<Boolean>();
        while(true){
            System.out.println("Please enter a new expression: ");
            String exp = s.nextLine();
            if(exp.equals("DONE"))
                break;
            int result = 0;
            int storedResult; // this will hold the result stored in the stack
            boolean add = true;//if false, means subtract
            boolean storedAdd = true; // this will hold the addition or subtraction sign stored in the stack
            for(int cur = 0; cur < exp.length();cur++){
                if(exp.charAt(cur) == '+')
                    add = true;
                else if (exp.charAt(cur) == '-')
                    add = false;
                else if (exp.charAt(cur) == '(') // when a parentheses is seen
                {
                    stack.push(result); // we push the previous result to the stack
                    signStack.push(add); // we push the addition or subtraction sign to another stack
                    result = 0; // we reset the result to 0
                    add = true; // we reset the sign to positive

                }
                else if (exp.charAt(cur) == ')') // when a closed parentheses is found
                {

                   storedResult = stack.pop(); // we pop the previous result
                   storedAdd = signStack.pop(); // we pop the previous add/subtract sign
                   if(storedAdd) // if the sign is an addition sign
                       result = storedResult + result; // the result now equals the previous result + the current result
                   if(!storedAdd) // if the sign is a subtraction sign
                       result = storedResult + -1 * result; // the result now equals the previous result + the negation of the current result
                    // the stored negative must be distributed to the result

                }

                else if (Character.isDigit(exp.charAt(cur))){
                    int operand = getOp(exp, cur);
                    while(cur < exp.length() && Character.isDigit(exp.charAt(cur)))
                        cur++;
                    cur--;
                    result = add? result + operand: result - operand;
                }else
                    throw new Exception("Error: bad input");
            }
            System.out.println("The result is " + result);
        }
    }

}
