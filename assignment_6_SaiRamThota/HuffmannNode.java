/*
  Name: Sai Ram Thota
  CWID: 11573236
  email-id: tsairam@okstate.edu
 */

import java.io.Serializable;
import java.util.*;


public class HuffmannNode implements Comparable<HuffmannNode>,Serializable
{
	private static final long serialVersionUID = 1L;

	public HuffmannNode(){
		
	}
		private HuffmannNode pNode=null;
		private HuffmannNode lChild=null;
		private  HuffmannNode rChild=null;
		private  int counter =0;
		private  byte byteVal;
		


		public HuffmannNode getParentNode() {
			return pNode;
		}

		public void setParentNode(HuffmannNode pNode) {
			this.pNode = pNode;
		}

		public HuffmannNode getLeftChild() {
			return lChild;
		}

		public void setLeftChild(HuffmannNode lChild) {
			this.lChild = lChild;
		}

		public HuffmannNode getRightChild() {
			return rChild;
		}

		public void setRightChild(HuffmannNode rChild) {
			this.rChild = rChild;
		}

		public int getCounter() {
			return counter;
		}

		public void addToCounter() {
			counter++;
		}

		public byte getByteValue() {
			return byteVal;
		}

		public void setByteValue(byte byteVal) {
			this.byteVal = byteVal;
		}

		public void setCounter(int counter){
			this.counter = counter;
		}

	    	
	    	public int compareTo(HuffmannNode arg) {
			
	    		if(this.counter>((HuffmannNode) arg).counter)
	    			return 1;
	    		if (this.counter < ((HuffmannNode) arg).counter)
	    			return -1;
	    		return 0;
	        }
	
	
		
		public int getSidePath(){
		if(pNode.getLeftChild().equals(this))
				return 0;
			else
				return 1;
		}
		
		
		public List<Integer> treeMapVal(){
			List<Integer> linkList= new LinkedList<Integer>(); 
			if(pNode==null)
				return linkList;
			else
				linkList.addAll(pNode.treeMapVal());
				linkList.add(getSidePath());
				return linkList;
		}
		
		public void moveTree(LinkedList<Integer> linkListBits, LinkedList<Byte> linkListBytes){
			while(!linkListBits.isEmpty()){
				linkListBytes.add(getValueOfBits(linkListBits));
			}
		}

		private Byte getValueOfBits(LinkedList<Integer> linkListBits) {
			
			if(!linkListBits.isEmpty()){
			int x = linkListBits.getFirst();
			if(x==0 && this.getLeftChild()!=null){
				linkListBits.poll();
				return this.getLeftChild().getValueOfBits(linkListBits);
			}
			if(x==1&&this.getRightChild()!=null){
				linkListBits.poll();
				return this.getRightChild().getValueOfBits(linkListBits);
			}
			else
				return this.byteVal;
			}
			return this.byteVal;
			
		}
		
	}