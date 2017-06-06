public class Ramanujan 
{
 public static double cal(int k, int count, double sum) { 
  if(count>k)
  {
   return sum;
  }  
  else
  {
	double ramanujanCal= 4*count;
	ramanujanCal=(double)(Factorial.calculate((int)ramanujanCal));
	
    
	ramanujanCal=(double)ramanujanCal*(1103+(26390*count));
	
    
	double temp=(double) Factorial.calculate(count);
	
    
	temp=Math.pow(temp,4);
	
	temp=temp*(Math.pow(396,4*count));
  
  
    sum = sum +(ramanujanCal/temp);    
    count++;
  
  return cal(k, count, sum);
  }
}

 public static void main(String[] args)
 {
    double sum = cal(1,0,0);
    

	sum=sum*Math.sqrt(8);
	
	sum=sum/9801;

    sum = 1/sum;
	
    System.out.println(sum);
    
    double perDiff = Math.round((Math.PI-sum)/((Math.PI+sum)/2)*100);

   System.out.println(perDiff);
 }
}