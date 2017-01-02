import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Piotr on 2017-01-02.
 * sample Junit4 class
 */

public class localUnitTest {


        @Test
        public void mainActivityTest() {

            assertThat(4, is(4));
        }


}
