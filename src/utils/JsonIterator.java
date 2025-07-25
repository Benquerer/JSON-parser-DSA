package utils;

//necessary imports
import java.util.ArrayList;

/**
 * 
 * This class provides an iterator for parsing JSON strings and extracting individual tokens (also of the String class).
 * The tokes are stored in an ArrayList to allow for future manipulation.
 * 
 * @author André Benquerer, Nº 24633.
 * @author Diogo Larangeira, Nº 24993.
 */
public class JsonIterator {
    
    /**
     * ArrayList in which the individual tokens extracted from the passed in string will be stored.
     */
    private final ArrayList<String> jsonArr; //aray that will store the substrings
    /**
     * An index that serves to represent which one is the current token to be referenced while iterating the "jsonArr" ArrayList.
     */
    private int current; //index to iterate jsonArr
    
    /**
     * Constructor for the JsonIterator object.
     * This constructor already iterates a passed in string, and stores its "tokens" into the jsonArr attribute.
     * @param str String to extract the tokens out of.
     */        
    public JsonIterator(String str) {
        //create array that will be stored in "jsonArr"
        ArrayList<String> arr = new ArrayList<>();
        //iterate each char of the string
        for (int i = 0; i < str.length(); i++) {          
            if(str.length()==1){ //if the string is only one char, add it to array and exit for loop
                arr.add(String.valueOf(str.charAt(i))); //add it to array 
                break; //exit for loop
            }
            if(!isSpace(String.valueOf(str.charAt(i)))){ //ignore spaces an only check chars that are significant
                if(isSpecial(String.valueOf(str.charAt(i)))){ //Check if the current char is considered special (array and object symbols, etc) - does not include ' " '
                    arr.add(String.valueOf(str.charAt(i))); //add it to the array
                }else{
                    String token=String.valueOf(str.charAt(i)); //create string that is built by the while loop
                    int it = 1; //secondary index (used for the jump when the token is created)
                    //iterate and build the token
                    //the fist condition garantees that the jump will not outbound the main string
                    //the second condition is the stop criteria that indicates the end of the token
                    while(!(i+it>=str.length()) && !isSpecial(String.valueOf(str.charAt(i+it)))){                
                        token+=str.charAt(i+it); //build the token
                        //if the following char is a quotation mark, break the while
                        if(isQuote(String.valueOf(str.charAt(i+it)))){ 
                            it++; //count the identified quotation mark
                            break;
                        }
                        it++;
//                        if (i+it>=str.length()) break;         // moved to while clause
                    }
                    arr.add(token); // add the created token
                    i+=it-1; //jump the main iterator to the end of the created token
                }
            }        
        }
        this.jsonArr = arr; //store the created array in the attribute
        this.current = 0; //inition current is always 0
    }
    
    /**
     * Checks if there are any left tokens in the jsonArr that have not been iterated by the iterator yet.
     * 
     * @return true if there are still tokens to be iterated, false otherwise.
     */
    public boolean hasNext(){ 
        //check if the current is in the last position
        return current != jsonArr.size();
    }
    
    /**
     * Retrieves the current token from the ArrayList, updating the "current" index, and returning the token retrieved.
     * 
     * @return The next token based on the "current" index.
     */
    public String next(){
        //store the current to be returned
        String a = jsonArr.get(current);
        //step the current pointer
        current++;
        //return the stored value
        return a;
    }
    
    /**
     * Checks what would be the next token in the iteration, without actually advancing the iterator.
     * 
     * @return The token after the current one.
     */
    public String peekNext(){
        //return the element next to the current
        return jsonArr.get(current+1);
    }
    
    /**
     * Checks if the passed in String represents a blank space.
     * 
     * @param str The String to be checked.
     * @return true if the parameter is a blank space, false otherwise.
     */
    private boolean isSpace(String str){
        //check for all kinds of space
        return str.equals(" ") || str.equals("\t") || str.equals("\n") || str.equals("\f") || str.equals("\r");
    }
    
    /**
     * Checks if the passed in String represents a character that is considered special in JSON.
     * 
     * @param str The String to be checked
     * @return true if the parameter is considered a "special" character in JSON.
     */
    private boolean isSpecial(String str){
        //check if the recieved string is inside the collection of special characters
        return "{}[],:".contains(str);
    }
    
    /**
     * Checks if the passed in String is a quote mark (").
     * 
     * @param str The String to be checked.
     * @return true if the String is a quote mark, false otherwise. 
     */
    private boolean isQuote(String str){
        //check if string equals a single cotation mark
        return "\"".contains(str);
    }
    
}
