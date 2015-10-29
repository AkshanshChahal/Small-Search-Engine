public class AVLNode
{
	AVLNode left, right ;
	Position posit ;
	int height ;

	//Constructor
	public AVLNode()
	{
		left = null;
		right = null;
		posit = null;
		height = 0;
	}

	//Constructor
	public AVLNode(Position p)
	{
		left = null;
		right = null;
		posit = p;
		height = 0;
	}

	

}
