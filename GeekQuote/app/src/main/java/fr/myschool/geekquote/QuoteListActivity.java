package fr.myschool.geekquote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.myschool.geekquote.model.Quote;

import java.util.ArrayList;
import java.util.Date;

public class QuoteListActivity extends AppCompatActivity {
    public static final String TAG = "GeekQuote";
    ArrayList<Quote> quotes = new ArrayList<>();

    EditText et_main_quote;
    Button bt_main_add;
    ListView lv_main_quotes;
    QuoteListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quote_list_activity);
        Log.d("GeekQuote", "Created");

        et_main_quote = findViewById(R.id.et_main_quote);
        bt_main_add = findViewById(R.id.bt_main_add);
        lv_main_quotes = findViewById(R.id.lv_main_quotes);

        adapter = new QuoteListAdapter(this, new ArrayList<Quote>());
        lv_main_quotes.setAdapter(adapter);

        String[] myQuoteArray = getResources().getStringArray(R.array.quotes_array);
        for(int i =0; i < myQuoteArray.length; i++){
            Quote quote = new Quote(myQuoteArray[i], 0, new Date());
            String item = myQuoteArray[i];
            addQuote(item);
            adapter.addItem(quote);
        }

        //show data

        bt_main_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et_main_quote.getText().toString();
                if (!text.isEmpty()) {
                    Quote quote = new Quote(text, 0, new Date());
                    adapter.addItem(quote);
                    et_main_quote.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });



    }

    //add quote
    void addQuote(String strQuote){
        Quote quote = new Quote(strQuote, 0, new Date());
        this.quotes.add(quote);
        Log.d("GeekQuote", "Quote added "+ quote.getStrQuote());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "GeekQuote - onActivityResult"+requestCode);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null && data.getExtras() != null) {
                    Quote response = (Quote) data.getExtras().getSerializable("RESULT_QUOTE");
                    int index = data.getExtras().getInt("INDEX");
                    Log.d(TAG, "Data : " + this);
                    adapter.editItem(response, index);
                    Toast.makeText(getApplicationContext(), "Note modifiee", Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
