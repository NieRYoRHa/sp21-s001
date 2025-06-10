package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>,Iterable<T>{

    private T[] array;
    private int front;
    private int rearNext;
    private int size;

    public ArrayDeque(){

        array=(T[]) new Object[8];
        front=0;
        rearNext =0;
    }

    @Override
    public void addFirst(T item) {
        //deque full?
        if (size >= array.length) {
        resize();
        }
        //move -> fill
        frontForward();
        array[front]=item;
        size++;
    }

    private void frontForward(){
        front--;
        if (front<0){
        front=Math.floorMod(front,array.length);
        }
    }

    private void frontBackward(){
        front++;
        if (front>=array.length){
            front=Math.floorMod(front,array.length);
        }
    }



    @Override
    public void addLast(T item) {
    //deque full?
        if (size >= array.length) {
            resize();
        }
    // fill - >move
        array[rearNext]=item;
        rearBackward();
        size++;
    }
    private void rearForward(){
        rearNext--;
        if (rearNext <0){
            rearNext =Math.floorMod(rearNext,array.length);
        }
    }

    private void rearBackward(){
        rearNext++;
        if (rearNext >=array.length){
            rearNext =Math.floorMod(rearNext,array.length);
        }
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
        for(T i:this){
        System.out.print(i+" ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        //if deque is null , return null
        if(size==0){
            return null;
        }

        if(size<=array.length*0.25){
            resize();
        }

            T returnItem =array[front];
            frontBackward();
            size--;
            return returnItem;

    }

    @Override
    public T removeLast() {
        //if deque is null , return null
        if(size==0){
            return null;
        }

        if(size<=array.length*0.25){
            resize();
        }
        rearForward();
        T returnItem =array[rearNext];
        size--;
        return returnItem;
    }

    private void resize(){
        int newbaseLength=2*size;
        T[] newArray=(T[]) new Object[newbaseLength];
        for(int i = 0; i<Math.min(array.length,newbaseLength); i++){
            newArray[i]=get(i);
        }
        front=0;
        rearNext=size;
        array=newArray;
    }
    //0  1  2  3  4  5  6  7
    //*  *  r           f  *
    //2  3  4           0  1      index
    //when index=1 , f=7 , return array[8]
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
        return array[Math.floorMod(front+index,array.length)];
    }

    private class ArrayDequeIterator<T> implements Iterator<T>{
        private int Seer;

        public ArrayDequeIterator(){
        Seer=0;
        }
        @Override
        public boolean hasNext() {
            return Seer<size;
        }

        @Override
        public T next() {
            T returnItem=(T) get(Seer);
            Seer+=1;
            return returnItem;
        }
    }
    public Iterator iterator(){
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof ArrayDeque) {
            ArrayDeque otherArrayDeque = (ArrayDeque) o;
            for(int i =0;i<size;i++){
                if (this.get(i)!=otherArrayDeque.get(i)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}