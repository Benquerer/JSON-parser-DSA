package utils;

/**
 *
 * This class represents a stack with unlimited item capacity.
 * It allow the addition and removal of elements in a last-in, first-out method (LIFO).
 * Implements the Stack interface.
 * 
 * @author André Benquerer, Nº 24633.
 * @author Diogo Larangeira, Nº 24993.
 */
public class unlimitedStack implements Stack {
    
    /**
     * Represents the item in that is at the top of the stack, which will be affected by the pop() and peek() methods.
     */
    private Item top;
    
    /**
     * Constructor for an empty stack. 
     */
    public unlimitedStack() {
        top = null;
    }
    
    /**
     * Checks if the item at the top of the stack is "null", which would mean the stack is empty.
     * 
     * @return true if the top item is "null", false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return top == null;
    }
    
    /**
     * Adds a new item to the top of stack.
     * The passed in object will be the item's "data" attribute, and the "next" attribute,the current item at the top of the stack.
     * Updates the top of the stack to be the new item.
     * 
     * @param item The data for the item that will be pushed into the stack.
     */
    @Override
    public void push(Object item) {
        Item nItem = new Item();
        nItem.data = item;
        nItem.next = top;
        top = nItem;
    }
    /**
     * Retrieves the current item at top of the stack and returns it.
     * Updates the top to be the next one referenced by the retrieved item.
     * 
     * @return The Item at the top of the stack, or "null" if the stack is empty.
     */
    @Override
    public Object pop() {
        if (isEmpty()) {
            return null;
        }
        Object data = top.data;
        top = top.next;
        return data;
    }
    /**
     * Returns which one is the current item at the top of the stack without modifying the stack's structure.
     * 
     * @return What item is at the top of that, or null if the stack is empty
     */
    @Override
    public Object peek() {
        return isEmpty() ? null : top.data;
    }
    
    /**
     * This inner class represents the items that are stored into the unlimitedStack 
     */
    private class Item {
        /**
         * The data stored in the item.
         * Can be any type of object.
         */
        Object data;
        /**
         * Reference to the next item in the stack's sequence (Can be interpreted as the item bellow the current one).
         */
        Item next;
    }

}
