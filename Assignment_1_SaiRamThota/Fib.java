import java.util.*;

public class Fib
{

 /**
     This method is a recursive method where the method calls the 
	 method itself. The method is named fib. 
	 
	 @parm int fib1 is the first fibonacci number.
	 @parm int fib2 is the second fibonnaci number.
	 @parm int n is the user input of number for the required
	 fibonacci number.
	 
 */
 
 public static void fib( int fib1 , int fib2,int n, int count)
 { 
	if(count==n)
	{
	 System.out.println(fib2);
	}
	else 
	{
	 count++;	
	 fib(fib2,fib1+fib2,n,count);
	}
 } 
  
 public static void main(String[] args)
 {
   if(args.length==1)
   {
    int n = Integer.parseInt(args[0]);
    fib(1,1,n,2);
   }
   else
   {
    System.out.println("Usage: java Fib <argument>");
    System.exit(0);
   }
  }
}