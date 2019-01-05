package codeCup2019;

public class FlippoComp {
	
	public FlippoComp()
	{
		System.out.println("Computer has loaded in");
	}
	
	public Board getBestMove(Board b, boolean maximizingPlayer)
	{
		Board finalMove = new Board(b);
		
		if(maximizingPlayer)
		{
			int score = b.getWhiteScore();
			
			for(int r = 0; r < 8; r++)
			{
				for(int c = 0; c < 8; c++)
				{
					Board temp = new Board(b);
					if(temp.get(r, c).getColor() == 0)
					{
						Board move = new Board(8, 8);
						for(int i = 0; i < 8; i++)
						{
							for(int j = 0; j< 8; j++)
							{
								move.set(new Token(i, j, temp.get(i, j).getColor()), i, j);
							}
						}
						move.add(new Token(r, c, 1), r, c);
						if(move.getBlackScore() > score)
						{
							System.out.println("before move");
							temp.print();
							finalMove = finalMove.copy(move);
							score = finalMove.getBlackScore();
							System.out.println("after move");
							finalMove.print();
						}
					}
				}
			}
			if(finalMove.equals(b))
			{
				out : for(int r = 0; r < 8; r++)
				{
					for(int c = 0; c < 8; c++)
					{
						if(finalMove.get(r, c).getColor() == 0)
						{
							finalMove.add(new Token(r, c, 1), r, c);
							break out;
						}
					}
				}
			}
		}
		else
		{
			int score = b.getBlackScore();
			
			for(int r = 0; r < 8; r++)
			{
				for(int c = 0; c < 8; c++)
				{
					Board temp = new Board(b);
					if(temp.get(r, c).getColor() == 0)
					{
						Board move = new Board(8, 8);
						for(int i = 0; i < 8; i++)
						{
							for(int j = 0; j< 8; j++)
							{
								move.set(new Token(i, j, temp.get(i, j).getColor()), i, j);
							}
						}
						move.add(new Token(r, c, 2), r, c);
						if(move.getBlackScore() > score)
						{
							System.out.println("before move");
							temp.print();
							finalMove = finalMove.copy(move);
							score = finalMove.getBlackScore();
							System.out.println("after move");
							finalMove.print();
						}
					}
				}
			}
			if(finalMove.equals(b))
			{
				out : for(int r = 0; r < 8; r++)
				{
					for(int c = 0; c < 8; c++)
					{
						if(finalMove.get(r, c).getColor() == 0)
						{
							finalMove.add(new Token(r, c, 2), r, c);
							break out;
						}
					}
				}
			}
		}
		return finalMove;
	}
	
//	public Board getBestMove(Board b, boolean maximizingPlayer)
//	{
//		
//		Board move= new Board(b);
//		
//		if(maximizingPlayer)
//		{
//			
//			int maxScore = b.getWhiteScore();
//			
//			for(int r = 0; r < 8; r++)
//			{
//				for(int c = 0; c < 8; c++)
//				{
//					if(b.get(r, c).getColor() == 0)
//					{
//						Board next = new Board(b);
//						next.add(new Token(r, c, 1), r, c);
//						if(next.getWhiteScore() > maxScore)
//						{
//							maxScore = next.getMinMaxWhiteScore();
//							move = move.copy(next);
//						}
//					}
//				}
//			}
//			if(move.equals(b))
//			{
//				for(int r = 0; r < 8; r++)
//				{
//					for(int c = 0; c < 8; c++)
//					{
//						if(b.get(r, c).getColor() == 0)
//						{
//							move.add(new Token(r, c, 1), r, c);
//							break;
//						}
//					}
//				}
//			}
//		}
//		else
//		{
//			
//			int minScore = b.getBlackScore();
//			System.out.println(minScore);
//			
//			for(int r = 0; r < 8; r++)
//			{
//				for(int c = 0; c < 8; c++)
//				{
//					if(b.get(r, c).getColor() == 0)
//					{
//						Board next = new Board(b);
//						Board temp = new Board(b);
//						
//						next.add(new Token(r, c, 2), r, c);
//						if(next.getBlackScore() > minScore)
//						{
//							minScore = next.getBlackScore();
//							move = move.copy(next);
//							System.out.println("before move");
//							temp.print();
//							System.out.println("after move");
//							move.print();
//						}
//					}
//				}
//			}
//			if(move.equals(b))
//			{
//				for(int r = 0; r < 8; r++)
//				{
//					for(int c = 0; c < 8; c++)
//					{
//						if(b.get(r, c).getColor() == 0)
//						{
//							move.add(new Token(r, c, 2), r, c);
//							break;
//						}
//					}
//				}
//			}
//		}
//		System.out.println("final choice");
//		move.print();
//		return move;
//		
//	}
	public Board minimax(Board position, int depth, int alpha, int beta, boolean maximizingPlayer)
	{
		if(depth == 0 || position.gameOver())
		{
			return getBestMove(position, maximizingPlayer);
		}
		
		if(maximizingPlayer)
		{
			Board maxEval = position.copy();
			for(int r = 0; r < 8; r++)
			{
				for(int c = 0; c < 8; c++)
				{
					Board next = position.copy();
					next.add(new Token(r, c, 1), r, c);
					Board eval = minimax(next, depth - 1, alpha, beta, false);
					System.out.println("MAX PLAYER : eval(" + eval.getMinMaxWhiteScore() + ") > maxEval(" + maxEval.getMinMaxWhiteScore() + ") ?");
					if(eval.getMinMaxWhiteScore() > maxEval.getMinMaxWhiteScore())
					{
						maxEval = eval.copy();
						System.out.println("yes");
					}
					System.out.println("maxEval = " + maxEval.getMinMaxWhiteScore());
					alpha = Math.max(alpha, eval.getMinMaxWhiteScore());
					if(beta <= alpha)
					{
						break;
					}
				}
			}
			return maxEval;
		}
		else
		{
			Board minEval = position.copy();
			for(int r = 0; r < 8; r++)
			{
				for(int c = 0; c < 8; c++)
				{
					Board next = position.copy();
					next.add(new Token (r, c, 2), r, c);
					Board eval = minimax(next, depth - 1, alpha, beta, true);
					System.out.println("MIN PLAYER : eval(" + eval.getMinMaxBlackScore() + ") < minEval(" + minEval.getMinMaxBlackScore() + ") ?");
					if(eval.getMinMaxBlackScore() < minEval.getMinMaxBlackScore())
					{
						minEval = eval.copy();
						System.out.println("yes");
					}
					System.out.println("minEval = " + minEval.getMinMaxBlackScore());
					beta = Math.min(beta, eval.getMinMaxBlackScore());
					if (beta <= alpha)
					{
						break;
					}
					return minEval;
				}
			}
		}
		return position;
		
	}

}
