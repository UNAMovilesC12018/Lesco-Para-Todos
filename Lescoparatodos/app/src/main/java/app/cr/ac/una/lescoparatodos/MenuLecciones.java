package app.cr.ac.una.lescoparatodos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MenuLecciones extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lecciones);
        // A continuación mi código para OnCreate

        LeccionesPracticas leccionesPracticas_datos[] = new LeccionesPracticas[]{
                new LeccionesPracticas(R.drawable.ai, "Aprender abecedario"),
                new LeccionesPracticas(R.drawable.h, "Aprender saludos"),
                new LeccionesPracticas(R.drawable.v, "Aprender verbos básicos"),
                new LeccionesPracticas(R.drawable.ni, "Aprender números"),
        };
        LeccionesPracticasAdapter adapter=new LeccionesPracticasAdapter(this,R.layout.list_item_row,leccionesPracticas_datos);
         list = (ListView) findViewById(R.id.listViewLecciones);

    View header=(View)getLayoutInflater().inflate(R.layout.list_header_row_lecciones,null);
    list.addHeaderView(header);
    list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==1){
                    Intent intento = new Intent(getApplicationContext(), LeccionAbecedario.class);
                    startActivity(intento);
                }
            }
        });

    } // Fin del Oncreate de la Actividad menuLecciones

    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};



} // [04:48:07 p.m.] Fin de la Clase Actividad menuLecciones