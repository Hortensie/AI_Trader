package aiViewer;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hortensie.ai_trader.aiViewer.model.CandleEntryRecord;
import com.hortensie.ai_trader.aiViewer.model.FireBaseCandleData;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.mock;

/**
 * Created by szczesny on 2017-03-27.
 * unit test for aiViewer & FireBaseCandleData class
 */

public class FireBaseCandleDataTest {

    private DatabaseReference reference;
    private FirebaseDatabase firebase;
    private FireBaseCandleData sut;
    private CandleEntryRecord record;
    private DataSnapshot snapshot;


    @Before
    public void setUp()
    {
        snapshot=mock(DataSnapshot.class);
        reference=mock(DatabaseReference.class);
        firebase=mock(FirebaseDatabase.class);
        record=mock(CandleEntryRecord.class);
    }

    @Test
    public void constructorShouldInitFireBase(){

        //given
        FireBaseCandleData object = new FireBaseCandleData(reference,firebase);

        //when
        object.getCandleListFromFireBase("symbol","period");

        //then
        Assert.assertNotNull(object);
    }


    @Test
    public void getCandleListFromFireBaseShouldReturnObservable (){

        //given
        TestObserver<List<CandleEntryRecord>> observer = new TestObserver<>();
        final List<CandleEntryRecord> list = new LinkedList<>();
        list.add(record);
        sut = new FireBaseCandleData(reference,firebase);

        /*
        Observable<List<CandleEntryRecord>> observable1 = Observable.create(new ObservableOnSubscribe<List<CandleEntryRecord>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<CandleEntryRecord>> e) throws Exception {


                e.onNext(list);
                e.onComplete();
            }
        });
        */

        //when
        //when(model.getCandleListFromFireBase(anyString(),anyString())).thenReturn(observable1);
        //observable1.subscribe(observer);
        //model.getCandleListFromFireBase("symbol1","period1");
        //sut.getCandleListFromFireBase("symbol1","period1");

        Observable<List<CandleEntryRecord>> observable2 = sut.getCandleListFromFireBase("symbol1","period1");
        observable2.subscribe(observer);


        //then
        observable2.test().assertSubscribed();
        observable2.test().onNext(list);
        observable2.test().onComplete();
        Assert.assertNotNull(observable2);
        //observer.assertComplete();
        //observer.assertNoErrors();
        //observer.assertSubscribed();
        //observer.onNext(list);

    }

}
