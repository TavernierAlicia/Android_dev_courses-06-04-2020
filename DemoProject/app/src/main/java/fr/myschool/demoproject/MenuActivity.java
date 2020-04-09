package fr.myschool.demoproject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MenuActivity extends Activity implements View.OnClickListener {
    public static final String TAG = "DemoProject";
    private Button bt_menu_spinner, bt_menu_picasso, bt_menu_demo_extra, bt_open_url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bt_menu_spinner = findViewById(R.id.bt_menu_spinner);
        bt_menu_picasso = findViewById(R.id.bt_menu_picasso);
        bt_menu_demo_extra = findViewById(R.id.bt_menu_demo_extra);
        bt_open_url = findViewById(R.id.bt_open_url);

        bt_menu_spinner.setOnClickListener(this);
        bt_menu_picasso.setOnClickListener(this);
        bt_menu_demo_extra.setOnClickListener(this);
        bt_open_url.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == bt_menu_spinner) {
            Log.d(TAG, "spinner button");
            Intent intent = new Intent(this, SpinnerActivity.class);
            startActivity(intent);
            return;

        } else if (v == bt_menu_demo_extra) {
            Log.d(TAG, "demo extra button");

            Virus virus = new Virus("Covid-19", "China", 2);

            Intent intent = new Intent(this, DemoExtraActivity.class);

            intent.putExtra("message", "This is a message from menu activity");
            intent.putExtra("virus", virus);

            startActivityForResult(intent, 0);
            return;

        } else if ( v == bt_open_url) {
            Log.d(TAG, "open website");
            //open url
            //implicit intent
            Uri uri = Uri.parse("https://www.mydigitalschool.com/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else {
            Log.d(TAG, "Picasso button");
            Intent intent = new Intent(this, PicassoActivity.class);
            startActivity(intent);
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "Demoproject - onActivityResult");
        Log.d(TAG, "resultCode : "+resultCode);
        Log.d(TAG, "data : "+data);

        if (data != null && data.getExtras() != null) {
            String response = data.getExtras().getString("response");
            Log.d(TAG, "Virus "+this);
            Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
        }
    }
}
