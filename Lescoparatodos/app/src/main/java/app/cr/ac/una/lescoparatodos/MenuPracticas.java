package app.cr.ac.una.lescoparatodos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
                new LeccionesPracticas(R.drawable.sp, "Prácticar saludos"),
                new LeccionesPracticas(R.drawable.vp, "Prácticar verbos "),
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

               }
           }
       });


    } // Fin del Oncreate de la Actividad menuPracticas

    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};


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




} // [05:16:07 p.m.] Fin de la Clase Actividad menuPracticas
