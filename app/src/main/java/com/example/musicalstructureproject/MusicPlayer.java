package com.example.musicalstructureproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MusicPlayer extends AppCompatActivity {

    //These are the view declarations where the info will be displayed
    TextView songNameTextView;
    TextView artistNameTextView;
    ImageView albumArt;

    //These are the variable declarations which store the current info from the selection
    Song song;

    //These are the play/pause button declarations
    Button playBtn;
    Button pauseBtn;

    //Media player declaration
    MediaPlayer mMediaPlayer;
    int currentPosition;        //This is for the pause/play function

    //Audio manager declaration
    private AudioManager mAudioManager;

    //This is the AudioFocus change listener for the media player
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {

                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };

    //This calls the releaseMediaPlayer method when the song file is done playing
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            playBtn.setVisibility(View.VISIBLE);        //Sets the play button to be visible when the media file is done
            pauseBtn.setVisibility(View.INVISIBLE);     //Sets the pause button to be invisible when the media file is done
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        mAudioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        //Immediately releases the media player when the activity is created, just in-case an instance already exists.
        releaseMediaPlayer();

        //This uses the infoSetter method to display all the info from the selection
        infoSetter();

        //This sets the play button to VISIBLE by default
        playBtn = findViewById(R.id.play_button);
        playBtn.setVisibility(View.VISIBLE);

        //This sets the pause button to VISIBLE by default
        pauseBtn = findViewById(R.id.pause_button);
        pauseBtn.setVisibility(View.INVISIBLE);

        //Plays music
        musicPlayer();

        //Play button On Click Listener
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusic();
            }
        });

        //Pause button On Click Listener
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseMusic();
            }
        });
    }

    //This method starts playing music when audio focus is gained
    public void musicPlayer() {

        //This gets the selected song object
        Bundle bundle = getIntent().getExtras();
        song = bundle.getParcelable("songObject");

        //This gets the result of the audio focus request and assigns it to a variable
        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        //This tells the media player what to do if audio focus is granted
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

            mMediaPlayer = MediaPlayer.create(this, song.getSongFile());
            playBtn.setVisibility(View.INVISIBLE);      //Sets the play button to be invisible when music is playing
            pauseBtn.setVisibility(View.VISIBLE);       //Sets the pause button to be visible when music is playing
            mMediaPlayer.start();

            mMediaPlayer.setOnCompletionListener(mCompletionListener);
        }
    }

    //This method is for when the play button is clicked
    public void playMusic() {
        playBtn.setVisibility(View.INVISIBLE);                      //"Swaps" the play and pause buttons by setting them to be VISIBLE/INVISIBLE
        pauseBtn.setVisibility(View.VISIBLE);

        mMediaPlayer.seekTo(currentPosition);                       //Seeks to the current position
        mMediaPlayer.start();                                       //Starts playing music at current position
    }

    //This method is for when the pause button is clicked
    public void pauseMusic() {
        playBtn.setVisibility(View.VISIBLE);                        //"Swaps" the play and pause buttons by setting them to be VISIBLE/INVISIBLE
        pauseBtn.setVisibility(View.INVISIBLE);

        mMediaPlayer.pause();                                       //Pauses the playback
        currentPosition = mMediaPlayer.getCurrentPosition();        //Sets the current position of the song
    }

    //This method gets the info from the selection and displays it on the UI
    public void infoSetter() {

        //Gets the song object which was selected (The song object contains the song name, artist, and art)
        Bundle bundle = getIntent().getExtras();
        Song songInfo = bundle.getParcelable("songObject");


        //Sets the Image Res ID to the ImageView
        albumArt = findViewById(R.id.album_art_img_view);
        albumArt.setImageResource(songInfo.getSongArt());

        //Sets the song name String to the TextView
        songNameTextView = findViewById(R.id.player_song_name);
        songNameTextView.setText(songInfo.getSongName());

        //Sets the artist name String to the TextView
        artistNameTextView = findViewById(R.id.player_artist_name);
        artistNameTextView.setText(songInfo.getSongArtist());
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

        if (id == R.id.playlist_menu_item){
            goToPlaylist();
        }
        return true;
    }

    //Method which creates an intent and switches activities
    public void goToPlaylist(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    //This method is called to destroy any instances of the media player object
    private void releaseMediaPlayer() {

        if (mMediaPlayer != null) {

            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
