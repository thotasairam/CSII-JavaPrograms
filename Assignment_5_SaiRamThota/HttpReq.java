/*
 * 
 * @CWID:11573236
 * Name: Sai Ram Thota
 * email-id: tsairam@okstate.edu
 */

import java.util.StringTokenizer;
import java.net.*;
import java.io.*;

final class HttpReq implements Runnable {

	 final static String CRLF = "\r\n";
	 Socket so;
	 
	 public HttpReq(Socket so) throws Exception
	 {
		 this.so = so;
	 }
	
	 public void run()
	 {
		 
		 try{
			 processRequest();
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 
	 }
	
	 private void processRequest() throws Exception
	 {
		 
		 DataOutputStream dos = new DataOutputStream(so.getOutputStream());
		 
		 BufferedReader b = new BufferedReader(new InputStreamReader(so.getInputStream()));
		 
		 String reqLine = b.readLine();
		 
		 System.out.println();
		 System.out.println(reqLine);
		 
		 String headerLine = null;
		 while((headerLine = b.readLine()).length() != 0) {
			 
			 System.out.println("Header Line " + headerLine);
		 }		 
			 StringTokenizer tokens = new StringTokenizer(reqLine);
			 tokens.nextToken();
			 String file = tokens.nextToken();
			 
			 if(file.equals("/")){
				 
				   file = "/index.html";
			 }
			 file = "." + file;
			 
			 FileInputStream fis = null;
			 boolean fileExists = true;
			 
			 try{
				 fis = new FileInputStream(file);
				 
		     }
			 
			 catch(FileNotFoundException e){
				 fileExists = false;
			 }
			 
			 String statusLine = null;
			 String contentTypeLine = null;
			 String entityBody = null;
			 
			 if(fileExists) {
				 statusLine = "HTTP/1.1 200 OK\r\n"; 
				 contentTypeLine = "Content-type: " + contentType(file) + "\r\n\r\n";
			 }
			 
			 else{
				 statusLine = "HTTP/1.1 404 Not Found\r\n\r\n";
				 contentTypeLine = "Content-type: text/html\r\n\r\n";
				 entityBody = "<HTML> <HEAD> <TITLE> 404 Error: Not Found </TITLE> </HEAD>" +
				              "<BODY> <b> 404 Error:  <i>" + file + "</i> Not Found. </b> </BODY> </HTML>";
			 }
			 
			 dos.writeBytes(statusLine);
			 dos.writeBytes(contentTypeLine);
			
			 if(fileExists) {
				 sendBytes(fis, dos);
				 fis.close();
				 
			 }
			 else {
				 dos.writeBytes(entityBody);
				 }
			 dos.close();
			 b.close();
			 so.close();
		
	 }
		 private static void sendBytes(FileInputStream fis, OutputStream os) throws Exception
		 {
			 
			
			 byte[] buffer = new byte[1024];
			 int bytes = 0;
			 
			
			 while((bytes = fis.read(buffer)) != -1) {
				 os.write(buffer, 0, bytes);
			 }
		 
		 }
		
		 private static String contentType(String fileName)
		 {
			 
			 if(fileName.endsWith(".htm") || fileName.endsWith(".html")){
				 return "text/html";
			 }
			 if(fileName.endsWith(".jpeg") || fileName.endsWith(".jpg")){
				 return "image/jpeg";
			 }
			 if(fileName.endsWith(".gif")){
				 return "image/gif";
			 }
			 if(fileName.endsWith(".png")){
				 return "image/png";
			 }
			 
			 return "application/octet-stream";
			 
		 }
}