package fr.myschool.geekquote.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.GsonBuildConfig;
import com.google.gson.reflect.TypeToken;

import fr.myschool.geekquote.BuildConfig;
import fr.myschool.geekquote.adapter.QuoteListAdapter;
import fr.myschool.geekquote.R;
import fr.myschool.geekquote.model.Quote;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class QuoteListActivity extends AppCompatActivity {
    public static final String TAG = "GeekQuote";
    ArrayList<Quote> quotes = new ArrayList<>();
    SharedPreferences sharedPref;
    EditText et_main_quote;
    Button bt_main_add;
    ListView lv_main_quotes;
    QuoteListAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            sharedPref = getSharedPreferences(BuildConfig.APPLICATION_ID+".SharedPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("isUserConnected", false);
            editor.apply();
            Log.e("GeekQuote", "Connected: "+sharedPref.getBoolean("isUserConnected", false));
            Intent intent = new Intent(this, ConnectActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

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

        checkPermissions();

        //show data
        bt_main_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et_main_quote.getText().toString();
                if (!text.isEmpty()) {
                    Quote quote = new Quote(text, 0, new Date());
                    //adapter.addItem(quote);
                    quotes.add(quote);
                    adapter.notifyDataSetChanged();
                    et_main_quote.setText("");
                    Gson gson = new GsonBuilder().create();
                    try {
                        writeToFile(gson.toJson(quotes));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), R.string.added_quote, Toast.LENGTH_SHORT).show();
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
                    quotes.set(index, response);
                    Gson gson = new GsonBuilder().create();
                    try {
                        writeToFile(gson.toJson(quotes));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), R.string.modified_rate, Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("quotes", quotes);
        super.onSaveInstanceState(outState);


    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList<Quote> retrievedQuotes = savedInstanceState.getParcelableArrayList("quotes");

        quotes.clear();
        quotes.addAll(retrievedQuotes);
        adapter.notifyDataSetChanged();
        adapter.setItems(retrievedQuotes);

    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //ask permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    },
                    3);
        } else {
            retrieveList();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 3) {
            if (grantResults.length > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                retrieveList();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void retrieveList() {
        String fileContent = "";
        try {
            fileContent = readFromFile();
            Gson gson = new GsonBuilder().create();
            Type type = new TypeToken<ArrayList<Quote>>() {}.getType();
            ArrayList<Quote> retrievedQuotes = gson.fromJson(fileContent, type);
            if (retrievedQuotes != null && !retrievedQuotes.isEmpty()) {
                this.quotes = retrievedQuotes;
                adapter.setItems(retrievedQuotes);
                Log.d("GeekQuotes", retrievedQuotes.get(0).getStrQuote());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String readFromFile() throws IOException {
        File path = this.getFilesDir();
        File file = new File(path, "data.txt");
        long length = file.length();
        byte[] bytes = new byte[(int)length];
        FileInputStream in = new FileInputStream(file);

        try {
            in.read(bytes);
        } finally {
            in.close();
        }
        return new String(bytes);
    }

    void writeToFile(String textToWrite) throws IOException {
        File path = this.getFilesDir();
        File file = new File(path, "data.txt");
        FileOutputStream stream = new FileOutputStream(file);

        try {
            stream.write(textToWrite.getBytes());
        } finally {
            stream.close();
        }

    }

}
