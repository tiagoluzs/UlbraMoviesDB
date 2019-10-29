package br.ulbra.ramon.prof.moviesdb;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

public class Movie implements Serializable {

    String id;
    String title;
    String overview;
    String thumb;

    public Movie(){

    }

    public Movie(String id, String title, String overview, String thumb) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.thumb = thumb;
    }

    @NonNull
    @Override
    public String toString() {
        return this.id + " " + this.title;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof Movie && ((Movie)obj).id.equals(this.id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
