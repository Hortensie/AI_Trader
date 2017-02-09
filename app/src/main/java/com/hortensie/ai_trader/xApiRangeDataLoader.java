package com.hortensie.ai_trader;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.github.mikephil.charting.data.CandleEntry;

import java.util.LinkedList;
import java.util.List;

import pro.xstore.api.message.command.APICommandFactory;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.RateInfoRecord;
import pro.xstore.api.message.response.APIErrorResponse;
import pro.xstore.api.message.response.ChartResponse;
import pro.xstore.api.sync.SyncAPIConnector;

/**
 * Created by Piotr Szczesny on 2016-12-08.
 * Async Task for retrieving historical data from xAPI server
 * It retrieves data within specific time frame (from startTime to endTime)
 * and for selected symbol & period
 * It gets data and save to FireBase database using saveDataToFireBase method
 * If successful toast message with be shown
 * If there is no data connection with xAPI server will be terminated
 */

 public class xApiRangeDataLoader extends AsyncTask<SyncAPIConnector,Void,Void> {

        private static List<CandleEntry> dataSet = new LinkedList<>();
        private ChartRangeInfo chartRangeInfo;
        private Context context;

    public xApiRangeDataLoader(ChartRangeInfo chartRangeInfo, Context context) {
        this.chartRangeInfo = chartRangeInfo;
        this.context = context;
    }

    public static List<CandleEntry> getDataSet() {
        return dataSet;
    }

    public static void setDataSet(List<CandleEntry> dataSet) {
        xApiRangeDataLoader.dataSet = dataSet;
    }


    @Override
    protected Void doInBackground(SyncAPIConnector... params) {
        try
        {
                SyncAPIConnector apiConnector=params[0];
                ChartResponse executeChartRangeCommand = APICommandFactory.executeChartRangeCommand(apiConnector,chartRangeInfo);
                List<RateInfoRecord> eurUsdList = executeChartRangeCommand.getRateInfos();
                if(eurUsdList!=null&&eurUsdList.size()!=0)
                {
                    FireBaseHandler fireBaseHandler = new FireBaseHandler();
                    ApiCandleConverter apiCandleConverter = new ApiCandleConverter();
                    fireBaseHandler.saveCandleListToFireBase(apiCandleConverter.saveApiRecordsToCandleEntryList(eurUsdList,chartRangeInfo),chartRangeInfo);
                    dataSet= apiCandleConverter.saveApiRecordsToCandleEntryList(eurUsdList,chartRangeInfo);
                    setDataSet(dataSet);
                }
                else
                {
                    //Close connection
                    apiConnector.close();
                }
        }
         catch (APIReplyParseException|APICommunicationException|APICommandConstructionException|APIErrorResponse e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(Void avoid)
    {
        if(xApiRangeDataLoader.dataSet!=null&&xApiRangeDataLoader.dataSet.size()!=0)
        {
            Toast toastLogged = Toast.makeText(context,"Data successfully saved to database!", Toast.LENGTH_SHORT);
            toastLogged.show();
        }
        else
        {
            Toast toastLogged = Toast.makeText(context,"Not all input values where set, re-try!", Toast.LENGTH_SHORT);
            toastLogged.show();
        }
    }


}

