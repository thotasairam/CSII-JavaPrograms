


/*
 Operator Frame.
 
@ CWID: 11573236
@ Name: Sai Ram Thota
@ email-id: tsairam@okstate.edu

*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OperatorFrame extends JFrame {
   
	private static final long serialVersionUID = 4523984221960925641L;
	
	String host = " 139.78.141.250";
	
	Controls controls = new Controls(host);
	
	JButton   fButton = new JButton("Forward");
    JButton      bButton = new JButton("Back");
    JButton      lButton = new JButton("Left");
    JButton     rButton = new JButton("Right");
    JButton        uButton = new JButton("Up");
    JButton      dButton = new JButton("Down"); 
    static JButton goButton = new JButton("TakeOff");
    JButton rotateCCWButton = new JButton("CCW");
    JButton  rotateCWButton = new JButton("CW");
    JButton      hostButton = new JButton("Host");
    
    
    double iRate = 0.0;
    double jRate = 0.0;
    double kRate = 0.0;
    
    
    double iRotationRate = 0.0;
    double jRotationRate = 0.0;
    double kRotationRate = 0.0;
    
    JLabel addressLabel = new JLabel("address:"); 
    static JTextField addressTextField = new JTextField("139.78.141.250",15);
    
    
    OperatorPanel mainPanel = new OperatorPanel(); 
    OperatorPanel addressPanel = new OperatorPanel(); 
    OperatorPanel yawPanel = new OperatorPanel();
    
    public OperatorFrame()
    {

    	
        setTitle("ARDRONE Control Panel");
           
     
        
        mainPanel.setLayout(new BorderLayout());
        addressPanel.setLayout(new BorderLayout());
        yawPanel.setLayout(new FlowLayout());
        
        Container contentPane = getContentPane();
        
        addressPanel.add(addressLabel,BorderLayout.WEST);
        addressPanel.add(addressTextField,BorderLayout.CENTER);
        addressPanel.add(hostButton,BorderLayout.EAST);

        
        mainPanel.add(fButton, BorderLayout.NORTH);
        mainPanel.add(bButton, BorderLayout.SOUTH);
        
        mainPanel.add(lButton, BorderLayout.WEST);
        mainPanel.add(rButton, BorderLayout.EAST);
        
        mainPanel.add(goButton, BorderLayout.CENTER);

        yawPanel.add(rotateCCWButton);
        yawPanel.add(uButton);
        yawPanel.add(dButton);
        yawPanel.add(rotateCWButton);
        
        
        contentPane.setLayout(new BorderLayout());
        contentPane.add(addressPanel, BorderLayout.NORTH);
        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(yawPanel, BorderLayout.SOUTH);
      
       
        hostButton.addActionListener(new Host());
        goButton.addActionListener(new Go());
        
        fButton.addActionListener(new Forward());
        bButton.addActionListener(new Back());
        
        lButton.addActionListener(new Left());
        rButton.addActionListener(new Right());
        
        uButton.addActionListener(new Up());
        dButton.addActionListener(new Down());
        
        rotateCCWButton.addActionListener(new CCW());
        rotateCWButton.addActionListener(new CW());
        
    }

    public class Host implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
        	try
            {
             	
                String address = addressTextField.getText();
                

                
                System.out.println("address = " + address);

               
                controls.addressButton(address);    

                
            }
            catch(Exception error)
            {
            	error.printStackTrace();
                
            }
        }
    }







    public class Go implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           controls.goButton(host);
        } 
    }
    
    
    public class Forward implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           iRate += 0.05;
           System.out.println("iRate = " + iRate);
           String message = "{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":" + iRate + ",\"y\":" + jRate + ",\"z\":" + kRate + "},\"angular\":{\"x\":0,\"y\":0,\"z\":" + kRotationRate + "}},\"type\":\"geometry_msgs/Twist\"}";
          
           controls.sendMessage(message);
        } 
    }

    public class Back implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
        	iRate -= 0.05;
        	System.out.println("iRate = " + iRate);
        	String message = "{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":" + iRate + ",\"y\":" + jRate + ",\"z\":" + kRate + "},\"angular\":{\"x\":0,\"y\":0,\"z\":" + kRotationRate + "}},\"type\":\"geometry_msgs/Twist\"}";
          
            controls.sendMessage(message);
        } 
    }
    
  
    public class Left implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           jRate += 0.05;
           System.out.println("jRate = " + jRate);
           String message = "{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":" + iRate + ",\"y\":" + jRate + ",\"z\":" + kRate + "},\"angular\":{\"x\":0,\"y\":0,\"z\":" + kRotationRate + "}},\"type\":\"geometry_msgs/Twist\"}";
           
           controls.sendMessage(message);
        } 
    }

    public class Right implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
        	jRate -= 0.05;
        	System.out.println("jRate = " + jRate);
        	String message = "{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":" + iRate + ",\"y\":" + jRate + ",\"z\":" + kRate + "},\"angular\":{\"x\":0,\"y\":0,\"z\":" + kRotationRate + "}},\"type\":\"geometry_msgs/Twist\"}";
           
            controls.sendMessage(message);
        } 
    }
    
  
    public class Up implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           kRate += 0.05;
           System.out.println("kRate = " + kRate);
           String message = "{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":" + iRate + ",\"y\":" + jRate + ",\"z\":" + kRate + "},\"angular\":{\"x\":0,\"y\":0,\"z\":" + kRotationRate + "}},\"type\":\"geometry_msgs/Twist\"}";
           
           controls.sendMessage(message);
        } 
    }

    public class Down implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
        	kRate -= 0.05;
        	System.out.println("kRate = " + kRate);
        	String message = "{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":" + iRate + ",\"y\":" + jRate + ",\"z\":" + kRate + "},\"angular\":{\"x\":0,\"y\":0,\"z\":" + kRotationRate + "}},\"type\":\"geometry_msgs/Twist\"}";
           
            controls.sendMessage(message);
        } 
    }
    
    
    public class CCW implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
           kRotationRate += 0.05;
           System.out.println("kRotationRate (yaw) = " + kRotationRate);
           String message = "{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":" + iRate + ",\"y\":" + jRate + ",\"z\":" + kRate + "},\"angular\":{\"x\":0,\"y\":0,\"z\":" + kRotationRate + "}},\"type\":\"geometry_msgs/Twist\"}";
          
           controls.sendMessage(message);
        } 
    }

    public class CW implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
        	kRotationRate -= 0.05;
        	System.out.println("kRotationRate (yaw) = " + kRotationRate);
            String message = "{\"receiver\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":" + iRate + ",\"y\":" + jRate + ",\"z\":" + kRate + "},\"angular\":{\"x\":0,\"y\":0,\"z\":" + kRotationRate + "}},\"type\":\"geometry_msgs/Twist\"}";
           
            controls.sendMessage(message);
        } 
    }
    
    public class OperatorPanel extends JPanel
    {
        
		private static final long serialVersionUID = -8925955271136405044L;

		public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
        }         
    }
}
