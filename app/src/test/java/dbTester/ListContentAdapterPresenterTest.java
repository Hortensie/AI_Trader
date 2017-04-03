package dbTester;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.hortensie.ai_trader.R;
import com.hortensie.ai_trader.aiViewer.model.CandleEntryRecord;
import com.hortensie.ai_trader.dbTester.presenter.ListContentAdapterPresenter;
import com.hortensie.ai_trader.xAPI.ListSymbolRecord;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by szczesny on 2017-04-03.
 * unit test for ListContentAdapterPresenter class under dbTester package
 */

public class ListContentAdapterPresenterTest {

    private String symbol;
    private ListContentAdapterPresenter sut;
    private Context context;
    private Observable<List<ListSymbolRecord>> recordList;
    private ListSymbolRecord singleEntry;
    private Resources resources;
    private TypedArray array;


    @Before
    public void setUp(){
        context=mock(Context.class);
        singleEntry=mock(ListSymbolRecord.class);
        resources=mock(Resources.class);
        array=mock(TypedArray.class);
    }

    @Test
    public void symbolSetterGetter(){
        //given
        final List<ListSymbolRecord> list = new LinkedList<>();
        list.add(singleEntry);

        recordList = Observable.create(new ObservableOnSubscribe<List<ListSymbolRecord>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<ListSymbolRecord>> e) throws Exception {


                e.onNext(list);
                e.onComplete();
            }
        });

        //indirect inputs
        //when
        when(context.getResources()).thenReturn(resources);
        when(resources.obtainTypedArray(R.array.place_avator)).thenReturn(array);

        //then
        sut=new ListContentAdapterPresenter(context,recordList);
        //assert that ListContentAdapterPresenter object is not null
        Assert.assertNotNull(sut);

        ListContentAdapterPresenter.setTemp_symbol("input");
        Assert.assertEquals("input",ListContentAdapterPresenter.getTemp_symbol());


    }

}
