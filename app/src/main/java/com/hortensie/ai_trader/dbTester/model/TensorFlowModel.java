package com.hortensie.ai_trader.dbTester.model;
import android.content.Context;
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
    private static final String MODEL_FILE = "file:///android_asset/optimized_tfdroid.pb";
    private static final String INPUT_NODE = "I";
    private static final String OUTPUT_NODE = "O";
    private static final int[] INPUT_SIZE = {1, 3};

    public TensorFlowModel(Context context) {
        this.context = context;
    }

    private TensorFlowInferenceInterface inferenceInterface;

    public void initTensorFlowModel()
    {
    inferenceInterface =new  TensorFlowInferenceInterface(context.getAssets(),MODEL_FILE);


    }

    public void performInference()
    {
        //float[] inputFloats = {num1, num2, num3};
        //inferenceInterface.fillNodeFloat(INPUT_NODE, INPUT_SIZE, inputFloats);
        //inferenceInterface.runInference(new String[] {OUTPUT_NODE});

        float[] resu = {0, 0};
        //inferenceInterface.readNodeFloat(OUTPUT_NODE, resu);
    }


}
