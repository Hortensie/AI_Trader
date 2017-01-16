package com.vaadin.polymer.demo.client.sampler.ai_trader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PeriodSelector extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_group);

        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.period_selector);

        radioGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Is the button now checked?
        boolean checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch(v.getId()) {
            case R.id.PERIOD_M1:
                if (checked) {
                    // Pirates are the best
                    Toast toastLogged = Toast.makeText(getApplicationContext(), "Selected Minute 1", Toast.LENGTH_SHORT);
                    toastLogged.show();
                }

                break;
            case R.id.PERIOD_M5:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }


}
