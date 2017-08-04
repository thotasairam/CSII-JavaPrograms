/*
 *Minesweeper Game!
 *
 * @CWID:11573236
 * Name: Sai Ram Thota
 * email-id: tsairam@okstate.edu
 * 
 * 
*/

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.*;

import javax.swing.*;

public class Minesweeper implements ActionListener
{
   public static JFrame mineFrame;
   public static JPanel myPanel;
   public static JMenu mineFieldMenu; 
   public static JLabel topStatusBar; 
   public static JLabel bottomStatusBar;
   public static String fileName;
 
   public static void main(String[] args) 
   {
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               Minesweeper ms = new Minesweeper();
               ms.createAndShowGUI();
            }
        });
   } 

   public void actionPerformed(ActionEvent ae)
   {

       String c = ae.getActionCommand();

       System.out.println(c);

   }

   public void createAndShowGUI() 
   {
      mineFrame = new JFrame("Minesweeper");
      
      mineFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      mineFrame.setLayout(new BorderLayout());
  
      MyPanel myPanel = new MyPanel();
      
      mineFrame.add(myPanel, BorderLayout.CENTER);
 
      mineFrame.pack();
       
      mineFrame.setResizable( true );
      mineFrame.setVisible( true ); 

      JMenuBar mb = new JMenuBar();
      mineFrame.setJMenuBar(mb);
      JMenu fileMenu = new JMenu("File");
      fileMenu.setMnemonic(KeyEvent.VK_F);
      mb.add(fileMenu);

      ImageIcon saveIcon = new ImageIcon(getClass().getResource("Save.png"));
      ImageIcon loadIcon = new ImageIcon(getClass().getResource("Load.png"));
      ImageIcon quitIcon = new ImageIcon(getClass().getResource("Quit.png"));
      
      JMenu newMenu = new JMenu("New");
     
      JMenuItem nov = new JMenuItem("Novice");
      nov.setMnemonic(KeyEvent.VK_N);
      JMenuItem inter = new JMenuItem("Intermediate");
      inter.setMnemonic(KeyEvent.VK_I);
      JMenuItem exp = new JMenuItem("Expert");
      exp.setMnemonic(KeyEvent.VK_E);
      
      newMenu.add(nov);
      newMenu.add(inter);
      newMenu.add(exp);
      
      nov.addActionListener(new ActionListener() {
    	 public void actionPerformed(ActionEvent e){ 
    	  MyPanel.initializeGame(0);
    	  mineFrame.pack();
    	 }
      });
      
      inter.addActionListener(new ActionListener() {
     	 public void actionPerformed(ActionEvent e){ 
     	  MyPanel.initializeGame(1);
     	  mineFrame.pack();
     	 }
       });
      
      exp.addActionListener(new ActionListener() {
     	 public void actionPerformed(ActionEvent e){ 
     	  MyPanel.initializeGame(2);
     	  mineFrame.pack();
     	 }
       });
      
      JMenuItem saveMenu = new JMenuItem("Save",saveIcon);
      saveMenu.setMnemonic(KeyEvent.VK_S);
     
      saveMenu.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  saveFile();
    	  }
      });

      JMenuItem loadMenu = new JMenuItem("Load",loadIcon);
      loadMenu.setMnemonic(KeyEvent.VK_L);
    
      loadMenu.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  
    		  if ((fileName = loadFile()) != null)
              {
                  try
                  {
                      FileInputStream fileIn =
                              new FileInputStream(fileName);
                      ObjectInputStream in =
                              new ObjectInputStream(fileIn);

                      int[][] myField = (int[][]) in.readObject();
                      if (myField instanceof int[][]) {
                    
                          MyPanel.mineField = (int[][]) myField;


                          int minesCount = 0;
                          int minesMarked = 0;
                         
                       
                          for (int i = 0; i < MyPanel.mineField.length; i++)
                          {
                              for (int j = 0; j < MyPanel.mineField[0].length; j++)
                              {
                                  if (MyPanel.mineField[i][j] % 10 == 9)
                                      minesCount++;
                                  if (MyPanel.mineField[i][j] >=20)
                                      minesMarked++;
                              }

                          }

                          MyPanel.minesLeft = minesCount - minesMarked;
                          MyPanel.numberOfMines = minesCount;
                          topStatusBar.setText(String.
                                  format("  %2d mines left.",
                                          MyPanel.minesLeft));

                         
                          MyPanel.rows = myField.length;
                          MyPanel.cols = myField[0].length;

                        
                          switch(MyPanel.numberOfMines)
                          {
                          case 10: 
                              MyPanel.level = 0;
                              break;
                          case 50: 
                              MyPanel.level = 1;
                              break;
                          case 100: 
                              MyPanel.level = 2;
                              break;

                          }                        
                          loadMineField();
                          
                      } else { 
                          System.out.println("Improper data format! Try another file.");
                          bottomStatusBar.setText(String.format("  Try another file!"));
                      }
                      in.close();
                      fileIn.close();
                      bottomStatusBar.setText(String.format(" Loaded: %s", fileName));
                  }
                  catch (FileNotFoundException f)
                  {
                     
                      bottomStatusBar.setText(String.format("  Try another file!"));
                  }
                  catch(StreamCorruptedException sc)
                  {
                      
                      bottomStatusBar.setText(String.format("  Try another file!"));
                  }
                  catch (IOException i)
                  {
                     
                          i.printStackTrace();
                      
                  }
                  catch (ClassNotFoundException c)
                  {
                      
                          c.printStackTrace();
                    
                  }
              } else {
                  bottomStatusBar.setText(String.format(" Load cancelled."));
              }
          }
      });
     
      JMenuItem quitMenu = new JMenuItem("Quit",quitIcon);
      quitMenu.setMnemonic(KeyEvent.VK_Q);
    
      quitMenu.addActionListener(new ActionListener(){
    	  public void actionPerformed(ActionEvent e){
    		  System.exit(0);
    	  }
      });      
      fileMenu.add(newMenu);
      fileMenu.addSeparator();
      fileMenu.add(saveMenu);
      fileMenu.add(loadMenu);
      fileMenu.addSeparator();
      fileMenu.add(quitMenu);
           
   } 
  
   
   private static void saveFile() {
 
	   File file = null;
	   JFileChooser fileChooser = new JFileChooser(".");
	   fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	   int result = fileChooser.showSaveDialog(myPanel);
	   if(result == JFileChooser.APPROVE_OPTION) {
		   file = fileChooser.getSelectedFile();
		   
		   String path = file.getPath();
		   
		   
		   try {
			   FileOutputStream fo = new FileOutputStream(path);
			   ObjectOutputStream oos = new ObjectOutputStream(fo);
			   oos.writeObject((Object)MyPanel.mineField);
			   oos.close();
			   fo.close();
		   }
		   
		   catch(FileNotFoundException f) {
			   f.printStackTrace();
		   }
		   
		   catch(IOException i) {
			   i.printStackTrace();
		   }				   
			  
	   } 		   
	   else {
		   		 
		   bottomStatusBar.setText(String.format("Save Cancelled"));
	   }	   
   }
    
   private static String loadFile() {
	   
	   File file = null;
	   String path = null;
	    
	   JFileChooser fileChooser = new JFileChooser(".");
	   fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	   int result = fileChooser.showOpenDialog(myPanel);
	   if(result == JFileChooser.APPROVE_OPTION) {
		   file = fileChooser.getSelectedFile();
		   path = file.getPath();
	   }
	   
		   return path;
   }
   
   public static void loadMineField() {   
    
	    MyPanel.width = MyPanel.cols * MyPanel.CELL_SIZE;
	    MyPanel.height = MyPanel.rows * MyPanel.CELL_SIZE;
	    MyPanel.widthOffset = MyPanel.leftOffset + MyPanel.rightOffset;
	    MyPanel.heightOffset = MyPanel.topOffset + MyPanel.bottomOffset;
	    MyPanel.mineFieldSize = new Dimension( MyPanel.width + MyPanel.widthOffset ,  
	        MyPanel.height +  MyPanel.heightOffset );    
	    myPanel.setPreferredSize(MyPanel.mineFieldSize);
	    mineFrame.setMinimumSize(MyPanel.mineFieldSize);
	    mineFrame.setMaximumSize(MyPanel.mineFieldSize);
	    mineFrame.pack();
	    
   }	   
} 