import java.io.*;
import java.util.*;
import java.lang.*;


public class MyHashTable
{


	///////////////////////////  VARIABLES  //////////////////////////////


	
	public  static PageIndex[] myHashTable; 


	public MyHashTable()
	{
		myHashTable = new PageIndex[300000];
	}



////private ???
	public int getHashIndex(String str)
	{
		int index = 0;
		int length = 300000;
		int l = str.length();
		for(int i=0;i<l;i++)
		{
			index += ((int)str.charAt(i)*Math.pow(l,i+1))%300000;
		}
		int hashindex = index%300000;
		return hashindex;
	}





	public void addPositionsForWord(WordEntry w)
	{
		String word = w.getWord();
		
		int ind = getHashIndex(word);

		PageIndex px;

		try
		{
			px = myHashTable[ind];

			Node<WordEntry> dummy = px.getWordEntries().head();
			boolean td = false;
		while(dummy!=null)
		{
			if(dummy.element.getWord().compareTo(word)==0)
			{
				dummy.element.addPositions(w.getAllPositionsForThisWord());
				td = true;
			}
			dummy = dummy.next;
		}
		if(!td)
		{
			px.getWordEntries().addFirst(w);
		}
		return;


			
		}
		catch(Exception e)
		{
			
			myHashTable[ind] = new PageIndex();
			PageIndex pxx = myHashTable[ind];
			pxx.getWordEntries().addFirst(w);
			return;

		}

	}

}
