package fr.myschool.demoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.myschool.demoproject.adapter.VirusListAdapter;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {
    private Button bt_main_1, bt_main_2, bt_main_3;
    private Spinner sp_main_select;
    private Spinner sp_main_custom_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_main_1 = findViewById(R.id.bt_main_1);
        bt_main_2 = findViewById(R.id.bt_main_2);
        bt_main_3 = findViewById(R.id.bt_main_3);

        bt_main_1.setOnClickListener(this);
        bt_main_2.setOnClickListener(this);
        bt_main_3.setOnClickListener(this);

        sp_main_select = findViewById(R.id.sp_main_select);
        sp_main_custom_select = findViewById(R.id.sp_main_custom_select);

        ProgramingLanguage[] programingLanguages = {
                new ProgramingLanguage("java", "Java sucks"),
                new ProgramingLanguage("Go", "Ze best"),
                new ProgramingLanguage("Python", "Is amazing"),
                new ProgramingLanguage("C", "Is basic"),
                new ProgramingLanguage("Rust", "Is complicated"),
                new ProgramingLanguage("C++", "Evil")
        };

        ArrayAdapter arrayAdapter = new ArrayAdapter<ProgramingLanguage>(
                this,
                R.layout.item_spinner,
                programingLanguages
        );
        sp_main_select.setAdapter(arrayAdapter);

        List<Virus> virusList = new ArrayList<>();
        virusList.add(new Virus("Covid-19", "Chine", 4));
        virusList.add(new Virus("Spanish-Flue", "Spain", 4));
        virusList.add(new Virus("WinXP", "USA", 45));
        virusList.add(new Virus("Vista", "USA", 98));

        VirusListAdapter virusListAdapter = new VirusListAdapter(this, virusList);

        sp_main_custom_select.setAdapter(virusListAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == bt_main_1) {
            Toast.makeText(this, "Button 1 clicked", Toast.LENGTH_SHORT).show();
            return;
        } else if (v == bt_main_2) {
            Object object = sp_main_select.getSelectedItem();
            ProgramingLanguage selectedProgramingLanguage = (ProgramingLanguage) object;

            String textToShow = selectedProgramingLanguage.getName()+"\n"+ selectedProgramingLanguage.getDescription();

            Toast.makeText(this, "Button 2 clicked", Toast.LENGTH_SHORT).show();
            return;
        } else if (v == bt_main_3) {
            Toast.makeText(this, "Button 3 clicked", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
