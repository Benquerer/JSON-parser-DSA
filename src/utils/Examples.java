package utils;

/**
 * 
 * This class is composed of constant strings, all of which are examples of valid or invalid JSON that a user could try to use.****
 * The examples are made to make the testing of the JsonValidator class easier.
 * 
 * Examples ending in 1-4 will be valid, and those ending in 5-8 invalid
 * 
 * @author André Benquerer, Nº 24633.
 * @author Diogo Larangeira, Nº 24993.
 */
public class Examples {
    
    //Examples of valid atomic JSONs  
    /**
     * Valid Atomic JSON: STRING value.
     */
    public static final String ATOM1 = "\"Atomico\"";
    /**
     * Valid Atomic JSON: BOOLEAN value.
     */
    public static final String ATOM2 = "false";
    /**
     * Valid Atomic JSON: NUMERIC value.
     */
    public static final String ATOM3 = "3.5e-23";
    /**
     * Valid Atomic JSON: NUMERIC value.
     */
    public static final String ATOM4 = "-78675.790087";  
    
    //Examples of invalid atomic JSONs
    /**
     * Invalid Atomic JSON: STRING was not closed.
     */
    public static final String ATOM5 = "\"Atomico";
    /**
     * Invalid Atomic JSON: BOOLEAN was written wrongly.
     */
    public static final String ATOM6 = "falsse";
    /**
     * Invalid Atomic JSON: More than one value outside an array or object.
     */
    public static final String ATOM7 = "true,1";
    /**
     * Invalid Atomic JSON: More than one value outside an array or object.
     */
    public static final String ATOM8 = "-786758 790087";
    
    
    //Examples of valid JSON arrays
    /**
     * Valid JSON Array: empty array.
     */
    public static final String ARR1 = "[]";
    /**
     * Valid JSON Array: 2 atomic values inside an array
     */
    public static final String ARR2 = "[2,\"Ola\"]";
    /**
     * Valid JSON Array: an array with atomic values an an array within
     */
    public static final String ARR3 = "[1,[2,\"Ola\"],[3,\"Mundo\"],4,\"EDA\"]";
    /**
     * Valid JSON Array: an array with atomic values, an an array and an object within
     */
    public static final String ARR4 = "[\"1\",{\"Nome\":\"Andre\", \"Nome2\":\"Diogo\"}, 123] ";
    
    //Examples of invalid JSON arrays
    /**
     * Invalid JSON Array: Array with no values, but not empty.
     */
    public static final String ARR5 = "[,]";
    /**
     * Invalid JSON Array: Array was not closed.
     */
    public static final String ARR6 = "[2,\"Ola\"";
    /**
     * Invalid JSON Array: Sequence of "value-comma-value" not respected between second and third values.
     */
    public static final String ARR7 = "[1,[2,\"Ola\"][3,\"Mundo\"],4,\"EDA\"]";
    /**
     * Invalid JSON Array: Array with no values, but not empty.
     */
    public static final String ARR8 = "[\"1\",{\"Nome\":\"Andre\", \"Nome2\"\"Diogo\"}, 123] ";
    
    
    //Examples of valid JSON objects
    /**
     * Valid JSON Object: Empty JSON object.
     */
    public static final String OBJ1 = "{}";
    /**
     * Valid JSON Object: Single key-value par.
     */
    public static final String OBJ2 = "{\"preco\":123}";
    /**
     * Valid JSON Object: An array inside a key-value par,
     */
    public static final String OBJ3 = "{\"nome\":[\"diogo\",\"andre\"]}";
    /**
     * Valid JSON Object: A complex object with object, arrays and atomic values,
     */
    public static final String OBJ4 = "{\"Ola\":\"Mundo\",\"computador\":{\"Intel\":true,\"Placa dedicada\":\"RTX 4060\"},\"umArray\":[[\"345345\",true,3],[4,5,6],6,{\"alunos\":[\"diogo\",\"andre\"]}]}";   
    
    //Examples of invalid JSON objects
    /**
     * Invalid JSON Object: After a valid JSON object, there is a "," and a atomic value, making the JSON invalid
     */
    public static final String OBJ5 = "{},true";
    /**
     * Invalid JSON Object: The value of the key-value par is not correct,
     */
    public static final String OBJ6 = "{\"preco\":\"123}";
    /**
     * Invalid JSON Object: The Array within the object is incorrect, thus making the whole JSON incorrect
     */
    public static final String OBJ7 = "{\"nome\":[\"diogo\"\"andre\"]}";
    /**
     * Invalid JSON Object: one of the objects inside the main object was not closed.
     */
    public static final String OBJ8 = "{\"Ola\":\"Mundo\",\"computador\":{\"Intel\":true,\"Placa dedicada\":\"RTX 4060\"},\"umArray\":[[\"345345\",true,3],[4,5,6],6,{\"alunos\":[\"diogo\",\"andre\"]]}";  
      
    
}
