public class Position
{
	private PageEntry page;
	private int wordPostion;
	private int wordPostionx;
	

	
	public Position(PageEntry p, int wordIndex, int wordIndexx)
	{
		this.page = p;
		this.wordPostion = wordIndex;
		this.wordPostionx = wordIndexx;
	}


	public PageEntry getPageEntry() { return page; }

	public int getWordIndex() { return wordPostion; }

	public int getWI() { return wordPostionx; }

	


















}
