import java.util.*;

public class Poly{

    private int[] cof;
	
	public Poly(int[] coeff){
	
		cof=new int[coeff.length];
		cof=coeff;

	}
	
	public int degree(){
		int temp=0;
		while(true){
			if(cof[temp]!=0){
				return (cof.length-1)-temp; 
			}
			else{
				temp=temp+1;
			}
		}
			
	}
	
	public String toString2(){
		int degree=cof.length-1;
		String res="";
		int j=0;
		for(int i=0;i<cof.length;i++){		
			if(cof[i]==0){
				degree--;
			}			
			else{
			
				if(degree==1){
					if(cof[i]<0)
						res=res+""+cof[i]+"x";
					else
						res=res+"+"+cof[i]+"x";
				}
				else if (degree==0){
					if(cof[i]<0)
							res=res+""+cof[i];
					else
						res=res+"+"+cof[i];
										
				}
				else{
					if(j==0)
						res=res+""+cof[i]+"x^"+degree;
					else{
						if(cof[i]<0)
							res=res+""+cof[i]+"x^"+degree;
						else
							res=res+"+"+cof[i]+"x^"+degree;
					}
				}
			degree--;
			j++;
			}		
		}
		return res;
		

	}	
	
		
	public Poly add(Poly a){

		int[] aCoeff=a.cof;
		int res[];
		int minlen,maxlen;
		int tmp[];
		boolean gr;
		if(aCoeff.length > cof.length){
			tmp=new int[aCoeff.length];
			res=new int[aCoeff.length];
			minlen=cof.length-1;
			maxlen=aCoeff.length-1;
			gr=true;
		}
		else{
			tmp=new int[cof.length];
			res=new int[cof.length];
			minlen=aCoeff.length-1;
			maxlen=cof.length-1;
			gr=false;
		}
		
		for(int i=maxlen;i>=0;i--){
		
			if((minlen)>=((maxlen)-i)){
				if(gr)
					tmp[i]=cof[i-(maxlen-minlen)];
				else
					tmp[i]=aCoeff[i];
			}		
			else{
				tmp[i]=0;
			}
			
		}
		
		for(int i=0;i<=maxlen;i++){
			if(aCoeff.length > cof.length)
				res[i]=tmp[i]+aCoeff[i];
			else
				res[i]=tmp[i]+cof[i];
		}
		
		
		
		return new Poly(res);
		
	}

	public double evaluate(double x){

		double sum=0;
		for(int i=0;i<cof.length;i++){

			sum=sum+(Math.pow(x,((cof.length-1)-i))*cof[i]);
		}

		return sum;
	}




	public static void main(String[] args) {


		int[] arr={4,0,2,3,1};
		int[] abb={8,8,1,7,6,5,-6,2,1};
		Poly k=new Poly(arr);
		System.out.println("f(x) = "+k.toString2());
		System.out.println("Highest degree of f(x) is "+k.degree()+".");
		System.out.println("f(3) = "+k.evaluate(3));
		Poly l=new Poly(abb);
		System.out.println("g(x) = "+l.toString2());
		System.out.println("Highest degree of g(x) is "+l.degree()+".");				
		System.out.println("g(2) = "+l.evaluate(2));		
		Poly x = k.add(l);		
		System.out.println(k.toString2()+" plus(+) "+l.toString2()+" = "+ x.toString2());
		
	}

}