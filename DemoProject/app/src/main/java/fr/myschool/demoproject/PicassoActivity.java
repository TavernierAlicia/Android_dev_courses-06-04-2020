package fr.myschool.demoproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

public class PicassoActivity extends Activity {
    public static final String TAG = "DemoProject - picasso";
    private ImageView iv_picasso_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        iv_picasso_content = findViewById(R.id.iv_picasso_content);
        Picasso.get().load("https://img.lemde.fr/2018/01/15/0/0/2234/2660/688/0/60/0/d2c98a6_26190-1mom9q2.1wcx.jpg").into(iv_picasso_content);
    }
}
