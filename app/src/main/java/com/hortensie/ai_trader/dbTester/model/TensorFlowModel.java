package com.hortensie.ai_trader.dbTester.model;
import android.content.Context;
import android.util.Log;

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
    private static final String MODEL_FILE = "file:///android_asset/optimized_linear_model.pb";
    private static final String INPUT_NODE = "I";
    private static final String OUTPUT_NODE = "O";
    private static final float[] INPUT_SIZE = {1,2,3,4,5,6};

    public TensorFlowModel(Context context) {
        this.context = context;
    }

    private TensorFlowInferenceInterface inferenceInterface;

    public void initTensorFlowModel()
    {
    //initializeTensorFlow: initialize TensorFlow object using a model graph as input
    inferenceInterface =new  TensorFlowInferenceInterface(context.getAssets(),MODEL_FILE);
    }

    public void performInference(long[] inputLongs)
    {
        //fillNodeFloat: copy input data into TensorFlow input array
        inferenceInterface.feed(INPUT_NODE, INPUT_SIZE, inputLongs);
        Log.d("Model","Success of Tensor feed");
        //runInference: run inference and save results in TensorFlow output array
        inferenceInterface.run(new String[] {OUTPUT_NODE},false);
        Log.d("Model","Success of Tensor run");
        float[] resu = {0, 0};
        //readNodeFloat: read from TensorFlow output array and save into your own array
        //inferenceInterface.readNodeFloat(OUTPUT_NODE, resu);

        //final TextView textViewR = (TextView) findViewById(R.id.txtViewResult);
        //textViewR.setText(Float.toString(resu[0]) + ", " + Float.toString(resu[1]));
    }


    }
