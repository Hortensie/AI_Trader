package aiViewer;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hortensie.ai_trader.aiViewer.model.CandleEntryRecord;
import com.hortensie.ai_trader.aiViewer.model.FireBaseCandleData;
import com.hortensie.ai_trader.aiViewer.model.FireBaseCandleDataInterface;
import com.hortensie.ai_trader.dbTester.view.Fragments.CardContentFragment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observers.TestObserver;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

/**
 * Created by szczesny on 2017-03-27.
 * unit test for aiViewer & FireBaseCandleData class
 */

public class FireBaseCandleDataTest {

    private FireBaseCandleDataInterface model;
    private DatabaseReference reference;
    //private FirebaseDatabase firebase;
    //private FireBaseCandleData sut;
    private CandleEntryRecord record;

    @Before
    public void setUp()
    {

        reference=mock(DatabaseReference.class);
        model=mock(FireBaseCandleDataInterface.class);
        //firebase=mock(FirebaseDatabase.class);
        record=mock(CandleEntryRecord.class);
    }

    @Test
    public void getCandleListFromFireBaseShouldReturnObservable (){

        //given
        TestObserver<List<CandleEntryRecord>> observer = new TestObserver<>();
        final List<CandleEntryRecord> list = new LinkedList<>();
        list.add(record);

        Observable<List<CandleEntryRecord>> observable = Observable.create(new ObservableOnSubscribe<List<CandleEntryRecord>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<CandleEntryRecord>> e) throws Exception {
                e.onNext(list);
                e.onComplete();
            }
        });

        //when
        when(model.getCandleListFromFireBase(anyString(),anyString())).thenReturn(observable);
        observable.subscribe(observer);
        model.getCandleListFromFireBase("symbol","period");



        //then
        observer.assertComplete();
        observer.assertNoErrors();

    }

}
