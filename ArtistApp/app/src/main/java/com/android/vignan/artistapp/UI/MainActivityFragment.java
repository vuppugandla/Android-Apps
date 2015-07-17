/*This class is a activity for Main Activity.
*Author: Vignan Uppugandla
 */
package com.android.vignan.artistapp.UI;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private Button websiteButton;
    private ImageButton fbButton;
    private Button imagesButton;
    private Button videosButton;
    private Button musicButton;
    private Button mailinglistButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        websiteButton = (Button)rootView.findViewById(R.id.button_website);
        fbButton = (ImageButton)rootView.findViewById(R.id.button_facebook);
        imagesButton = (Button)rootView.findViewById(R.id.button_images);
        videosButton = (Button)rootView.findViewById(R.id.button_videos);
        musicButton = (Button)rootView.findViewById(R.id.button_music);
        mailinglistButton = (Button)rootView.findViewById(R.id.button_mailing_list);

        websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://en.wikipedia.org/wiki/A._R._Rahman")));
            }
        });

        fbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/arrahman")));
            }
        });

        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MusicActivity.class);
                startActivity(intent);
            }
        });

        videosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VideosActivity.class);
                startActivity(intent);
            }
        });

        imagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WallpapersActivity.class);
                startActivity(intent);
            }
        });

        mailinglistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MailingListActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
