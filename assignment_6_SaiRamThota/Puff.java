


/*
 * 
 * Program to implement decoding to decompress arbitrary files.
 * 
 *  Name: Ananta Soumya Melanathur
 * CWID: 11548615
 * email-id: soumya.melanathur@okstate.edu
 * 
 * 
 * 
 * Decoding a string of bits is simply reversing the process. Starting from the root of the tree and
taking each bit in turn from the bit string, follow the corresponding branch through the tree until
you end at a leaf. The byte represented by that leaf is the one you want. In our example, the first
bit of the code is 0. We take the left branch from the root and end up at the lowest leaf node. There's our
first byte. The next bit is a 1; we take the right branch at the root and we are not yet at a leaf.
The next bit is 1; we take the right branch again. Still no leaf. The next bit is 1 again, and now
we've arrived at our second byte. Starting from the root again, the next bit is 0, which
leads us to our third byte. And so on.

 */


import java.io.*;
import java.util.*;

public class Puff {

	
	public static void main(String[] args) {
		if(args.length!=1){
			System.out.println("Huffmann Decoding used for decompression");
		}
		
		byte[] byteArrValues;
		File f=new File(args[0]);//This is going to accept the file entered by/from the user through command line and store in file object if not there newly creates a file 
		try{
			//this is to read in an object and from file through file input stream
			FileInputStream fis = new FileInputStream(f); 
			ObjectInputStream ois =new ObjectInputStream(fis); //pass fis object to ois.
			HuffmannNode hufmantree = (HuffmannNode) ois.readObject();//read object
			byteArrValues = new byte[fis.available()];//creating a byte array
			fis.read(byteArrValues);
			
			
			/*
			 * Decompression works much the same, in reverse. Create an ObjectInputStream, read in the
Huffman tree and any additional information you saved, then the array of bytes. You can use the
available() method (after you've read in the objects) to see how large the array needs to be, and
the readFully() method to get a hold of the bytes. Turn the bytes into a bit stream, and then
use the Huffman tree, starting from the root, to figure out which byte is represented by the bit
sequence. Again, a recursive decoding method is recommended. Create a byte array to hold each
new byte as you decode it, until you have decoded the entire bit stream (and handled the padding
appropriately). Write this file to disk.
			 */
			
			
			
			//This is used to decompress the file contents
			LinkedList<Integer> linklistbits= new LinkedList<Integer>(Twiddle.bytesToBits(byteArrValues));
			shrinkEndOfLinkedList(linklistbits);
			LinkedList<Byte> linklistbytes =new LinkedList<Byte>();
			hufmantree.moveTree(linklistbits, linklistbytes);
			
			// This is going to output the contents of the file onto the stream and then onto the browser.
			byte[] outputArrOfBytes= converttobyteArray(linklistbytes);
			String outputFileName=args[0].substring(0, args[0].indexOf(".huff"));
			outputFileName = outputFileName + ".puff";//to create the decompressed file format for the huff file created.
			f=new File(outputFileName);
			FileOutputStream outputFile= new FileOutputStream(f);
			outputFile.write(outputArrOfBytes);
			outputFile.flush();//flush the contents of file onto the browser.
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	//This is used to convert the  linkedList into a byte array
	private static byte[] converttobyteArray(LinkedList<Byte> linklistbytes) {
		byte[] outputByteArray=new byte[linklistbytes.size()];
		for(int b = 0; b <outputByteArray.length; b++){
			outputByteArray[b] = (byte)linklistbytes.poll();			
		}
		return outputByteArray;
	}
	// This would erase the added bits by trying to get the last bit at least one of them being removed and removing the end bit until it reaches an element that is not the same
	
	public static void shrinkEndOfLinkedList(LinkedList<Integer> linklist){
		int lastbitasKey = linklist.pollLast();
		while(lastbitasKey == linklist.getLast()){
			linklist.pollLast();
		}
	}
}