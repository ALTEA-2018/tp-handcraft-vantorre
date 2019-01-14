import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HelloTest{

    @Test
    public void testGetMessage(){
        assertEquals("Hello World", new Hello().getMessage());
    }

}
