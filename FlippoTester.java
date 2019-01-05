package codeCup2019;

import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FlippoTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int turns = 0;
		
		Board b = new Board(8, 8);
		b.setVisible(true);
		
		int playerColor = 1;
		int compColor = 2;
		
		Scanner scan = new Scanner(System.in);
		
		
		System.out.println("To make a turn, please type in the following format : A5 or D7 and so on...");
		System.out.println("WHITE goes first");
		
		
		while(turns <= 60)
		{
			
			String turn = scan.nextLine();
			
			if(turn.equals("QUIT"))
			{
				b.dispatchEvent(new WindowEvent(b, WindowEvent.WINDOW_CLOSING));
				scan.close();
				break;
			}
			
			if(compColor == 1)
			{
				FlippoComp comp = new FlippoComp();
				System.out.println("Computer making move . . .");
//				b = comp.minimax(b, 3, 60, -60, true).copy();
				b = b.copy(comp.getBestMove(b,  true));
				b.repaint();
				System.out.println("Type your move");
				char letter = turn.charAt(0);
				int row = letter - 65;
				char number = turn.charAt(1);
				String number1 = "" + number;
				int col = Integer.parseInt(number1) - 1;
				
				if(!b.get(row, col).isToken())
				{
					b.add(new Token(row, col, 1), row, col);
//					b.set(new Token(row, col, 1), row, col);
				}
				else
				{
					System.out.println("There is already a piece there!");
				}
			}
			else
			{
				char letter = turn.charAt(0);
				int row = letter - 65;
				char number = turn.charAt(1);
				String number1 = "" + number;
				int col = Integer.parseInt(number1) - 1;
				
				if(!b.get(row, col).isToken())
				{
					b.add(new Token(row, col, 1), row, col);
//					b.set(new Token(row, col, 1), row, col);
					System.out.println("Player Move :");
					b.print();
					b.repaint();
				}
				else
				{
					System.out.println("There is already a piece there!");
				}
				
				FlippoComp comp = new FlippoComp();
				System.out.println("Computer making move . . .");
				b = b.copy(comp.minimax(b, 3, 60, -60, false));
//				b = b.copy(comp.getBestMove(b,  false));
				System.out.println("Comp Move:");
				b.print();
				b.repaint();
				
			}
			
			b.repaint();
			turns++;
		}
		
		scan.close();
		
		System.out.println("Game Over!");
		if(b.getWhiteScore() > b.getBlackScore())
		{
			System.out.println("White Wins!");
		}
		else
		{
			System.out.println("Black Wins!");
		}
		
	}

}
