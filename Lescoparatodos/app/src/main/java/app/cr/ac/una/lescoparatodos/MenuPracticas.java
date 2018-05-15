package app.cr.ac.una.lescoparatodos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuPracticas extends AppCompatActivity {
    ListView list;
    Intent intento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_practicas);
        // A continuación mi código para OnCreate


        LeccionesPracticas leccionesPracticas_datos[] = new LeccionesPracticas[]{
                new LeccionesPracticas(R.drawable.ap, "Prácticar el abecedario"),
                new LeccionesPracticas(R.drawable.ds, "Prácticar los dias y los meses"),
                new LeccionesPracticas(R.drawable.vp, "Prácticar verbos"),
                new LeccionesPracticas(R.drawable.np, "Práctica de números"),
        };
        LeccionesPracticasAdapter adapter=new LeccionesPracticasAdapter(this,R.layout.list_item_row,leccionesPracticas_datos);
        list = (ListView) findViewById(R.id.listViewPracticas);

        View header=(View)getLayoutInflater().inflate(R.layout.list_header_row_practicas,null);
        list.addHeaderView(header);
        list.setAdapter(adapter);

       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if(position==1){
                   verificaAvanceSalvado_y_mensajeDeConfirmacion("ProgresoAbecedarioPractica.txt",4);
               }
               if(position==2){
                   verificaAvanceSalvado_y_mensajeDeConfirmacion("ProgresoDiasMesesPractica.txt",5);

               }
               if(position==3){
                   verificaAvanceSalvado_y_mensajeDeConfirmacion("ProgresoVerbosPractica.txt",6);

               }
               if(position==4){
                   verificaAvanceSalvado_y_mensajeDeConfirmacion("ProgresoNumerosPractica.txt",7);
               }
           }
       });


    } // Fin del Oncreate de la Actividad menuPracticas

    @Override
    public void onBackPressed() {
        intento = new Intent(getApplicationContext(), MenuPrincipal.class);
        startActivity(intento);
    }

    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_de_opciones, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1: intento = new Intent(getApplicationContext(), MenuLecciones.class);
                startActivity(intento);  break;
            case R.id.item2:  intento = new Intent(getApplicationContext(), MenuPracticas.class);
                startActivity(intento); break;
            case R.id.item3: intento = new Intent(getApplicationContext(), Consultas.class);
                startActivity(intento); break;
            default:  Mensaje("No clasificado"); break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void verificaAvanceSalvado_y_mensajeDeConfirmacion(String nombreArchivo,int seleccion){
        boolean vacio=false;
        boolean existe=true;

        final int Tam_bloque_lectura = 100;
        try
        {
            FileInputStream fIn = openFileInput(nombreArchivo);
            InputStreamReader isr = new InputStreamReader(fIn);
            char[] inputBuffer = new char[Tam_bloque_lectura];
            String s = "";
            int charRead;
            while ((charRead = isr.read(inputBuffer))>0)
            {
                //---convert the chars to a String---
                String readString =
                        String.copyValueOf(inputBuffer, 0,
                                charRead);
                s += readString;
                inputBuffer = new char[Tam_bloque_lectura];
            }
            isr.close();
            if(s==""){
                vacio=true;
            }
        }
        catch (IOException ioe) {
            existe=false;
            ioe.printStackTrace();
        }

        if(!vacio && existe){
            intento = new Intent(getApplicationContext(), Practicas.class);
            intento.putExtra("seleccion", seleccion);

            AlertDialog.Builder builder1 = new AlertDialog.Builder(MenuPracticas.this);
            builder1.setMessage("Hay un avance de ésta práctica salvado, ¿Desea recuperarlo?");
            builder1.setCancelable(true);
            builder1.setPositiveButton("Sí",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            intento.putExtra("recuperarAvancePractica", true);
                            startActivity(intento);
                        } });
            builder1.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            intento.putExtra("recuperarAvancePractica", false);
                            startActivity(intento);
                        } });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        else {
            intento = new Intent(getApplicationContext(), Practicas.class);
            intento.putExtra("seleccion", seleccion);
            startActivity(intento);
        }
    }




} // [05:16:07 p.m.] Fin de la Clase Actividad menuPracticas
