public class Factorial
{
  public static int calculate(int n)
  {
    if(n<0 || n>12)
    {
      System.out.println("n should be between 0 and 12");
      System.exit(0);
    }    
    else
    {
      if(n==0)
      {
        return 1;
      }  
      else
      {
        return n * (calculate(n-1));
      }
    }
    return n ; 
  } 
 
  public static void main(String[] args)
  {
    if(calculate(0)==1)
    {
      System.out.println("Factorial.calculate(0) returned 1. Test passed!");
    }
    else
    {
     System.out.println("Test Failed!");
    }
    
    if(calculate(5)==120)
    {
      System.out.println("Factorial.calculate(5) returned 120. Test passed!");
    }
    else
    {
     System.out.println("Test Failed!");
    }
  }
}
