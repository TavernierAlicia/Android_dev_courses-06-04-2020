package fr.myschool.geekquote.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

import fr.myschool.geekquote.R;
import fr.myschool.geekquote.activity.QuoteActivity;
import fr.myschool.geekquote.model.Quote;

public class QuoteListAdapter extends ArrayAdapter  {

    public static final String TAG = "GeekQuote";

    private final Context context;

    public QuoteListAdapter(Context context, ArrayList<Quote> quotes) {
        super(context, R.layout.quote_item, quotes);
        this.context = context ;
    }

    private ArrayList<Quote> list = new ArrayList<>();

    public void addItem(Quote quote) {
        list.add(quote);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.quote_item, parent, false);
        }

        ((TextView)(convertView.findViewById(R.id.tv_quote))).setText(list.get(position).getStrQuote());
        ((TextView)(convertView.findViewById(R.id.tv_listquote_rate))).setText(""+list.get(position).getRating());
        if (position % 2 == 0) {
            convertView.findViewById(R.id.master_block).setBackgroundColor(context.getResources().getColor(R.color.GooseWhite));
        } else {
            convertView.findViewById(R.id.master_block).setBackgroundColor(context.getResources().getColor(R.color.TchernobylGrey));
        }

        //set Activity
        convertView.findViewById(R.id.master_block).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuoteActivity.class);
                intent.putExtra("QUOTE", (Serializable) list.get(position));
                intent.putExtra("INDEX", position);
                ((Activity)context).startActivityForResult(intent, 1);
            }
        });

        return convertView;
    }

    public void editItem(Quote editedQuote, int position) {
        list.set(position, editedQuote);
        notifyDataSetChanged();
    }

    // Dans l'adapter
    public void setItems(ArrayList<Quote> quotesList) {
        list = quotesList ;
        notifyDataSetChanged();
    }
}
