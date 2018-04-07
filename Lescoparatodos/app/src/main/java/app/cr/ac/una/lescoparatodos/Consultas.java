package app.cr.ac.una.lescoparatodos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Consultas extends AppCompatActivity {
    Intent intento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);
        // A continuación mi código para OnCreate


    } // Fin del Oncreate de la Actividad consultas

    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_de_opciones, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
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

} // [07:01:33 a.m.] Fin de la Clase Actividad consultas