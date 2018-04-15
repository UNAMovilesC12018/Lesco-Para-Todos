package app.cr.ac.una.lescoparatodos;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;


public class Numeros100al10000 extends Fragment {
    View rootView;
    VideoView videoView;
    Button empezar;

    public Numeros100al10000() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_numeros100al10000, container, false);
        videoView=rootView.findViewById(R.id.videoViewEspecial2);
        empezar=rootView.findViewById(R.id.empezar2);
        empezar.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View arg0) {

                Uri uri=Uri.parse("https://firebasestorage.googleapis.com/v0/b/lesco-para-todos-64d10.appspot.com/o/numeros%2Fnumeros100al10000000.mp4?alt=media&token=af17ea6a-4add-46ef-8e5c-09382f1da7ce");
                videoView.setMediaController(new MediaController(getContext()));
                videoView.setVideoURI(uri);
                videoView.requestFocus();
                videoView.start();
            }

        });

        return rootView;
    }



}
