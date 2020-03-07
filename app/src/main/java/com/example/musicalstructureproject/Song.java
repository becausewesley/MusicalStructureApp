package com.example.musicalstructureproject;

public class Song {

    private String mSongName;

    private String mSongArtist;

    private int mSongFile;


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

    public Song (String songName, String songArtist, int songFile){

        mSongName = songName;
        mSongArtist = songArtist;
        mSongFile = songFile;

    }

    public int getSongFile(){

        return mSongFile;
    }
}


