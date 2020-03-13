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

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

//This is the song list fragment
public class Fragment1 extends Fragment {


    MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

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

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


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



        SongAdapter adapter = new SongAdapter(getActivity(), songs);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = songs.get(position);

                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    mMediaPlayer = MediaPlayer.create(getActivity(),song.getSongFile());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }

    private void releaseMediaPlayer() {

        if (mMediaPlayer != null) {

            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
