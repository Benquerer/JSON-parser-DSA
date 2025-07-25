package utils;

//necessary imports
import JSON.JsonElement;
import java.util.Iterator;

/**
 * 
 * This class implements a iterable basic LinkedList structure, that can store ONLY JsonElements objects as it's Item's data.
 * It only provides the needed methods to be used in this JAVA-JSON project, not implementing some of the common LinkedList 
 * methods (E.g. contains(), remove(Object obj), and others). This decision was made to minimize the amount of unused code.
 * 
 * @author André Benquerer, Nº 24633.
 * @author Diogo Larangeira, Nº 24993.
 */
public class LinkedList implements List, Iterator<Object> {
    //class implements the iterator interface to get the next and has next methods
    
    /**
     * Reference to the first element in the list.
     */
    private Item head; //pointer to the start of the list
    
    /**
     * Reference to the last element in the list.
     */
    private Item tail; //pointer to the end of the list
    
    /**
     * References the current item during an iteration (by default, references the head).
     */
    private Item current; //pointer that indicates the current item during an iteration
    
    /**
     * Represents the total number of items in the list (the list's size).
     */
    private int size; //total number of items in the list
    
    
    /**
     * Constructs an empty Linked
     */
    public LinkedList() {
        this.head = null; // head is null for empty lists
        this.tail = null; // tails is null for empty lists
        this.current = head; //current will always start at the same spot as head
        this.size = 0; // size is 0 for empty lists
    }

    /**
     * Constructs a LinkedList parsing the passed in string with a JsonIterator and storing all the tokens in the items of the list.
     * @see utils.JsonIterator
     * 
     * @param str The String that will be parsed.
     */
    public LinkedList(String str) {
        this(); //creates an empty list
        //create an iterator object that extracts substrings from the passed in string
        JsonIterator iterator = new JsonIterator(str); 
        //add all substrings as JsonElements to the list
        while(iterator.hasNext()){
            this.addLast(new JsonElement(iterator.next()));
        }
    }
    
//    public LinkedList(LinkedList list) {
//        this.head = null;
//        this.tail = null;
//        this.current = head;
//        while(list.hasNext()){
//            addLast(list.next());
//        }
//    }
      
    /**
     * Checks if there are any items in the head of the list, which would indicate whether the list is empty or not.
     * 
     * @return true if there is an item in the head, false if the head is null
     */
    @Override
    public boolean isEmpty() {
        return head == null; //null at head pointer indicates empty list
    }
    
    /**
     * Adds an Item to the start of the list, updating the references accordingly and the size of the list
     * 
     * @param o JsonElement to be stored in the item's data.
     */
    @Override
    public void addFirst(JsonElement o) {
        //item to be stored in
        Item item = new Item(); 
        //the data (alwasys JsonElemet) for the item
        item.data = o;
        //for now, its next will be null
        item.next = null;
        //now depending on list state, we manage the pointers
        if (head == null) { //for a empty list, the item becomes the head and the tail, its next will stay null
            head = item; 
            tail = item;
        } else { //add item at the start
            //set its next to be the previous head
            item.next = head;
            //set it as the new head
            head = item;
        }
        //after adding an item, we increase the size of the list
        size++;
    }
    
    /**
     * Adds an Item to the end of the list, updating the references accordingly and the size of the list.
     * 
     * @param o JsonElement to be stored in the item's data.
     */
    @Override
    public void addLast(JsonElement o) {
        //item to be stored in
        Item item = new Item();
        //the data (alwasys JsonElemet) for the item
        item.data = o;
        //for now, its next will be null
        item.next = null;
        //now depending on list state, we manage the pointers
        if (head == null) { //for a empty list, the item becomes the head and the tail, its next will stay null
            head = item;
            tail = item;
        } else { //add item at the end
            tail.next = item; // the last item's next is now the added item
            tail = item; // the added item becomes the tail
        }
        current = head; //garantees that the current stays at head
        size++; //at last, increse the since
    }
    
    /**
     * Removes the first item in the list, setting the head to be the removed item's next item.
     * If an item was removed, the size of the list is updated.
     * If the list is empty, this method will do nothing.
     */
    public void removeFirst(){
        //only does anything if the list is not empty
        if(head!=null){
            //if its a single list, then turn the list empty by setting head and tail to point to null
            if(head==tail){
                head = tail = null;
                current = head; //current stays at head
            }else{ //for lists with more than 1 item
                //the head becomes the secont item
                head = head.next; 
                //point current to new head
                current = head;
            }
            size--; //size is decreased after item removal
        }    
    }
    
    /**
     * Removes the last item in the list, by finding the second to last item and making it the tail.
     * If an item was removed, the size of the list is updated.
     * If the list is empty, this method will do nothing
     */
    public void removeLast(){
        //only does anything if the list is not empty
        if(head!=null){
            //if its a single list, then turn the list empty by setting head and tail to point to null
            if(head==tail){
                head = tail = null;
                current = head;//current stays at head
            }else{//for lists with more than 1 item
                //iterator starts at list head
                Item i = head; 
                //iterate the list looking for the  second to last item
                while(i.next!=tail){
                    i=i.next;
                }
                tail = i; //set the second to last item as the new tail
                tail.next = null; //set second to last (now new tail) next pointer to null
            }
            size--; //decrease list size
        }        
    }
    
    /**
     * Peeks at the first item in the list, returning it's data. 
     * 
     * @return The data of the item that is first on the list, or null if the list has no items.
     */
    @Override
    public JsonElement peekFirst() {
        //return what is the JsonElement at the start of the list
        return isEmpty() ? null : head.data; 
    }
    
    /**
     * Peeks at the last item in the list, returning it's data. 
     * 
     * @return The data of the item that is last on the list, or null if the list has no items.
     */
    @Override
    public JsonElement peekLast() {
        //return what is the JsonElement at the end of the list
        return isEmpty() ? null : tail.data;
    }
    
    /**
     * Checks if there are any left Items in the list that have not been iterated by yet.
     * 
     * @return true if there are still Items to be iterated, false otherwise.
     */
    @Override
    public boolean hasNext() {
        //return if the current is still pointing at an valid item
        return current!=null;
    }

    /**
     * Checks whether the list has only one Item or not, by comparing the "head" and "tail" references.
     * @return true if the list has only one item, false otherwise
     */
    public boolean isSingle(){
        //head and tail pointing to the same item will indicate a single list
        return head==tail;
    }
    
    /**
     * Retrieves the JsonElement data of the current Item, updating the "current" reference, and returning the data retrieved.
     * 
     * @return The next token based on the "current" index.
     */
    public JsonElement next() {    
        //store the JsonElement in the current item of the iteration 
        JsonElement c = current.data;
        //step the current pointer
        current = current.next;
        //return stored item
        return c;
    }

    /**
     * Peeks what would be the next JsonElement data of the item of the iteration, without actually advancing the iteration.
     *
     * @return The JsonElement stored in the would be "current" item's data
     */
    public JsonElement peekNext() {
        //return what is the next JsonElement without moving the current pointer 
        if(current.next==null){
            return null;
        }
        return current.next.data;
    }
    
    /**
     * Peeks at JsonElement data of the item referenced by the "current" reference.
     * 
     * @return The JsonElement stored in the current item's data.
     */
    public JsonElement peekCurrent() {
        //get current's data, without stepping the current, as would a .next()
        return current.data;
    }
    
    /**
     * Resets the "current" attribute back to the head of the list, making so that further iterations start at the beginning of the list again.
     */
    public void resetCurrent(){
        //reset current back to head
        current = head;
    }
    
    /**
     * Clears the whole list by setting the references head, tail, and current to null, and it's size back to 0.
     */
    public void cleanList(){
        //set the list back to an empty state like in the default constructor
        head = null;
        tail = null;
        current = null;
        size = 0;
    }
    
    /**
     * Gets the total number of items the Linked List, as it is currently.
     * 
     * @return The number of items in the list.
     */
    public int getSize(){
        //return amount of items in the list
        return size;
    }
    
    /**
     * This class represents the items that compose the LinkedList.
     * The Item data HAS to be a JsonElement object.
     */
    private class Item {
        /**
         * The JsonElement object stored in the item's data.
         */
        JsonElement data; //the data of this list's items will always be a JsonElement.
        /**
         * Reference to the next item in the LinkedList.
         */
        Item next;

    }
    
}
