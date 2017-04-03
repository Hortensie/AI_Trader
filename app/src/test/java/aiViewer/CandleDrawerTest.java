package aiViewer;

import com.github.mikephil.charting.data.CandleEntry;
import com.hortensie.ai_trader.aiViewer.model.CandleEntryRecord;
import com.hortensie.ai_trader.aiViewer.model.FireBaseCandleDataInterface;
import com.hortensie.ai_trader.aiViewer.presenter.CandleDrawer;
import com.hortensie.ai_trader.aiViewer.presenter.CandleDrawerInterface;
import com.hortensie.ai_trader.aiViewer.view.CandleViewInterface;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by szczesny on 2017-03-29.
 * Unit Test for CandleDrawer
 */

public class CandleDrawerTest {

    private CandleViewInterface view;
    private FireBaseCandleDataInterface model;
    private CandleEntryRecord record;
    private CandleEntry entry;
    private CandleDrawer drawer;
    private CandleDrawerInterface presenter;

    @Before
    public void setUp(){
        model=mock(FireBaseCandleDataInterface.class);
        view=mock(CandleViewInterface.class);
        record=mock(CandleEntryRecord.class);
        entry=mock(CandleEntry.class);
        drawer=new CandleDrawer(model,view);
    }

    @Test
    public void candleDrawerShouldExecuteModel(){
        reset(view);

        //given
        CandleDrawer presenter = new CandleDrawer(model,view);
        final List<CandleEntryRecord> list = new LinkedList<>();
        list.add(record);
        list.add(record);

        //when
        when(model.getCandleListFromFireBase(anyString(),anyString())).thenReturn(new Observable<List<CandleEntryRecord>>() {
            @Override
            protected void subscribeActual(Observer<? super List<CandleEntryRecord>> observer) {
                observer.onNext(list);
            }
        });

        //then

        presenter.showChartData("USD","label");
        model.getCandleListFromFireBase(anyString(),anyString());
    }

    @Test
    public void shouldConvertCandleEntryRecordToCandleEntry(){

        //given
        final List<CandleEntryRecord> inputList = new LinkedList<>();
        record.setHigh(10);
        record.setOpen(5);
        record.setClose(7);
        record.setLow(2);
        record.setX(1);
        record.setY(2);
        inputList.add(record);

        List<CandleEntry> finalList = new LinkedList<>();
        entry.setHigh(inputList.get(0).getHigh());
        entry.setOpen(inputList.get(0).getOpen());
        entry.setClose(inputList.get(0).getClose());
        entry.setLow(inputList.get(0).getLow());
        entry.setX(inputList.get(0).getX());
        entry.setY(inputList.get(0).getY());
        finalList.add(entry);

        //when

        //then
        drawer.convertListCandleEntryRecordToCandleEntry(inputList);
        Assert.assertNotNull(finalList);
        Assert.assertEquals(0, finalList.get(0).getHigh(),0);

    }

    @Test
    public void onCreateShouldCreateConnections(){



    }

}
