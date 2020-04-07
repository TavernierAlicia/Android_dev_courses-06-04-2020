package fr.mds.heatconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "HeatConvert";
    private EditText et_to_conv_c;
    private EditText et_to_conv_f;
    private TextView tv_error;
    private Button bt_convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "HeatConvert - Create");

        et_to_conv_c = findViewById(R.id.et_to_conv_c);
        et_to_conv_f = findViewById(R.id.et_to_conv_f);
        bt_convert = findViewById(R.id.bt_convert);
        tv_error = findViewById(R.id.tv_error);

        //event button click
        bt_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "HeatConvert - Convert!");

                //CONVERT CELCIUS IN FAHRENHEIGHT
                if (et_to_conv_f.getText().toString().isEmpty() && !et_to_conv_c.getText().toString().isEmpty()) {
                    Log.d(TAG, "HeatConvert - Convert - celcius");
                    //convert to float
                    //test to avoid crashing
                    try {
                        float value = Float.parseFloat(et_to_conv_c.getText().toString());
                        Log.d(TAG, "converted: "+value);
                        //count
                        float result = (value*(9.0f/5.0f)) + 32.0f;
                        Log.d(TAG, "HeatConvert - Convert - Result is "+result);
                        //display result
                        et_to_conv_f.setText(String.format("%.02f", result));
                        //remove error
                        tv_error.setText(getString(R.string.none));
                    }
                    catch (Exception e) {
                        //convert is not possible
                        tv_error.setText(getString(R.string.error));
                        //remove antic result
                        et_to_conv_f.setText(getString(R.string.none));
                        et_to_conv_c.setText(getString(R.string.none));
                        //reset field
                        et_to_conv_c.setText(getString(R.string.none));
                        Log.d(TAG, "HeatConvert - Convert - Invalid Value");
                        return;
                    }

                //CONVERT FAHRENHEIGHT IN CELCIUS
                } else if (et_to_conv_c.getText().toString().isEmpty() && !et_to_conv_f.getText().toString().isEmpty()) {
                    Log.d(TAG, "HeatConvert - Convert - fahrenheight");
                    //convert to float
                    //test to avoid crashing
                    try {
                        float value = Float.parseFloat(et_to_conv_f.getText().toString());
                        Log.d(TAG, "converted: "+value);
                        //count
                        //32 °F − 32) × 5/9
                        float result = (value-32)*(5.0f/9.0f);
                        Log.d(TAG, "HeatConvert - Convert - Result is "+result);
                        //display result
                        et_to_conv_c.setText(String.format("%.02f", result));
                        //remove error
                        tv_error.setText(getString(R.string.none));
                    }
                    catch (Exception e) {
                        //convert is not possible
                        tv_error.setText(getString(R.string.error));
                        //remove antic results
                        et_to_conv_f.setText(getString(R.string.none));
                        et_to_conv_c.setText(getString(R.string.none));
                        //reset field
                        et_to_conv_c.setText(getString(R.string.none));
                        Log.d(TAG, "HeatConvert - Convert - Invalid Value");
                        return;
                    }
                //NO VALUE OR BOTH
                } else {
                    Log.d(TAG, "HeatConvert - Convert - No value");
                    tv_error.setText(getString(R.string.empty_error));
                    return;
                }
            }
        });
    }
}
