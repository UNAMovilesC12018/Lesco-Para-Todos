package app.cr.ac.una.lescoparatodos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Consultas extends AppCompatActivity {
    private Intent intento;
    private VariablesGlobales variablesGlobales = VariablesGlobales.getInstance();
    private List<String> todosLosElementos = new ArrayList<>();
    private ImageView imagen;
    private AutoCompleteTextView autoCompleteConsulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);
        // A continuación mi código para OnCreate

        imagen = findViewById(R.id.imageViewConsulta);
        imagen.setVisibility(View.INVISIBLE);
        cargaListaConValores();
        cargaAutoComplete();
        cargaBoton();

    } // Fin del Oncreate de la Actividad consultas

    public void Mensaje(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


    public void cargaListaConValores() {
        int contador = 0;
        while (contador < 68) {
            String elemento = variablesGlobales.db.ObtenerNombre(contador).toUpperCase();
            this.todosLosElementos.add(elemento);
            Log.i("Elemento:", elemento);
            contador++;
        }
    }

    public void cargaBoton() {
        Button MiButton = findViewById(R.id.botonBuscarConsulta);
        MiButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View arg0) {
                String elementoABuscar = autoCompleteConsulta.getText().toString().toUpperCase();
                int posicion = obtengoPosicion(elementoABuscar);
                if (posicion >= 0) {
                    seteaImagen(posicion);
                    cierraTeclado();
                    return;
                }
                cierraTeclado();
                Mensaje("Elemento no encontrado");
                imagen.setVisibility(View.INVISIBLE);
            }

        });

    }

    public void cierraTeclado(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_de_opciones, menu);
        return true;
    }

    public int obtengoPosicion(String busqueda) {
        int tamano = this.todosLosElementos.size();
        String elemento;
        for (int i = 0; i < tamano; i++) {
            elemento = this.todosLosElementos.get(i);
            if (elemento.equals(busqueda)) {
                return i;
            }
        }
        return -1;
    }

    public void cargaAutoComplete() {
        autoCompleteConsulta = findViewById(R.id.autoCompleteConsulta);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, todosLosElementos);
        autoCompleteConsulta.setThreshold(3);
        autoCompleteConsulta.setAdapter(adapter);
    }


    public void seteaImagen(long rowID) {
        Glide.with(Consultas.this)
                .load(variablesGlobales.db.ObtenerDireccion(rowID))
                .fitCenter()
                .into(imagen);
        imagen.setVisibility(View.VISIBLE);
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

} // [07:01:33 a.m.] Fin de la Clase Actividad consultas