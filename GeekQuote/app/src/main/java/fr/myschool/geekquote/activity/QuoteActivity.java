package fr.myschool.geekquote.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fr.myschool.geekquote.BuildConfig;
import fr.myschool.geekquote.R;
import fr.myschool.geekquote.model.Quote;

public class QuoteActivity extends AppCompatActivity {
    public static final String TAG = "GeekQuote";
    SharedPreferences sharedPref;
    int position;
    Quote quote;
    Button bt_rate_cancel, bt_rate_validate;
    TextView tv_quote_activity, tv_quote_rate, tv_quote_date;
    RatingBar ratingBar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            final Context context = this;
            sharedPref = getSharedPreferences(BuildConfig.APPLICATION_ID+".SharedPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("isUserConnected", false);
            editor.apply();
            Log.e("GeekQuote", "Connected: "+sharedPref.getBoolean("isUserConnected", false));
            Intent intent = new Intent(context, ConnectActivity.class);
            startActivity(intent);
            finishAffinity();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quote_activity);
        tv_quote_activity = findViewById(R.id.tv_quote_activity);
        tv_quote_rate = findViewById(R.id.tv_quote_rate);
        tv_quote_date = findViewById(R.id.tv_quote_date);

        ratingBar = findViewById(R.id.ratingBar);

        bt_rate_cancel = findViewById(R.id.bt_rate_cancel);
        bt_rate_validate = findViewById(R.id.bt_rate_validate);

        bt_rate_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("GeekQuote", "Rating"+quote.getRating());
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        bt_rate_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quote.setRating((int) ratingBar.getRating());
                Intent returnIntent = new Intent();
                returnIntent.putExtra("INDEX", position);
                returnIntent.putExtra("RESULT_QUOTE", (Serializable) quote);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                Log.e("GeekQuote", "Rating"+quote.getRating());
            }
        });


        Log.d("GeekQuote", "Create view");

        //Set data
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.quote = ((Quote) bundle.getSerializable("QUOTE"));
            position = bundle.getInt("INDEX");
            int rate = bundle.getInt("Rate");


            try {
                String date = bundle.getString("Date");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
                Date formattedDate = simpleDateFormat.parse(this.quote.getCreationDate().toString());
                tv_quote_date.setText(formattedDate.toString());
            } catch (Exception e) {
                Date dateToParse = new Date() ;
                String newstring = new SimpleDateFormat("dd/MM/yyyy").format(dateToParse);
                tv_quote_date.setText(newstring);
            }

            //set data
            Log.e("GeekQuote", "Rate: "+quote.getRating());
            tv_quote_activity.setText(this.quote.getStrQuote());
            ratingBar.setRating((float) quote.getRating());
            Log.e("GeekQuote", "GetText: "+tv_quote_rate.getText());
            Log.e("GeekQuote", "GetText: "+tv_quote_date.getText());
        }
    }

    @Override
    public void finishActivity(int requestCode) {
        if (requestCode == 1) {
            setResult(Activity.RESULT_OK);
            Log.d("GeekQuote", "Activity closed");
        }
        super.finishActivity(requestCode);
    }
}
