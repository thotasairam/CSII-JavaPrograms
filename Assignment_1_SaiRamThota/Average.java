import java.util.*;

public class Average
{

 /**
    This is recursive method named avg. This method caluculates the 
    average of the user input numbers.
	
    @parm int val is the user input values.
    @parm int sum is the sum of the values.
    @parm int count is the number of values.

*/

 public static void avg(int val,int sum,int count)
 {
  Scanner in = new Scanner(System.in);
  if(val<0)
  {
	System.out.println("You entered " + count +" numbers averaging "+ Math.abs((float)sum/(float)count)+" ." );
  }
  else
  {
   sum=sum+val;
   val=in.nextInt();
   count++;
   avg(val,sum,count);
  }
 }
 
 public static void main(String[] args)
 {
   Scanner in = new Scanner(System.in);
   
   System.out.println("Enter a series of numbers. Enter a negative number to quit.");   
   int val=in.nextInt();
   avg(val,0,0);
 
 }  
}