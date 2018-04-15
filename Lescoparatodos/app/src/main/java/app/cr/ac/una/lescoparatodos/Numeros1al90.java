package app.cr.ac.una.lescoparatodos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;


public class Numeros1al90 extends Fragment {
    View rootView;
    VideoView videoView;
    Button empezar;

    public Numeros1al90() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_numeros1al90, container, false);
        videoView = rootView.findViewById(R.id.videoView);

        empezar=(Button)rootView.findViewById(R.id.empezar);

        //Programamos el evento onclick

        empezar.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View arg0) {
                Uri uri=Uri.parse("https://firebasestorage.googleapis.com/v0/b/lesco-para-todos-64d10.appspot.com/o/numeros%2Fnumeros1al90.mp4?alt=media&token=a6c1939e-89cf-4fa2-a5c6-d3298a77697d");
                videoView.setMediaController(new MediaController(getContext()));
                videoView.setVideoURI(uri);
                videoView.requestFocus();
                videoView.start();

            }

        });

        return rootView;
    }

}