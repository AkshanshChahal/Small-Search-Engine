import java.io.*;
import java.util.*;
import java.lang.*;



public  class InvertedPageIndex
{

	//////////////////////////  VARIABLES  ////////////////////////

	   
	public static MyHashTable hsTable;


	public InvertedPageIndex()
	{
		hsTable = new MyHashTable();
		
	}









	public void addPage(PageEntry p)
	{
		
		PageIndex mipi = p.getPageIndex();

		Node<WordEntry> dummy = new Node<WordEntry>();



		dummy = mipi.getWordEntries().head();

		while(dummy!=null)
		{
			
			hsTable.addPositionsForWord(dummy.element);
			dummy = dummy.next;
		}



	}



	public MySet<PageEntry> getPagesWhichContainWord(String str)
	{
		

		MySet<PageEntry> new_setOfPageEntries = new MySet<PageEntry>();
		int u = hsTable.getHashIndex(str);
		
		try
		{
			Node<WordEntry> headx = hsTable.myHashTable[u].getWordEntries().head();
		}
		catch(Exception e)
		{
			//System.out.println(" Error while searching for the word  " + str);
			return null;
		}
		

		Node<WordEntry> headx = hsTable.myHashTable[u].getWordEntries().head();
		boolean b = false;
		while(headx!=null)
		{
			if(headx.element.getWord().compareTo(str)==0)
			{
				b = true;
				Node<Position> pos = headx.element.getAllPositionsForThisWord().head();
				while(pos!=null)
				{
					
					if(new_setOfPageEntries.IsMember(pos.element.getPageEntry()))
					{
						pos = pos.next;
						continue;
					}

					new_setOfPageEntries.addElement(pos.element.getPageEntry());
					pos = pos.next;
				}
				return new_setOfPageEntries;

			}
			headx = headx.next;
		}

		if(!b)
			return null;

		return null;


	}



	public MySet<PageEntry> getPagesWhichContainPhrase(String str[])
	{
		MySet<PageEntry> phraseSearch = new MySet<PageEntry>();
		int u = hsTable.getHashIndex(str[0]);
		try
		{
			Node<WordEntry> headx = hsTable.myHashTable[u].getWordEntries().head();

		}
		catch(Exception e)
		{
			System.out.println(" There is no such word as " + str[0] + "  present in the HASHTABLE !!!!!!!!!!!!!");
			return null;
		}

		Node<WordEntry> headx = hsTable.myHashTable[u].getWordEntries().head();

		boolean b = false;
		
		while(headx!=null)
		{
			if(headx.element.getWord().compareTo(str[0])==0) /// Reached Word Entry of the First Word in the Phrase !!
			{
				b = true;
				Node<Position> pos = headx.element.getAllPositionsForThisWord().head(); 
				while(pos!=null)       ///  Going to each position in the word entry of the Ist word ..
				{

					int windex = pos.element.getWI();
					PageEntry pgentri = pos.element.getPageEntry();
					boolean task = true;
					for(int i=1;i<str.length;i++)
					{
						
						Position px = new Position(pgentri,0,windex+i);

						int z = hsTable.getHashIndex(str[i]);


						Node<WordEntry> headxy = hsTable.myHashTable[z].getWordEntries().head();
						while(headxy!=null)
						{
							if(headxy.element.getWord().compareTo(str[i])==0)
							{
								if(!headxy.element.search(px))
								{
									task = false;
								}
								break;
							}
							headxy = headxy.next;
						}
						if(!task)
							break;


					}

					if(task)
					{
						phraseSearch.addElement(pgentri);
					}


					pos = pos.next;
				}
			}

			if(b)
				break;

			headx = headx.next;
		}

		return phraseSearch;		

	}




}
