package app.cr.ac.una.lescoparatodos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MenuPrincipal extends AppCompatActivity {
    Intent intento;
    VariablesGlobales vg;

    ArrayList<String> listaadjunta = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        // A continuación mi código para OnCreate
        /* Mensaje("Bienvenido a: Lesco para todos");*/
        vg = VariablesGlobales.getInstance();

        CrearYAbrirBaseDeDatos();
        vg.db.BorrarTabla();
        CrearYAbrirBaseDeDatos();
        ArchivoTextoAdjuntoALista();
        if (vg.db.NumeroRegistrosTabla() == 0) {
            AgregarDatos_A_DB();
        }


        OnclickDelButton(R.id.button);
        OnclickDelButton(R.id.button2);
        OnclickDelButton(R.id.button3);
        OnclickDelButton(R.id.buttonEstudiantes);
    } // Fin del Oncreate de la Actividad 01

    public void Mensaje(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    ;

    public void OnclickDelButton(int ref) {

        // Ejemplo  OnclickDelButton(R.id.MiButton);
        // 1 Doy referencia al Button
        View view = findViewById(ref);
        Button miButton = (Button) view;
        //  final String msg = miButton.getText().toString();
        // 2.  Programar el evento onclick
        miButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if(msg.equals("Texto")){Mensaje("Texto en el botón ");};
                switch (v.getId()) {

                    case R.id.button:

                        intento = new Intent(getApplicationContext(), MenuLecciones.class);
                        startActivity(intento);
                        break;

                    case R.id.button2:
                        intento = new Intent(getApplicationContext(), MenuPracticas.class);
                        startActivity(intento);

                        break;

                    case R.id.button3:
                        /*intento = new Intent(getApplicationContext(), .class);
                        startActivity(intento);*/
                        break;

                    case R.id.buttonEstudiantes:
                        intento = new Intent(getApplicationContext(), MenuAcercaDe.class);
                        startActivity(intento);
                        break;

                    default:
                        break;
                }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelButton


    private void ArchivoTextoAdjuntoALista() {
        InputStream miarchivo = getResources().openRawResource(R.raw.vocabulario);
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


    public void CrearYAbrirBaseDeDatos() {
        if (vg.db == null) {
            vg.db = new DBAdapter(this);
            vg.db.open();
        }
    }


    public void AgregarDatos_A_DB() {
        boolean todobien = true;
        if (vg.db != null) {
            for (int i = 0; i < listaadjunta.size(); i++) {
                String linea = listaadjunta.get(i);
                String[] partes = linea.split(",");
                String letra = partes[0];
                String direccion = partes[1];
                long resultado = vg.db.insertDato(letra, direccion);
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
            /*  Mensaje("Vocabulario Cargado con exito");*/
        } else {
            /*       Mensaje("Error al cargar el abecedario");*/
        }
    }


    public void MensajeOK(String msg) {
        View v1 = getWindow().getDecorView().getRootView();
        android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(v1.getContext());
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        android.app.AlertDialog alert11 = builder1.create();
        alert11.show();
    }


} // [04:06:13 p.m.] Fin de la Clase Actividad 01
