package UnitTests;

//necessary imports
import JSON.JsonValidator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.Examples;
import utils.LinkedList;

/**
 * This class contains JUnit tests methods for testing the JSON validation of various strings (many available in the Examples class) using the validator in the JsonValidator class.
 * @see utils.Examples
 * @see JSON.JsonValidator
 * 
 * @author André Benquerer, Nº 24633
 * @author Diogo Larangeira, Nº 24993
 */
public class ValidationTests {
    
    /**
     * The JSON validator that is used in the tests.
     */
    private final JsonValidator validator;
    
    /**
     * The String that will be using in the tests.
     */
    private String str;
    
    /**
     * Initializes the ValidationTests object.
     * The string to be validated needs to be indicated in each test.
     */
    public ValidationTests() {
        this.validator = new JsonValidator();
        this.str = "";
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    
    //Testing for valid atomic JSONs
    @Test
    public void testAtomic1(){
        str =  Examples.ATOM1;
        LinkedList list = new LinkedList(str);
        assertEquals(true, validator.isValid(list));     
    }
    @Test
    public void testAtomic2(){
        str =  Examples.ATOM2;
        LinkedList list = new LinkedList(str);
        assertEquals(true, validator.isValid(list));     
    }
    @Test
    public void testAtomic3(){
        str =  Examples.ATOM3;
        LinkedList list = new LinkedList(str);
        assertEquals(true, validator.isValid(list));     
    }
    @Test
    public void testAtomic4(){
        str =  Examples.ATOM4;
        LinkedList list = new LinkedList(str);
        assertEquals(true, validator.isValid(list));     
    }
    //Testing invalid atomic JSONs
    @Test
    public void testAtomic5(){
        str =  Examples.ATOM5;
        LinkedList list = new LinkedList(str);
        assertEquals(false, validator.isValid(list));     
    }
    @Test
    public void testAtomic6(){
        str =  Examples.ATOM6;
        LinkedList list = new LinkedList(str);
        assertEquals(false, validator.isValid(list));     
    }
    @Test
    public void testAtomic7(){
        str =  Examples.ATOM7;
        LinkedList list = new LinkedList(str);
        assertEquals(false, validator.isValid(list));     
    }
    @Test
    public void testAtomic8(){
        str =  Examples.ATOM8;
        LinkedList list = new LinkedList(str);
        assertEquals(false, validator.isValid(list));     
    }
    
    
    //Testing valid JSON arrays
    @Test
    public void testArr1(){
        str =  Examples.ARR1;
        LinkedList list = new LinkedList(str);
        assertEquals(true, validator.isValid(list)); 
    }
    @Test
    public void testArr2(){
        str =  Examples.ARR2;
        LinkedList list = new LinkedList(str);
        assertEquals(true, validator.isValid(list)); 
    }
    @Test
    public void testArr3(){
        str =  Examples.ARR3;
        LinkedList list = new LinkedList(str);
        assertEquals(true, validator.isValid(list)); 
    }
    @Test
    public void testArr4(){
        str =  Examples.ARR4;
        LinkedList list = new LinkedList(str);
        assertEquals(true, validator.isValid(list)); 
    }
    //Testing invalid JSON arrays
    @Test
    public void testArr5(){
        str =  Examples.ARR5;
        LinkedList list = new LinkedList(str);
        assertEquals(false, validator.isValid(list)); 
    }
    @Test
    public void testArr6(){
        str =  Examples.ARR6;
        LinkedList list = new LinkedList(str);
        assertEquals(false, validator.isValid(list)); 
    }
    @Test
    public void testArr7(){
        str =  Examples.ARR7;
        LinkedList list = new LinkedList(str);
        assertEquals(false, validator.isValid(list)); 
    }
    @Test
    public void testArr8(){
        str =  Examples.ARR8;
        LinkedList list = new LinkedList(str);
        assertEquals(false, validator.isValid(list)); 
    }
    
    
    
    //Testing valid JSON objects
    @Test
    public void testObj1(){
        str =  Examples.OBJ1;
        LinkedList list = new LinkedList(str);
        assertEquals(true, validator.isValid(list)); 
    }
    @Test
    public void testObj2(){
        str =  Examples.OBJ2;
        LinkedList list = new LinkedList(str);
        assertEquals(true, validator.isValid(list)); 
    }
    @Test
    public void testObj3(){
        str =  Examples.OBJ3;
        LinkedList list = new LinkedList(str);
        assertEquals(true, validator.isValid(list)); 
    }
    @Test
    public void testObj4(){
        str =  Examples.OBJ4;
        LinkedList list = new LinkedList(str);
        assertEquals(true, validator.isValid(list)); 
    }
    //Testing invalid JSON objects
    @Test
    public void testObj5(){
        str =  Examples.OBJ5;
        LinkedList list = new LinkedList(str);
        assertEquals(false, validator.isValid(list)); 
    }
    @Test
    public void testObj6(){
        str =  Examples.OBJ6;
        LinkedList list = new LinkedList(str);
        assertEquals(false, validator.isValid(list)); 
    }
    @Test
    public void testObj7(){
        str =  Examples.OBJ7;
        LinkedList list = new LinkedList(str);
        assertEquals(false, validator.isValid(list)); 
    }
    @Test
    public void testObj8(){
        str =  Examples.OBJ7;
        LinkedList list = new LinkedList(str);
        assertEquals(false, validator.isValid(list)); 
    }
    
}
