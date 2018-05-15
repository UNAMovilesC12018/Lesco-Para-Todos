package app.cr.ac.una.lescoparatodos;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Practicas extends AppCompatActivity {

    TextView tituloSuperior;
    TextView pregunta;
    TextView preguntas;
    TextView aciertos;

    ImageView opcion1,opcion2,opcion3,opcion4;
    ImageView continuar;
    ImageView guardar;

    VariablesGlobales vg;
    Intent intentoLlegada;
    Intent intentoIda;
    Intent intentoMenu;

    int contador, seleccion, numeroPregunta;

    int arregloAbecedario[];
    int arregloDiasMeses[];
    int arregloVerbos[];
    int arregloNumeros[];

    int arregloImagenesAleatorias[];

    boolean recuperarAvancePractica;
    boolean enPartida;

    final Handler handler = new Handler();

    private static final int PRACTICA_ABECEDARIO = 4;
    private static final int PRACTICA_DIAS_MESES = 5;
    private static final int PRACTICA_VERBOS = 6;
    private static final int PRACTICA_NUMEROS =7;

    int posicionEnMatrixOpcionCorrecta;
    int contadorAciertos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicas);

        // A continuación mi código para OnCreate

        tituloSuperior = findViewById(R.id.tituloSuperior);

        pregunta = findViewById(R.id.pregunta);
        preguntas = findViewById(R.id.preguntas);

        aciertos=(TextView)findViewById(R.id.aciertos);

        opcion1=(ImageView)findViewById(R.id.opcion1);
        opcion2=(ImageView)findViewById(R.id.opcion2);
        opcion3=(ImageView)findViewById(R.id.opcion3);
        opcion4=(ImageView)findViewById(R.id.opcion4);


        continuar = (ImageView) findViewById(R.id.btnContinuarPractica);
        guardar=(ImageView) findViewById(R.id.btnGuardarPractica);

        vg = VariablesGlobales.getInstance();

        arregloAbecedario = new int[30];
        arregloDiasMeses = new int[19];
        arregloVerbos = new int[19];
        arregloNumeros = new int[10];

        arregloImagenesAleatorias = new int[4];


        intentoLlegada = getIntent();
        seleccion = intentoLlegada.getIntExtra("seleccion", 0);
        recuperarAvancePractica = intentoLlegada.getBooleanExtra("recuperarAvancePractica",false);
        enPartida=true;

        switch (seleccion) {
            case PRACTICA_ABECEDARIO:
                tituloSuperior.setText("Práctica-Abecedario, de click sobre la imagen correcta.");
                tituloSuperior.setBackgroundColor(Color.parseColor("#6cad99"));

                if(recuperarAvancePractica){
                    recuperaProgreso(PRACTICA_ABECEDARIO);
                }
                else{
                    llenoConAleatorios(arregloAbecedario,30,1,30);
                }


                pregunta.setText("Seña de la letra: '"+vg.db.ObtenerNombre(arregloAbecedario[contador])+"'");

                preparaMatrix(arregloAbecedario,1,30);

                numeroPregunta=contador+1;
                preguntas.setText("Pregunta:  "+numeroPregunta+"/30");
                aciertos.setText("Aciertos:  "+contadorAciertos);

                contador++;

                recuperarAvancePractica=false;

                //Programamos el evento onclick

                continuar.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                    if(enPartida){
                        Mensaje("Seleccione una opción para poder continuar..");
                    }else{
                        if (contador > 29) {
                            continuar.setEnabled(false);
                            double nota=(contadorAciertos*100)/30;
                            MensajeOK("Práctica concluida satisfactoriamente.\n \n NOTA OBTENIDA: "+nota);

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    limpiarArchivo(PRACTICA_ABECEDARIO);
                                    intentoIda = new Intent(getApplicationContext(), MenuPracticas.class);
                                    startActivity(intentoIda);
                                }
                            }, 3000);


                        } else {
                            enPartida=true;

                            opcion1.setBackgroundColor(Color.parseColor("#ffffff"));
                            opcion2.setBackgroundColor(Color.parseColor("#ffffff"));
                            opcion3.setBackgroundColor(Color.parseColor("#ffffff"));
                            opcion4.setBackgroundColor(Color.parseColor("#ffffff"));

                            opcion1.setEnabled(true);
                            opcion2.setEnabled(true);
                            opcion3.setEnabled(true);
                            opcion4.setEnabled(true);

                            pregunta.setText("Seña de la letra: '"+vg.db.ObtenerNombre(arregloAbecedario[contador])+"'");
                            preparaMatrix(arregloAbecedario,1,30);
                            contador++;

                            preguntas.setText("Pregunta:  "+contador+"/30");
                        }
                    }
                    }

                });
                break;


            case PRACTICA_DIAS_MESES:
                tituloSuperior.setText("Práctica-Días/Meses, de click sobre la imagen correcta.");
                tituloSuperior.setBackgroundColor(Color.parseColor("#FF8000"));

                if(recuperarAvancePractica){
                    recuperaProgreso(PRACTICA_DIAS_MESES);
                }
                else{
                    llenoConAleatorios(arregloDiasMeses,19,31,49);
                }

                String diaOmes="día";
                if(arregloDiasMeses[contador]>37){
                 diaOmes="mes";
                }



                pregunta.setText("Seña del "+diaOmes+ " '"+vg.db.ObtenerNombre(arregloDiasMeses[contador])+"'");

                preparaMatrix(arregloDiasMeses,31,49);

                 numeroPregunta=contador+1;
                preguntas.setText("Pregunta:  "+numeroPregunta+"/18");
                aciertos.setText("Aciertos:  "+contadorAciertos);

                contador++;

                recuperarAvancePractica=false;

                //Programamos el evento onclick

                continuar.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        if(enPartida){
                            Mensaje("Seleccione una opción para poder continuar..");
                        }else{
                            if (contador > 18) {
                                continuar.setEnabled(false);
                                double nota=(contadorAciertos*100)/19;
                                MensajeOK("Práctica concluida satisfactoriamente.\n \n NOTA OBTENIDA: "+nota);

                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        limpiarArchivo(PRACTICA_DIAS_MESES);
                                        intentoIda = new Intent(getApplicationContext(), MenuPracticas.class);
                                        startActivity(intentoIda);
                                    }
                                }, 3000);


                            } else {
                                enPartida=true;

                                opcion1.setBackgroundColor(Color.parseColor("#ffffff"));
                                opcion2.setBackgroundColor(Color.parseColor("#ffffff"));
                                opcion3.setBackgroundColor(Color.parseColor("#ffffff"));
                                opcion4.setBackgroundColor(Color.parseColor("#ffffff"));

                                opcion1.setEnabled(true);
                                opcion2.setEnabled(true);
                                opcion3.setEnabled(true);
                                opcion4.setEnabled(true);

                                String diaOmes="día";
                                if(arregloDiasMeses[contador]>37){
                                    diaOmes="mes";
                                }


                                pregunta.setText("Seña del "+diaOmes+ " '"+vg.db.ObtenerNombre(arregloDiasMeses[contador])+"'");

                                preparaMatrix(arregloDiasMeses,31,49);
                                contador++;

                                preguntas.setText("Pregunta:  "+contador+"/19");
                            }
                        }
                    }

                });
                break;


            case PRACTICA_VERBOS:

                break;

            case PRACTICA_NUMEROS:
                tituloSuperior.setText("Práctica-Números, de click sobre la imagen correcta.");
                tituloSuperior.setBackgroundColor(Color.parseColor("#FF8000"));

                if(recuperarAvancePractica){
                    recuperaProgreso(PRACTICA_NUMEROS);
                }
                else{
                    llenoConAleatorios(arregloNumeros,10,69,78);
                }



                pregunta.setText("Seña del número '"+vg.db.ObtenerNombre(arregloNumeros[contador])+"'");

                preparaMatrix(arregloNumeros,69,78);

                numeroPregunta=contador+1;
                preguntas.setText("Pregunta:  "+numeroPregunta+"/10");
                aciertos.setText("Aciertos:  "+contadorAciertos);

                contador++;

                recuperarAvancePractica=false;

                //Programamos el evento onclick

                continuar.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        if(enPartida){
                            Mensaje("Seleccione una opción para poder continuar..");
                        }else{
                            if (contador > 9) {
                                continuar.setEnabled(false);
                                double nota=(contadorAciertos*100)/10;
                                MensajeOK("Práctica concluida satisfactoriamente.\n \n NOTA OBTENIDA: "+nota);

                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        limpiarArchivo(PRACTICA_NUMEROS);
                                        intentoIda = new Intent(getApplicationContext(), MenuPracticas.class);
                                        startActivity(intentoIda);
                                    }
                                }, 3000);


                            } else {
                                enPartida=true;

                                opcion1.setBackgroundColor(Color.parseColor("#ffffff"));
                                opcion2.setBackgroundColor(Color.parseColor("#ffffff"));
                                opcion3.setBackgroundColor(Color.parseColor("#ffffff"));
                                opcion4.setBackgroundColor(Color.parseColor("#ffffff"));

                                opcion1.setEnabled(true);
                                opcion2.setEnabled(true);
                                opcion3.setEnabled(true);
                                opcion4.setEnabled(true);


                                pregunta.setText("Seña del número '"+vg.db.ObtenerNombre(arregloNumeros[contador])+"'");

                                preparaMatrix(arregloNumeros,69,78);

                                contador++;

                                preguntas.setText("Pregunta:  "+contador+"/10");
                            }
                        }
                    }

                });
                break;


        } //cierre switch





        guardar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0){
                if(enPartida){
                    guardaProgreso(seleccion);
                }
                else{
                    Mensaje("Continue al siguiente punto para poder salvar partida..");
                }
            }

        });



        OnclickDelImageView(R.id.opcion1);
        OnclickDelImageView(R.id.opcion2);
        OnclickDelImageView(R.id.opcion3);
        OnclickDelImageView(R.id.opcion4);

    } // Fin del Oncreate de la Actividad Lecciones





    public void Mensaje(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }



    public void llenoConAleatorios(int[] arreglo, int tamCiclo, int limInferior, int limSuperior) {
        int numero_elementos = 0;
        int aleatorio;
        boolean encontrado;

        //Hasta que el numero de elementos no sea como el de la longitud del array no salimos
        while (numero_elementos < tamCiclo) {

            aleatorio = generaNumeroAleatorio(limInferior, limSuperior);
            encontrado = false;

            //Buscamos si el numero existe

            for (int i = 0; i < arreglo.length && !encontrado; i++) {
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


    public void llenoConAleatoriosExeptoImagenEnPractica(int[] arreglo,int [] arregloActual, int tamCiclo, int limInferior, int limSuperior) {
        int numero_elementos = 0;
        int aleatorio;
        boolean encontrado;

        //Hasta que el numero de elementos no sea como el de la longitud del array no salimos
        while (numero_elementos < tamCiclo) {

            aleatorio = generaNumeroAleatorio(limInferior, limSuperior);
            encontrado = false;

            //Buscamos si el numero existe

            for (int i = 0; i < arreglo.length && !encontrado; i++) {
                if (aleatorio == arreglo[i] || aleatorio==arregloActual[contador]) {
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

            default:
                Mensaje("No clasificado");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void guardaProgreso(int practica) {
        boolean exito = false;
        exito = guardaEnArchivo(practica);
        if (exito) {
            Mensaje("Progreso guardado");
            return;
        }
        Mensaje("Error al guardar el progreso");
    }





    public boolean guardaEnArchivo(int practica) {
        String nombreArchivo= getNombreArchivo(practica);

        try {
            FileOutputStream fileOutputStream =
                    openFileOutput(nombreArchivo, MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            switch (practica) {
                case PRACTICA_ABECEDARIO:
                    outputStreamWriter.write(Arrays.toString(this.arregloAbecedario) + "\n");
                    break;

                case PRACTICA_DIAS_MESES:
                    outputStreamWriter.write(Arrays.toString(this.arregloDiasMeses) + "\n");
                  break;

                case PRACTICA_VERBOS:
                    outputStreamWriter.write(Arrays.toString(this.arregloVerbos) + "\n");
                    break;

                case PRACTICA_NUMEROS:
                    outputStreamWriter.write(Arrays.toString(this.arregloNumeros) + "\n");
                    break;
            }

            outputStreamWriter.write(String.valueOf(this.contador-1) + "\n");
            outputStreamWriter.write(String.valueOf(this.contadorAciertos) + "\n");
            outputStreamWriter.write(Arrays.toString(this.arregloImagenesAleatorias) + "\n");
            outputStreamWriter.write(String.valueOf(this.posicionEnMatrixOpcionCorrecta) + "\n");

            outputStreamWriter.close();
            return true;
        } catch (Exception e) {
            System.out.print("Error al guardar el progreso " + e.getMessage());
            return false;
        }
    }

    public void recuperaProgreso(int practica) {
        String nombreArchivo = getNombreArchivo(practica);
        int arregloActual[]=new int[30];
        int limiteInferior=0, limiteSuperior=0;

      try {
            FileInputStream fileInputStream = openFileInput(nombreArchivo);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            switch (practica) {
                case PRACTICA_ABECEDARIO:
                    this.arregloAbecedario = parseoString(bufferedReader.readLine());
                    arregloActual=arregloAbecedario;
                    limiteInferior=1;
                    limiteSuperior=30;
                    break;

                case PRACTICA_DIAS_MESES:
                    this.arregloDiasMeses = parseoString(bufferedReader.readLine());
                    arregloActual=arregloDiasMeses;
                    limiteInferior=31;
                    limiteSuperior=49;
                    break;

                case PRACTICA_VERBOS:
                    this.arregloVerbos = parseoString(bufferedReader.readLine());
                    arregloActual=arregloVerbos;
                    limiteInferior=50;
                    limiteSuperior=68;
                    break;

                case PRACTICA_NUMEROS:
                    this.arregloNumeros = parseoString(bufferedReader.readLine());
                    arregloActual=arregloNumeros;
                    limiteInferior=69;
                    limiteSuperior=78;
                    break;
            }

            this.contador = Integer.parseInt(bufferedReader.readLine());
            this.contadorAciertos = Integer.parseInt(bufferedReader.readLine());
            this.arregloImagenesAleatorias = parseoString(bufferedReader.readLine());
            this.posicionEnMatrixOpcionCorrecta = Integer.parseInt(bufferedReader.readLine());

            preparaMatrix(arregloActual,limiteInferior,limiteSuperior);

            bufferedReader.close();

        } catch (Exception e) {
            System.out.println("Error al leer el archivo " + e.getMessage());
        }
    }

    public void limpiarArchivo(int practica) {
        String nombreArchivo = getNombreArchivo(practica);
        try {
            FileOutputStream fOut =
                    openFileOutput(nombreArchivo, MODE_PRIVATE);

            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
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


    public String getNombreArchivo(int practica){
        switch (practica){
            case PRACTICA_ABECEDARIO:
                return "ProgresoAbecedarioPractica.txt";

            case PRACTICA_DIAS_MESES:
                return "ProgresoDiasMesesPractica.txt";

            case PRACTICA_VERBOS:
                return "ProgresoVerbosPractica.txt";

            case PRACTICA_NUMEROS:
                return "ProgresoNumerosPractica.txt";
        }
        return "";
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


    public  void preparaMatrix(int[] arregloActual,int limiteInferior, int limiteSuperior){

        if(!recuperarAvancePractica) {
            llenoConAleatoriosExeptoImagenEnPractica(arregloImagenesAleatorias, arregloActual, 4, limiteInferior, limiteSuperior);
        }

        Glide.with(Practicas.this)
                .load(vg.db.ObtenerDireccion(arregloImagenesAleatorias[0]))
                .fitCenter()
                .into(opcion1);

        Glide.with(Practicas.this)
                .load(vg.db.ObtenerDireccion(arregloImagenesAleatorias[1]))
                .fitCenter()
                .into(opcion2);

        Glide.with(Practicas.this)
                .load(vg.db.ObtenerDireccion(arregloImagenesAleatorias[2]))
                .fitCenter()
                .into(opcion3);

        Glide.with(Practicas.this)
                .load(vg.db.ObtenerDireccion(arregloImagenesAleatorias[3]))
                .fitCenter()
                .into(opcion4);

         if(!recuperarAvancePractica){
             posicionEnMatrixOpcionCorrecta = generaNumeroAleatorio(1, 4);
         }


        if(posicionEnMatrixOpcionCorrecta==1){
            Glide.with(Practicas.this)
                    .load(vg.db.ObtenerDireccion(arregloActual[contador]))
                    .fitCenter()
                    .into(opcion1);
        }

        if(posicionEnMatrixOpcionCorrecta==2){
            Glide.with(Practicas.this)
                    .load(vg.db.ObtenerDireccion(arregloActual[contador]))
                    .fitCenter()
                    .into(opcion2);
        }

        if(posicionEnMatrixOpcionCorrecta==3){
            Glide.with(Practicas.this)
                    .load(vg.db.ObtenerDireccion(arregloActual[contador]))
                    .fitCenter()
                    .into(opcion3);
        }

        if(posicionEnMatrixOpcionCorrecta==4){
            Glide.with(Practicas.this)
                    .load(vg.db.ObtenerDireccion(arregloActual[contador]))
                    .fitCenter()
                    .into(opcion4);
        }

    }


    public void OnclickDelImageView(int ref) {

        // Ejemplo  OnclickDelImageView(R.id.MiImageView);
        // 1 Doy referencia al ImageView
        View view =findViewById(ref);
        ImageView miImageView = (ImageView) view;
        //  final String msg = miImageView.getText().toString();
        // 2.  Programar el evento onclick
        miImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // if(msg.equals("Texto")){Mensaje("Texto en el botón ");};
                switch (v.getId()) {

                    case R.id.opcion1:
                        mostrarOpcionCorrecta();

                        if(posicionEnMatrixOpcionCorrecta==1){
                            Snackbar.make(findViewById(android.R.id.content), "Excelente", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            contadorAciertos++;

                            aciertos.setText("Aciertos:  "+contadorAciertos);

                        }
                        else{
                            opcion1.setBackgroundColor(Color.parseColor("#FF0000"));
                            Snackbar.make(findViewById(android.R.id.content), "Respuesta incorrecta", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                        }
                        break;

                    case R.id.opcion2:
                        mostrarOpcionCorrecta();

                        if(posicionEnMatrixOpcionCorrecta==2){
                            Snackbar.make(findViewById(android.R.id.content), "Excelente", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            contadorAciertos++;

                            aciertos.setText("Aciertos:  "+contadorAciertos);

                        }
                        else{
                            opcion2.setBackgroundColor(Color.parseColor("#FF0000"));
                            Snackbar.make(findViewById(android.R.id.content), "Respuesta incorrecta", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                        }

                        break;

                    case R.id.opcion3:

                        mostrarOpcionCorrecta();

                        if(posicionEnMatrixOpcionCorrecta==3){
                            Snackbar.make(findViewById(android.R.id.content), "Excelente", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            contadorAciertos++;

                            aciertos.setText("Aciertos:  "+contadorAciertos);

                        }
                        else{
                            opcion3.setBackgroundColor(Color.parseColor("#FF0000"));
                            Snackbar.make(findViewById(android.R.id.content), "Respuesta incorrecta", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                        }

                        break;

                    case R.id.opcion4:

                        mostrarOpcionCorrecta();

                        if(posicionEnMatrixOpcionCorrecta==4){
                            Snackbar.make(findViewById(android.R.id.content), "Excelente", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            contadorAciertos++;

                            aciertos.setText("Aciertos:  "+contadorAciertos);

                        }
                        else{
                            opcion4.setBackgroundColor(Color.parseColor("#FF0000"));
                            Snackbar.make(findViewById(android.R.id.content), "Respuesta incorrecta", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                        }

                        break;
                    default:break; }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelImageView



    public void mostrarOpcionCorrecta(){
        enPartida=false;

        opcion1.setEnabled(false);
        opcion2.setEnabled(false);
        opcion3.setEnabled(false);
        opcion4.setEnabled(false);


        if(posicionEnMatrixOpcionCorrecta==1){
            opcion1.setBackgroundColor(Color.parseColor("#00FF00"));
        }
        if(posicionEnMatrixOpcionCorrecta==2){
            opcion2.setBackgroundColor(Color.parseColor("#00FF00"));
        }
        if(posicionEnMatrixOpcionCorrecta==3){
            opcion3.setBackgroundColor(Color.parseColor("#00FF00"));
        }
        if(posicionEnMatrixOpcionCorrecta==4){
            opcion4.setBackgroundColor(Color.parseColor("#00FF00"));
        }

    }


    @Override
    public void onBackPressed(){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("¿Realmente desea salir de la práctica?");
            builder1.setCancelable(true);
            builder1.setPositiveButton("Sí",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intento = new Intent(getApplicationContext(), MenuPracticas.class);
                            startActivity(intento);
                        } });
            builder1.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        } });
            AlertDialog alert11 = builder1.create();
            alert11.show();
            }
}
