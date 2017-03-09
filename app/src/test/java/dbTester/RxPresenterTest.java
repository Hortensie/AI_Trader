package dbTester;

import com.google.firebase.database.DataSnapshot;
import com.hortensie.ai_trader.dbTester.model.FireBaseModel;
import com.hortensie.ai_trader.dbTester.model.FireBaseModelInterface;
import com.hortensie.ai_trader.dbTester.presenter.RxPresenter;
import com.hortensie.ai_trader.dbTester.view.RxView;
import com.hortensie.ai_trader.dbTester.view.RxViewInterface;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Flowable;
import io.reactivex.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by szczesny on 2017-03-07.
 * rxJava 2 test
 */

public class RxPresenterTest {

    RxViewInterface viewInterface;
    FireBaseModelInterface fireBaseModelInterface;

    @Before
    public void setUp(){
        fireBaseModelInterface=mock(FireBaseModelInterface.class);
        viewInterface=mock(RxViewInterface.class);
    }


    @Test
    public void shouldPassStringsToView(){

        //given
        RxViewInterface view = new MockView();
        FireBaseModelInterface model = new MockFireBaseModel();

        //when
        RxPresenter presenter = new RxPresenter(viewInterface,fireBaseModelInterface);
        //presenter.showData();
        when(fireBaseModelInterface.getData()).thenReturn(Observable.<String>empty());
        fireBaseModelInterface.getData().test().assertSubscribed();
        fireBaseModelInterface.getData().test().onNext("");

        //then
        //Assert.assertEquals(true,((MockView)view).passed);

    }
    private class MockView implements RxViewInterface{

        boolean passed;

        @Override
        public void updateUi(String s) {
        passed=true;
        }

    }

    private class MockFireBaseModel implements FireBaseModelInterface{

        @Override
        public Observable<String> getData() {
            return null;
        }

        @Override
        public Flowable<DataSnapshot> getDataFromFireBase(String childName) {
            return null;
        }
    }
}
