package com.hortensie.ai_trader.dbTester.view;



import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import com.hortensie.ai_trader.R;
import com.hortensie.ai_trader.dbTester.model.FireBaseModel;
import com.hortensie.ai_trader.dbTester.model.FireBaseModelInterface;
import com.hortensie.ai_trader.dbTester.presenter.DetailActivity;
import com.hortensie.ai_trader.dbTester.presenter.DetailActivityInter;
import com.hortensie.ai_trader.xAPI.ListSymbolRecord;

import java.util.List;

/**
 * Created by szczesny on 2017-02-28.
 * Provides UI for the Detail page with Collapsing Toolbar.
 * This Class provides extension when User Click on specific Recycler Viewer row
 * Provides detailed information's about selected symbol
 */

public class DetailActivityView extends AppCompatActivity implements DetailActivityViewInterface {

    public static final String EXTRA_POSITION = "position";
    private FireBaseModelInterface model=new FireBaseModel();
    CollapsingToolbarLayout collapsingToolbar;
    //position in recycler viewer
    int position;
    //symbol details
    TextView symbolName;
    TextView currencyName;
    TextView categoryName;
    TextView groupName;
    TextView bid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //we are using external toolbar (not actionbar)
        //toolbar needs to be initialized before next method (setDisplayHomeAsUpEnabled) to prevent NullPointException
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        //assert that app is using action bar (toolbar) instead of theme based action bar
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Set Collapsing Toolbar layout to the screen
        collapsingToolbar =(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        symbolName = (TextView) findViewById(R.id.symbol_description);
        currencyName =  (TextView) findViewById(R.id.currency_description);
        categoryName = (TextView) findViewById(R.id.category_name_description);
        groupName =  (TextView) findViewById(R.id.group_name_description);
        bid =  (TextView) findViewById(R.id.bid_description);

        // Set title of Detail page
        collapsingToolbar.setTitle(getString(R.string.item_title));
        position = getIntent().getIntExtra(EXTRA_POSITION, 0);
        //get resources for pictures
        Resources resources = getResources();
        //create object via Detail Activity Interface with connection to current view
        DetailActivityInter detailInterface = new DetailActivity(this,model);
        //call showDetails method from DetailActivity interface to print symbol details
        detailInterface.showSymbolDetails();

        //initialize picture resources
        TypedArray placePictures = resources.obtainTypedArray(R.array.places_picture);
        ImageView placePicture = (ImageView) findViewById(R.id.image);
        placePicture.setImageDrawable(placePictures.getDrawable(position % placePictures.length()));
        placePictures.recycle();
    }

    //method that Update Ui for selected symbol
    //it updates symbol, currency, categoryName, groupName, bid ... etc information's
    @Override
    public void updateTitle(List<ListSymbolRecord> inputList) {
        collapsingToolbar.setTitle(inputList.get(position).getDescription());
        symbolName.setText(inputList.get(position).getSymbol());
        currencyName.setText(inputList.get(position).getCurrency());
        categoryName.setText(inputList.get(position).getCategoryName());
        groupName.setText(inputList.get(position).getGroupName());
        bid.setText(String.valueOf(inputList.get(position).getBid()));
}

}