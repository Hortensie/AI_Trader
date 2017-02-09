import com.hortensie.ai_trader.PeriodSelector;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static pro.xstore.api.message.codes.PERIOD_CODE.PERIOD_M1;

/**
 * Created by Piotr on 2017-01-20.
 */

public class PeriodSelectorTest {

    @Test
    public void validateStaticGetterSetterForPeriodSelector()
    {
        PeriodSelector.setTempValue(PERIOD_M1);
        assertEquals(PERIOD_M1,PeriodSelector.getTempValue());

    }
}
