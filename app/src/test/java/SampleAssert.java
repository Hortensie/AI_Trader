import junit.framework.Assert;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
/**
 * Created by Piotr on 2017-01-06.
 * sample tests for assert methods
 */

public class SampleAssert {

    @Test
    public void assert_boolean_conditions() throws Exception
    {
        Assert.assertTrue(true);
        Assert.assertFalse(false);
    }

    @Test
    public void assert_null_and_not_null_object_values() throws Exception
    {
        Object object = null;
        Assert.assertNull(object);

        object=new String("String value");
        Assert.assertNotNull(object);
    }

    @Test
    public void assert_equals_test() throws Exception
    {
        Integer anInteger = 5;
        Integer anotherInteger = 5;
        assertEquals(anInteger,anotherInteger);
    }
    @Test
    public void assert_not_same_test() throws Exception
    {
        Integer anInt = new Integer("5");
        Integer anotherInteger  = new Integer("5");
        assertNotSame(anInt,anotherInteger);
    }
    @Test
    public void assert_same_test() throws Exception
    {
        Integer anInt = new Integer("5");
        Integer anotherInt = anInt;
        assertSame(anInt,anotherInt);
    }

    @Test(expected=RuntimeException.class)
    public void test_exception_condition() {
        throw new RuntimeException();
    }


}
