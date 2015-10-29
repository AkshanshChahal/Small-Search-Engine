import java.io.*;
import java.util.*;
import java.lang.*;


public class WordEntry
{
	
	////////////////////////////  VARIABLES  /////////////////////////

	public MySet<Position> setOfPositions;


	private String word;

	private AVLNode root;

	//Constructor
	public WordEntry(String word)
	{
		this.word = word;
		setOfPositions = new MySet<Position>();
		root  = null;
	}

	public String getWord()
	{
		return this.word;
	}

	public boolean isEmpty()
    {
        return root == null;
    }

    /* Make the tree logically empty */
    public void makeEmpty()
    {
        root = null;
    }

    private int height(AVLNode t)
    {
    	return t == null ? -1 : t.height;
    }


    public void addPositions(MyLinkedList<Position> positions)
	{
		//MySet<Position> newset = new MySet<Position>(positions);
		//setOfPositions = setOfPositions.union(newset);
		Node<Position> n = positions.head();
		while(n!=null)
		{
			addPosition(n.element);
			n = n.next; 
		}
	}


    //////////// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ /////////////

    public void addPosition(Position position)
    {
    	root = addPosition(position,root);
    	setOfPositions.addElement(position);
    }

    private AVLNode addPosition(Position x, AVLNode t)
    {
    	
    	if(t==null)
    	{
    		t = new AVLNode(x);
    	}

    	else if (x.getWI() < t.posit.getWI())
    	{
    		t.left = addPosition( x, t.left );

    		if( height(t.left) - height(t.right) == 2)
    			if(x.getWI() < t.left.posit.getWI())   /////   checking which rotation to apply !!
    				t = rotateWithLeftChild(t);
    			else
    				t = twice_rotateWithLeftChild(t);

    	}

    	else if (x.getWI() >= t.posit.getWI())
    	{
    		t.right = addPosition( x, t.right);

    		if( height(t.right)-height(t.left) == 2)
    			if(x.getWI() >= t.right.posit.getWI())
    				t = rotateWithRightChild(t);
    			else
    				t = twice_rotateWithRightChild(t);

    	}

///////////    	else ;  /// Duplicate Entry hence do nothing

    	t.height = Math.max( height(t.left), height(t.right)) + 1;

    	return t;


    }



    private AVLNode rotateWithLeftChild(AVLNode k2)
    {
    	AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = Math.max( height( k1.left ), k2.height ) + 1;
        return k1;

    }

    private AVLNode rotateWithRightChild(AVLNode k1)
    {
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = Math.max( height( k2.right ), k1.height ) + 1;
        return k2;
    }

    /**
      * Double rotate binary tree node: first left child
      * with its right child; then node k3 with new left child */
    private AVLNode twice_rotateWithLeftChild(AVLNode k3)
    {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }
    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child */      
    private AVLNode twice_rotateWithRightChild(AVLNode k1)
    {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }  


    public MyLinkedList<Position> getAllPositionsForThisWord()
	{
		return setOfPositions.getList();
	}  


	public boolean search(Position p)
	{
		return search(root,p);
	}

	private boolean search(AVLNode n, Position p)
	{
		if(n==null)
			return false;
		else if(p.getWI()==n.posit.getWI())
			if(p.getPageEntry().getWebPageName().compareTo(n.posit.getPageEntry().getWebPageName())==0)
				return true;
			else
				return search(n.right,p);

		if(p.getWI()<n.posit.getWI())
		{
			return search(n.left,p);
		}
		else
			return search(n.right,p);

	}









}
