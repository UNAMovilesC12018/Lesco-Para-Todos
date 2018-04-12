package app.cr.ac.una.lescoparatodos;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UbicacionesEscuelas extends AppCompatActivity {
    ArrayList<String> listaEscuelas,
            listaUbicaciones,
            listaURL;
    ArrayList<Button> listaButtonUbicaciones,
            listaButtonURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicaciones);

        this.setTitle("Escuelas de lesco en el país");
        cargoEscuelasDesdeArchivo();
        cargoEscuelasDesdelista();
    }


    private void cargoEscuelasDesdeArchivo() {
        InputStream miarchivo = getResources().openRawResource(R.raw.escuelaslesco);
        listaEscuelas.clear();
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(miarchivo));
            while ((line = br.readLine()) != null) {
                listaEscuelas.add(line);
                listaUbicaciones.add("geo:" + line);
                listaURL.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void cargoEscuelasDesdelista() {
        LinearLayout layoutLinear = findViewById(R.id.layoutLinearVertical);
        TextView textViewTemporal;
        Button buttonTemporal;
        int tamanoListas = this.listaEscuelas.size();
        String contenido = "";

        for (int i = 0; i < tamanoListas; i++) {
            contenido = this.listaEscuelas.get(i);

            textViewTemporal = new TextView(this);
            textViewTemporal.setText(contenido);
            layoutLinear.addView(textViewTemporal);

            buttonTemporal = new Button(this);
            buttonTemporal.setText("UBICACION");
            this.listaButtonUbicaciones.add(buttonTemporal);
            layoutLinear.addView(buttonTemporal);
            onclickButtonUbicacion(buttonTemporal);

            contenido = this.listaURL.get(i);
            buttonTemporal = new Button(this);
            buttonTemporal.setText(contenido);
            this.listaButtonURL.add(buttonTemporal);
            layoutLinear.addView(buttonTemporal);
            onclickButtonURL(buttonTemporal);
        }


    }

    private void onclickButtonUbicacion(Button buttonUbicacion) {
        final String ubicacion = this.listaUbicaciones.get(this.listaButtonUbicaciones.indexOf(buttonUbicacion));
        buttonUbicacion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ubicacion));
                startActivity(intent);
            }

        });
    }

    private void onclickButtonURL(Button buttonURL) {
        final String sURL = this.listaURL.get(this.listaButtonURL.indexOf(buttonURL));
        buttonURL.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sURL));
                startActivity(browserIntent);
            }

        });
    }
}
