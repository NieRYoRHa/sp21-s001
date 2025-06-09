package deque;


import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>,Iterable<T>{
    private class LinkedListDequeIterator<T> implements Iterator<T>{
        Node Seer;
        public LinkedListDequeIterator(){
            Seer=Sentienl.next;
        }
        @Override
        public boolean hasNext() {
            return Seer!=Sentienl;
        }

        @Override
        public T next() {
            T returnItem =(T) Seer.item;
            Seer=Seer.next;
            return returnItem;
        }
    }



    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class Node{
        T item;
        Node prev;
        Node next;

        public Node(){
            item = null;
        }

        public Node(T t,Node p,Node n){
            item =t;
            prev=p;
            next=n;
        }
    }

    private final Node Sentienl;
    private int size;


    public LinkedListDeque(){
        Sentienl =new Node();
        Sentienl.prev=Sentienl;
        Sentienl.next=Sentienl;
        size =0;
    }

    @Override
    public void addFirst(T t){
        Node newNode=new Node(t,Sentienl,Sentienl.next);
        Sentienl.next.prev =newNode;
        Sentienl.next=newNode;
        size++;
    }

    @Override
    public void addLast(T t){
        Node newNode=new Node(t,Sentienl.prev,Sentienl);
        Sentienl.prev.next =newNode;
        Sentienl.prev=newNode;
        size++;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        if (this.isEmpty()) {
            return;
        }
        Node pNode =Sentienl;
        for(int i =0;i!=size;i++){
            pNode=pNode.next;
            System.out.print(pNode.item +" ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        if (size==0){
            return null;
        }
        T removeItems =Sentienl.next.item;
        Node newFirst =Sentienl.next.next;
        Sentienl.next=newFirst;
        newFirst.prev=Sentienl;
        size--;
        return removeItems;
    }

    @Override
    public T removeLast(){
        if (size==0){
            return null;
        }
        T removeItems =Sentienl.prev.item;
        Node newLast =Sentienl.prev.prev;
        Sentienl.prev=newLast;
        newLast.next=Sentienl;
        size--;
        return removeItems;
    }

    @Override
    public T get(int index){
        //when the Deque is empty
        if (isEmpty()){
            return null;
        }
        //when index>=size
        if (index>=size){
            return null;
        }
        return getIterative(index,Sentienl.next).item;

    }

    private Node getIterative(int x,Node n){
        if(x ==0){
            return n;
        }
        else {
            return getIterative(x-1,n.next);
        }
    }
}
