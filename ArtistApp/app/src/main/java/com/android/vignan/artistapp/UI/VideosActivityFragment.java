/*This class is a fragment from Videos Activity
*Author: Vignan Uppugandla
 */
package com.android.vignan.artistapp.UI;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.VideoView;


/**
 * A placeholder fragment containing a simple view.
 */
public class VideosActivityFragment extends Fragment {

    private VideoView videoView;
    private RadioButton selectedVideo;
    private RadioGroup videoGroup;


    public VideosActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_videos, container, false);

        videoGroup = (RadioGroup) rootView.findViewById(R.id.videosCatalogGroup);
        Button playButton = (Button) rootView.findViewById(R.id.startVideoBtn);

        playButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int selectId = videoGroup.getCheckedRadioButtonId();

                try
                {
                    play(selectId);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        return rootView;
    }

    private void play(int selectId) throws Exception{

        VideoView videoView =(VideoView)getActivity().findViewById(R.id.videoView);

        MediaController mediaController= new MediaController(getActivity());
        mediaController.setAnchorView(videoView);


        String uriPath  ="android.resource://" + getActivity().getPackageName() + "/";
        Uri uri = Uri.parse(uriPath + R.raw.maa_tujhe_salam);
        //Setting MediaController and URI, then starting the videoView


        switch (selectId) {
            case R.id.video1_button:
                uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/"+R.raw.maa_tujhe_salam);
                break;
            case R.id.video2_button:
                uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/"+R.raw.roobaroo_sad);
                break;
            default:
                break;
        }
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

    }
}
