/*
 * MyPanel reperesent Panel for Minesweeper game.
 *
 *
 * @CWID:11573236
 * Name: Sai Ram Thota
 * email-id: tsairam@okstate.edu
 *
*/
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.util.Random;

public class MyPanel extends JPanel
{
	private static final long serialVersionUID = 7773229614653317943L;
	private static final int NUM_IMAGES = 13; 
	public static final int CELL_SIZE = 15;
	private static final int COVERED_CELL = 10; 
	private static final int MARKED_CELL = 20;
	private static final int EMPTY_CELL = 0; 
	private static final int MINED_CELL = 9; 
	private static final int COVERED_MINED_CELL = MINED_CELL + COVERED_CELL;
	private static final int MARKED_MINED_CELL = COVERED_MINED_CELL + MARKED_CELL;
	private static final int DRAW_MINE = 9; 
	private static final int DRAW_COVER = 10; 
	private static final int DRAW_MARK = 11;
	private static final int DRAW_WRONG_MARK = 12; 
	public static int level;
	private final static int[] NUMBER_OF_MINES = { 10, 50, 100 };
	private final static int[] ROWS = { 10, 25, 25 };
	private final static int[] COLUMNS = { 10, 25, 40 };
	public static boolean inGame;
	private static boolean firstMove;
	public static int numberOfMines;
	public static int minesLeft;	
	public static int rows;
	public static int cols;	
	public static int[][] mineField;
	public static int width;
	public static int height;
	public static int widthOffset;
	public static int heightOffset;
	public static int leftOffset;
	public static int rightOffset;
	public static int topOffset;
	public static int bottomOffset;
	public static Dimension mineFieldSize;
	private Image[] img;

	
	public MyPanel()
	{
		Minesweeper.myPanel = this;
		Border blackLine;
		blackLine = BorderFactory.createLineBorder(Color.BLACK, 2);
		this.setBorder(blackLine);
		img = new Image[NUM_IMAGES];
		for (int i = 0; i < NUM_IMAGES; i++) 
		{
			img[i] = 
				(new ImageIcon(this.getClass().getResource((i) + ".png"))).getImage();
		}
		Minesweeper.topStatusBar = new JLabel("  " + minesLeft + " mines left!");
		Minesweeper.myPanel.add(Minesweeper.topStatusBar, BorderLayout.NORTH);
		Minesweeper.bottomStatusBar = new JLabel( "  Play with me! Please..." );
		Minesweeper.bottomStatusBar.setBackground(Color.WHITE);
		Minesweeper.bottomStatusBar.setOpaque(true);
		Minesweeper.myPanel.add( Minesweeper.bottomStatusBar, BorderLayout.SOUTH );
		Insets myFrameInsets = Minesweeper.myPanel.getInsets();
		Insets insets = this.getInsets();
		leftOffset = insets.left + myFrameInsets.left;
		topOffset = insets.top + myFrameInsets.top;
		rightOffset = insets.right + myFrameInsets.right;
		bottomOffset = insets.bottom + myFrameInsets.bottom ;
		MinesAdapter mouse = new MinesAdapter();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		initializeGame(1);

	}

	public static void initializeGame(int difficulty)
	{
		
		Random randGenerator = new Random();
        level = difficulty;
		firstMove = true; 
		rows = ROWS[level];
		cols = COLUMNS[level];
		numberOfMines = NUMBER_OF_MINES[level];
		mineField = new int[rows][cols]; 
		minesLeft = numberOfMines;
		width = cols * CELL_SIZE;
		height = rows * CELL_SIZE;
		int widthOffset = leftOffset + rightOffset; 
		int heightOffset = topOffset + bottomOffset;
		mineFieldSize = new Dimension( width + widthOffset, height+heightOffset);
		Minesweeper.myPanel.setPreferredSize(mineFieldSize); 
		Minesweeper.myPanel.setMinimumSize(mineFieldSize);
		Minesweeper.myPanel.setMaximumSize(mineFieldSize);
		
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				mineField[i][j] = DRAW_COVER;
		int mPlaced = 0;
		while (mPlaced < numberOfMines) 
		{
			int mineX = randGenerator.nextInt(cols);
			int mineY = randGenerator.nextInt(rows);
			if (mineField[mineY][mineX] != COVERED_MINED_CELL)
			{
				mineField[mineY][mineX] = COVERED_MINED_CELL;
				mPlaced++;
			}
		}
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				if (mineField[i][j] != COVERED_MINED_CELL) 
				{
					mineField[i][j] = getSurroundingCount(i,j);
				}
			}
		}
		inGame = true; 
		Minesweeper.bottomStatusBar.setText(" GoodLuck !!!!!");
		Minesweeper.topStatusBar.setText("  " + minesLeft + " mines left!");

	} 
	private static int getSurroundingCount(int row, int col){
		int surroundingCount = COVERED_CELL;
		for(int R = row - 1; R <= row + 1; R++)
		{
			if( (R >= 0) && (R < rows) )
			{
				for( int C = col - 1; C <= col + 1; C++ )
				{ 
					if( (C >= 0) && (C < cols) && (R != row || C != col) &&
							(mineField[R][C] == COVERED_MINED_CELL)
					  )
					{
						surroundingCount++;
					}
				}
			}
		}
		return surroundingCount;
	}

	public void find_empty_cells(int row, int col) {

		int checkRow, checkColumn; 

		if (col > 0) {

			checkRow = row;
			checkColumn = col - 1;
			if (checkRow >= 0 && checkColumn >= 0)
				if (mineField[checkRow][checkColumn] > COVERED_MINED_CELL)
					return;
				else
					if (mineField[checkRow][checkColumn] > MINED_CELL) 
					{
						mineField[checkRow][checkColumn] -= COVERED_CELL; // remove cover
						if (mineField[checkRow][checkColumn] == EMPTY_CELL)
							find_empty_cells(checkRow,checkColumn);
					}
		}
		
		checkRow = row - 1;
		checkColumn = col;
		if (checkRow >= 0 && checkColumn >= 0)
			if (mineField[checkRow][checkColumn] > COVERED_MINED_CELL)
				return;
			else
				if (mineField[checkRow][checkColumn] > MINED_CELL) 
				{
					mineField[checkRow][checkColumn] -= COVERED_CELL;
					if (mineField[checkRow][checkColumn] == EMPTY_CELL)
						find_empty_cells(checkRow, checkColumn);
				}
		
		checkRow = row + 1;
		checkColumn = col;
		if (checkRow < rows && checkColumn < cols)
			if (mineField[checkRow][checkColumn] > COVERED_MINED_CELL)
				return;
			else
				if (mineField[checkRow][checkColumn] > MINED_CELL) 
				{
					mineField[checkRow][checkColumn] -= COVERED_CELL;
					if (mineField[checkRow][checkColumn] == EMPTY_CELL)
						find_empty_cells(checkRow, checkColumn);
				}

		if (col < (cols - 1)) {

			checkRow = row;
			checkColumn = col + 1;
			if (checkRow < rows && checkColumn < cols)
				if (mineField[checkRow][checkColumn] > COVERED_MINED_CELL)
					return;
				else
					if (mineField[checkRow][checkColumn] > MINED_CELL) {
						mineField[checkRow][checkColumn] -= COVERED_CELL;
						if (mineField[checkRow][checkColumn] == EMPTY_CELL)
							find_empty_cells(checkRow, checkColumn);
					}
		}

	}

	private class MinesAdapter extends MouseAdapter implements MouseListener, MouseMotionListener
	{
		
		public void mousePressed( MouseEvent event )
		{
			if (inGame)
			{
				String str = "";
				int button = event.getButton();
				int x, y;
				int mineFieldColumn, mineFieldRow;
				x = event.getX() - leftOffset;
				y = event.getY() - topOffset;
				mineFieldColumn = x/CELL_SIZE;
				if (mineFieldColumn >= cols)
					mineFieldColumn = cols-1;

				mineFieldRow = y/CELL_SIZE;
				if (mineFieldRow >= rows)
					mineFieldRow = rows -1;
				
				switch(button)
				{
					case MouseEvent.BUTTON1: 
						str = "Button1";
						break;
					case MouseEvent.BUTTON2: 
						str = "Button2";
						break;
					case MouseEvent.BUTTON3: 
						str = "Button3";
						break;
				}
				Minesweeper.bottomStatusBar.setText( 
						String.format( "    " + str + " pressed at [%d, %d]", 
							mineFieldRow, mineFieldColumn ) );

				repaint();

			} 

		} 

		public void mouseReleased( MouseEvent event )
		{
			if (inGame)
			{
				String str = "";
				int button = event.getButton();
				int mineFieldColumn, mineFieldRow;

				mineFieldColumn = event.getX()/CELL_SIZE;
				if (mineFieldColumn >= cols)
					mineFieldColumn = cols-1;

				mineFieldRow = event.getY()/CELL_SIZE;
				if (mineFieldRow >= rows)
					mineFieldRow = rows-1;

				switch(button)
				{
					case MouseEvent.BUTTON1:
						str = "Button1";
						break;
					case MouseEvent.BUTTON2:
						str = "Button2";
						break;
					case MouseEvent.BUTTON3:
						str = "Button3";
						break;
				}

				Minesweeper.bottomStatusBar.setText( 
						String.format( "    " + str + " released at [%d, %d]", 
							mineFieldRow, mineFieldColumn ) );

				repaint();
			}

		} 

		public void mouseMoved( MouseEvent event )
		{
			if (inGame)
			{
				int x, y;
				int mineFieldRow, mineFieldColumn;

				
				x = event.getX() - leftOffset;
				y = event.getY() - topOffset;

				mineFieldColumn = x/CELL_SIZE;
				if (mineFieldColumn >= cols)
					mineFieldColumn = cols-1;

				mineFieldRow = y/CELL_SIZE;
				if (mineFieldRow >= rows)
					mineFieldRow = rows-1;

				Minesweeper.bottomStatusBar.setText( 
						String.format( "    Mouse over [%d, %d]", 
							mineFieldRow, mineFieldColumn ) );

				repaint();

			} 

		} 
		
		public void mouseExited( MouseEvent event )
		{

			Minesweeper.bottomStatusBar.setBackground(Color.WHITE);

			if (!inGame)
				Minesweeper.bottomStatusBar.setText("     Click a button to start over!");
			else
				Minesweeper.bottomStatusBar.setText( "    Hunt for the mines!" );

			repaint();

		} 

		public void mouseClicked( MouseEvent event ) 
		{
			int x, y;
			int clickedColumn, clickedRow;
			x = event.getX() - leftOffset;
			y = event.getY() - topOffset;
			clickedColumn = x / CELL_SIZE;
			clickedRow = y / CELL_SIZE;
			boolean rePaint = false;  

			if (!inGame) {
				initializeGame(level);
				repaint();
			}

			if ((x < cols * CELL_SIZE) && (y < rows * CELL_SIZE)) 
			{
				if (event.getButton() == MouseEvent.BUTTON3) 
				{
					Minesweeper.bottomStatusBar.setText(
							String.format("    Clicked on [%d, %d]", 
								clickedRow, clickedColumn));

					if (mineField[clickedRow][clickedColumn] > MINED_CELL) 
					{
						rePaint = true;

						if (mineField[clickedRow][clickedColumn] <= COVERED_MINED_CELL) 
						{
							if (minesLeft > 0) 
							{
								mineField[clickedRow][clickedColumn] += MARKED_CELL;
								
								minesLeft--;

								Minesweeper.topStatusBar.setText(String.format("    " + minesLeft + " mines left!"));
							} 
							else
								Minesweeper.topStatusBar.setText("    No markers left!");
						} 
						else 
						{
							mineField[clickedRow][clickedColumn] -= MARKED_CELL;
							minesLeft++;

							Minesweeper.topStatusBar.setText(String.format("    " + minesLeft + " mines left!"));
						}
					}
				}
				else 
				{
					if (mineField[clickedRow][clickedColumn] > COVERED_MINED_CELL) 
					{
						return;
					}

					if ((mineField[clickedRow][clickedColumn] > MINED_CELL) &&
							(mineField[clickedRow][clickedColumn] <= MARKED_MINED_CELL)) 
					{
						
						if (firstMove && mineField[clickedRow][clickedColumn]%10 == MINED_CELL)
						{
							
							moveMine(clickedRow,clickedColumn);
						}

						firstMove = false;
						mineField[clickedRow][clickedColumn] -= COVERED_CELL;
						rePaint = true;

						if (mineField[clickedRow][clickedColumn] == MINED_CELL)
						{
							inGame = false;
							Minesweeper.bottomStatusBar.setText("   Click a button to start over!");
						}	
						if (mineField[clickedRow][clickedColumn] == EMPTY_CELL)
							find_empty_cells(clickedRow, clickedColumn);
					}
				}

				if (rePaint)
					repaint();
			}
		}
	}

	public void moveMine(int row, int col)
	{
		
		mineField[row][col] = COVERED_CELL;

		boolean mineMoved = false;

		for (int y = 0; y < rows; y++)
		{
			for (int x = 0; x < cols; x++)
			{
				if (x == col && y == row)
					continue;	

				if (mineField[y][x] != COVERED_MINED_CELL)
				{
					mineField[y][x] = COVERED_MINED_CELL;
					updateMineField();
					mineMoved = true;
					break;
				}
			}
			if (mineMoved)
				break;
		}
	}

	private void updateMineField()
	{
		
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				if (mineField[i][j] != COVERED_MINED_CELL) 
				{
					mineField[i][j] = getSurroundingCount(i,j);
				}
			}
		}
	}

	public void paintComponent(Graphics g) 
	{

		int cell = 0;
		int uncover = 0;
		super.paintComponent(g);

		for (int i = 0; i < rows; i++) 
		{
			for (int j = 0; j < cols; j++) 
			{

				cell = mineField[i][j];

				
				if (inGame && cell == MINED_CELL)
				{
					inGame = false;
				}

				if (!inGame) {
					if (cell == COVERED_MINED_CELL) {
						cell = DRAW_MINE;
					} else if (cell == MARKED_MINED_CELL) {
						cell = DRAW_MARK;
					} else if (cell > COVERED_MINED_CELL) {
						cell = DRAW_WRONG_MARK;
					} else if (cell > MINED_CELL) {
						cell = DRAW_COVER;
					}
				} else {
					if (cell > COVERED_MINED_CELL)
						cell = DRAW_MARK;
					else if (cell > MINED_CELL) {
						cell = DRAW_COVER;
						uncover++;
					}
				}

				g.drawImage(img[cell], (j * CELL_SIZE)+leftOffset,
						(i * CELL_SIZE)+topOffset, null);

			} 

		} 
		if (uncover == 0 && inGame) {
			inGame = false;
			Minesweeper.topStatusBar.setText("    You won! :-)");
			Minesweeper.bottomStatusBar.setBackground(Color.WHITE);
			Minesweeper.bottomStatusBar.setText("     Click a button to start over!");
		} 
		else 
		{
			if (!inGame && minesLeft != 0)
			{
				Minesweeper.topStatusBar.setText("    You lost! :-(");
				Minesweeper.bottomStatusBar.setBackground(Color.WHITE);
				Minesweeper.bottomStatusBar.setText("     Click a button to start over!");
			}
		}

	}
}