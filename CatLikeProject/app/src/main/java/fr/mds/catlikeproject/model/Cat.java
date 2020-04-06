package fr.mds.catlikeproject.model;

import android.util.Log;

import fr.mds.catlikeproject.MainActivity;

public class Cat extends Catlike {
    private String catFoodBrand;

    public Cat(){

    }
    public Cat(String catFoodBrand) {
        this.catFoodBrand = catFoodBrand;
    }

    public String getCatFoodBrand() {
        return catFoodBrand;
    }

    public void setCatFoodBrand(String catFoodBrand) {
        this.catFoodBrand = catFoodBrand;
    }

    @Override
    protected void eat() {
        Log.d(MainActivity.TAG, "Eating like a cat");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "catFoodBrand='" + catFoodBrand + '\'' +
                '}';
    }
}
