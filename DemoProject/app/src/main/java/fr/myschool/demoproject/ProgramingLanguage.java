package fr.myschool.demoproject;

import androidx.annotation.NonNull;

public class ProgramingLanguage {
    private String name;
    private String description;

    public ProgramingLanguage() {

    }

    public ProgramingLanguage(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
