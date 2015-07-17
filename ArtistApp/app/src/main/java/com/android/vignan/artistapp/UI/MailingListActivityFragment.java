/*This class is a fragment from Mailing List Activity
*Author: Vignan Uppugandla
 */
package com.android.vignan.artistapp.UI;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A placeholder fragment containing a simple view.
 */
public class MailingListActivityFragment extends Fragment {
    private Button mailingListButton;
    public MailingListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mailing_list, container, false);

        mailingListButton = (Button) rootView.findViewById(R.id.launchEmailButton);

        mailingListButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"arr@rahman.com"});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hey Rahman");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                        "Hey, \n I am a great fan of yours. \n" +
                                "Add me to your mailing list.");

                startActivity(Intent.createChooser(emailIntent, "Sending email!!!!!"));
            }
        });
        return rootView;

    }
}
