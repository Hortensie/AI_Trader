package dbTester;

import com.hortensie.ai_trader.dbTester.model.FireBaseModelInterface;
import com.hortensie.ai_trader.dbTester.presenter.DetailActivity;
import com.hortensie.ai_trader.dbTester.view.DetailActivityViewInterface;
import com.hortensie.ai_trader.xAPI.ListSymbolRecord;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by szczesny on 2017-03-13.
 * RxJava2 unit test for DetailActivity
 */

public class DetailActivityTest {

    private DetailActivityViewInterface viewInterface;
    private FireBaseModelInterface fireBaseModelInterface;
    private ListSymbolRecord listSymbolRecord;

    @Before
    public void setUp(){

        viewInterface=mock(DetailActivityViewInterface.class);
        fireBaseModelInterface=mock(FireBaseModelInterface.class);
        listSymbolRecord=mock(ListSymbolRecord.class);
    }

    @Test
    public void shouldExecuteModelGetMethod(){

        //given
        DetailActivity presenter = new DetailActivity(viewInterface,fireBaseModelInterface);

        final List<ListSymbolRecord> list = new LinkedList<>();
        list.add(listSymbolRecord);
        list.add(listSymbolRecord);

        //when
        when(fireBaseModelInterface.getSymbolListFromFireBase("DemoSymbols")).thenReturn(new Observable<List<ListSymbolRecord>>() {
            @Override
            protected void subscribeActual(Observer<? super List<ListSymbolRecord>> observer) {
                observer.onNext(list);
            }
        });

         presenter.showSymbolDetails();

        //then
        //fireBaseModelInterface.getSymbolListFromFireBase("ListSymbolRecords").test().onNext(list);
        //fireBaseModelInterface.getSymbolListFromFireBase("ListSymbolRecords").test().assertSubscribed();

    }

}
