package com.course.innopolis.habsrss.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Cristina on 21.07.2017.
 */

public class HabrArticle implements Parcelable {

    private String title;
    private String description;
    private String pubDate;
    private String creator;
    private String categories;

    public HabrArticle() {
    }

    public HabrArticle(String title, String description, String pubDate, String creator, String categories) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.creator = creator;
        this.categories = categories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Упаковка объекта в Parcel
    public void writeToParcel(Parcel parcel, int flag){
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(pubDate);
        parcel.writeString(creator);
        parcel.writeString(categories);
    }

    // Creator необходим, чтобы создавать новые объекты или массивы объектов
    public static final Parcelable.Creator<HabrArticle> CREATOR = new Parcelable.Creator<HabrArticle>() {
        public HabrArticle createFromParcel(Parcel in) {
            return new HabrArticle(in);
        }
        public HabrArticle[] newArray(int size) {
            return new HabrArticle[size];
        }
    };

    // Конструктор, считывающий данные из Parcel
    private HabrArticle(Parcel parcel){
        title = parcel.readString();
        description = parcel.readString();
        pubDate = parcel.readString();
        creator = parcel.readString();
        categories = parcel.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategorys(String categories) {
        this.categories = categories;
    }
}
