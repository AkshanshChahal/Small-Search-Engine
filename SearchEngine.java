import java.io.*;
import java.util.*;
import java.lang.*;


public class SearchEngine
{

	private  static InvertedPageIndex ipi ;

	public SearchEngine()
	{
		ipi = new InvertedPageIndex();

	}



	
	
	private char pun[] = {'.', ',', '\'', '\"', '?', '#', '!', '-', ':', '(', ')' , '{', '}', '[', ']', '<', '>' , '=', ';' };

	private String connectors[] = { "a", "an", "the", "they", "these", "this" , "for", "is", "are", "of", "or", "and", "does", "will", "whose", "was" };



	public void performAction(String actionMessage) 
	{
		
		
		

		if(actionMessage.length() < 9)
		{
			System.out.println("FATAL ERROR : INVALID INPUT STRING");
			System.out.println();
			return;
		}


		switch(actionMessage.substring(0,8))
		{


			case "addPage ":

							String webpage = actionMessage.substring(8,actionMessage.length());

							PageEntry pgEntry = new PageEntry(webpage);

							ipi.addPage(pgEntry);

							System.out.println();



							break;



			case "queryFin":
			
							
							System.out.print(actionMessage + " :::::::  ");

							if(actionMessage.substring(0,30).compareTo("queryFindPagesWhichContainWord")==0)
							{

								
								String word = actionMessage.substring(31,actionMessage.length());


								////////////////  TRIMMING the WORD   ////////////
								word = word.toLowerCase();
								
								boolean t = false;
								for(int i=0;i<connectors.length;i++)
								{
									if(word.compareTo(connectors[i])==0)
									{
										t = true;
										System.out.println( word +" IS A CONNECTOR WORD, COULD BE PRESENT ON EVERY WEBPAGE, THINK B4 U SEARCH");
										break;
									}
								}
								if(t)
									break;





								if(word.compareTo("stacks")==0 || word.compareTo("structures")==0 || word.compareTo("applications")==0 )
								{
									word = word.substring(0,word.length()-1);
								}


								for(char c : pun)
								{
									if(word.indexOf(c) == -1)
									{
										continue;
									}
									else
									{
										word = word.replace(c,' ');
									}
								}

								word = word.trim();

				
								
				


								if(word.compareTo("stacks")==0 || word.compareTo("structures")==0 || word.compareTo("applications")==0 )
								{
									word = word.substring(0,word.length()-1);
								}


              					/////////////////     TRIMMING DONE     ///////////////



								

								if(ipi.getPagesWhichContainWord(word)==null)
								{
									

									System.out.println("No webpage contains word " + word);
									System.out.println();
									break;
								}

								String wd[] = new String[1];

								wd[0] = word;

								
								Node<PageEntry> pg = ipi.getPagesWhichContainWord(word).set.head();

								Vector<SearchResult> vxy = new Vector<SearchResult>();

								Vector<String> vstr = new Vector<String>();

								while(pg!=null)
								{
									if(vstr.contains(pg.element.getWebPageName()))
									{
										pg = pg.next;
										continue;
									}

									vstr.add(pg.element.getWebPageName());
									SearchResult sr = new SearchResult(pg.element.getWebPageName(),pg.element.getRelevanceOfPage(wd,false,ipi.hsTable));
									vxy.add(sr);
									pg = pg.next;
								}

								Collections.sort(vxy);

								for(int i=vxy.size()-1;i>=0;i--)
								{
									
									
									if(i==0)
									{
										System.out.print( vxy.get(i).pageName() );
									}
									else
										System.out.print( vxy.get(i).pageName() + ", ");
									

								}
								System.out.println("");
								System.out.println();
								break;








							}	
							else if(actionMessage.substring(0,31).compareTo("queryFindPositionsOfWordInAPage")==0)
							{

								
								String word = "";

								int n = 32;
						
								while( n < actionMessage.length() && ((int)actionMessage.charAt(n)!=32))
								{
						    
									word = word + actionMessage.charAt(n);
									n++;
								}

								n++;

								 if(n>=actionMessage.length() || (int)actionMessage.charAt(n)==32)
						 		{
						 			System.out.println("FATAL ERROR : INVALID INPUT STRING");
						 			break;
						 		}

						 		String webpagex = actionMessage.substring(n,actionMessage.length());

						 		


						 		////////////////  TRIMMING the WORD   ////////////
								word =word.toLowerCase();
								
								boolean t = false;
								for(int i=0;i<connectors.length;i++)
								{
									if(word.compareTo(connectors[i])==0)
									{
										t = true;
										System.out.println( word +" IS A CONNECTOR WORD, WOULD BE PRESENT ON EVERY WEBPAGE, THINK B4 U SEARCH");
										break;
									}
								}
								if(t)
									break;





								if(word.compareTo("stacks")==0 || word.compareTo("structures")==0 || word.compareTo("applications")==0 )
								{
									word = word.substring(0,word.length()-1);
								}



								for(char c : pun)
								{
									if(word.indexOf(c) == -1)
									{
										continue;
									}
									else
									{
										word = word.replace(c,' ');
									}
								}

								word = word.trim();



              					/////////////////     TRIMMING DONE     ///////////////
								
								int u = ipi.hsTable.getHashIndex(word);
								
								try
								{
									Node<WordEntry> headx = ipi.hsTable.myHashTable[u].getWordEntries().head();

								}
								catch(Exception e)
								{
									System.out.println(" YOUR SEARCH QUERY WORD IS NOT AVAILABLE ");
									//System.out.println(e.getMessage());
									break;
								}
								Node<WordEntry> headx = ipi.hsTable.myHashTable[u].getWordEntries().head();

								boolean b = false;
								boolean q = false;
								int k = 0;
								while(headx!=null)
								{
									
									if(headx.element.getWord().compareTo(word)==0)
									{	
										b = true;

										
										Node<Position> pos = headx.element.getAllPositionsForThisWord().head();
										while(pos!=null)
										{
											
											if(pos.element.getPageEntry().getWebPageName().compareTo(webpagex)==0)
											{
												
												q = true;
												if(k==0)
												{
													System.out.print(pos.element.getWordIndex());
													k++;
												}
												else
												{
													System.out.print(", " + pos.element.getWordIndex());
												}
											}
											pos = pos.next;
										}
										if(!q)
										{
											System.out.println("No such webpage available");
										}
										else
											System.out.println("");
										break;
									}
									headx = headx.next;
								}

								if(!b)
									System.out.println("No webpage contains word " + word);


								System.out.println();

								break;



							}
							else if(actionMessage.substring(0,34).compareTo("queryFindPagesWhichContainAllWords")==0)
							{
								Vector<String> vectstr = new Vector<String>();
								Vector<SearchResult> searchResults = new Vector<SearchResult>();
								
								int n = 35;

								String temp = "";

								while( n < actionMessage.length() ) 
								{
									if((int)actionMessage.charAt(n)!=32)
									{
										temp = temp + actionMessage.charAt(n);
									}

									else if((int)actionMessage.charAt(n)==32)
									{
										
										vectstr.add(temp);
										temp = "";

									}

									if(n==actionMessage.length()-1)
									{
										
										vectstr.add(temp);

									}

									n++;
								}

								int l = vectstr.size();

								
								String x = new String();

								x = vectstr.get(0);


								MySet<PageEntry> pageEntrySet = ipi.getPagesWhichContainWord(x);



								for(int i=1; i<l; i++)
								{
									x = vectstr.get(i);
									pageEntrySet = pageEntrySet.intersection(ipi.getPagesWhichContainWord(x));
								}

								Node<PageEntry> pen = pageEntrySet.set.head();

								String strr[] = new String[l];

								for(int i=0;i<l;i++)
								{
									strr[i] = new String();
									strr[i] = vectstr.get(i);
								}

								
								Vector<String> pqr = new Vector<String>();





								while(pen!=null)
								{
									if(pqr.contains(pen.element.getWebPageName()))
									{
										pen = pen.next;
										continue;
									}

									pqr.add(pen.element.getWebPageName());

									SearchResult sr = new SearchResult(pen.element.getWebPageName(),pen.element.getRelevanceOfPage( strr , false, ipi.hsTable ));
									searchResults.add(sr);
									pen = pen.next;
								}

								Collections.sort(searchResults);

								l = searchResults.size();

								for(int i=l-1;i>=0;i--)
								{

									if(i==l-1)
									{
										System.out.print(searchResults.get(i).pageName() +  " ( " +  searchResults.get(i).getRelevance()    + " )  ");
										continue;
									}
									System.out.print("  ,"+searchResults.get(i).pageName() +  " ( " +  searchResults.get(i).getRelevance()    + " )  ");
								}
								System.out.println("");
								System.out.println();
								break;

								// *********************** THE END ****************************** //

							}
							else if(actionMessage.substring(0,41).compareTo("queryFindPagesWhichContainAnyOfTheseWords")==0)
							{


								Vector<String> vectstr = new Vector<String>();
								Vector<SearchResult> searchResults = new Vector<SearchResult>();
								
								int n = 42;

								String temp = "";

								while( n < actionMessage.length() ) 
								{
									if((int)actionMessage.charAt(n)!=32)
									{
										temp = temp + actionMessage.charAt(n);
									}

									else if((int)actionMessage.charAt(n)==32)
									{
										
										vectstr.add(temp);
										temp = "";

									}

									if(n==actionMessage.length()-1)
									{
										
										vectstr.add(temp);

									}


									n++;
								}

								int l = vectstr.size();

								

								String x = new String();

								x = vectstr.get(0);

								
								

								MySet<PageEntry> pageEntrySet = ipi.getPagesWhichContainWord(x);

								String strr[] = new String[l];

								for(int i=0;i<l;i++)
								{
									strr[i] = new String();
									strr[i] = vectstr.get(i);
								}



								for(int i=1; i<l; i++)
								{
									x = vectstr.get(i);
									pageEntrySet = pageEntrySet.union(ipi.getPagesWhichContainWord(x));
								}

								Node<PageEntry> pen = pageEntrySet.set.head();

								Vector<String> pqr = new Vector<String>();


								while(pen!=null)
								{
									
									if(pqr.contains(pen.element.getWebPageName()))
									{
										pen = pen.next;
										continue;
									}

									pqr.add(pen.element.getWebPageName());


									SearchResult sr = new SearchResult(pen.element.getWebPageName(),pen.element.getRelevanceOfPage( strr , false ,ipi.hsTable));
									searchResults.add(sr);
									pen = pen.next;
								}

								Collections.sort(searchResults);

								l = searchResults.size();

								for(int i=l-1;i>=0;i--)
								{

									if(i==l-1)
									{
										System.out.print(searchResults.get(i).pageName() +  " ( " +  searchResults.get(i).getRelevance()    + " )  ");
										continue;
									}
									System.out.print("  ,"+searchResults.get(i).pageName() +  " ( " +  searchResults.get(i).getRelevance()    + " )  ");
								}
								System.out.println("");
								System.out.println();

								break;
								// *********************** THE END ****************************** //



							}
							else if(actionMessage.substring(0,32).compareTo("queryFindPagesWhichContainPhrase")==0)
							{



								Vector<String> vectstr = new Vector<String>();
								Vector<SearchResult> searchResults = new Vector<SearchResult>();
								

								int n = 33;

								String temp = "";

								while( n < actionMessage.length() ) 
								{
									if((int)actionMessage.charAt(n)!=32)
									{
										temp = temp + actionMessage.charAt(n);
									}

									else if((int)actionMessage.charAt(n)==32)
									{
										
										vectstr.add(temp);
										temp = "";

									}

									if(n==actionMessage.length()-1)
									{
										
										vectstr.add(temp);

									}


									n++;
								}

								int l = vectstr.size();


								

								String strr[] = new String[l];

								for(int i=0;i<l;i++)
								{
									strr[i] = new String();
									strr[i] = vectstr.get(i);
								}



								MySet<PageEntry> pageEntrySet = ipi.getPagesWhichContainPhrase(strr);





								

								Node<PageEntry> pen = pageEntrySet.set.head();

								Vector<String> pqr = new Vector<String>();


								while(pen!=null)
								{
									
									if(pqr.contains(pen.element.getWebPageName()))
									{
										pen = pen.next;
										continue;
									}

									pqr.add(pen.element.getWebPageName());


									SearchResult sr = new SearchResult(pen.element.getWebPageName(),pen.element.getRelevanceOfPage( strr , true, ipi.hsTable ));
									searchResults.add(sr);
									pen = pen.next;
								}

								Collections.sort(searchResults);

								l = searchResults.size();

								for(int i=l-1;i>=0;i--)
								{

									if(i==l-1)
									{
										System.out.print(searchResults.get(i).pageName() +  " ( " +  searchResults.get(i).getRelevance()    + " )  ");
										continue;
									}
									System.out.print("  ,"+searchResults.get(i).pageName() +  " ( " +  searchResults.get(i).getRelevance()    + " )  ");
								}
								System.out.println("");
								System.out.println();

								break;
								// *********************** THE END ****************************** //





							}

							break;


			default:
							System.out.println("FATAL ERROR : INVALID INPUT STRING");
							System.out.println();
							break;
																





		}
	}
}
