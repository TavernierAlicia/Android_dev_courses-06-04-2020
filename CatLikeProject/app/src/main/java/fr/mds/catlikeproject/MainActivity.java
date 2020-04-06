package fr.mds.catlikeproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import fr.mds.catlikeproject.model.Catlike;
import fr.mds.catlikeproject.model.Cat;
import fr.mds.catlikeproject.model.Tiger;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "CatLikeProject";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "MainActivity - Oncreate");

        ArrayList<Integer> myIntList = new ArrayList<>();
        myIntList.add(10);
        myIntList.add(5);
        myIntList.add(1);
        myIntList.add(14);

        ArrayList<Catlike> Catlikes = new ArrayList<>();
        Catlikes.add(new Cat());
        Catlikes.add(new Catlike());
        Catlikes.add(new Tiger());

        for (Catlike c : Catlikes) {
            Log.d(TAG, c.toString());
        }

        Catlike myCatLike = new Cat("RoyalCanin");

        //cast
        Cat myCat = (Cat) myCatLike;
        String.foodBrand = myCatLike.getCatFoodBrand();

        ArrayList<Catlike> catlikes2 = new ArrayList<>();
        catlikes2.add(new Cat("Royal Canin"));
        catlikes2.add(new Cat("Whiscas"));
        catlikes2.add(new Cat("Hill's"));
        catlikes2.add(new Tiger());
        catlikes2.add(new Catlike());

        //return random catlike
        Catlike getRandomCatlike() {
            int randomInt = new Random().nextInt(3);
            if (randomInt == 0) {
                return new Catlike();
            } else if (randomInt == 1) {
                return new Cat();
            } else {
                return new Tiger;
            }
        }

        for (Catlike c : catlikes2) {
            if (c instanceof  Cat){
                Log.d(MainActivity.TAG, "This is a cat");
                Cat myCat2 = (Cat)getRandomCatlike;
                myCat2.getCatFoodBrand();
            } else if (c instanceof  Tiger){
                Log.d(MainActivity.TAG, "This is a tiger");
            } else {
                Log.d(MainActivity.TAG, "This is a catlike")
            }
        }
    }
}
