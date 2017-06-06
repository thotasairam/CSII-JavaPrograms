/*
 
@@CWID: 11573236
@@Name: Sai Ram Thota
@@Email-id: tsairam@okstate.edu

*/


import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MessagePanel extends JPanel {

        
        public void paint(Graphics g){
            String x="I am Trapped in CS";
            char[] m=x.toCharArray();
            int[] xPoints={230,230,200,100,100,400,400,300,265,265};
            int[] yPoints={30,60,100,100,400,400,100,100,60,30};
            g.drawPolygon(xPoints, yPoints,10);
            g.drawOval(230, 30, 35, 10);
            g.drawChars(m, 0, m.length, 200, 248);
            
        }
    
       
}
