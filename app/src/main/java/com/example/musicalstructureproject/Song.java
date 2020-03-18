package com.example.musicalstructureproject;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Song implements Parcelable {

    private String mSongName;

    private String mSongArtist;

    private int mSongFile;

    private int mSongArt;


    //This is a basic contsructor for storing song name and artist only (Temporary)
    public Song(String songName, String songArtist) {

        mSongName = songName;
        mSongArtist = songArtist;
    }

    //Getter methods
    public String getSongName() {
        return mSongName;
    }

    public String getSongArtist() {
        return mSongArtist;
    }

    public Song(String songName, String songArtist, int songFile, int songArt) {
        mSongName = songName;
        mSongArtist = songArtist;
        mSongFile = songFile;
        mSongArt = songArt;
    }

    public int getSongFile() {
        return mSongFile;
    }

    public int getSongArt() {
        return mSongArt;
    }

    //This part makes the object parcelable
    public Song(Parcel in) {
        String[] data = new String[4];

        in.readStringArray((data));
        this.mSongName = data[0];
        this.mSongArtist = data[1];
        this.mSongFile = Integer.parseInt(data[2]);
        this.mSongArt = Integer.parseInt(data[3]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                this.mSongName, this.mSongArtist, String.valueOf(this.mSongFile),
                String.valueOf(this.mSongArt)});
    }

    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {

        @Override
        public Song createFromParcel(Parcel source) {
            return new Song(source);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}


