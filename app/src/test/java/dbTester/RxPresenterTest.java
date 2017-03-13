package dbTester;
import com.hortensie.ai_trader.dbTester.model.FireBaseModelInterface;
import com.hortensie.ai_trader.dbTester.presenter.RxPresenter;
import com.hortensie.ai_trader.dbTester.view.RxViewInterface;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by szczesny on 2017-03-07.
 * rxJava 2 sample test
 */

public class RxPresenterTest {

    private RxViewInterface viewInterface;
    private FireBaseModelInterface fireBaseModelInterface;

    @Before
    public void setUp(){
        fireBaseModelInterface=mock(FireBaseModelInterface.class);
        viewInterface=mock(RxViewInterface.class);
    }


    @Test
    public void shouldExecuteModelMethod(){
        reset(viewInterface);

        //given
        RxPresenter presenter = new RxPresenter(viewInterface,fireBaseModelInterface);

        //when
        when(fireBaseModelInterface.getData()).thenReturn(Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("home");
                e.onComplete();
            }
        }));


        presenter.showData();

        //then
        verify(fireBaseModelInterface).getData();


    }


}
