package app.cr.ac.una.lescoparatodos;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class LeccionAbecedario extends AppCompatActivity {
    Intent intento;
    ImageView imagen;
    ImageButton MiButton;
    DBAdapter db;
    ArrayList<String> listaadjunta = new ArrayList<>();
    int contador=0;
    int arreglo[];
    final Handler handler = new Handler();





    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leccion_abecedario);
        // A continuación mi código para OnCreate
        imagen = findViewById(R.id.letra_a_mostrar);
        CrearYAbrirBaseDeDatos();
        ArchivoTextoAdjuntoALista();
        arreglo=new int[29];


        if (db.NumeroRegistrosTabla() == 0){
            AgregarDatos_A_DB();
        }

       int numero_elementos=0;
        int aleatorio;
        boolean encontrado;


            //Hasta que el numero de elementos no sea como el de la longitud del array no salimos
            while(numero_elementos<29){

                aleatorio=generaNumeroAleatorio(0,29);
                encontrado=false;

                //Buscamos si el numero existe

                for(int i=0;i<arreglo.length && !encontrado;i++){
                if(aleatorio==arreglo[i]){
                        encontrado=true;
                    }
                }


                //Si no esta lo agregamos
                if(!encontrado){
                    arreglo[numero_elementos] = aleatorio;
                    numero_elementos++;
                }
            }


            Glide.with(LeccionAbecedario.this)
                .load(db.ObtenerDireccion(arreglo[contador]))
                .fitCenter()
                .into(imagen);
             contador++;



        // alambramos el Button
         MiButton = (ImageButton) findViewById(R.id.botonContinuar);

        //Programamos el evento onclick

        MiButton.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View arg0) {
               if(contador > 28){
                   MiButton.setEnabled(false);
                   MensajeOK("Lección concluida satisfactoriamente...");

                   handler.postDelayed(new Runnable() { @Override public void run() {
                       Intent intento = new Intent(getApplicationContext(), MenuLecciones.class);
                       startActivity(intento);
                   } }, 2500);


                   contador=0;
               }
               else{

                   Glide.with(LeccionAbecedario.this)
                           .load(db.ObtenerDireccion(arreglo[contador]))
                           .fitCenter()
                           .into(imagen);
                   contador++;
               }

            }

        });

    } // Fin del Oncreate de la Actividad LeccionAbecedario

    public void Mensaje(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    public static int generaNumeroAleatorio(int minimo,int maximo){

        int num=(int)Math.floor(Math.random()*(maximo-minimo+1)+(minimo));
        return num;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_de_opciones, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                intento = new Intent(getApplicationContext(), MenuLecciones.class);
                startActivity(intento);
                break;
            case R.id.item2:
                intento = new Intent(getApplicationContext(), MenuPracticas.class);
                startActivity(intento);
                break;
            case R.id.item3:
                intento = new Intent(getApplicationContext(), Consultas.class);
                startActivity(intento);
                break;
            default:
                Mensaje("No clasificado");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void ArchivoTextoAdjuntoALista() {
        InputStream miarchivo = getResources().openRawResource(R.raw.enlacesvocabularioabecedario);
        listaadjunta.clear();
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(miarchivo));
            while ((line = br.readLine()) != null) {
                listaadjunta.add(line);
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
        return;

    }


    public void CrearYAbrirBaseDeDatos(){
        if (db == null) {
            db = new DBAdapter(this);
            db.open();
        }
    }


    public void AgregarDatos_A_DB(){
        boolean todobien = true;
        if (db != null) {
            for (int i =0; i<listaadjunta.size(); i++) {
                String linea = listaadjunta.get(i);
                String[] partes = linea.split(",");
                String letra = partes[0];
                String direccion = partes[1];
                long resultado = db.insertDato(letra,direccion);
                if (resultado < 0) {
                    todobien = false;
                }
            }
        } else {
            todobien = false;
            MensajeOK("BD nula");
            return;
        }
        if (todobien) {
            Mensaje("Abecedario Cargado con exito");
        } else {
            Mensaje("Error al cargar el abecedario");
        }
    }


    public void MensajeOK(String msg){
        View v1 = getWindow().getDecorView().getRootView();
        android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder( v1.getContext());
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {} });
        android.app.AlertDialog alert11 = builder1.create();
        alert11.show();
        ;};
}// [07:20:46 a.m.] Fin de la Clase Actividad LeccionAbecedario