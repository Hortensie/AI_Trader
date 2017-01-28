import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Piotr on 2017-01-06.
 * Main point of test running
 * It contain tests list for AI_Trader unit testing
 */


@RunWith(Suite.class)
@Suite.SuiteClasses({
                     SymbolDataTest.class,
                     SymbolRecordTest.class,
                     SymbolTest.class,
                     PeriodSelectorTest.class,
                     FireBaseHandlerTest.class,
                     CandleChartDrawerTest.class,
                     ChartRangeInfoTest.class,
                     xApiRangeDataLoaderTest.class,
                     CalendarSelectorTest.class,
                     xApiConnectionLoginTest.class,
                     ApiCandleConverterTest.class,
                     MainActivityRobolectricTest.class,
                     xApiConnectionLoginRobolectricTest.class,
                     xApiSymbolLoaderTest.class
                     //MainOfflineTest.class


})
public class AiTraderTestSuite {
}
