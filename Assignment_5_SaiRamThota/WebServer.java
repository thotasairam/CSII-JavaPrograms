/*
 *WebServer!
 * 
 * @CWID:11573236
 * Name: Sai Ram Thota
 * email-id: tsairam@okstate.edu
 */
import java.net.*;

public class WebServer {
	
	public static void main(String[] args) throws Exception
	{
		int port = 1025; 
		ServerSocket sr = new ServerSocket(port);
		
		while(true){		
		Socket sk = sr.accept();
		HttpReq x = new HttpReq(sk);	
		Thread y = new Thread(x);
		y.start();
		}
	}
}
