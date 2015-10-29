public class MyLinkedList<E>
{
     //------------nested node class----------------//
     /*private static class Node<E>
     {
     	private E element;

     	private Node<E> next;
     	
     	public Node(E e,Node<E> n)
     	{
     		element = e;
     		next = n;
     	}
          public E getElement() {return element;}
          public Node<E> getNext() {return next;}
          public void setNext(Node<E> n) {next = n;}

     }   //------end of Nested Node class-------------//*/



     //   Instance variables of the MyLinkedList 
     private Node<E> head = null;
     private Node<E> tail = null;
     private int size = 0;
     public MyLinkedList(){}      // constructs an initially empty list



     // access methods
     public int size() { return size; }
     public boolean isEmpty() { return size==0; }
     public Node<E> head()
     {
          return head;
     }
     
     public E first()
     {
          if(isEmpty()) return null;
          return head.getElement();
     }

     public E last()
     {
          if(isEmpty()) return null;
          return tail.getElement();
     }

     




     // update methods
     public void addFirst(E e)
     {
          head = new Node<>(e, head);
          if(size==0)
               tail = head;
          size++;
     }
     public void addLast(E e)
     {
          Node<E> newest = new Node<>(e, null);
          if(isEmpty())
               head = newest;
          else 
               tail.setNext(newest);
          tail = newest;
          size++;
     }
     public E removeFirst()
     {
          if(isEmpty()) return null;
          E answer = head.getElement();
          head = head.getNext();
          size--;
          if(size==0)
               tail = null;
          return answer;
     }

     public void delete(E e) 
     {
          if(e == head.getElement()){e = removeFirst(); return; }
          Node<E> p = new Node<>();
          Node<E> r = new Node<>();
          
    
          p = head;
    
          for(int i=1;i<size();i++)
          {
               r = p;
               p = p.next;
               
               if( p.element==e)
               {
                    r.next = p.next;
                    return;
               }
               //if(p.getNext()==null)
                 //   break;
          }    
    
          if(p.next==null)
          {
              System.out.println("Object to be deleted is not present");
          }    
          
     }

}
