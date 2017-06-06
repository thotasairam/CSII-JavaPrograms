

/*

Controls for Robot.
 
@ CWID: 11573236
@ Name: Sai Ram Thota
@ email-id: tsairam@okstate.edu

*/





import java.io.PrintStream;
import java.net.Socket;

/* 
 * Controls: 
 * Takeoff (center button in ControlPanel toggle button) Initially takeoff flips to land
 * Land  (center button in ControlPanel toogle button) if takeoff already becomes land
 * Up/Down  U - up, D - down
 * Left/Right  <- left, -> right
 * Forward/Backward ^ forward, v backward
 * rotate left/rotate right (radians/sec)  (.  left rotate  .) rotate right
 */

public class Controls
{    
    private final int ARDRONE_JSON_SOCKET = 9090;
    private PrintStream out;
    private Socket socket;
    private String host;
    private String mes = "";
    private boolean takeOff = true;
    

    Controls(String host)
	{
  	    	
    	try 
        {            
        	
    		this.host = host.trim(); 
        	System.out.println("host:port = " + host + ":" + ARDRONE_JSON_SOCKET);
        	        	
            socket = new Socket(this.host, ARDRONE_JSON_SOCKET);
            out = new PrintStream(socket.getOutputStream());
            mes = "raw\r\n\r\n";
            sendMessage(mes);
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    	    	
    }

    
    public void addressButton(String host)
    {
    	out.close();
    	OperatorFrame.goButton.setText("TakeOff");
    	takeOff = true; 
    	    	
    	try 
        {
    		this.host = OperatorFrame.addressTextField.getText();
    		
    		this.host = host.trim(); 
        	System.out.println("host = " + host);
        	        	
            socket = new Socket(host, ARDRONE_JSON_SOCKET);
            out = new PrintStream(socket.getOutputStream());
            mes = "raw\r\n\r\n";
            sendMessage(mes);
            
        } 
		
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    
    public void goButton(String host)
    {
        String takeOffMsg = "{\"receiver\":\"/ardrone/takeoff\",\"msg\":{},\"type\":\"std_msgs/Empty\"}";
        String    landMsg = "{\"receiver\":\"/ardrone/land\",\"msg\":{},\"type\":\"std_msgs/Empty\"}";
        
        
        if (takeOff) 
		{
        	mes = takeOffMsg;
        	OperatorFrame.goButton.setText("Land");
        	takeOff = false;
        } 
		
		else 
		{
        	mes = landMsg;
        	OperatorFrame.goButton.setText("Takeoff");
        	takeOff = true;
        }
                
        sendMessage(mes);
        
    }
    
    
    public void sendMessage(String mes)
    {
    	try 
        {            
       
            out.write(0);
            out.print(mes);
            out.write(-1);
            out.flush();
            
            System.out.println("SENT: mes = " + mes);
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    	
    }
}
