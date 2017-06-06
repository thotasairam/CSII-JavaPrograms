
/*
  Name: Sai Ram Thota
  CWID: 11573236
  email-id: tsairam@okstate.edu
 */

import java.io.*;
import java.util.*;

public class Huff {

	
 	public static void main(String[] args) throws Exception{
	
 		
 	    if(args.length != 1){
 	    System.out.println("Huffmann Coding");
 	    System.exit(0);
 	    	
 	    }
 		 		
		byte[] byteArrValues;
		File f=new File(args[0]);
		
		try{
			
			FileInputStream fis = new FileInputStream(f); 
			int bytes = fis.available(); 
			byteArrValues = new byte[bytes];
		    fis.read(byteArrValues );
	    
			HashMap<Byte,HuffmannNode> hufmanHashMap = new HashMap<Byte,HuffmannNode>(256);
		
			for (byte i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++){
				
				HuffmannNode hufman = new HuffmannNode();
				hufman.setByteValue(i);
				hufmanHashMap.put(i,hufman);
				if(Byte.MAX_VALUE == i)
					break;
			}
			
			for(byte i: byteArrValues){
				hufmanHashMap.get(i).addToCounter();
			}
			PriorityQueue<HuffmannNode> hufmanPrioQue=new PriorityQueue<HuffmannNode>();
		
			
			for(HuffmannNode hufman:hufmanHashMap.values()){
				if(hufman.getCounter()!=0){
					hufmanPrioQue.add(hufman);
				}
			}
			
			HuffmannNode hufmanTree= createHufmanTree(hufmanPrioQue);
			
			
			LinkedList<Integer> linklistbits=new LinkedList<Integer>();
			for(byte i: byteArrValues){
				linklistbits.addAll(hufmanHashMap.get(i).treeMapVal());
			}
			
			endFixLastBits(linklistbits);
			byte[] compress;
			
			 ArrayList<Integer> bitsArr = new ArrayList<Integer>(linklistbits);
			compress=Twiddle.bitsToBytes(bitsArr);
				
			File outputFile = new File(args[0].toString()+".huff");
			FileOutputStream outputFileWriter = new FileOutputStream(outputFile);
			ObjectOutputStream writeontoFile= new ObjectOutputStream(outputFileWriter);
			writeontoFile.writeObject(hufmanTree);
			outputFileWriter.write(compress);
			
			writeontoFile.flush();
			writeontoFile.close();
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
 	
	public static HuffmannNode createHufmanTree(PriorityQueue<HuffmannNode> hufmanPrioQue){
		while(hufmanPrioQue.size()>1){
			HuffmannNode ltChild =hufmanPrioQue.poll();
			HuffmannNode rtChild =hufmanPrioQue.poll();
			HuffmannNode parentroot= new HuffmannNode();
			parentroot.setLeftChild(ltChild);
			parentroot.setRightChild(rtChild);
			parentroot.setCounter(ltChild.getCounter()+rtChild.getCounter());
			ltChild.setParentNode(parentroot);
			rtChild.setParentNode(parentroot);
			hufmanPrioQue.add(parentroot);
		}
		return hufmanPrioQue.poll();
	}
	
	
	public static LinkedList<Integer> endFixLastBits(LinkedList<Integer> linklistbits){
		int endlastbit = linklistbits.getLast();
		int newEndlastbit = 1;
		if (endlastbit == 1){
			newEndlastbit = 0;
		}
	
		linklistbits.add(newEndlastbit);
		while(linklistbits.size() %8 != 0){
			linklistbits.add(newEndlastbit);
		}
		return linklistbits;
	}
}