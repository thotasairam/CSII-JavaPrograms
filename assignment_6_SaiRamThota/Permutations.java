/* 
  Name: Sai Ram Thota
  CWID NO.: 11573236
  Email-id: tsairam@okstate.edu
 */

import java.util.ArrayList;
import java.util.List;

public class Permutations<R> {

	private List<R> val;
	private R h;
	private Permutations<R> pIter;
	private boolean lPerm;
	private int i;
	
	public Permutations(List<R> l) { 
	
		this.val = new ArrayList<R>(l);
		
		i = 0;
		if(!val.isEmpty()) {
			this.h = this.val.remove(0);
			this.pIter = new Permutations<R>(this.val);
			this.lPerm = false;
			this.pIter.next(); 
		}
		else
		{
			this.h = null;
			this.pIter = null;
			this.lPerm = true; 

		}
	}

	public boolean hasNext() {
		return !lPerm;
	}

	public List<R> next() {
		List<R> r = new ArrayList<R>(val);
        r.add(i,this.h);
		if(!this.lPerm){
        	if(this.i < this.val.size())
        	{
        		i++;
        	}
        	else if(this.pIter.hasNext())
        	{
        		this.val = this.pIter.next();
        		i = 0;
        	}
        	else
        	{
        		this.lPerm = true;
        	}
        }
		return r;
	}	
	
	
	public static void main(String[] args) {
			ArrayList<Integer> arrTest=new ArrayList<Integer>();
			arrTest.add(0);
			arrTest.add(1);
			arrTest.add(2);
			
			long time=System.currentTimeMillis();
			Permutations<Integer> permTest=new Permutations<Integer>(arrTest);
			long time1=System.currentTimeMillis();
			
			ArrayList<Integer> arrTest1=new ArrayList<Integer>(arrTest);
			System.out.println("On an average it takes "+(time1-time)/2 + " milli seconds to do a Permutations sort with "+arrTest.size()+" terms.");
			System.out.println("Here are the Different permutations of "+arrTest.size()+" terms:");
			
			while(permTest.hasNext()){
				System.out.println(permTest.next());
			}
			
			arrTest1.add(3);
			arrTest1.add(4);
			arrTest1.add(5);
			
			
			long time3=System.currentTimeMillis();
			Permutations<Integer> permTest1=new Permutations<Integer>(arrTest1);
			long time4=System.currentTimeMillis();
			
			ArrayList<Integer> arrTest2=new ArrayList<Integer>(arrTest1);
			arrTest2.add(6);
			
			long time5=System.currentTimeMillis();
			Permutations<Integer> permTest2=new Permutations<Integer>(arrTest2);
			long time6=System.currentTimeMillis();
			System.out.println("On an average it would take "+(time4-time3)/2 + " milli seconds to do a Permutations sort with "+arrTest1.size()+" terms.");
			System.out.println("On an average it would take "+(time6-time5)/2 + " milli seconds to do a Permutations sort with "+arrTest2.size()+" terms.");
			
		}//End of main
}//End of PermutationIterator class
