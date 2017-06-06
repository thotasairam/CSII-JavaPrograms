

/*
CaesarCipher.

@ CWID: 11573236
@ Name: Sai Ram Thota
@ email-id: tsairam@okstate.edu


*/


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CaesarCipher 
{
	final static boolean DEBUG = true;
	
        public static void main(String[] args) 
	
        {
	
        	File inputFile, outputFile = null;
		    FileInputStream fis = null;
	    	FileOutputStream fos = null; 

		if (args.length < 2)
		{
			System.out.println("Usage: java CaesarCipher key infile [outfile]");
			System.out.println("[outfile] optional: output sent to screen if not given");
			System.exit(0);
		}

		int key = Integer.parseInt(args[0].trim()); 
		
		if (DEBUG) 
		{
			System.out.println("key = " + key); 
		}
		
		inputFile = new File(args[1]); 
		
		
		if (DEBUG) 
		{
			System.out.println("inFileName = " + args[1]);
		}
		
		if (args.length == 3) 
		{ 
			
			outputFile = new File(args[2]);
			
			if (DEBUG) 
			{
				System.out.println("outputFileName = " + outputFile.getName() );
			}
			
			try 
			{
				fos = new FileOutputStream(outputFile);
			}
			catch (FileNotFoundException e1) 
			{	
			    System.out.println(outputFile.getName() + " cannot be written to or does not exist.\nStackTrace:");
				e1.printStackTrace();
				System.exit(0);
			} 
		}        

		if (!inputFile.exists()) 
		{
			System.out.println(args[1] + " does not exist.");
			return;
		}

		if (!(inputFile.isFile() && inputFile.canRead()))
		{
			System.out.println(inputFile.getName() + " cannot be read from.");
			return;
		}
		
		try 
		{
			fis = new FileInputStream(inputFile);
			
			char cur;
			
			while(fis.available() > 0) 
			{
				cur = (char) fis.read();
				
				if (fos != null) 
				{
					fos.write(enCode((int)cur, key));
				} 
				
				else 
				{ 
					System.out.print(enCode((int)cur, key));
				}
			}
		} 
		catch (IOException e)
		{
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
        {		
			return (char) cur;
        }
		
		cur = cur + key; 
		
		while (cur > 126)
		{
			cur = cur - 95;
		}
		while (cur < 32)
		{
			cur = cur + 95;
		}
		return (char) cur;
	}
}

