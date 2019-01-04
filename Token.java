package codeCup2019;

//class to store tokens

public class Token {
	
	private int type;
	private int x;
	private int y;
	/*
	 * one and only constructor
	 */
	public Token(int XCoordinate, int YCoordinate, int color)
	{
		x = XCoordinate;
		y = YCoordinate;
		type = color;
	}
	
	public String toString() {
		return "x: "+x+"; y: "+y+"; type: "+type;
	}
	
	public void flip()
	{
		if(type == 1)
		{
			type = 2;
		}
		else if(type == 2)
		{
			type = 1;
		}
	}
	
	/*
	 * get x
	 */
	public int getX()
	{
		return x;
	}
	
	/*
	 * set x
	 */
	public void setX(int XCoordinate)
	{
		x = XCoordinate;
	}
	
	/*
	 * get y
	 */
	public int getY()
	{
		return y;
	}
	
	/*
	 * set y
	 */
	public void setY(int YCoordinate)
	{
		y = YCoordinate;
	}
	
	/*
	 * get the integer that represents the color i.e. black, white, or null
	 */
	public int getColor()
	{
		return type;
	}
	
	/*
	 * set the integer that represent scolor to either 0, 1, or 2
	 */
	public void setColor(int color)
	{
		type = color;
	}
	
	/*
	 * determine if it is not a token or if it is
	 */
	public boolean isToken()
	{
		if(type != 0)
		{
			return true;
		}
		return false;
	}
	
	/*
	 * determines what color the token is
	 */
	public boolean isWhite()
	{
		return type == 1;
	}
	
	public boolean isBlack()
	{
		return type == 2;
	}
}
