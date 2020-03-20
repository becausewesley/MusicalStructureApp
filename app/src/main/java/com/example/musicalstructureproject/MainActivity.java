/*
 * **DISCLAIMER**
 * I (Wesley Swart) do not own any of the sound clips or images which appear
 * in this application which is to be used for academic purposes only.
 *
 * All images downloaded from https://unsplash.com/
 *
 * All sound clips downloaded from https://freesound.org/
 *
 * Sound clips courtesy of the following artists/users:
 *
 * - Joe Mattson (Flick3r) : https://freesound.org/people/Flick3r/
 * - Benjamin Mastripolito (Lemoncreme) :  https://benpm.github.io/
 * - Setuniman : https://freesound.org/people/Setuniman/
 * - JPMusic82 : https://soundcloud.com/jani-official/
 * - Milton Paredes (milton.) : https://freesound.org/people/milton./
 * - AlienXXX : https://freesound.org/people/AlienXXX/
 */

package com.example.musicalstructureproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.playlist_activity);

        //This is the array of Song objects which contain all relevant info (See Song.class)
        final ArrayList<Song> songs = new ArrayList<Song>();

        songs.add(new Song("Fairyland", "AlienXXX", R.raw.alienxxx__fairyland, R.drawable.art1));
        songs.add(new Song("Nephlim", "Flick3r", R.raw.flick3r__nephlim, R.drawable.art2));
        songs.add(new Song("Melody Loop", "JPMusic82", R.raw.jpmusic82__melody_loop, R.drawable.art3));
        songs.add(new Song("Back to the 80s", "Milton.", R.raw.milton__back_to_80s, R.drawable.art4));
        songs.add(new Song("Rhythm Guitar Riff", "AlienXXX", R.raw.alienxxx__rhythm_guitar_riff, R.drawable.art5));
        songs.add(new Song("Floating Synth Melody", "Lemoncreme", R.raw.lemoncreme__floating_synth_melody, R.drawable.art6));
        songs.add(new Song("Phat Bass Line", "Flick3r", R.raw.flick3r__phat_bass_line, R.drawable.art7));
        songs.add(new Song("Equal", "Setuniman", R.raw.setuniman__equal, R.drawable.art8));
        songs.add(new Song("Melody Mix", "JPMusic82", R.raw.jpmusic82__samples_melody_mix, R.drawable.art9));
        songs.add(new Song("Symphony Sounds", "Lemoncreme", R.raw.lemoncreme__symphony_sounds, R.drawable.art10));
        songs.add(new Song("The Fall of Icarus", "Flick3r", R.raw.flick3r__the_fall_of_icarus, R.drawable.art11));
        songs.add(new Song("Happy Again", "Setuniman", R.raw.setuniman__happy_again, R.drawable.art12));

        //This attaches the custom class adapter
        SongAdapter adapter = new SongAdapter(MainActivity.this, songs);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        //This is the onClickListener for the items in the list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = songs.get(position); //This gets which item was selected

                //This creates the intent for the new activity and sends the selected Song object through
                Intent intent = new Intent(view.getContext(), MusicPlayer.class);
                intent.putExtra("songObject", song); //This is passing a song object
                startActivity(intent);

            }
        });

    }

    //This creates the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //This gets which item is selected from the menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if (id == R.id.now_playing_menu_item){
            goToNowPlaying();
        }
        return true;
    }

    //Method which creates an intent and switches activities
    public void goToNowPlaying(){
        Intent intent = new Intent(getApplicationContext(),MusicPlayer.class);
        startActivity(intent);
    }
}
