import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Piotr on 2017-01-06.
 * Main point of test running
 * It contain tests list for AI_Trader unit testing
 */


@RunWith(Suite.class)
@Suite.SuiteClasses({MainActivityTest.class,
                     SymbolDataTest.class,
                     SymbolRecordTest.class,
                     SymbolTest.class,
                     PeriodSelectorTest.class,
                     FireBaseHandlerTest.class,
                     xApiConnectionLoginTest.class,
                     xApiConnectionLoginTest2.class,
                     FireBaseHandlerTestMock.class,
                     CandleChartDrawerTest.class


})
public class AiTraderTestSuite {
}
