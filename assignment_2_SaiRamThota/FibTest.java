public class FibTest
{     
  
  public static int fibRecur(int n)
  {     
	if(n <= 2 )
	{
	 return 1; 
	}
	else 
	{		  
		int fib1 = 1;
		int fib2 = 1 ;
           
        return fibRecur(n-1)+fibRecur(n-2);        
	}       
  }

  public static int fibIter(int n)
  {     
  
   int fib1=1,fib2=1;
   int temp=fib2;
   for(int i=3;i<=n;i++)   
   {
    fib1=temp;
    temp=fib2;
    fib2=fib1+fib2;
   }  
   return fib2;
  }
 
  public static void main(String[] args)
  {
     double n1 = System.currentTimeMillis();
 
     System.out.println(fibRecur(40));   
  
     double n2 = System.currentTimeMillis();
 
     System.out.println("Time took for recursion is "+(n2-n1)+" millisecond(s).");   
    
     n1 = System.currentTimeMillis();
     
     System.out.println(fibIter(40));   
    
     n2 = System.currentTimeMillis();
 	
     System.out.println("Time took for itersion is "+(n2-n1)+" millisecond(s). ");  
  }
}