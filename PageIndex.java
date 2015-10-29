import java.io.*;
import java.util.*;
import java.lang.*;


public class PageIndex
{

	/////////////////////  VARIABLES  ///////////////////////

	
	private MySet<WordEntry> setOfWordEntries;

	public PageIndex()
	{
		setOfWordEntries = new MySet<WordEntry>();
	}



	private boolean isMember(MySet<WordEntry> m, String s)
	{
		

        ////////////  ?????????????


		Node<WordEntry> w = m.set.head();
		for(int i=0;i<m.set.size();i++)
		{
			if(w.element.getWord().compareTo(s)==0)
			{
				return true;
			}
			w = w.next;
		}
		return false;

	}




	public void addPositionForWord(String str, Position p)
	{
		



		if(!isMember(setOfWordEntries,str))
		{
			//System.out.println("   Reached pos 1 ");
			
			
			WordEntry newWordEntry = new WordEntry(str);
			//System.out.println("   Reached pos 2 ");
			newWordEntry.addPosition(p);
			//System.out.println("   Reached pos 3 ");
			setOfWordEntries.addElement(newWordEntry);
			//System.out.println("   Reached pos 4 ");
			return;
		}
		else
		{
			

			
			//System.out.println(" IN THE ELSE     POS 1");


			Node<WordEntry> w = setOfWordEntries.set.head();

			//System.out.println(" IN THE ELSE     POS 2");
			

			for(int i=0;i<setOfWordEntries.set.size();i++)
			{
				
				if(w.element.getWord().compareTo(str)==0)
				{
					w.element.addPosition(p);

					//.out.println(" IN THE ELSE     POS  3333  ");
			

					return;
				}
				w = w.next;
			}

			//System.out.println(" IN THE ELSE     POS  3333  ");
			
			return;
		}
	}




	public MyLinkedList<WordEntry> getWordEntries()
	{
		return setOfWordEntries.set;
		
	}



}
