/*
 
@@CWID: 11573236
@@Name: Sai Ram Thota
@@Email-id: tsairam@okstate.edu

*/


import java.math.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Dimension;


public class Triangle extends JPanel implements Runnable
{
    private static int w=SierpinskiFrame.w;
    private static int h=SierpinskiFrame.h;

public Triangle()
{
    setBackground(Color.WHITE);

    Toolkit tk = Toolkit.getDefaultToolkit();  
    w = ((int) tk.getScreenSize().getWidth());  
    h = ((int) tk.getScreenSize().getHeight());  
   
    setVisible(true);
    setPreferredSize(new Dimension(w,h));



}

public void paintComponent( Graphics g )
{
    g.setColor(Color.BLACK);

    w=getWidth();
    h=getHeight();

    
    
    triangle(g, w/2, 0, w, h, 0, h, 0);
}

public void triangle(Graphics g, int a, int b, int c, int d, int e, int f, int r)
{


    
        if(Math.sqrt((double)(Math.pow(c-a, 2)) + (double)(Math.pow(d-b, 2))) > 2)
        {
            g.drawLine(a, b, c, d);     
            g.drawLine(c, d, e, f);
            g.drawLine(e, f, a, b);

	    int m1, m2, m3, m4, m5, m6;   
	    m1 = (a + c) / 2;             
	    m2 = (b + d) / 2;
	    m3 = (a + e) / 2;
	    m4 = (b + f) / 2;
	    m5 = (c + e) / 2;
	    m6 = (d + f) / 2;


	    triangle(g, a, b, m1, m2, m3, m4, r-1);   
	    triangle(g, m1, m2, c, d, m5, m6, r-1);
	    triangle(g, m3, m4, m5, m6, e, f, r-1);

        }
}

public void run()
{
    try{
        Thread.currentThread().sleep(3);    
    }
    catch(Exception e)
    {
    }
}
}
