package com.example.musicalstructureproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

//This is the song list fragment
public class Fragment1 extends Fragment {

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.song_list, container, false);


        final ArrayList<Song> songs = new ArrayList<Song>();

        songs.add(new Song("Song 1", "Artist 1"));
        songs.add(new Song("Song 2", "Artist 2"));
        songs.add(new Song("Song 3", "Artist 3"));
        songs.add(new Song("Song 4", "Artist 4"));
        songs.add(new Song("Song 5", "Artist 5"));
        songs.add(new Song("Song 6", "Artist 6"));
        songs.add(new Song("Song 7", "Artist 7"));
        songs.add(new Song("Song 8", "Artist 8"));
        songs.add(new Song("Song 9", "Artist 9"));
        songs.add(new Song("Song 10", "Artist 10"));
        songs.add(new Song("Song 11", "Artist 11"));
        songs.add(new Song("Song 12", "Artist 12"));

        SongAdapter adapter = new SongAdapter(getActivity(), songs);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        return rootView;
    }

}
