package fr.myschool.geekquote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class QuoteListAdapter extends ArrayAdapter {

    private final Context context;

    QuoteListAdapter(Context context, ArrayList<String> quotes) {
        super(context, R.layout.quote_item, quotes);
        this.context = context ;
    }

    private ArrayList<String> list = new ArrayList<>();

    void addItem(String text) {
        list.add(text);
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
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.quote_item, parent, false);
        }

        ((TextView)(convertView.findViewById(R.id.tv_quote))).setText(list.get(position));
        if (position % 2 == 0) {
            convertView.findViewById(R.id.master_block).setBackgroundColor(context.getResources().getColor(R.color.RacoonGrey));
        } else {
            convertView.findViewById(R.id.master_block).setBackgroundColor(context.getResources().getColor(R.color.TchernobylGrey));
        }

        return convertView;
    }
}
