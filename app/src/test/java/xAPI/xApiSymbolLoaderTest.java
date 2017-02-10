package xAPI;

import android.content.Context;

import com.hortensie.ai_trader.xAPI.xApiSymbolLoader;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Created by Piotr on 2017-01-28.
 * unit test for SymbolDataLoader which gets symbol list from xAPI server
 */

public class xApiSymbolLoaderTest {

    private Context context;


    public void setUp(){
        context=mock(Context.class);
    }


    @Test
    public void validateConstructorSymbolLoader(){

        xApiSymbolLoader symbolDataLoaderObject = new xApiSymbolLoader(context);

        Assert.assertNotNull(symbolDataLoaderObject);

    }


}
