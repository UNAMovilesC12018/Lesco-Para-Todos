package app.cr.ac.una.lescoparatodos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UbicacionesEscuelas extends AppCompatActivity {

    ArrayList<String> listaEscuelas = new ArrayList<>(),
            listaUbicaciones = new ArrayList<>(),
            listaURL = new ArrayList<>();
    ArrayList<ImageView> listaButtonUbicaciones = new ArrayList<>(),
            listaButtonURL = new ArrayList<>();

    Drawable iconoUbicacion,
            iconoWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicaciones);
        this.setTitle("Escuelas de lesco en el país");
        iconoUbicacion = getResources().getDrawable(R.drawable.location);
        iconoWeb = getResources().getDrawable(R.drawable.internet);
        cargoEscuelasDesdeArchivo();
        cargoEscuelasDesdelista();
    }


    private void cargoEscuelasDesdeArchivo() {
        InputStream miarchivo = getResources().openRawResource(R.raw.escuelaslesco);
        listaEscuelas.clear();
        BufferedReader br = null;
        String line, nombreTemporal;
        try {
            br = new BufferedReader(new InputStreamReader(miarchivo));
            while ((line = br.readLine()) != null) {
                listaEscuelas.add(line);
                nombreTemporal = new String(line);
                line = br.readLine();
                listaUbicaciones.add("geo:0,0?q=" + line + "(" + Uri.encode(nombreTemporal) + ")");
                line = br.readLine();
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
        TableLayout tableLayout = findViewById(R.id.layoutTablaUbicaciones);
        TextView textViewTemporal;
        ImageView imageViewTemporal;
        //Obtiene las imagenes en el tamaño deseado
        Drawable iconoUbicacionR = cambioTamanoImagen(this.iconoUbicacion),
                iconoWebR = cambioTamanoImagen(this.iconoWeb);
        int tamanoListas = this.listaEscuelas.size();
        String contenido = "";

        for (int i = 0; i < tamanoListas; i++) {
            //Crea lineas para la tabla de manera dinamica y les asigna un tamaño y margenes.
            TableRow lineaTablaTemporal = new TableRow(this);
            TableLayout.LayoutParams tamanoYPosicionTableLayout = new TableLayout.LayoutParams
                    (TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.MATCH_PARENT);
            tamanoYPosicionTableLayout.setMargins(10, 50, 10, 50);
            lineaTablaTemporal.setLayoutParams(tamanoYPosicionTableLayout);

            //Crea un textview con las escuelas obtenidas del archivo de texto
            contenido = this.listaEscuelas.get(i);
            textViewTemporal = new TextView(this);
            textViewTemporal.setText(contenido);
            textViewTemporal.setTextSize(25);
            lineaTablaTemporal.addView(textViewTemporal);

            //Crea un imageview con el icono de ubicacion y le asigna un listener para abrir la aplicacion de mapas con la ubicacion asignada
            imageViewTemporal = new ImageView(this);
            this.listaButtonUbicaciones.add(imageViewTemporal);
            imageViewTemporal.setImageDrawable(iconoUbicacionR);
            lineaTablaTemporal.addView(imageViewTemporal);
            onclickImageViewUbicacion(imageViewTemporal);

            //Crea un imageview con el icono de navegacion web y le asigna un listener para abrir el navegador con la url de la pagina
            imageViewTemporal = new ImageView(this);
            this.listaButtonURL.add(imageViewTemporal);
            imageViewTemporal.setImageDrawable(iconoWebR);
            lineaTablaTemporal.addView(imageViewTemporal);
            onclickImageViewURL(imageViewTemporal);

            tableLayout.addView(lineaTablaTemporal);
        }


    }

    private Drawable cambioTamanoImagen(Drawable imagen) {
        Bitmap bitmapTemporal = ((BitmapDrawable) imagen).getBitmap();
        return new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmapTemporal, 100, 100, true));
    }

    private void onclickImageViewUbicacion(ImageView buttonUbicacion) {
        final String ubicacion = this.listaUbicaciones.get(this.listaButtonUbicaciones.indexOf(buttonUbicacion));
        buttonUbicacion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ubicacion));
                startActivity(intent);
            }

        });
    }

    private void onclickImageViewURL(ImageView buttonURL) {
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
