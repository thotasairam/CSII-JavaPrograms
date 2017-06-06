/*
 
@@CWID: 11573236
@@Name: Sai Ram Thota
@@Email-id: tsairam@okstate.edu

*/
import java.awt.event.*;
import javax.swing.JFrame;
import java.awt.*;




public class SierpinskiFrame extends JFrame
{
    public static int w;//width
    public static int h;//height

public SierpinskiFrame()
{
    super("Sierpinski");
    Toolkit tk = Toolkit.getDefaultToolkit();

    w = ((int) tk.getScreenSize().getWidth());
    h = ((int) tk.getScreenSize().getHeight());

    setSize(w,h);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.getContentPane().add(new Triangle());

    setVisible(true);
}

}
