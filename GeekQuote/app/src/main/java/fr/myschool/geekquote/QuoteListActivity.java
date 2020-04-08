package fr.myschool.geekquote;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

        adapter = new QuoteListAdapter(this, new ArrayList<String>());
        lv_main_quotes.setAdapter(adapter);

        String[] myQuoteArray = getResources().getStringArray(R.array.quotes_array);
        for(int i =0; i < myQuoteArray.length; i++){
            String item = myQuoteArray[i];
            addQuote(item);
            adapter.addItem(item);
        }

        //show data

//        final ArrayAdapter<Quote> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, quotes);
//        lv_main_quotes.setAdapter(adapter);

        bt_main_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et_main_quote.getText().toString();
                if (!text.isEmpty()) {
                    adapter.addItem(text);
                    //addQuote(text);
                    //adapter.notifyDataSetChanged();
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
        Quote quote = new Quote();
        quote.setStrQuote(strQuote);
        quote.setRating(0);
        quote.setCreationDate(new Date());
        //set background
        //if (quotes.size() % 2 == 0) {
        //  findViewById(R.id.text1).setBackgroundColor(R.color.redWine);
        //}

        this.quotes.add(quote);
        Log.d("GeekQuote", "Quote added "+ quote.getStrQuote());
    }

}
