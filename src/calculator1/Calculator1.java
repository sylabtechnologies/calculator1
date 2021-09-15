// abstract frequent ops
// totally get the states
/// OK REWORK ALL W/ UNARY SIGNS ALL BE FINE


package calculator1;
import java.util.LinkedList;

class Solution 
{
    private MyStack stack = new MyStack();
    
    public int calculate(String s) 
    {
        char arr[] = s.trim().replaceAll("\\s+","").toCharArray();
        
        TokenType state = TokenType.LEFTBRACE;
        StringBuilder curr = new StringBuilder();

        for (char c : arr) 
        {
            /// each one = command
            TokenType newstate;
            if (c == '(') 
                newstate = TokenType.LEFTBRACE;
            else if (MyStack.isop(c))
                newstate = TokenType.OPER;
            else
                newstate = TokenType.NUMBER;
            
            if (c == ')')
            {
                stack.flush(curr);
                exec();
                continue;
            }
            
            switch(state)
            {
                case LEFTBRACE:
                    stack.flush(curr);
                    break;

                case OPER:
                    if (newstate == TokenType.OPER) 
                        throw new IllegalArgumentException("bad op");
                    stack.flush(curr);
                    break;
                    
                case NUMBER:
                    if (newstate == TokenType.OPER) 
                        stack.flush(curr);
                    break;
                    
                default: 
                        throw new IllegalStateException("");
            }

            curr.append(c);
            state = newstate;
            
        }
        
        stack.flush(curr);
        exec();
        return stack.top();
    }

    private void exec() 
    {
        LinkedList<Integer> formula = new LinkedList<>();
        while (!stack.isEmpty())
        {
            int rh = stack.top();
            stack.pop();
            if (stack.isEmpty())
            {
                formula.add(rh);
                break;
            }
            else if (stack.peek().equals("("))
            {
                formula.add(rh);
                stack.pop();
                break;
            }
            
            char op = stack.pop().charAt(0);
            if (op == '-') rh = - rh;
            formula.add(rh);

            if (!stack.isEmpty() && stack.peek().equals("("))
            {
                stack.pop();
                break;
            }

        }

        int res = 0;
        for (Integer x : formula)
            res += x;
        stack.push(Integer.toString(res));
    }
    
}

public class Calculator1
{
    public static void main(String[] args)
    {
        
        System.out.println(new Solution().calculate(" -2 + 1 "));
//        System.out.println(new Solution().calculate("(1+(4+5+2)-3)+(6+8)"));

    }
}
