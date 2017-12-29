package com.hortensie.ai_trader.dbTester.model;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.hortensie.ai_trader.R;
import com.hortensie.ai_trader.dbTester.view.RxViewInterface;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;


/**
 * Created by szczesny on 2017-05-13.
 * Class that uses pre build optimized tensor flow model
 */

public class TensorFlowModel {

    static {
        System.loadLibrary("tensorflow_inference");
    }

    private Context context;
    private static final String MODEL_FILE = "file:///android_asset/optimized_vader.pb";
    private static final String INPUT_NODE = "I";
    private static final String OUTPUT_NODE = "O";
    private static final long[] INPUT_SIZE = {1,3};

    private RxViewInterface viewInterface;

    public TensorFlowModel(Context context,RxViewInterface viewInterface) {
        this.context = context;
        this.viewInterface = viewInterface;
    }

    //private TensorFlowInferenceInterface inferenceInterface2;
    private TensorFlowInferenceInterface inferenceInterface;

    public void initTensorFlowModel()
    {
    //initializeTensorFlow: initialize TensorFlow object using a model graph as input
    inferenceInterface =new  TensorFlowInferenceInterface(context.getAssets(),MODEL_FILE);
    //inferenceInterface2 = new TensorFlowInferenceInterface(context.getAssets(),MODEL_FILE);
    }

    public void performInference(float[] inputFloats)
    {
        //fillNodeFloat: copy input data into TensorFlow input array
        //inferenceInterface.feed(INPUT_NODE, INPUT_SIZE, inputLongs);
        // INPUT_SHAPE is an int[] of expected shape, input is a float[] with the input data

        inferenceInterface.feed(INPUT_NODE,inputFloats,INPUT_SIZE);
        Log.d("Model","Success of Tensor feed");
        //runInference: run inference and save results in TensorFlow output array
        inferenceInterface.run(new String[]{OUTPUT_NODE},false);
        //inferenceInterface.run(new String[] {OUTPUT_NODE},false);
        Log.d("Model","Success of Tensor run");
        float[] resu = {0, 0};
        //readNodeFloat: read from TensorFlow output array and save into your own array
        inferenceInterface.fetch(OUTPUT_NODE,resu);
        //inferenceInterface.readNodeFloat(OUTPUT_NODE, resu);
        Log.d("Model","Success of Tensor fetch");
        Log.d("Model","Result"+resu[0]);
        viewInterface.updateUi(""+resu[0]+", "+resu[1]);
        //textViewR.setText(Float.toString(resu[0]) + ", " + Float.toString(resu[1]));
    }


    }
