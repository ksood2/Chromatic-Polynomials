/**
 * @author Kyler Sood, University of Minnesota, Twin Cities
 * CSCI 4041H Spring 2022 with Dr. James Moen
 * sood0027@umn.edu
 * Honors Project - Graphs, Chromatic Polynomials
 */
// Node class for singly linked list implementation
public class Node<T>{
    private T value;
    private Node<T> next;
    public Node(){
    }
    public Node(T value, Node<T> next){
        this.value = value;
        this.next = next;
    }
    public T getValue(){
        return this.value;
    }
    public Node<T> getNext(){
        return next;
    }
    public void setValue(T value){
        this.value = value;
    }
    public void setNext(Node<T> next){
        this.next = next;
    }
}