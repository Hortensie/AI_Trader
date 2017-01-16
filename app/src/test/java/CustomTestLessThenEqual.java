import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/**
 * Created by Piotr on 2017-01-08.
 *
 */

public class CustomTestLessThenEqual<T extends Comparable<T>> extends BaseMatcher<Comparable<T>> {

    private final Comparable<T> expectedValue;

    @Override
    public boolean matches(Object item) {

        int compareTo = expectedValue.compareTo((T) item);
        return compareTo > -1;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" less then or equal(<=)" + expectedValue);

    }

    public CustomTestLessThenEqual(T expectedValue) {
        this.expectedValue = expectedValue;
    }

    @Factory
    public static <T extends Comparable<T>> Matcher<T> CustomTestLessThenEqual(T t)
    {

        return new CustomTestLessThenEqual(t);
    }

}
