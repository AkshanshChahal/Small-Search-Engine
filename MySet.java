public class MySet<E> 
{
	// Node object //
	
	  // an empty set
	
	public MyLinkedList<E> set;


	public MySet(MyLinkedList<E> e)  
	{
		set = e;
	}                      

	public MySet()
	{
		set = new MyLinkedList<>();
	}

	public MyLinkedList<E> getList()
	{
		return set; ///////////???????????
	}
	
	public boolean IsEmpty() { return set.isEmpty();}
	
	public boolean IsMember(E o)
	{
		Node<E> testnode = new Node<>();
		testnode = set.head();
		for(int i=0;i<set.size();i++)
		{
			if(testnode.element == o) { return true; }
			testnode = testnode.next;
		}
		return false;

	}

	public void addElement(E o)
	{
		set.addFirst(o);
	}

	public void Delete(E o) 
	{
		if(o==set.head()){ o = set.removeFirst(); return; }
		set.delete(o);
		return;
	}

	public MySet<E> union(MySet<E> a)
	{
		MySet<E> newset = new MySet<>();
		Node<E> testnode = new Node<>();
		testnode = set.head();
		for(int i=0;i<set.size();i++)
		{

			newset.set.addFirst(testnode.element);
			testnode = testnode.next;

		}


		testnode = a.set.head();
		for(int i=0;i<a.set.size();i++)
		{
			
			if(newset.IsMember(testnode.element))
			{
				testnode = testnode.next;
				continue;
			}
			newset.set.addFirst(testnode.element);
			testnode = testnode.next;

		}

		return newset;


	}


	public MySet<E> intersection(MySet<E> a)
	{
		MySet<E> newset = new MySet<>();
		Node<E> testnode = new Node<>();
		testnode = set.head();
		for(int i=0;i<set.size();i++)
		{
			if(a.IsMember(testnode.element))
			{
				newset.set.addFirst(testnode.element);
			}
			testnode = testnode.next;
		}

		return newset;


	}















	
}
