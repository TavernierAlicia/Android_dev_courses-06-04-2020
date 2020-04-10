package fr.myschool.geekquote.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class Quote implements Serializable, Parcelable {

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

    protected Quote(Parcel in) {
        strQuote = in.readString();
        rating = in.readInt();
        long tmpCreationDate = in.readLong();
        creationDate = tmpCreationDate != -1 ? new Date(tmpCreationDate) : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(strQuote);
        parcel.writeInt(rating);
        parcel.writeLong(creationDate != null ? creationDate.getTime() : -1L);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Quote> CREATOR = new Parcelable.Creator<Quote>() {
        @Override
        public Quote createFromParcel(Parcel in) {
            return new Quote(in);
        }

        @Override
        public Quote[] newArray(int size) {
            return new Quote[size];
        }
    };
}