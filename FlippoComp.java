package codeCup2019;

public class FlippoComp {
	
	public FlippoComp()
	{
		
	}
	
	public Board getBestMove(Board b, boolean maximizingPlayer)
	{
		
		Board move = b.copy();
		
		if(maximizingPlayer)
		{
			
			int maxScore = b.getMinMaxWhiteScore();
			
			for(int r = 0; r < 8; r++)
			{
				for(int c = 0; c < 8; c++)
				{
					if(b.get(r, c).getColor() == 0)
					{
						Board next = b.copy();
						next.set(new Token(r, c, 1), r, c);
						if(next.getMinMaxWhiteScore() > maxScore)
						{
							maxScore = next.getMinMaxWhiteScore();
							move = next.copy();
						}
					}
				}
			}
		}
		else
		{
			
			int minScore = b.getMinMaxBlackScore();
			
			for(int r = 0; r < 8; r++)
			{
				for(int c = 0; c < 8; c++)
				{
					Board next = b.copy();
					System.out.println("r,c:" +r+","+c);
					next.add(new Token(r, c, 2), r, c);
					if(next.getMinMaxBlackScore() < minScore)
					{
						minScore = next.getMinMaxBlackScore();
						move = next.copy();
					}
				}
			}
		}
		
		return move;
		
	}
	
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
					if(eval.getMinMaxWhiteScore() > maxEval.getMinMaxWhiteScore())
					{
						maxEval = eval.copy();
					}
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
					if(eval.getMinMaxBlackScore() < minEval.getMinMaxBlackScore())
					{
						minEval = eval.copy();
					}
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
