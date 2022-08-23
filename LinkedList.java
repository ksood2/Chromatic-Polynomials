/**
 * @author Kyler Sood, University of Minnesota, Twin Cities
 * CSCI 4041H Spring 2022 with Dr. James Moen
 * sood0027@umn.edu
 * Honors Project - Graphs, Chromatic Polynomials
 */
// Singly linked list wrapper class
public class LinkedList<T> {
    private Node<T> start;
    public LinkedList(){
    }
    public LinkedList(T value){
        start = new Node<T>(value, null);
    }
    public LinkedList(T[] values){
        start = new Node<T>(values[values.length-1], null);
        for(int i=values.length-2; i>=0; i--){
            start = new Node<T>(values[i], start);
        }
    }
    public Node<T> getStart(){
        return start;
    }
    public void setStart(Node<T> start){
        this.start = start;
    }
    public void add(T value){
        Node<T> temp = start;
        while(temp.getNext()!=null){
            temp = temp.getNext();
        }
        temp.setNext(new Node<>(value, null));
    }
    public boolean add(T value, int i){
        /**
         * @param i index to add at starting at 0. Following method conclusion, value will be at index i
         */
        if(i<0 || value==null){
            return false;
        }
        Node<T> addNode = new Node<>(value, null);
        Node<T> temp = start;
        int j = 0;
        while(j<i){
            temp = temp.getNext();
            j++;
            if(temp==null){
                return false;
            }
        }
        Node<T> temp2 = temp.getNext();
        addNode.setNext(temp2);
        temp.setNext(addNode);
        return true;
    }
    public T remove(int i){
        if(i < 0 || start==null){
            return null;
        }
        if(i==0){
            T x = start.getValue();
            start = start.getNext();
            return x;
        }
        int j = 0;
        Node<T> temp = start;
        while(j < i-1){
            temp = temp.getNext();
            j++;
            if(temp == null){
                return null;
            }
        }
        Node<T> remove = temp.getNext();
        temp.setNext(remove.getNext());
        return remove.getValue();
    }
    public boolean remove(T value){
        if(start==null || value==null) {
            return false;
        }
        Node<T> trailer = start;
        Node<T> ptr = start.getNext();
        if(trailer.getValue().equals(value)){
            start = ptr;
            return true;
        }
        while(ptr != null){
            if(ptr.getValue().equals(value)){
                trailer.setNext(ptr.getNext());
                return true;
            }
            trailer = trailer.getNext();
            ptr = ptr.getNext();
        }
        return false;
    }
    public void removeAll(T value){
        if(start==null || value==null) {
            return;
        }
        Node<T> trailer = start;
        Node<T> ptr = start.getNext();
        if(trailer.getValue().equals(value)){
            start = ptr;
        }
        while(ptr != null){
            if(ptr.getValue().equals(value)){
                trailer.setNext(ptr.getNext());
            }
            trailer = trailer.getNext();
            ptr = ptr.getNext();
        }
    }
    public T get(int i){
        if(i < 0 || start==null){
            return null;
        }
        int j = 0;
        Node<T> temp = start;
        while(j<i){
            j++;
            temp = temp.getNext();
            if(temp==null){
                return null;
            }
        }
        return temp.getValue();
    }
    public String toString(){
        String s = "";
        Node<T> temp = start;
        while(temp!=null){
            s+=temp.getValue().toString();
            if(temp.getNext()!=null)
                s+=" ";
            temp = temp.getNext();
        }
        return s;
    }
    public void display(){
        Node<T> temp = start;
        while(temp!=null){
            if(temp.getNext()!=null)
                System.out.print(temp.getValue()+" -> ");
            else
                System.out.print(temp.getValue());
            temp = temp.getNext();
        }
    }
    public void mergeNoDuplicates(LinkedList<T> other){
        // Assume that the lists do not already have duplicates.
        Node<T> temp = start;
        while(temp.getNext()!=null){
            temp = temp.getNext();
        }
        Node<T> temp2 = other.start;
        while(temp2!=null){
            if(!contains(temp2.getValue())){
                temp.setNext(new Node<>(temp2.getValue(), null));
                temp = temp.getNext();
            }
            temp2 = temp2.getNext();
        }
    }
    public boolean contains(T value){
        Node<T> temp = start;
        while(temp!=null){
            if(temp.getValue().equals(value)){
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }
    public void replace(T toReplace, T replaceWith){
        Node<T> temp = start;
        while(temp!=null && toReplace!=null && replaceWith!=null){
            if(temp.getValue()!=null && temp.getValue().equals(toReplace)){
                temp.setValue(replaceWith);
            }
            temp = temp.getNext();
        }
    }
    public LinkedList<T> clone(){
        Node<T> temp = new Node(start.getValue(), null);
        Node<T> clone = temp;
        Node<T> temp2 = start.getNext();
        while(temp2!=null){
            temp.setNext(new Node(temp2.getValue(), null));
            temp2 = temp2.getNext();
            temp = temp.getNext();
        }
        LinkedList<T> cloneList = new LinkedList<>();
        cloneList.setStart(clone);
        return cloneList;
    }
    public void removeDuplicatesOf(T remove){
        if(start==null || remove==null) {
            return;
        }
        int i = 0;
        Node<T> trailer = start;
        Node<T> ptr = start.getNext();
        if(trailer.getValue().equals(remove)){
            start = ptr;
            i++;
        }
        while(ptr != null){
            if(ptr.getValue().equals(remove)){
                if(i==0){
                    i++;
                }
                else{
                    trailer.setNext(ptr.getNext());
                }
            }
            trailer = trailer.getNext();
            ptr = ptr.getNext();
        }
    }
}
