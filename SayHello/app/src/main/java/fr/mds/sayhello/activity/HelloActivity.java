package fr.mds.sayhello.activity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import fr.mds.sayhello.R;

public class HelloActivity extends Activity{
    public static final String TAG = "SayHello";

    private EditText et_hello_name;
    private TextView tv_hello_message;
    private Button bt_hello_say;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_layout);

        et_hello_name = findViewById(R.id.et_hello_name);
        tv_hello_message = findViewById(R.id.tv_hello_message);
        bt_hello_say = findViewById(R.id.bt_hello_say);

        tv_hello_message.setText("Hello from code");
        bt_hello_say.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "HelloActivity - bt_hello_say - onClick");
                tv_hello_message.setText("Hello "+et_hello_name.getText().toString());
            }
        });
    }

}
