package JSON;

//necessary imports
import utils.LinkedList;
import utils.unlimitedStack;

/**
 * This class provides a validator object with the intent to validate JSON Strings or LinkedLists that represent a JSON String.
 * It's methods take care of the validation of atomic values, arrays and objects.
 * 
 * @author André Benquerer, Nº 24633.
 * @author Diogo Larangeira, Nº 24993.
 */
public class JsonValidator {
    
    /**
     * Checks if a string is represents a valid JSON.
     * A String represents a valid JSON when it follows the correct JSON syntax rules.
     * 
     * @param str The String to be validated.
     * @return true if the JSON is valid, false otherwise.
     */
    public boolean isValid(String str){
        //create a list with str from parameter
        LinkedList list = new LinkedList(str);
        //validate the list created
        return isValid(list);
    }
    
    /**
     * Checks if a LinkedList represents a valid JSON.
     * A LinkedList represents a valid JSON when its items follow the correct JSON syntax rules .
     * 
     * @param list The LinkedList to be validated.
     * @return true if the JSON is valid, false otherwise.
     */
    public boolean isValid(LinkedList list) {
        if (list.isEmpty()) { //for empty lists
            return false;//empty json's will be considered as invalid
        }
        if (list.isSingle()) { //for list with only one node
            return list.peekFirst().isAtomic(); //checks if the element in the head node is atomic, making the list valid if true, false otherwise
            //now, for lists with more than one node.
        } else if (list.peekFirst().isArrOpen() && list.peekLast().isArrClose()) { //to be an array, the head of the list has to be a '[', and the tail a ']'
            if (list.getSize() == 2) { //if the previous condition is true, a list size of 2 would indicate an empty array
                return true; //since empty arrays are valid in json, returns true
            }
            //if there are more than 2 nodes in the list, and the head and tail indicate an array
            //the list must be checked as an array
            return isValidArray(list);
        } else if (list.peekFirst().isObjOpen() && list.peekLast().isObjClose()) { //to be an object, the head of the list has to be a '{', and the tail a '}'
            if (list.getSize() == 2) {//if the previous condition is true, a list size of 2 would indicate an empty object
                return true; //since empty objects are valid in json, returns true
            }
            //if there are more than 2 nodes in the list, and the head and tail indicate an object
            //the list must be checked as an object
            return isValidObj(list);
        } else {
            //if its not an atomic value, nor an array, nor an object, it cant be a valid json
            return false;
        }
    }

    /**
     * Checks if a Linked list represents a syntax-correct JSON array.
     * Arrays in JSON will always start and end with box-brackets, and it's elements have to be comma-separated, and be valid JSONs as well.
     * 
     * @param list The List that will be checked with JSON array syntax as criteria.
     * @return true if the list represents a JSON array, false otherwise.
     */
    public boolean isValidArray(LinkedList list) {
        //create an auxiliary list that will store each segment of the array (comma-separated blocks)
        LinkedList newList = new LinkedList();
        //remove first and last nodes of the main list, since they were already checked in the method that called this
        list.removeFirst();
        list.removeLast();
        //create counter for arrays and object within the list.
        int arrays = 0;
        int objs = 0;
        //create the stack that will store each one of the newly created lists
        unlimitedStack aux = new unlimitedStack();
        //iterate the main list
        while (list.hasNext()) {
            if (list.peekCurrent().isArrOpen()) { //if an array start is found 
                arrays++; //increse the inner arrays counter
                newList.addLast(list.peekCurrent()); //add that token to the auxiliary list
                list.next(); //contiue the iteration
            } else if (list.peekCurrent().isArrClose()) { //if an array end is found 
                arrays--; //decrease the inner arrays counter
                newList.addLast(list.peekCurrent()); //add that token to the auxiliary list
                list.next(); //contiue the iteration
            } else if (list.peekCurrent().isObjOpen()) { //if an object start is found 
                objs++; //increse the inner objects counter
                newList.addLast(list.peekCurrent()); //add that token to the auxiliary list
                list.next(); //contiue the iteration
            } else if (list.peekCurrent().isObjClose()) { //if an object end is found 
                objs--; //decrease the inner objects counter
                newList.addLast(list.peekCurrent());//add that token to the auxiliary list
                list.next(); //contiue the iteration
            } else if (list.peekCurrent().isComma() && arrays == 0 && objs == 0) {//if a comma thats not in any inner array or object is found, the block is complete
                aux.push(newList); //push the created list (block) to the stack
                newList = new LinkedList(); //reset the auxiliary list
                list.next(); //contiue the iteration
            } else { //for non-special tokens
                newList.addLast(list.peekCurrent()); //add token to list
                list.next(); //continue iteration
            }
        }
        //add the last created list to the stack
        aux.push(newList);
        //start the validator object
        JsonValidator valid = new JsonValidator();
        // "go through" the stack, validating each block created from the main list
        // this will create the indirect recursion that will eventually reach (or not) an atomic value
        while (!aux.isEmpty()) {
            if (!valid.isValid((LinkedList) aux.pop())) {
                return false;
            }
        }
        //if there were no blocks that returned false, than the list is valid.
        return true;
    }

    /**
     * Checks if a Linked list represents a syntax-correct JSON object.
     * Objects in JSON will always start and end with box-brackets, and it's elements are comma-separated key-value pairs, that have a valid String as a key, and a valid JSON as a value.
     * 
     * @param list The List that will be checked with JSON object syntax as criteria.
     * @return true if the list represents a JSON object, false otherwise.
     */
    public boolean isValidObj(LinkedList list) {
        //create an auxiliary list that will store each segment of the object (comma-separated blocks)
        LinkedList newList = new LinkedList();
        //remove first and last nodes of the main list, since they were already checked in the method that called this
        list.removeFirst();
        list.removeLast();
        //create counter for arrays and object within the list.
        int arrays = 0;
        int objs = 0;
        //create the stack that will store each one of the newly created lists
        unlimitedStack aux = new unlimitedStack();
        //iterate the main list
        while (list.hasNext()) {
            if (list.peekCurrent().isArrOpen()) {//if an array start is found 
                arrays++; //increse the inner arrays counter
                newList.addLast(list.peekCurrent()); //add that token to the auxiliary list
                list.next(); //contiue the iteration
            } else if (list.peekCurrent().isArrClose()) { //if an array end is found 
                arrays--; //decrease the inner arrays counter
                newList.addLast(list.peekCurrent()); //add that token to the auxiliary list
                list.next(); //contiue the iteration
            } else if (list.peekCurrent().isObjOpen()) { //if an object start is found 
                objs++; //increse the inner objects counter
                newList.addLast(list.peekCurrent()); //add that token to the auxiliary list
                list.next(); //contiue the iteration
            } else if (list.peekCurrent().isObjClose()) { //if an object end is found 
                objs--; //decrease the inner objects counter
                newList.addLast(list.peekCurrent()); //add that token to the auxiliary list
                list.next(); //contiue the iteration
            } else if (list.peekCurrent().isComma() && arrays == 0 && objs == 0) { //if a comma thats not in any inner array or object is found, the block is complete
                aux.push(newList); //push the created list (block) to the stack
                newList = new LinkedList(); //add that token to the auxiliary list
                list.next(); //contiue the iteration
            } else {
                newList.addLast(list.peekCurrent()); //add that token to the auxiliary list
                list.next(); //contiue the iteration
            }
        }
        //add the last created list to the stack
        aux.push(newList);
        //start the validator object
        JsonValidator validator = new JsonValidator();
        // "go through" the stack, validating each block created from the main list
        // this will create the indirect recursion that will eventually reach (or not) an atomic value
        while (!aux.isEmpty()) {
            //block will now reference the list that needs to be referenced
            LinkedList block = (LinkedList) aux.pop();
            if (!block.peekFirst().isString()) {//in an object the first token of a block HAS to be a string (key)
                return false; //return false if theres no key found
            } else { //after a key, a colon is needed
                if (!block.peekNext().isColon()) {
                    return false; //no colon: false object
                } else {
                    block.removeFirst(); //removes the key from the block
                    block.removeFirst(); //removes the colon from the block 
                    if (!validator.isValid(block)) { //now, with the key and colon removed, the block can be validated.
                        return false;
                    }
                }
            }
        }
        //if there were no blocks that returned false, than the list is valid.
        return true; 
    }
    

    //JSON validation with ArrayList
//    public boolean validateArray(LinkedList list) {
//        LinkedList newList = new LinkedList();
//        list.removeFirst();
//        list.removeLast();
//        int arrays = 0;
//        int objs = 0;
//        ArrayList<LinkedList> aux = new ArrayList<>();
//        while (list.hasNext()) {
//            if (list.peekCurrent().isArrOpen()) {
//                arrays++;
//                newList.addLast(list.peekCurrent());
//                list.next();
//            } else if (list.peekCurrent().isArrClose()) {
//                arrays--;
//                newList.addLast(list.peekCurrent());
//                list.next();
//            } else if (list.peekCurrent().isObjOpen()) {
//                objs++;
//                newList.addLast(list.peekCurrent());
//                list.next();
//            } else if (list.peekCurrent().isObjClose()) {
//                objs--;
//                newList.addLast(list.peekCurrent());
//                list.next();
//            } else if (list.peekCurrent().isComma() && arrays == 0 && objs == 0) {
//                aux.add(newList);
//                newList = new LinkedList();
//                list.next();
//            } else {
//                newList.addLast(list.peekCurrent());
//                list.next();
//            }
//        }
//        aux.add(newList);
//        JsonValidator valid = new JsonValidator();
//        for (LinkedList lista : aux) {
//            if (!valid.isValid(lista)) {
//                return false;
//            }
//        }
//        return true;
//    }
    //Object validation with ArrayList
//    public boolean validateObj(LinkedList list){
//        LinkedList newList = new LinkedList();
//        list.removeFirst();
//        list.removeLast();
//        int arrays = 0;
//        int objs = 0;
//        ArrayList<LinkedList> aux = new ArrayList<>();
//        while (list.hasNext()) {
//            if (list.peekCurrent().isArrOpen()) {
//                arrays++;
//                newList.addLast(list.peekCurrent());
//                list.next();
//            } else if (list.peekCurrent().isArrClose()) {
//                arrays--;
//                newList.addLast(list.peekCurrent());
//                list.next();
//            } else if (list.peekCurrent().isObjOpen()) {
//                objs++;
//                newList.addLast(list.peekCurrent());
//                list.next();
//            } else if (list.peekCurrent().isObjClose()) {
//                objs--;
//                newList.addLast(list.peekCurrent());
//                list.next();
//            } else if (list.peekCurrent().isComma() && arrays == 0 && objs == 0) {
//                aux.add(newList);
//                newList = new LinkedList();
//                list.next();
//            } else {
//                newList.addLast(list.peekCurrent());
//                list.next();
//            }
//        }
//        aux.add(newList);
//        JsonValidator valid = new JsonValidator();
//        for (LinkedList bloco : aux) {
//            if(!bloco.peekFirst().isString()){
//                return false;
//            }else{
//                if(!bloco.peekNext().isColon()){
//                    return false;
//                }else{
//                    bloco.removeFirst();
//                    bloco.removeFirst();
//                    if(!valid.isValid(bloco)){
//                        return false;
//                    }
//                }
//            }
//        }
//        return true;
//    }
    
}
