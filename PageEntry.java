import java.io.*;
import java.util.*;
import java.lang.*;


public  class PageEntry
{


	//////////////////  VARIABLES  ///////////////////

	

	private String webpage;

	private PageIndex pi;

	
	
	private char pun[] = {'.', ',', '\'', '\"', '?', '#', '!', '-', ':', '(', ')' , '{', '}', '[', ']', '<', '>' , '=', ';' };

	private String connectors[] = { "a", "an", "the", "they", "these", "this" , "for", "is", "are", "of", "or", "and", "does", "will", "whose", "was" };




	public String getWebPageName()
	{
		return this.webpage;
	}



	public PageEntry(String pageName)
	{
		
		pi = new PageIndex();
		

		try
		{

		
		Scanner s = new Scanner(new FileInputStream(pageName));

		
		
		webpage = pageName;
		int wi = 0;
		int WI = 0;

		while(s.hasNextLine())
		{
			
			String str[]=s.nextLine().split("\\s+");

			if(str.length==0)
			{
				continue;
			}
			


			for(int u=0;u<str.length;u++)
			{
				wi++;
				WI++;		
				String sw = str[u];

				sw = sw.toLowerCase();

				boolean t = false;
				for(int i=0;i<connectors.length;i++)
				{
					if(sw.compareTo(connectors[i])==0)
					{
						t = true;
						break;
					}
				}
				if(t)
				{
					WI++;
					continue;
				}

				sw = sw.toLowerCase();

				if(sw.compareTo("stacks")==0 || sw.compareTo("structures")==0 || sw.compareTo("applications")==0 )
				{
					sw = sw.substring(0,sw.length()-1);
				}


				for(char c : pun)
				{
					if(sw.indexOf(c) == -1)
					{
						continue;
					}
					else
					{
						sw = sw.replace(c,' ');
					}
				}

				sw = sw.trim();

				
				while(true)

				{	if(sw.indexOf(' ') == -1)
					{
						break;
					}
					else
					{
						String snew = sw.substring(0,sw.indexOf(' '));


						if(snew.compareTo("stacks")==0 || snew.compareTo("structures")==0 || snew.compareTo("applications")==0 )
						{
							snew = snew.substring(0,sw.length()-1);
						}

						Position newps = new Position(this,wi,WI);

						wi++;
						WI++;

						pi.addPositionForWord( snew, newps);



						sw = sw.substring(sw.indexOf(' ')+1, sw.length());
					}
				}
				




				
				if(sw.compareTo("stacks")==0 || sw.compareTo("structures")==0 || sw.compareTo("applications")==0 )
				{
					sw = sw.substring(0,sw.length()-1);
				}

				

				
				Position newps = new Position(this,wi,WI);


				

				pi.addPositionForWord( sw, newps);

				



			}

		}	
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("WEBPAGE NOT AVAILABLE  ----////" + pageName + "/////" );
			System.out.println();
			System.out.println(e.getMessage());
		}




	}



	public PageIndex getPageIndex()
	{
		return this.pi;	
	}


	public float getRelevanceOfPage(String str[], boolean doTheseWordsRepresentAPhrase, MyHashTable hsTable)
	{
		if(doTheseWordsRepresentAPhrase)
		{
			int u = hsTable.getHashIndex(str[0]);
			float r=0;
			Node<WordEntry> headx = hsTable.myHashTable[u].getWordEntries().head();
			while(headx!=null)
			{
				if(headx.element.getWord().compareTo(str[0])==0)
				{
					Node<Position> pos = headx.element.getAllPositionsForThisWord().head();
					while(pos!=null)
					{
					
						if(pos.element.getPageEntry().getWebPageName().compareTo(this.webpage)==0)
						{
							
							//////////////////////////////////////////////////////////////////////////////////////

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
							r += 1/(float)Math.pow(pos.element.getWordIndex(),2);
							
							
						}

						
							
						}

						pos = pos.next;
					}

					return r;				
				}
				headx = headx.next;
			}
			return 0;

		}

		/////////////////////////////////////////////////////////////////////////////






		else
		{



		float total = 0 ;



		for(int i=0; i<str.length; i++)
		{
			int z = hsTable.getHashIndex(str[i]);

			Node<WordEntry> headxy = hsTable.myHashTable[z].getWordEntries().head();


			while(headxy!=null)
			{
				if(headxy.element.getWord().compareTo(str[i])==0)
				{
					Node<Position> p = headxy.element.setOfPositions.set.head();
					while(p!=null)
					{
						if(p.element.getPageEntry().getWebPageName().compareTo(this.webpage)==0)
						{
							total += 1/Math.pow(p.element.getWordIndex(),2);
							
						}

						p = p.next;
					}
					break;

				}

				headxy = headxy.next;
			}
		}

		return total;
		//////////////////////////////////////////////////////////////////////////////////
		



		}





	}









}
