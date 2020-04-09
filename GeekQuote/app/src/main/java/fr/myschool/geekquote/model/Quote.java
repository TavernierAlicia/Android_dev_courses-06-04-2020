package fr.myschool.geekquote.model;

import java.io.Serializable;
import java.util.Date;

public class Quote implements Serializable {

    private String strQuote = "";
    private int rating = 0;
    private Date creationDate;

    public Quote(String strQuote, int rating, Date creationDate) {
        this.strQuote = strQuote;
        this.rating = rating;
        this.creationDate = creationDate;
    }

    public String getStrQuote() {
        return strQuote;
    }

    public void setStrQuote(String strQuote) {
        this.strQuote = strQuote;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return this.strQuote;

    }
}
