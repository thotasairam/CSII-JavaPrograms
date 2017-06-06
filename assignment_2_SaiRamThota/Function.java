public abstract class Function {

	public abstract double evaluate(double x);


	public static void main(String args[]){
	
		Function x2=new SinFunc();
		
		System.out.println("SIN \t" + x2.findRoot(3,4,0.00000001));
	
		x2=new CosFunc();
		
		System.out.println("COS \t"+x2.findRoot(1,3,0.00000001));
		
		int val[]={1,0,-3};
		x2=new PolyFunc(val);
		System.out.println("x^2 - 3 where x=3\t"+x2.evaluate(3.0));
		
		int[] val2={1,-1,-2};
		x2=new PolyFunc(val2);
		System.out.println("x^2-x-2 where x=3\t"+x2.evaluate(3.0));
	
	
	}

	public double findRoot(double a, double b, double epsilon)
  {
    double x = (a+b)/2;
    
    if(Math.abs(a-x) < epsilon)
    {
      return x;
    }
    else
    {
      if((evaluate(x)>0 && evaluate(a)>0) || (evaluate(x)<0 && evaluate(a)<0 ))
      {
       return findRoot(x,b,epsilon);    
      }
      else
      {
       return findRoot(a,x,epsilon);
      }
    }
	}
	}
 
