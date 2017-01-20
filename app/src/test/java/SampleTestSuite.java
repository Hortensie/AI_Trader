import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Piotr on 2017-01-06.
 * Main point of test running
 * It contain tests list for AI_Trader unit testing
 */


@RunWith(Suite.class)
@Suite.SuiteClasses({MainActivityTest.class,
                     xApiConnectionLoginTest.class,
                     xApiLoginMockito.class,
                     SymbolRecordTest.class,
                     SymbolTest.class,
                     PeriodSelectorTest.class


})
public class SampleTestSuite {
}
