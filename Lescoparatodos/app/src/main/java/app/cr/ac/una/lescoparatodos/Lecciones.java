package app.cr.ac.una.lescoparatodos;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Lecciones extends AppCompatActivity {

    TextView tituloSuperior;
    TextView tipo;
    ImageView imagen;
    TextView nombre;
    ImageButton continuar;

    VariablesGlobales vg;
    Intent intentoLlegada;
    Intent intentoIda;
    Intent intentoMenu;

    int contador,
            contadorAbecedario,
            contadorDiasMeses,
            contadorVerbos,
            seleccion;
    int arregloAbecedario[];
    int arregloDiasMeses[];
    int arregloVerbos[];

    final Handler handler = new Handler();

    private static final int LECCION_ABECEDARIO = 1;
    private static final int LECCION_DIAS_MESES = 2;
    private static final int LECCION_VERBOS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecciones);

        // A continuación mi código para OnCreate

        tituloSuperior = findViewById(R.id.tituloSuperior);
        imagen = findViewById(R.id.imagen_a_mostrar);
        nombre = findViewById(R.id.nombre);
        tipo = findViewById(R.id.tipo);
        continuar = findViewById(R.id.botonContinuar);

        vg = VariablesGlobales.getInstance();

        arregloAbecedario = new int[30];
        arregloDiasMeses = new int[19];
        arregloVerbos = new int[19];


        int numero_elementos = 0;
        int aleatorio;
        boolean encontrado;

        intentoLlegada = getIntent();
        seleccion = intentoLlegada.getIntExtra("seleccion", 0);

        switch (seleccion) {
            case LECCION_ABECEDARIO:
                tituloSuperior.setText("Aprendiendo el abecedario...");
                tituloSuperior.setBackgroundColor(Color.parseColor("#6cad99"));
                tipo.setText("LETRA");
                continuar.setBackgroundResource(R.drawable.botonderecha);

                recuperaProgreso(LECCION_ABECEDARIO);
               // numero_elementos = 0;
                contador = contadorAbecedario;
                llenoArreglo(LECCION_ABECEDARIO);
/*
                //Hasta que el numero de elementos no sea como el de la longitud del array no salimos

                    while (numero_elementos < 30) {

                        aleatorio = generaNumeroAleatorio(0, 30);
                        encontrado = false;

                        //Buscamos si el numero existe

                        for (int i = 0; i < arregloAbecedario.length && !encontrado; i++) {
                            if (aleatorio == arregloAbecedario[i]) {
                                encontrado = true;
                            }
                        }


                        //Si no esta lo agregamos
                        if (!encontrado) {
                            arregloAbecedario[numero_elementos] = aleatorio;
                            numero_elementos++;
                        }
                    }
                }*/
                nombre.setText('"' + vg.db.ObtenerNombre(arregloAbecedario[contador]) + '"');
                Glide.with(Lecciones.this)
                        .load(vg.db.ObtenerDireccion(arregloAbecedario[contador]))
                        .fitCenter()
                        .into(imagen);
                contador++;


                //Programamos el evento onclick

                continuar.setOnClickListener(new View.OnClickListener() {

                    @Override

                    public void onClick(View arg0) {
                        if (contador > 29) {
                            continuar.setEnabled(false);
                            MensajeOK("Lección concluida satisfactoriamente...");

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    intentoIda = new Intent(getApplicationContext(), MenuLecciones.class);
                                    startActivity(intentoIda);
                                }
                            }, 2500);


                        } else {
                            nombre.setText('"' + vg.db.ObtenerNombre(arregloAbecedario[contador]) + '"');
                            Glide.with(Lecciones.this)
                                    .load(vg.db.ObtenerDireccion(arregloAbecedario[contador]))
                                    .fitCenter()
                                    .into(imagen);
                            contador++;
                            contadorAbecedario = contador;
                        }

                    }

                });
                break;


            case LECCION_DIAS_MESES:
                tituloSuperior.setText("Aprendiendo los días y meses...");
                tituloSuperior.setBackgroundColor(Color.parseColor("#FF8000"));
                continuar.setBackgroundResource(R.drawable.botonnaranja);

                recuperaProgreso(LECCION_DIAS_MESES);
                //numero_elementos = 0;
                contador = contadorDiasMeses;
                llenoArreglo(LECCION_DIAS_MESES);
                /*
                //Hasta que el numero de elementos no sea como el de la longitud del array no salimos
                if (verificaVacio(LECCION_DIAS_MESES)) {
                    while (numero_elementos < 19) {

                        aleatorio = generaNumeroAleatorio(31, 49);
                        encontrado = false;

                        //Buscamos si el numero existe

                        for (int i = 0; i < arregloDiasMeses.length && !encontrado; i++) {
                            if (aleatorio == arregloDiasMeses[i]) {
                                encontrado = true;
                            }
                        }


                        //Si no esta lo agregamos
                        if (!encontrado) {
                            arregloDiasMeses[numero_elementos] = aleatorio;
                            numero_elementos++;
                        }
                    }


                    if (arregloDiasMeses[contador] < 38) {
                        tipo.setText("DÍA");
                    } else {
                        tipo.setText("MES");
                    }
                }*/
                nombre.setText("'" + vg.db.ObtenerNombre(arregloDiasMeses[contador]) + "'");
                Glide.with(Lecciones.this)
                        .load(vg.db.ObtenerDireccion(arregloDiasMeses[contador]))
                        .fitCenter()
                        .into(imagen);
                contador++;


                //Programamos el evento onclick

                continuar.setOnClickListener(new View.OnClickListener() {

                    @Override

                    public void onClick(View arg0) {
                        if (contador > 18) {
                            continuar.setEnabled(false);
                            MensajeOK("Lección concluida satisfactoriamente...");

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    intentoIda = new Intent(getApplicationContext(), MenuLecciones.class);
                                    startActivity(intentoIda);
                                }
                            }, 2500);

                        } else {

                            if (arregloDiasMeses[contador] < 38) {
                                tipo.setText("DÍA");
                            } else {
                                tipo.setText("MES");
                            }

                            nombre.setText("'" + vg.db.ObtenerNombre(arregloDiasMeses[contador]) + "'");
                            Glide.with(Lecciones.this)
                                    .load(vg.db.ObtenerDireccion(arregloDiasMeses[contador]))
                                    .fitCenter()
                                    .into(imagen);
                            contador++;
                            contadorDiasMeses = contador;
                        }

                    }

                });
                break;


            case LECCION_VERBOS:
                tipo.setText("VERBO");

                recuperaProgreso(LECCION_VERBOS);
                //numero_elementos = 0;
                contador = contadorVerbos;
                llenoArreglo(LECCION_VERBOS);
                /*
                //Hasta que el numero de elementos no sea como el de la longitud del array no salimos
                if (verificaVacio(LECCION_VERBOS)) {
                    while (numero_elementos < 19) {

                        aleatorio = generaNumeroAleatorio(50, 68);
                        encontrado = false;

                        //Buscamos si el numero existe

                        for (int i = 0; i < arregloVerbos.length && !encontrado; i++) {
                            if (aleatorio == arregloVerbos[i]) {
                                encontrado = true;
                            }
                        }


                        //Si no esta lo agregamos
                        if (!encontrado) {
                            arregloVerbos[numero_elementos] = aleatorio;
                            numero_elementos++;
                        }
                    }
                }*/
                nombre.setText('"' + vg.db.ObtenerNombre(arregloVerbos[contador]) + '"');

                Glide.with(Lecciones.this)
                        .load(vg.db.ObtenerDireccion(arregloVerbos[contador]))
                        .fitCenter()
                        .into(imagen);
                contador++;


                //Programamos el evento onclick

                continuar.setOnClickListener(new View.OnClickListener() {

                    @Override

                    public void onClick(View arg0) {
                        if (contador > 18) {
                            continuar.setEnabled(false);
                            MensajeOK("Lección concluida satisfactoriamente...");

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    intentoIda = new Intent(getApplicationContext(), MenuLecciones.class);
                                    startActivity(intentoIda);
                                }
                            }, 2500);


                            contador = 0;
                        } else {
                            nombre.setText("'" + vg.db.ObtenerNombre(arregloVerbos[contador]) + "'");
                            Glide.with(Lecciones.this)
                                    .load(vg.db.ObtenerDireccion(arregloVerbos[contador]))
                                    .fitCenter()
                                    .into(imagen);
                            contador++;
                            contadorVerbos = contador;
                        }

                    }

                });
                break;
        }


    } // Fin del Oncreate de la Actividad Lecciones


    public void Mensaje(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void llenoArreglo(int leccion){
        switch (leccion){
            case LECCION_ABECEDARIO:
                llenoConAleatorios(LECCION_ABECEDARIO, arregloAbecedario, 30, 0, 30);
                break;

            case LECCION_DIAS_MESES:
                llenoConAleatorios(LECCION_DIAS_MESES, arregloDiasMeses, 19, 31, 49);
                break;

            case LECCION_VERBOS:
                llenoConAleatorios(LECCION_VERBOS, arregloVerbos, 19, 50, 68);
                break;
        }

    }

    public void llenoConAleatorios(int leccion, int[] arreglo, int tamCiclo, int limInferior, int limSuperior) {
        int numero_elementos = 0;
        int aleatorio;
        boolean encontrado;

        if (verificaVacio(leccion)) {
            //Hasta que el numero de elementos no sea como el de la longitud del array no salimos
            while (numero_elementos < tamCiclo) {

                aleatorio = generaNumeroAleatorio(limInferior, limSuperior);
                encontrado = false;

                //Buscamos si el numero existe

                for (int i = 0; i < arreglo.length && !encontrado && arreglo[i] != 0; i++) {
                    if (aleatorio == arreglo[i]) {
                        encontrado = true;
                    }
                }


                //Si no esta lo agregamos
                if (!encontrado) {
                    arreglo[numero_elementos] = aleatorio;
                    numero_elementos++;
                }
            }
        }
    }

    public static int generaNumeroAleatorio(int minimo, int maximo) {

        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
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
                intentoMenu = new Intent(getApplicationContext(), MenuLecciones.class);
                startActivity(intentoMenu);
                break;
            case R.id.item2:
                intentoMenu = new Intent(getApplicationContext(), MenuPracticas.class);
                startActivity(intentoMenu);
                break;
            case R.id.item3:
                intentoMenu = new Intent(getApplicationContext(), Consultas.class);
                startActivity(intentoMenu);
                break;

            case R.id.item4:
                guardaProgreso(seleccion);
                break;


            default:
                Mensaje("No clasificado");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void guardaProgreso(int leccion) {
        boolean exito = false;
        exito = guardaEnArchivo(leccion);
        if (exito) {
            Mensaje("Progreso guardado");
            return;
        }
        Mensaje("Error al guardar el progreso");
    }


    public boolean guardaEnArchivo(int leccion) {
        String nombreArchivo= getNombreArchivo(leccion);


        try {
            FileOutputStream fileOutputStream =
                    openFileOutput(nombreArchivo, MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            switch (leccion) {
                case LECCION_ABECEDARIO:
                    outputStreamWriter.write(Arrays.toString(this.arregloAbecedario) + "\n");
                    outputStreamWriter.write(String.valueOf(this.contadorAbecedario) + "\n");
                    break;

                case LECCION_DIAS_MESES:
                    outputStreamWriter.write(Arrays.toString(this.arregloDiasMeses) + "\n");
                    outputStreamWriter.write(String.valueOf(this.contadorDiasMeses) + "\n");
                    break;

                case LECCION_VERBOS:
                    outputStreamWriter.write(Arrays.toString(this.arregloVerbos) + "\n");
                    outputStreamWriter.write(String.valueOf(this.contadorVerbos));
                    break;
            }

            outputStreamWriter.close();
            return true;
        } catch (Exception e) {
            System.out.print("Error al guardar el progreso " + e.getMessage());
            return false;
        }

    }

    public void leeArchivo(int leccion) {
        String nombreArchivo = getNombreArchivo(leccion);
        try {
            FileInputStream fileInputStream = openFileInput(nombreArchivo);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            switch (leccion) {
                case LECCION_ABECEDARIO:
                this.arregloAbecedario = parseoString(bufferedReader.readLine());
                this.contadorAbecedario = Integer.parseInt(bufferedReader.readLine());
                break;

                case LECCION_DIAS_MESES:
                this.arregloDiasMeses = parseoString(bufferedReader.readLine());
                this.contadorDiasMeses = Integer.parseInt(bufferedReader.readLine());
                break;

                case LECCION_VERBOS:
                this.arregloVerbos = parseoString(bufferedReader.readLine());
                this.contadorVerbos = Integer.parseInt(bufferedReader.readLine());
                break;
            }
            bufferedReader.close();

        } catch (Exception e) {
            System.out.println("Error al leer el archivo " + e.getMessage());
            reiniciaProgreso(leccion);
        }
    }

    private static int[] parseoString(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        int result[] = new int[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }

    public void reiniciaProgreso(int leccion) {
        switch (leccion){
            case LECCION_ABECEDARIO:
                arregloAbecedario = new int[30];
                contadorAbecedario = 0;
                break;

            case LECCION_DIAS_MESES:
                arregloDiasMeses = new int[19];
                contadorDiasMeses = 0;
                break;

            case LECCION_VERBOS:
                arregloVerbos = new int[19];
                contadorVerbos = 0;
                break;
        }
        llenoArreglo(LECCION_ABECEDARIO);
    }

    public boolean verificaVacio(int leccion) {
        switch (leccion) {
            case LECCION_ABECEDARIO:
                if (this.arregloAbecedario[0] == 0)
                    return true;
                return false;

            case LECCION_DIAS_MESES:
                if (this.arregloDiasMeses[0] == 0)
                    return true;
                return false;

            case LECCION_VERBOS:
                if (this.arregloVerbos[0] == 0)
                    return true;
                return false;
        }
        return true;
    }

    public String getNombreArchivo(int leccion){
        switch (leccion){
            case LECCION_ABECEDARIO:
                return "ProgresoAbecedario.txt";

            case LECCION_DIAS_MESES:
                return "ProgresoDiasMeses.txt";

            case LECCION_VERBOS:
                return "ProgresoVerbos.txt";
        }
        return "";
    }

    public void recuperaProgreso(int leccion) {
        leeArchivo(leccion);
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


} // [01:33:06 p.m.] Fin de la Clase Actividad LeccionVerbos