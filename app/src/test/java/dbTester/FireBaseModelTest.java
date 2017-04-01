package dbTester;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hortensie.ai_trader.aiViewer.model.CandleEntryRecord;
import com.hortensie.ai_trader.aiViewer.model.FireBaseCandleData;
import com.hortensie.ai_trader.dbTester.model.FireBaseModel;
import com.hortensie.ai_trader.xAPI.ListSymbolRecord;
import com.hortensie.ai_trader.xAPI.SymbolRecord;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;

/**
 * Created by szczesny on 2017-02-27.
 *
 */

public class FireBaseModelTest {

    private DatabaseReference reference;
    private FirebaseDatabase firebase;
    private ListSymbolRecord record;
    private FireBaseModel sut;

    @Before
    public void setUp()
    {
        record=mock(ListSymbolRecord.class);
        reference=mock(DatabaseReference.class);
        firebase=mock(FirebaseDatabase.class);

    }
    @Test
    public void constructorShouldInitFireBase(){

        //given
        FireBaseModel object = new FireBaseModel(reference,firebase);

        //when
        object.getSymbolListFromFireBase("symbols");

        //then
        Assert.assertNotNull(object);
    }

    @Test
    public void getSymbolListFromFireBaseReturnObservable(){

        //given
        TestObserver<List<ListSymbolRecord>> observer = new TestObserver<>();
        final List<ListSymbolRecord> list = new LinkedList<>();
        list.add(record);
        sut = new FireBaseModel(reference,firebase);

        //when
        Observable<List<ListSymbolRecord>> observable2 = sut.getSymbolListFromFireBase("symbols");
        observable2.subscribe(observer);

        //then
        observable2.test().assertSubscribed();
        observable2.test().onNext(list);
        observable2.test().onComplete();
        Assert.assertNotNull(observable2);
    }


}
