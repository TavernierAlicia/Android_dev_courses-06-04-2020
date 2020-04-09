package fr.myschool.demoproject;

import java.io.Serializable;

public class Virus implements Serializable {
    private String name;
    private String countryOrigin;
    private int mortalityRate;

    public Virus() {
    }

    public Virus(String name, String countryOrigin, int mortalityRate) {
        this.name = name;
        this.countryOrigin = countryOrigin;
        this.mortalityRate = mortalityRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public int getMortalityRate() {
        return mortalityRate;
    }

    public void setMortalityRate(int mortalityRate) {
        this.mortalityRate = mortalityRate;
    }
}
