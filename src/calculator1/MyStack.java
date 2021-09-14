package calculator1;
import java.util.Stack;

public class MyStack extends Stack<String>
{
    void flush(StringBuilder curr) 
    {
        if (curr.length() == 0) return;
        this.push(curr.toString());
        curr.delete(0, curr.length());
    }
    
    int top()
    {
        String val = this.peek();
        return Integer.parseInt(val);
    }
    
    static boolean isop(char c)
    {
        if (c == '+' || c == '-')
            return true;
        else
            return false;
    }
           
}
