package utils;

//necessary imports
import JSON.JsonElement;

/**
 * 
 * This interface is used to implement variations of a data structure that works as a Linked List.
 * A Linked Lists that implements this interface has to have JsonElements as it's Item's data.
 * 
 * @author André Benquerer, Nº 24633.
 * @author Diogo Larangeira, Nº 24993.
 */
public interface List {
    
    public boolean isEmpty();
    public void addFirst(JsonElement o); 
    public void addLast(JsonElement o);   
    public JsonElement peekFirst();
    public JsonElement peekLast();
}
