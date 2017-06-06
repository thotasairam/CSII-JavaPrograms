
/*

Web Browser.

@ CWID: 11573236
@ Name: Sai Ram Thota
@ email-id: tsairam@okstate.edu

*/

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class Browser extends JFrame 
{

    JTextField webTextField;  

    JLabel webBrowserLabel;

    JTextArea webBodyText = new JTextArea(25,80);

    String webPageText = "";


    public Browser(){   
      
        webBrowserLabel = new JLabel("North", JLabel.CENTER);

        webTextField = new JTextField("http://www.google.com", 50);
        
        webTextField.addActionListener(new Run());
       
        this.getContentPane().add(webTextField, BorderLayout.NORTH);
 
        JScrollPane webBrowserScrollPane = new JScrollPane(webBodyText);
	
        webBodyText.setLineWrap(true);

        this.getContentPane().add(webBrowserScrollPane, BorderLayout.SOUTH);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setVisible(true);

    }
   
   public class Run implements ActionListener 
   {
    public void actionPerformed(ActionEvent e)
    {
        String webURL = webTextField.getText(); 
		 
		System.out.println("webURL = " + webURL);
        
		webBrowserLabel.setText(webURL);      

        webLoadURL(webURL);
    }  
   }

    public void webLoadURL(String urlPage)
    {
          
          try 
		  {

  			System.out.println("urlPage = " + urlPage);
  
            URL userURL = new URL(urlPage);
			
			System.out.println("userURL = " + userURL.toString());

            
            String webHostName = userURL.getHost(); 


			String webPathtName = userURL.getPath();


			System.out.println("Name of the Host is : " + userURL.getHost());


			System.out.println("Name of the Path is : " + userURL.getPath());



			if(webPathtName == "")
			{

				webPathtName = "/";


			}
       
            System.out.println("webPathtName " + webPathtName + "\n");

            
            System.out.println("webHostName " + webHostName + "\n");

			System.out.println("userURL " + userURL);

			Socket webSocket = new Socket(webHostName, 80);

			PrintWriter webOut = new PrintWriter(webSocket.getOutputStream(), true);   
			

			BufferedReader webIn = new BufferedReader(new InputStreamReader(webSocket.getInputStream()));
			
            String webPermission = "GET " + webPathtName + " HTTP/1.1\r\n";

			webPermission += "Host: " + webHostName + "\r\n";

			webPermission += "Connection: Close \n\n";

			webOut.print(webPermission);

			webOut.flush();

            String res;

            while((res = webIn.readLine()) != null)
			{

				webPageText += res;
			       
				System.out.println("|" + res + "|");
 
            }

		
			System.out.println("webPageText = " + webPageText);

			String webTitle = getWebTitle(); 

			setTitle("Web Browser: " + webTitle);
	
			webBodyText.setText(getWebBody());


          }

          catch (IOException e)
		  {
          
              e.printStackTrace();
            
          }
      
     }



  public String getWebTitle()
  { 
  
     String webTitleSearch = "<title";

     String webTitleSearch1 = "<TITLE";

     
     int webTitleLength = webTitleSearch.length();

     int webTitleLength1 = webTitleSearch1.length();

     
     String endTitleSearch = "</title";

     String endTitleSearch1 = "</TITLE";

    
     int bSub = -1;

     int eSub = -1;

     
     boolean titleFound = false;

     bSub = webPageText.indexOf(webTitleSearch);

     if (bSub < 0)

     {

           bSub = webPageText.indexOf(webTitleSearch1);

           if (bSub < 0)

           {

                //Does not do anything when not not found.

           }


           else {

                 titleFound = true;

                 bSub = bSub + webTitleLength1;

                 bSub = webPageText.indexOf(">", bSub);

                 bSub++;


                 eSub = webPageText.indexOf(endTitleSearch, bSub);

                 if (eSub < 0)

                 {

                         eSub = webPageText.indexOf(endTitleSearch, bSub);

                         if (eSub < 0)

                         {

                                eSub = webPageText.length()-bSub;

                         }

                 }

                }      

     
     }


     else {

          titleFound = true;

          bSub = bSub + webTitleLength;

          bSub = webPageText.indexOf(">", bSub);

          bSub++;

        

          eSub = webPageText.indexOf(endTitleSearch, bSub);

          if (eSub < 0)

            {

                eSub = webPageText.indexOf(endTitleSearch1, bSub);

                if (eSub < 0);
   
                {

                         eSub = webPageText.length()-bSub;

                }

            }

         }

 
        if (titleFound)

        {

              return (webPageText.substring(bSub, eSub));

        }

        else {

              return ("No <title> tag found");

        }

   }



   public String getWebBody() 
   {
 
       
        String webBodySearch = "<body";

        String webBodySearch1 = "<BODY";

       
        int webBodyLength = webBodySearch.length();

        int webBodyLength1 = webBodySearch1.length();


        String endBodySearch = "</body";

        String endBodySearch1 = "</BODY";


        int bSub = -1;

        int eSub = -1;

        boolean titleFound = false;

       
        bSub = webPageText.indexOf(webBodySearch);

        if (bSub < 0)

        {

                bSub = webPageText.indexOf(webBodySearch1);

                if (bSub < 0)

                {

                     //Does not do anything when not found
                     
                }

                else {

                     titleFound = true;

                     bSub = bSub + webBodyLength1;

                     bSub = webPageText.indexOf(">", bSub);

                     bSub++;

 
                     eSub = webPageText.indexOf(endBodySearch, bSub);

                     if (eSub < 0)

                     {

                             eSub = webPageText.indexOf(endBodySearch, bSub);

                     }

                }

        }


        else
		{

             titleFound = true;

             bSub = bSub + webBodyLength;

             bSub = webPageText.indexOf(">", bSub);

             bSub++; 


             eSub = webPageText.indexOf(endBodySearch, bSub);

             if (eSub < 0)

             {

                eSub = webPageText.indexOf(endBodySearch1, bSub);

             }

        }

        if (titleFound)
        {

            System.out.println("\n Original Tagged webPageText:");

            System.out.println("webPageText.length() = " + webPageText.length());

            System.out.println("webPageText bSub = " + bSub + " webPageText eSub = " + eSub);

            System.out.println("--------------------------------------------------------------------------------------------------");

            System.out.println(webPageText);                  

            webPageText = hideWebTags(webPageText.substring(bSub, eSub));

            System.out.println("\nhideTags() processed webPageText:");

            System.out.println("webPageText.length() = " + webPageText.length());

            System.out.println("webPageText bSub = " + bSub + " webPageText eSub = " + eSub);

            System.out.println("---------------------------------------------------------------------------------------------------");

            System.out.println(webPageText);

             return (webPageText);

        }

        else

        {

             System.out.println("No <body> tag found");

             return ("No <body> tag found");

        }


    }

 

  public String hideWebTags(String w)
  {

          String webTag = ""; 

          for (int j = 0; j < w.length(); j++)
          {
              
                  if (w.charAt(j) == '<')
                  {
                    webTag = w.substring(0,j);
                  }

                

                 else if (w.charAt(j) == '>')
                 {
                  w = webTag + w.substring(j+1);
                  j = 0;
                 }

          }

          return (w);

  }




  public static void main(String[] args)
  {

    Browser webBrowser = new Browser();

	webBrowser.setTitle("Web Browser");
  }


}
