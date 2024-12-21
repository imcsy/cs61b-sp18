import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {

    /** Performs a few arbitrary tests to see if the product method is correct */

    @Test
    public void testProduct() {
        for(int i = 0; i<500; i+=1){
            assertTrue(i + " not euqal", Flik.isSameNumber(i, i));
            assertFalse(Flik.isSameNumber(i, i+1));
        }
    }

}
