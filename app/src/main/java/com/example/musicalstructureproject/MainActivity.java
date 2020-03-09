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

import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicalstructureproject.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }
}