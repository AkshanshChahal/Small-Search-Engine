public class Node<E>
     {
     	public E element;

     	public Node<E> next;
     	
     	public Node(E e,Node<E> n)
     	{
     		element = e;
     		next = n;
     	}
          public Node()
          {

          }
          public E getElement() {return element;}
          public Node<E> getNext() {return next;}
          public void setNext(Node<E> n) {next = n;}

     } 
