

/*

VigenereCipher.

@ CWID: 11573236
@Name: Sai Ram Thota
@email-id: tsairam@okstate.edu


*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class VignereCipher 
{
	final static boolean DEBUG = true;
	
	public static void main(String[] args) 
	{
		File inputFile, outputFile = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		String inCipher = null;
		int key = 0;
		int count = 0;
		int decode = 0;
		
		if (args.length < 3)
		{
			System.out.println("Usage: java VignereCipher flag key infile [outfile]");
			System.out.println("       flag: 0 = encode/1 = decode");
			System.out.println("[outfile] optional: output sent to screen if not given");
			System.exit(0);
		}
		
		decode = Integer.parseInt(args[0]); 
		inCipher = args[1];
		
		if (DEBUG) {
			System.out.println("inCipher = " + inCipher); 
		}
		
		inputFile = new File(args[2]); 
		if (DEBUG) {
			System.out.println("inFilename = " + args[2]);
		}
		
		if (args.length == 4) {  
			outputFile = new File(args[3]);
			if (DEBUG) {
				System.out.println("outputFilename = " + args[3]);
			}
			
	
                
			try {           
				fos = new FileOutputStream(outputFile);
			} catch (FileNotFoundException e1) {
				
				System.out.println(outputFile.getName() + " cannot be written to or does not exist.\nStackTrace:");
				e1.printStackTrace();
				System.exit(0);
			}
		}



    
                
		if (!inputFile.exists()) {
			System.out.println(inputFile.getName() + " does not exist.");
			return;
		}
		if (!(inputFile.isFile() && inputFile.canRead())) {
			System.out.println(inputFile.getName() + " cannot be read from.");
			return;
		}
		try {
			fis = new FileInputStream(inputFile);
			char cur;
			int length = inCipher.length();
			while (fis.available() > 0) 
			{
		
				cur = (char) fis.read();

				key = (int)inCipher.charAt(count % length);
				
				count++;
				
				if (fos != null) 
				{
					if (decode == 1) 
						fos.write(deCode((int)cur, key));
					else 
						fos.write(enCode((int)cur, key));
				} else { 
					if (decode == 1)
						System.out.print(deCode((int)cur, key));
					else
						System.out.print(enCode((int)cur, key));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static char enCode(int cur, int key) 
	{
		if (cur > 127) {
			System.out.println("Non-text character (>127 decimal) in file: EXITING! char value = " + (int)cur);
			System.exit(0);
		}
		
		if (cur < 32 || cur > 126) 
			return (char) cur;
		cur = cur + key;  

		while (cur > 126)
			cur = cur - 95;
		while (cur < 32)
			cur = cur + 95;
		return (char) cur;
	}
	
	public static char deCode(int cur, int key) 
	{
		if (cur > 127) {
			System.out.println("Non-text character (>127 decimal) in file: EXITING! char value = " + (int)cur);
			System.exit(0);
		}
		
		if (cur < 32 || cur > 126) 
			return (char) cur;
		cur = cur - key;

		while (cur > 126)
			cur = cur - 95;
		while (cur < 32)
			cur = cur + 95;
		return (char) cur;
	}
}

