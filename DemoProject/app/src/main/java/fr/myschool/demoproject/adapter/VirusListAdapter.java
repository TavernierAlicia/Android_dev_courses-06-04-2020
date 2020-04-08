package fr.myschool.demoproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fr.myschool.demoproject.R;
import fr.myschool.demoproject.Virus;

public class VirusListAdapter extends ArrayAdapter<Virus> {
    public VirusListAdapter(@NonNull Context context, @NonNull List<Virus> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Virus v = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_virus_spinner, parent, false);
        }
        TextView tv_item_virus_name = convertView.findViewById(R.id.tv_virus_name);
        TextView tv_item_virus_country = convertView.findViewById(R.id.tv_virus_country);
        TextView tv_item_virus_rate = convertView.findViewById(R.id.tv_virus_rate);

        tv_item_virus_name.setText(v.getName());
        tv_item_virus_country.setText(v.getCountryOrigin());
        tv_item_virus_rate.setText(v.getMortalityRate()+"%");

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
