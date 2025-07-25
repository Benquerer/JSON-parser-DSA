package utils;

/**
 * 
 * This interface is used to implement variations of a data structure that works with the LIFO methodology.
 * 
 * @author André Benquerer, Nº 24633.
 * @author Diogo Larangeira, Nº 24993.
 */
public interface Stack {
    boolean isEmpty(); 
    void push(Object item); 
    public Object pop(); 
    public Object peek();
}
