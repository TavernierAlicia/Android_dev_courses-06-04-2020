package fr.mds.javatraining.model;

import fr.mds.javatraining.MainActivity;

public class Bicycle extends Vehicle {
    private String color;
    private Wheel frontWheel;
    private Wheel backWheel;

    public Bicycle() {

    }

    public Bicycle(Wheel frontWheel, Wheel backWheel) {
        this.frontWheel = frontWheel;
        this.backWheel = backWheel;
    }

    public Bicycle(String color, Wheel frontWheel, Wheel backWheel) {
        this.frontWheel = frontWheel;
        this.backWheel = backWheel;
        this.color = color;

    }

    @Override
    void goFoward() {
        Log.d(MainActivity.TAG, "bicycle go foward");
    }

    public Wheel getFrontWheel() {return frontWheel;}
    public Wheel getColor() {return color;}
    public void setColor(String color) {this.color = color;}
    public void setFrontWheel(Wheel frontWheel) {this.frontWheel = frontWheel;}
    public Wheel getBackWheel() {return backWheel;}
    public void setBackWheel(Wheel backWheel) {this.backWheel = backWheel;}

    @Override
    public String toString() {
        return "Bicycle{"+
                "color="+color+'\'' +
                ", frontWheel="+frontWheel+", " +
                "backwheel="+backWheel+ "}";
    }

}
