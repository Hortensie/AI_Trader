package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import pro.xstore.api.message.codes.PERIOD_CODE;

public class PeriodSelector extends Activity {

    static PERIOD_CODE tempValue;

    public static PERIOD_CODE getTempValue() {
        return tempValue;
    }

    public static void setTempValue(PERIOD_CODE tempValue) {
        PeriodSelector.tempValue = tempValue;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_group);

        final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.period_selector);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);

                switch (index)
                {
                    case 0:
                        setTempValue(PERIOD_CODE.PERIOD_M1);
                        Toast periodM1 = Toast.makeText(getApplicationContext(), "Selected 1 minute period" , Toast.LENGTH_SHORT);
                        periodM1.show();
                        Intent sentM1 = new Intent(PeriodSelector.this,xApiUiInput.class);
                        startActivity(sentM1);
                        break;
                    case 1:
                        setTempValue(PERIOD_CODE.PERIOD_M5);
                        Toast periodM5 = Toast.makeText(getApplicationContext(), "Selected 5 minute period" , Toast.LENGTH_SHORT);
                        periodM5.show();
                        Intent sentM5 = new Intent(PeriodSelector.this,xApiUiInput.class);
                        startActivity(sentM5);
                        break;
                    case 2:
                        setTempValue(PERIOD_CODE.PERIOD_M15);
                        Toast periodM15 = Toast.makeText(getApplicationContext(), "Selected 15 minute period" , Toast.LENGTH_SHORT);
                        periodM15.show();
                        Intent sentM15 = new Intent(PeriodSelector.this,xApiUiInput.class);
                        startActivity(sentM15);
                        break;
                    case 3:
                        setTempValue(PERIOD_CODE.PERIOD_M30);
                        Toast periodM30 = Toast.makeText(getApplicationContext(), "Selected 30 minute period" , Toast.LENGTH_SHORT);
                        periodM30.show();
                        Intent sentM30 = new Intent(PeriodSelector.this,xApiUiInput.class);
                        startActivity(sentM30);
                        break;
                    case 4:
                        setTempValue(PERIOD_CODE.PERIOD_H1);
                        Toast periodH1 = Toast.makeText(getApplicationContext(), "Selected 1 hour period" , Toast.LENGTH_SHORT);
                        periodH1.show();
                        Intent sentH1 = new Intent(PeriodSelector.this,xApiUiInput.class);
                        startActivity(sentH1);
                        break;
                    case 5:
                        setTempValue(PERIOD_CODE.PERIOD_H4);
                        Toast periodH4 = Toast.makeText(getApplicationContext(), "Selected 4 hour period" , Toast.LENGTH_SHORT);
                        periodH4.show();
                        Intent sentH4 = new Intent(PeriodSelector.this,xApiUiInput.class);
                        startActivity(sentH4);
                        break;
                    case 6:
                        setTempValue(PERIOD_CODE.PERIOD_D1);
                        Toast periodD1 = Toast.makeText(getApplicationContext(), "Selected 1 day period" , Toast.LENGTH_SHORT);
                        periodD1.show();
                        Intent sentD1 = new Intent(PeriodSelector.this,xApiUiInput.class);
                        startActivity(sentD1);
                        break;
                    case 7:
                        setTempValue(PERIOD_CODE.PERIOD_W1);
                        Toast periodW1 = Toast.makeText(getApplicationContext(), "Selected 1 week period" , Toast.LENGTH_SHORT);
                        periodW1.show();
                        Intent sentW1 = new Intent(PeriodSelector.this,xApiUiInput.class);
                        startActivity(sentW1);
                        break;
                    case 8:
                        setTempValue(PERIOD_CODE.PERIOD_MN1);
                        Toast periodMN1 = Toast.makeText(getApplicationContext(), "Selected 1 month period" , Toast.LENGTH_SHORT);
                        periodMN1.show();
                        Intent sentMN1 = new Intent(PeriodSelector.this,xApiUiInput.class);
                        startActivity(sentMN1);
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
