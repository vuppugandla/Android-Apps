/*This class is a fragment from Music Activity
*Author: Vignan Uppugandla
 */
package com.android.vignan.artistapp.UI;

import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class MusicActivityFragment extends Fragment{

    private MediaPlayer mMediaPlayer;
    private RadioGroup songsRadioGroup;
    private RadioButton selectedSong;


    public MusicActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_music, container, false);

        songsRadioGroup = (RadioGroup) rootView.findViewById(R.id.songsRadioGroup);
        Button startPlayerBtn = (Button) rootView.findViewById(R.id.startSongButton);
        Button stopPlayerBtn = (Button) rootView.findViewById(R.id.stopSongButton);

        startPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int songId = songsRadioGroup.getCheckedRadioButtonId();
                selectedSong = (RadioButton) view.findViewById(songId);

                try {
                    play(songId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        stopPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.reset();
                }
            }
        });


        return rootView;
    }

    private void play(int songId)
    {
        switch(songId) {
            case R.id.song1Button:
                mMediaPlayer = MediaPlayer.create(getActivity(), R.raw.sakhi);
                break;

            case R.id.song2Button:
                mMediaPlayer = MediaPlayer.create(getActivity(), R.raw.roja);
                break;
            default: break;
        }

        mMediaPlayer.start();
    }
}
