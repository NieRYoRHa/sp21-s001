package deque;

public class ArrayDeque<T> implements Deque<T>{

    private T[] array;
    private int front;
    private int rear;
    private int size;
    private int baseLength;
    public ArrayDeque(){
        baseLength=8;
        array=(T[]) new Object[baseLength+1];
        front=0;
        rear=0;
    }

    @Override
    public void addFirst(T item) {
        front--;
        array[Math.floorMod(front,array.length)]=item;
        size++;
    }

    @Override
    public void addLast(T item) {
        rear++;
        array[rear]=item;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for(int i=front;i<0;i++){
            System.out.print(array[Math.floorMod(i,array.length)]+" ");
        }
        for(int i=1;i<=rear;i++){
            System.out.print(array[i]+" ");
        }

        System.out.println();

    }

    @Override
    public T removeFirst() {
        T returnItem =array[Math.floorMod(front,array.length)];
        front++;
        return returnItem;
    }

    @Override
    public T removeLast() {
        T returnItem =array[rear];
        rear--;
        return returnItem;
    }


    //0 -8 -7 -6 -5 -4 -3 -2 -1      f
    //0  1  2  3  4  5  6  7  8
    //   *  r              f  *
    //   2  3  4           0  1      index
    //when index=1 , f=-2 , return array[8]
    //when index=4 , f=-2 , return array[3]
    @Override
    public T get(int index) {
        //when array is empty
        if (isEmpty()){
            return null;
        }
        //when index>=size
        if (index>=size){
            return null;
        }
        if(index+front<0){
            return array[Math.floorMod(front+index,array.length)];
        }

        else return array[ front + index + 1];

    }
}
