package com.example.musicalstructureproject;

public class Song {

    private String mSongName;

    private String mSongArtist;


    //This is a basic contsructor for storing song name and artist only (Temporary)
    public Song(String songName, String songArtist){

        mSongName = songName;
        mSongArtist = songArtist;
    }

    public String getSongName(){

        return mSongName;
    }

    public String getSongArtist(){

        return mSongArtist;
    }
}
