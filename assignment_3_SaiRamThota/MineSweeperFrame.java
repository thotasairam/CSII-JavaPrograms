/*
 
@@CWID: 11573236
@@Name: Sai Ram Thota
@@Email-id: tsairam@okstate.edu

*/

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class MineSweeperFrame extends JFrame{
    
    GridLayout lay=new GridLayout(10,10);
    JLayeredPane pan2=new JLayeredPane();
    JLayeredPane pan=new JLayeredPane();
    //char val[10][10];
    JButton grid[][]=new JButton[10][10];
    int lab[][]=new int[12][12];
    boolean fill[][]=new boolean[12][12];
    
    public MineSweeperFrame(){
        this.setTitle("Mine Sweeper");
        pan.setLayout(lay);
       
        setMines();
        for(int i=1;i<11;i++){
            for(int j=1;j<11;j++){
                //val[i][j]='d';
                grid[i-1][j-1]=new JButton();
                grid[i-1][j-1].setText("?");
                grid[i-1][j-1].setName(i+"x"+j);
                grid[i-1][j-1].addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                            JButton x=(JButton)e.getSource();
                            if((e.getModifiers() & InputEvent.BUTTON3_MASK)== InputEvent.BUTTON3_MASK){
                                x.setText("F");
                            }
                            else{
                            String temp=x.getName();
                            int tmp1=Integer.parseInt(temp.substring(0,temp.indexOf("x")));
                            int tmp2=Integer.parseInt(temp.substring(temp.indexOf("x")+1));
                            if(fill[tmp1][tmp2]) {
                            x.setText("*");
                            JFrame z=new JFrame("Game Over");
                            z.setVisible(true);
                            z.setSize(100,100);
                            z.getContentPane().add(new JLabel("      GAME OVER"));
                           
                            }
                            else {
                            x.setText(""+lab[tmp1][tmp2]);
                            
                            }
                           }
                    }

                    
                });
                
                pan.add(grid[i-1][j-1]);
            }
        }
        
        this.setSize(500,500);
          this.getContentPane().add(pan2);
         this.getContentPane().add(pan);
      
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }

    private void setMines(){
      
        for(int i=1;i<11;i++){
            int val1=((int) Math.round(Math.random()*11));
            int val2=((int) Math.round(Math.random()*11));
            
            if(!fill[val1][val2]){
                lab[val1][val2]=-999;
                fill[val1][val2]=true;
              //  grid[val1-1][val2-1].setText("*");
                }
            else{
                i--;
            }
                
            
        }
        for(int i=1;i<11;i++){
            for(int j=1;j<11;j++) {
                if(!fill[i][j]) {
                    lab[i][j]=lab[i][j]+calc(i,j);
                }
            }
            
        }
        
        
        
        
    }
   
public int calc(int i,int j){
    int count=0;
    
    if(fill[i-1][j-1]){
        count++;
    }
    if(fill[i-1][j]){
        count++;
    }
    if(fill[i-1][j+1]){
        count++;
    }
    if(fill[i][j-1]){
        count++;
    }
    if(fill[i][j]){
        count++;
    }
    if(fill[i][j+1]){
        count++;
    }
    if(fill[i+1][j-1]){
        count++;
    }
    if(fill[i+1][j]){
        count++;
    }
    if(fill[i+1][j+1]){
        count++;
    }
    
    return count;
}
    
}
