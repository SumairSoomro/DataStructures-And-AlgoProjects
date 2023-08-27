package app;

import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T> {

  private int size;
  private Node<T> head = null;

  public RecursiveList() {
    this.head = null;
    this.size = 0;
  }

  public RecursiveList(Node<T> first) {
    this.head = first;
    this.size = 1;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void insertAtBeginning(T elem) {
      //TODO: Implement this method.
     if(elem == null){
      throw new NullPointerException();
      }
      else{
        Node<T> newNode = new Node<>(elem, head);
        head = newNode;
        size++; 
      }
    
  }

  @Override
  public void insertAtEnd(T elem) {
      //TODO: Implement this method.
      if(elem == null){
        throw new NullPointerException();
        }
      Node<T> newNode = new Node<>(elem,null);
      if(head == null){
        head = newNode;
        size++;
      }
      else{
        
        Node<T> end = findNode(size-1, head);
        end.setNext(newNode);
        size++;

      }
      

  }
  

  @Override
  public void insertAt(int index, T elem) {
      //TODO: Implement this method.
      
      if (elem == null) {
        throw new NullPointerException(); 
      }
      else if (index > size || index < 0) {
        throw new IndexOutOfBoundsException(); 
      }
      else if(index == 0){
        insertAtBeginning(elem);  
      }
      else if(index == size){
        insertAtEnd(elem);
      }
      else{
        helpInsAt(index, head, elem);
    }
  }
  public void helpInsAt(int index, Node<T> head, T elem){
    if(index == 1){
    Node<T> newNode = new Node<>(elem, head.getNext());
    head.setNext(newNode);
    size++;
    }
    else{
    helpInsAt(index-1, head.getNext(), elem);
    
    }


  }

  @Override
  public T removeFirst() {
    if (isEmpty()){
      throw new IllegalStateException();
    }
      T removedItem = null;
     
      removedItem = head.getData();
      head = head.getNext();
      return removedItem;
  }

  @Override
  public T removeLast() {
    
      //TODO: Implement this method.
      
      if (isEmpty()) {
        throw new IllegalStateException(); 
      }
	  
	T removedItem = findNode(size-2, head).getData();  
	
	  if(size == 1) {
    	head = null;
    	size--;
    }
  else  {
    	
    Node<T> end = findNode(size-3, head); 
    end.setNext(null);
    size--; 
     
    }
    return removedItem;
    
  }

  @Override
  public T removeAt(int i) {
   //TODO: Implement this method.
   if(i >=size || i < 0){
    throw new IndexOutOfBoundsException(); 
   }
   if(i == 0){
      return removeFirst();
    }
    if(i == size-1){
      return removeLast();
    }
    else{
      Node<T> prev = findNode(i - 1, head);
      Node<T> current = prev.getNext();
      prev.setNext(current.getNext());
      size--;
      return current.getData();
    }
    
  }
  
 

  @Override
  public T getFirst() {
     //TODO: Implement this method.
     if (isEmpty()) {
      throw new IllegalStateException();
     }
     return head.getData();
  }

  @Override
  public T getLast() {
      
    if (isEmpty()) {
      throw new IllegalStateException();
     }
	
	if (size == 1) {
    	head = null;
    	size--;
    }
      //TODO: Implement this method.
      Node<T> lastNode = findNode(size-1, head);
      return lastNode.getData();
  }

  @Override
  public T getAt(int i) {
    //TODO: Implement this method.
    if (head.getNext().equals(null)) {
      throw new IndexOutOfBoundsException();
    }
    else if(head == null){
      throw new NullPointerException();
    }
    else if (i >= size || i < 0 ) {
		  throw new IndexOutOfBoundsException();   
    }
    else{
      return findNode(i, head).getData();
    }
  }



  @Override
  public void removeElement(T elem) {
      //TODO: Implement this method.
      if (elem == null) {
        throw new NullPointerException(); 
      }
    
      if(head.getData().equals(elem)){
    head = head.getNext();
    }
    else{
      helpRemEle(elem, head);
      if (!head.getData().equals(elem)) {
        throw new ItemNotFoundException(); 
      }
    }
  }
  public void helpRemEle(T elem,Node<T> head){
    if(head.getNext() == null){
      throw new ItemNotFoundException();
    }
    if(head.getNext().getData().equals(elem)){
      head = head.getNext();
    }
    else{
      helpRemEle( elem,head.getNext());
    }
  }
 

  @Override
  public int indexOf(T elem) {
     //TODO: Implement this method.

    return indexFinder(0, head, elem);
    
  } 
      


  @Override
  public boolean isEmpty() {
      //TODO: Implement this method.
    if(head == null){
      return true;
     }
    else{
      return false;
    }
  }


  public Iterator<T> iterator() {
    Iterator<T> iter = new LinkedNodeIterator<>(head);
      //TODO: Implement this method.

   return iter;
  }
  private final Node<T> findNode(int distance, Node<T> curr){
    if(distance == 0){
      return curr;
    }
    else{
      return findNode(distance-1,curr.getNext());
    }
  }
  private int indexFinder(int index,Node<T> head, T elem){
    if(elem == null){
      throw new NullPointerException();
    }
    if(head == null){
      return -1;
    }
    if(head.getData().equals(elem)){
      return index;
    }
    else{
      return indexFinder(++index, head.getNext(), elem );
    }
  } 
}
