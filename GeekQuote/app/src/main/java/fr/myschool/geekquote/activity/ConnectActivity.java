package fr.myschool.geekquote.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fr.myschool.geekquote.BuildConfig;
import fr.myschool.geekquote.R;

public class ConnectActivity extends AppCompatActivity {
    public static final String TAG = "GeekQuote";
    boolean connected = false;
    SharedPreferences sharedPref;
    Context context;
    Button bt_connect;
    EditText et_connect_username, et_connect_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();

        sharedPref = getSharedPreferences(BuildConfig.APPLICATION_ID+".SharedPreferences", Context.MODE_PRIVATE);
        connected = sharedPref.getBoolean("isUserConnected", false);
        Log.e("GeekQuote", "Connected: "+connected);

        if (connected == false) {
            setContentView(R.layout.connect_activity);
            bt_connect = findViewById(R.id.bt_connect);
            et_connect_username = findViewById(R.id.et_connect_username);
            et_connect_password = findViewById(R.id.et_connect_password);

            Log.d("GeekQuote", "Connect Layout");

            bt_connect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("GeekQuote", "Connect Button");
                    String username = et_connect_username.getText().toString();
                    String password = et_connect_password.getText().toString();

                    if (username.isEmpty() || password.isEmpty()) {
                        Toast.makeText(getApplicationContext(), R.string.empty_ids, Toast.LENGTH_SHORT).show();
                        Log.e("GeekQuote", "Empty field");
                    } else if (!username.equals("test") || !password.equals("test")) {
                        Toast.makeText(getApplicationContext(), R.string.wrong_ids, Toast.LENGTH_SHORT).show();
                        Log.e("GeekQuote", "Password or Username not good: " + username + " " + password);
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.connected, Toast.LENGTH_SHORT).show();
                        Log.e("GeekQuote", "Connected");
                        connected = true;
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putBoolean("isUserConnected", connected);
                        editor.apply();
                        Log.e("GeekQuote", "Connected: "+sharedPref.getBoolean("isUserConnected", false));

                        Intent intent = new Intent(context, QuoteListActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        } else {
            Log.e("GeekQuote", "Already Connected");
            Intent intent = new Intent(context, QuoteListActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
