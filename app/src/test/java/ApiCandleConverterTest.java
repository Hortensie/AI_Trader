import com.github.mikephil.charting.data.CandleEntry;
import com.vaadin.polymer.demo.client.sampler.ai_trader.ApiCandleConverter;
import com.vaadin.polymer.demo.client.sampler.ai_trader.ChartRangeInfo;
import com.vaadin.polymer.demo.client.sampler.ai_trader.SymbolData;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import pro.xstore.api.message.records.RateInfoRecord;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Piotr on 2017-01-27.
 * unit test for class that convert APi records to CandleEntry records (Required by CandleDrawer)
 */

public class ApiCandleConverterTest {

    @Test
    public void validateSaveApiRecordsToCandleEntryList(){

        //arrange
        ChartRangeInfo chartRangeInfoMock = mock(ChartRangeInfo.class);
        RateInfoRecord rateElement = mock(RateInfoRecord.class);
        ApiCandleConverter sut = new  ApiCandleConverter();
            //creating candle list
        List<CandleEntry> data = new LinkedList<>();
            //creating mock RateInfoList
        List<RateInfoRecord> rateList = new LinkedList<>();
        rateElement.setCtm(66);
        rateElement.setOpen(20);
        rateList.add(rateElement);
        SymbolData symbolData = new SymbolData(rateList.get(0).getCtm(),rateList.get(0).getVol(),chartRangeInfoMock);
        //Assert.assertEquals(66, symbolData.getTime(),0);
        data.add(new CandleEntry(0,1,1,1,1,symbolData));
        Assert.assertEquals(0, rateElement.getOpen(),0);
        sut.saveApiRecordsToCandleEntryList(rateList,chartRangeInfoMock);

        Assert.assertNotNull(rateList);
        Assert.assertThat(rateList,hasItem(rateElement));
        Assert.assertEquals(1, data.size());
        Assert.assertNotNull(data.get(0).getData());
        verify(rateElement).setCtm(66);
        verify(rateElement).setOpen(20);

    }
}
