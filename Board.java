package codeCup2019;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JFrame{
	
	private Token[][] board;
	private int rows;
	private int cols;
	
	/*
	 * one and only constructor
	 */
	public Board(int x, int y)
	{
		super("FLIPPO BOARD");
		setSize(800, 800);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		rows = x;
		cols = y;
		board = new Token[x][y];
		for(int r = 0; r < rows; r++)
		{
			for(int c = 0; c < cols; c++)
			{
				board[r][c] = new Token(r, c, 0);
			}
		}
		
		board[3][3] = new Token(3, 3, 1);
		board[3][4] = new Token(3, 4, 2);
		board[4][3] = new Token(4, 3, 2);
		board[4][4] = new Token(4, 4, 1);
	}
	
	public Board(Board b)
	{
		
		super("FLIPPO BOARD");
		setSize(800, 800);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		rows = b.rows;
		cols = b.cols;
		
		board = new Token[rows][cols];
		for(int r = 0; r < rows; r++)
		{
			for(int c = 0; c < cols; c++)
			{
				this.set(b.get(r,  c), r, c);
			}
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 */
	
	public void paint(Graphics g)
	{
		
		//draw the grid
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		for(int i = 1; i < 8; i++)
		{
			Line2D col = new Line2D.Float(i * 100, 0, i * 100, 800);
			g2.draw(col);
			Line2D row = new Line2D.Float(0, i * 100, 800, i * 100);
			g2.draw(row);
		}
		
		for(int r = 0; r < 8; r++)
		{
			for(int c = 0; c < 8; c++)
			{
				if(board[c][r].getColor() == 1)
				{
					Ellipse2D.Double circle = new Ellipse2D.Double((r * 100), (c * 100), 100, 100);
					g2.setColor(new Color(255, 0, 0));
					g2.fill(circle);
				}
				else if(board[c][r].getColor() == 2)
				{
					Ellipse2D.Double circle = new Ellipse2D.Double((r * 100), (c * 100), 100, 100);
					g2.setColor(new Color(0, 0, 0));
					g2.fill(circle);
				}
			}
		}
		
	}
	
	public void set(Token t, int x, int y)
	{
//		print();
		board[x][y] = t;
//		print();
	}
	
	public int getWhiteScore()
	{
		int score = 0;
		for(int r = 0; r < 8; r++)
		{
			for(int c = 0; c < 8; c++)
			{
				if(board[r][c].getColor() == 1)
				{
					score += 1;
				}
			}
		}
		
		return score-2;
	}
	
	public int getBlackScore()
	{
		int score = 0;
		for(int r = 0; r < 8; r++)
		{
			for(int c = 0; c < 8; c++)
			{
				if(board[r][c].getColor() == 2)
				{
					score += 1;
				}
			}
		}
		
		return score-2;
	}
	
	public int getMinMaxWhiteScore()
	{
		int score = 0;
		for(int r = 0; r < 8; r++)
		{
			for(int c = 0; c < 8; c++)
			{
				if(board[r][c].getColor() == 1)
				{
					score += 1;
				}
			}
		}
		
		return score-2;
	}
	
	public int getMinMaxBlackScore()
	{
		int score = 0;
		for(int r = 0; r < 8; r++)
		{
			for(int c = 0; c < 8; c++)
			{
				if(board[r][c].getColor() == 1)
				{
					score += 1;
				}
			}
		}
		
		return 0-(score-2);
	}
	
	public void add(Token t, int x, int y)
	{
		//System.out.println("x,y: " +x+","+y);
		
		try
		{
			board[x][y] = t;
			if(t.getColor() != 0)
			{
				int color = t.getColor();
				for(int i = -1; i <= 1; i++)
				{
					for(int j = -1; j <= 1; j++)
					{
						if(x + i < 8 && x + i >= 0 && y + j < 8 && y + j >= 0)
						{
							if(!(i == 0 && j == 0))
							{
								Token perimeter = board[x + i][y + j];
								int firstX = x + i;
								int firstY = y + j;
//								System.err.println("i = " + i + ", and j = " + j);
//								System.err.println("The coordinates for this perimeter token is (" + firstX + ", " + firstY + ")");
								if(perimeter.isToken())
								{
//									System.err.println("There IS a token at the coordinates (" + firstX + ", " + firstY + ")");
									int lastX = firstX;
									int lastY = firstY;
//									for(int r = x + i; r < 8; r+=i)
//									{
//										for(int c = y + j; c < 8; c+=j)
//										{
//											if(board[r][c].getColor() == color)
//											{
//												lastX = r;
//												lastY = c;
//											}
//										}
//									}
									int r = x + i;
									int c = y + j;
									boolean temp = board[r][c].isToken();
									while(temp && r < 8 && c < 8 && r >= 0 && c >= 0)
									{
										int col = board[r][c].getColor();
										if(col == color)
											
										{
											lastX = r;
											lastY = c;
										}
										r+=i;
										c+=j;
									}
//									System.err.println("The furthest token of color " + color + "in the direction of token (" + firstX + ", " + firstY + ") is (" + lastX + ", " + lastY + ")");
//									for(int r = lastX; r != firstX; r-=i)
//									{
//										for(int c = lastY; c != firstY; c -=j)
//										{
//											board[r][c].flip();
//										}
//									}
									int r1 = lastX;
									int c1 = lastY;
									while(!(r1 == firstX && c1 == firstY))
									{
										r1-=i;
										c1-=j;
										board[r1][c1].flip();
//										System.err.println("Flipped token at (" + r1 + ", " + c1 + ")");
									}
									
								}
								else
								{
//									System.err.println("There is NOT a token at the coordinates (" + firstX + ", " + firstY + ")");
								}
							}
							else
							{
//								System.err.println("Token is original placement, not perimeter");
							}
						}
					}
				}
			}
		}
		catch(Exception e)
		{
//			System.err.println("Class : Board.java ; Method : void add(Token t, int x, int y)");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public Token get(int x, int y)
	{
		return board[x][y];
	}
	
	public void print()
	{
		for(int r = 0; r < 8; r++)
		{
			for(int c = 0; c < 8; c++)
			{
				System.out.print(board[r][c].getColor());
			}
			System.out.print("\n");
		}
		System.out.println("\n");
	}
	
	
	public Board copy()
	{
		Board b = new Board(8, 8);
		for(int r = 0; r < 8; r++)
		{
			for(int c = 0; c < 8; c++)
			{
				b.set(new Token(r, c, board[r][c].getColor()), r, c);
			}
		}
		return b;
	}
	
	public Board copy(Board b) {
		return new Board(b);
	}
	
	public boolean gameOver()
	{
		for(int r = 0; r < 8; r++)
		{
			for(int c = 0; c < 8; c++)
			{
				if(board[r][c].getColor() == 0)
				{
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	public Token[][] getBoard()
	{
		return board;
	}
	
	public boolean equals(Board b)
	{
		Token[][] other = b.getBoard();
		for(int r = 0; r < 8; r++)
		{
			for(int c = 0; c< 8; c++)
			{
				if(!(board[r][c].equals(other[r][c])))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	
}
