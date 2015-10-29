import java.util.Collections;
import java.util.Comparator;
import java.awt.*;

public class SearchResult implements Comparable<SearchResult>
{

	String wbpname;
	float value;

	public SearchResult(String str, float vl)
	{
		wbpname =  str;
		value = vl;
	}

	public static class SearchResultByPosition implements Comparator <SearchResult>
	{
		public int compare(SearchResult r1, SearchResult r2)
		{
			return r1.compareTo(r2);
		}
	}

	public String pageName(){ return wbpname; }

	public float getRelevance(){ return value; }

	public int compareTo(SearchResult otherObject)
	{
		return this.value > otherObject.value ? 1 : (this.value < otherObject.value ? -1 : 0 );
	}





}
