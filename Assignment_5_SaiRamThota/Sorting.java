/*
 *Sorting and Merging 
 *
 * @CWID:11573236
 * Name: Sai Ram Thota
 * email-id: tsairam@okstate.edu
 * 
 */

import java.util.Random;

public class Sorting {

	public static void main(String[] args){

		long startTime,endTime,differenceTime;

		boolean sortFlag = true;

		Random r = new Random();

		double[] rand1;

		double[] rand2;

		

		int countMaximum = 10;

		while(sortFlag){
			

			System.out.println("There are " + countMaximum + " elements in the random elements");

			rand1 = new double[countMaximum];

			System.out.println("\nRandom generated numbers for Bubble sorting = \n");

			for(int i = 0; i < countMaximum; i++){

				rand1[i] = r.nextDouble();
				
			}

			startTime = System.currentTimeMillis();

			sortFlag = bubbleSort(rand1);

			endTime = System.currentTimeMillis();

			differenceTime = endTime - startTime;

			System.out.println("\nBubble Sorted data is:- \n");

			for(int i = 0; i < 10; i++){

				System.out.println(rand1[i]);

			}

			System.out.println("\nThe time gap between the start and end time for Bubble Sort is = " + differenceTime);

			System.out.println("\n");

			countMaximum = countMaximum * 10;

		}

		sortFlag = true;

		countMaximum = 10;

		while(sortFlag){
			

			System.out.println("There are " + countMaximum + " elements in the random elements");

			

			try{
				rand2  = new double[countMaximum];

				System.out.println("\nRandom generated numbers for Merge sorting = \n");

				for(int i = 0; i < countMaximum; i++){

					rand2[i] = r.nextDouble();
					

				}




			System.out.println("\n");

			startTime = System.currentTimeMillis();

			

			sortFlag = mergeSort(rand2);

			endTime = System.currentTimeMillis();
			
			differenceTime = endTime - startTime;

			System.out.println("\nMerge Sorted data is:- \n");

			for(int i = 0; i < 10; i++){

				System.out.println(rand2[i]);

			}

			System.out.println("\nThe time gap between the start and end time for Merge Sort is = " + differenceTime);
			
		}
			

			catch(OutOfMemoryError e){
				 
				System.out.println("Heap allocation error!!!!!!");
				System.exit(0);
				
			}

			countMaximum = countMaximum * 10;

		}			
		
	}
	
	public static boolean bubbleSort(double[] bs)

	{
		boolean swapped;

		long startTime,currentTime,differenceTime;


		startTime = System.currentTimeMillis();



		do { 

			swapped = false;


			currentTime = System.currentTimeMillis();

			differenceTime = currentTime - startTime;

			if(differenceTime > 20000){
				return false;
			}

			for(int i = 1;i < bs.length; i++){

				
				if(bs[i-1] > bs[i]){
					
					double temp;
					temp = bs[i-1];
					bs[i-1] = bs[i];
					bs[i] = temp;

					swapped = true;
				}

			}


		} while(swapped);

		return true;

	}

	public static boolean mergeSort(double[] ms)
	{
		double[] pout;                    
		double[] ptmp;                     
		int width, i;

		long startTime,currentTime,differenceTime;


		startTime = System.currentTimeMillis();


		pout = new double[ms.length]; 

		for(width = 1; width < ms.length; width *= 2){

			currentTime = System.currentTimeMillis();

			differenceTime = currentTime - startTime;

			if(differenceTime > 20000){
				return false;
			}

			for(i = 0; i < ms.length; i += 2 * width){ 
				mergePair(pout, ms, i, Math.min(i+width, ms.length), Math.min(i+2*width, ms.length));
			}
			ptmp = ms;           
			ms = pout;
			pout = ptmp;
		}

		return true;               
	}

	public static void mergePair(double[] pout, double[] pinp, int iLeft, int iRight, int iEnd)
	{
		int iL = iLeft;
		int iR = iRight;
		int iOut;
		
		for (iOut = iLeft; iOut < iEnd; iOut++){
			if (iL < iRight && (iR >= iEnd || pinp[iL] <= pinp[iR]))
				pout[iOut] = pinp[iL++];
			else
				pout[iOut] = pinp[iR++];
		}
	}	

	public static double[] mergeSortrecur(double[] ms)
	{
		double[] pleft, pright;

		if(1 >= ms.length)
			return ms;	

		pleft  = new double[ms.length / 2];
		pright = new double[ms.length - pleft.length];
		System.arraycopy(ms, 0, pleft, 0, pleft.length);
		System.arraycopy(ms, pleft.length, pright, 0, pright.length);

		pleft  = mergeSortrecur(pleft);
		pright = mergeSortrecur(pright);

		return mergePairrecur(pleft, pright);
	}

	public static double[] mergePairrecur(double[] pleft, double[] pright)
	{
		double[] plist;

		int iL = 0;
		int iR = 0;
		int iOut;
		plist = new double[pleft.length + pright.length];

		for (iOut = 0; iOut < plist.length; iOut++){

			if (iL < pleft.length && (iR >= pright.length || pleft[iL] <= pright[iR]))
				plist[iOut] = pleft[iL++];
			else
				plist[iOut] = pright[iR++];
		}
		return(plist);
	}
}	