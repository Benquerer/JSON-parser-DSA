package JSON;

/**
 * This class represents a single element in the JSON structure 
 * It provides methods to perform various verifications.
 * 
 * @author André Benquerer, Nº 24633.
 * @author Diogo Larangeira, Nº 24993.
 */
public class JsonElement {
    
    /**
     * The string represents a JSON element
     */
    final private String element; //store the json token

    /**
     * Constructs a JSON element from a passed in String.
     * 
     * @param element The String that represents the JSON element
     */
    public JsonElement(String element) {
        this.element = element; //store passed in element
    }

    /**
     * Gets the string in the "element" attribute.
     * 
     * @return the "element" string.
     */
    public String getElem() {
        return element; //return string storend in element constant
    }

    /**
     * Checks if the element represents a JSON boolean.
     * In JSON, booleans have to be written in lower case only.
     * 
     * @return true if the element is a boolean, false otherwise
     */
    public boolean isBool() {
        //compare the string with "true" or "false" and return accordingly
        return element.equals("true") || element.equals("false"); 
    }
    /**
     * Checks if the element represents an integer number in JSON.
     * 
     * @return true if the element is an integer, false otherwise.
     * @deprecated Use isNumber() instead. As any Integer can also be converted to a Double.
     */
    @Deprecated
    public boolean isInt() {
        try { //if the string can be parsed to Integer, than its a INT
            Integer.valueOf(element);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }
    /**
     * Checks if the element represents a floating point number in JSON.
     * 
     * @return true if the element is a floating point number, false otherwise.
     * @deprecated Use isNumber() instead. As any Integer can also be converted to a Double.
     */
    @Deprecated
    public boolean isReal() {
        try {//if the string can be parsed to Double, than its a floating point
            Double.valueOf(element);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }
    
    /**
     * Checks if the element represents a valid JSON number.
     * In JSON, numbers work pretty much like java. Integers, Floating point numbers and scientific notations are valid.
     * 
     * @return true if the element is a number, false otherwise.
     */
    public boolean isNumber() {
        try {//Since integers can also be parsed to double, if there is no exception in the parsing of the string, than its a number (int, float, etc)
            Double.valueOf(element);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    /**
     * Checks if the element represents an opening braces symbol ('{').
     * In JSON opening braces are used to indicate the start of an object.
     * 
     * @return true if the element is an opening brace, false otherwise.
     */
    public boolean isObjOpen() {
        //compare string to "{"
        return element.equals("{");
    }
    
    /**
     * Checks if the element represents a closing braces symbol ('}').
     * In JSON closing braces are used to indicate the end of an object.
     * 
     * @return true if the element is a closing brace, false otherwise.
     */
    public boolean isObjClose() {
        //compare string to "}"
        return element.equals("}");
    }

    /**
     * Checks if the element represents an opening brackets symbol ('[').
     * In JSON opening brackets are used to indicate the start of an array.
     * 
     * @return true if the element is an opening bracket, false otherwise
     */
    public boolean isArrOpen() {
        //compare string to "["
        return element.equals("[");
    }

    /**
     * Checks if the element represents a closing brackets symbol (']').
     * In JSON closing brackets are used to indicate the end of an array.
     * 
     * @return true if the element is a closing bracket, false otherwise
     */
    public boolean isArrClose() {
        //compare string to "]"
        return element.equals("]");
    }

    /**
     * Checks if the element represents a colon punctuation mark (':').
     * 
     * @return true if the element is a colon, false otherwise.
     */
    public boolean isColon() {
        //compare string to ":"
        return element.equals(":");
    }

    /**
     * Checks if the element represents a comma punctuation mark  (',').
     * 
     * @return true if the element is a comma, false otherwise
     */
    public boolean isComma() {
        //compare string to ","
        return element.equals(",");
    }
    /**
     * Checks if the element represents a null JSON value.
     * 
     * @return true if the element is null, false otherwise.
     */
    public boolean isNull(){
        //compare string to "null"
        return element.equals("null");
    }

    /**
     * Checks if the element represents a JSON string.
     * 
     * @return true if the element is a string, false otherwise.
     */
    public boolean isString() {
        if(element.length()==0){ //check if the string is empty
            throw new IndexOutOfBoundsException("Element is empty"); //throw exception if so
        }
        //a string must start and end with '"'
        if (element.charAt(0)!='"' || element.charAt(element.length()-1)!= '"'){
            return false; //if condition fails, then its not a string
        }
        //now, if a string does not have a value of anyother kind, it can be considered a string
        //this will prevent that a value such as a number passes as a string.
        return !isBool()  && !isNumber() && !isObjOpen() && !isObjClose() && !isArrOpen() && !isArrClose() && !isColon() && !isComma();
    }

    /**
     * Checks if the element represents an atomic JSON (string, boolean, number or null).
     * 
     * @return true if the element is atomic, false otherwise.
     */
    public boolean isAtomic() {
        //check for atomic json values
        return isString() || isBool() || isNumber() || isNull(); 
    }
        
    /**
     * Returns the string version of an JsonElement.
     * 
     * @return The string representation of the element.
     */
    @Override
    public String toString() {
        return element.toString();
    }

    /**
     * Indicates of what the type is the JSON element.
     * 
     * @return A string that indicates the JSON element type, or "unknown" if the token doesn't match any type.
     */
    public String type() {
        //give an indication to the type of element using the methods from this class
        if (isNumber()) {
            return "Number";
        } else if (isBool()) {
            return "Boolean";
        } else if (isArrOpen()) {
            return "Open Array";
        } else if (isArrClose()) {
            return "Closing Array";
        } else if (isObjOpen()) {
            return "Open Object";
        } else if (isObjClose()) {
            return "Closing Object";
        } else if (isColon()) {
            return "is colon";
        } else if (isComma()) {
            return "is comma";
        } else if (isString()) {
            return "is a string";
        } else {
            //not accounted for cases
            return "unknown";
        }
    }   

}


