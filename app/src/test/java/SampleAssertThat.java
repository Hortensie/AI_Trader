import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

/**
 * Created by Piotr on 2017-01-06.
 * JUnit4 examples for AssertThat method
 */

public class SampleAssertThat {

    @Test
    public void test_matcher_behaviour() throws Exception{
        int myAge=30;
        assertThat(myAge,equalTo(30));
        assertThat(myAge,is(30));

        //partial match
        assertThat(myAge,not(equalTo(33)));
        assertThat(myAge,is(not(33)));
    }

    @Test
    public void verify_multiple_values() throws Exception
    {
        double marks = 100.00;

        assertThat(marks,either(is(100.00)).or(is(90.9)));
        assertThat(marks,both(not(99.99)).and(not(60.00)));
        assertThat(marks,anyOf(is(60.00),is(100.00),is(1.00),is(55.00),is(65.00)));
        assertThat(marks,not(anyOf(is(0.00),is(200.00))));
        assertThat(marks,not(allOf(is(1.00),is(100.00),is(30.00))));

    }

    @Test
    public void verify_collection_values() throws Exception {
        List<Double> salary = Arrays.asList(50.0, 200.0, 500.0);
        assertThat(salary, hasItem(50.00));
        assertThat(salary, hasItems(50.00, 200.00));
        assertThat(salary, not(hasItem(1.00)));
    }
    @Test
    public void verify_Strings() throws Exception {
        String myName = "John Jr Dale";
        assertThat(myName, startsWith("John"));
        assertThat(myName, endsWith("Dale"));
        assertThat(myName, containsString("Jr"));
    }

    @Test
    public void less_then_equal() throws Exception
    {

        int actualGoalScore = 2;
        int expectedGoalScore = 4;

        assertThat(actualGoalScore, new CustomTestLessThenEqual(expectedGoalScore));

    }
}
