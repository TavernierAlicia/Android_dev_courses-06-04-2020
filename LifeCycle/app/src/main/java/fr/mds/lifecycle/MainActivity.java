package fr.mds.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "LifeCycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "LifeCycle - onCreate");

        //get ressources
        Resources resources = getResources();
        String helloWorldStr = resources.getString(R.string.hello_string);

        Log.d(TAG, helloWorldStr);
        String cancelAndroidStr = resources.getString(android.R.string.cancel);

        Log.d(TAG, cancelAndroidStr);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "LifeCycle - onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "LifeCycle - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "LifeCycle - onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "LifeCycle - onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "LifeCycle - onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "LifeCycle - onRestart");
    }
}
