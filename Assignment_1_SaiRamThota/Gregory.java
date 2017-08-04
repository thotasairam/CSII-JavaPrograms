import java.util.*;

public class Gregory
{
 //Intoducing global variable
 public static double x;
 
 /**
     This is recursive method named cal. 
	 This method does all the caluculations required for 
	 the geogory series.
	 
	 @parm int k is the no. from the user
	 @parm int count is the track of the count
	 @parm double sum is the sum of the geogory series.  
 */
 
 public static void cal(int k, int count,double sum)
 {
  if(count == k)
  {
   x= sum;
  }
  else
  {
   if((count+1)%2==0)
   {
     sum = sum + 1/(double)((2*count)-1);
   }
   else
   {
	 sum = sum - (1)/(double)((2*count)-1);
   }	
   
   count++; 
   cal(k,count,sum);
  
  }
 }
 
 public static void main(String[] args)
 { 
  if(args.length==1)
   {
	double org = Math.PI;
	double calc;
	int k = 0;
    k = Integer.parseInt(args[0]);
	
    //Defensive programming.
	if(k==1)
	{
		calc=4;
	}
	else
	{
	 cal(k,1,0);
	 calc=x*4;
	}
	
	System.out.println("PI according to Gregory series: "+ calc);
    System.out.println("This differs Java's value by "+ Math.abs((org-calc)/((org+calc)/2))*100+" percent.");
   }
   else
   {
     System.out.println("Usage: java Gregory <argument>");
	 System.exit(0);
   } 
 }
}