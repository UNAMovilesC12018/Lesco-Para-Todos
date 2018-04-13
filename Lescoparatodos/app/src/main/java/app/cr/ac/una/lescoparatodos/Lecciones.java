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

public class Lecciones extends AppCompatActivity{

    TextView tituloSuperior;
    TextView tipo;
    ImageView imagen;
    TextView nombre;
    ImageButton continuar;

    VariablesGlobales vg;
    Intent intentoLlegada;
    Intent intentoIda;
    Intent intentoMenu;

    int contador;
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

        tituloSuperior=findViewById(R.id.tituloSuperior);
        imagen = findViewById(R.id.imagen_a_mostrar);
        nombre=findViewById(R.id.nombre);
        tipo=findViewById(R.id.tipo);
        continuar=findViewById(R.id.botonContinuar);

        vg = VariablesGlobales.getInstance();

        arregloAbecedario=new int[30];
        arregloDiasMeses=new int[19];
        arregloVerbos=new int[19];

        int numero_elementos=0;
        int aleatorio;
        boolean encontrado;

        intentoLlegada = getIntent();
        int seleccion = intentoLlegada.getIntExtra("seleccion", 0);

        switch (seleccion){
          case LECCION_ABECEDARIO:
              tituloSuperior.setText("Aprendiendo el abecedario...");
              tituloSuperior.setBackgroundColor(Color.parseColor("#6cad99"));
              tipo.setText("LETRA");
              continuar.setBackgroundResource(R.drawable.botonderecha);

              numero_elementos=0;
              contador=0;

              //Hasta que el numero de elementos no sea como el de la longitud del array no salimos
              while(numero_elementos<30){

                  aleatorio=generaNumeroAleatorio(0,30);
                  encontrado=false;

                  //Buscamos si el numero existe

                  for(int i=0;i<arregloAbecedario.length && !encontrado;i++){
                      if(aleatorio==arregloAbecedario[i]){
                          encontrado=true;
                      }
                  }


                  //Si no esta lo agregamos
                  if(!encontrado){
                      arregloAbecedario[numero_elementos] = aleatorio;
                      numero_elementos++;
                  }
              }

              nombre.setText('"'+vg.db.ObtenerNombre(arregloAbecedario[contador])+'"');
              Glide.with(Lecciones.this)
                      .load(vg.db.ObtenerDireccion(arregloAbecedario[contador]))
                      .fitCenter()
                      .into(imagen);
              contador++;


              //Programamos el evento onclick

              continuar.setOnClickListener(new View.OnClickListener(){

                  @Override

                  public void onClick(View arg0) {
                      if(contador > 29){
                          continuar.setEnabled(false);
                          MensajeOK("Lección concluida satisfactoriamente...");

                          handler.postDelayed(new Runnable() { @Override public void run() {
                              intentoIda = new Intent(getApplicationContext(), MenuLecciones.class);
                              startActivity(intentoIda);
                          } }, 2500);


                      }
                      else{
                          nombre.setText('"'+vg.db.ObtenerNombre(arregloAbecedario[contador])+'"');
                          Glide.with(Lecciones.this)
                                  .load(vg.db.ObtenerDireccion(arregloAbecedario[contador]))
                                  .fitCenter()
                                  .into(imagen);
                          contador++;
                      }

                  }

              });
              break;


            case LECCION_DIAS_MESES:
                tituloSuperior.setText("Aprendiendo los días y meses...");
                tituloSuperior.setBackgroundColor(Color.parseColor("#FF8000"));
                continuar.setBackgroundResource(R.drawable.botonnaranja);

                numero_elementos=0;
                contador=0;
                //Hasta que el numero de elementos no sea como el de la longitud del array no salimos
                while(numero_elementos<19){

                    aleatorio=generaNumeroAleatorio(31,49);
                    encontrado=false;

                    //Buscamos si el numero existe

                    for(int i=0;i<arregloDiasMeses.length && !encontrado;i++){
                        if(aleatorio==arregloDiasMeses[i]){
                            encontrado=true;
                        }
                    }


                    //Si no esta lo agregamos
                    if(!encontrado){
                        arregloDiasMeses[numero_elementos] = aleatorio;
                        numero_elementos++;
                    }
                }


                if(arregloDiasMeses[contador]<38){
                    tipo.setText("DÍA");
                }
                else {
                    tipo.setText("MES");
                }

                nombre.setText("'"+vg.db.ObtenerNombre(arregloDiasMeses[contador])+"'");
                Glide.with(Lecciones.this)
                        .load(vg.db.ObtenerDireccion(arregloDiasMeses[contador]))
                        .fitCenter()
                        .into(imagen);
                contador++;


                //Programamos el evento onclick

                continuar.setOnClickListener(new View.OnClickListener(){

                    @Override

                    public void onClick(View arg0) {
                        if(contador >18){
                            continuar.setEnabled(false);
                            MensajeOK("Lección concluida satisfactoriamente...");

                            handler.postDelayed(new Runnable() { @Override public void run() {
                                intentoIda = new Intent(getApplicationContext(), MenuLecciones.class);
                                startActivity(intentoIda);
                            } }, 2500);

                        }
                        else{

                            if(arregloDiasMeses[contador]<38){
                                tipo.setText("DÍA");
                            }
                            else {
                                tipo.setText("MES");
                            }

                            nombre.setText("'"+vg.db.ObtenerNombre(arregloDiasMeses[contador])+"'");
                            Glide.with(Lecciones.this)
                                    .load(vg.db.ObtenerDireccion(arregloDiasMeses[contador]))
                                    .fitCenter()
                                    .into(imagen);
                            contador++;
                        }

                    }

                });
                break;



            case LECCION_VERBOS:
                tipo.setText("VERBO");

                numero_elementos=0;
                contador=0;

                //Hasta que el numero de elementos no sea como el de la longitud del array no salimos
                while(numero_elementos<19){

                    aleatorio=generaNumeroAleatorio(50,68);
                    encontrado=false;

                    //Buscamos si el numero existe

                    for(int i=0;i<arregloVerbos.length && !encontrado;i++){
                        if(aleatorio==arregloVerbos[i]){
                            encontrado=true;
                        }
                    }


                    //Si no esta lo agregamos
                    if(!encontrado){
                        arregloVerbos[numero_elementos] = aleatorio;
                        numero_elementos++;
                    }
                }

                nombre.setText('"'+vg.db.ObtenerNombre(arregloVerbos[contador])+'"');

                Glide.with(Lecciones.this)
                        .load(vg.db.ObtenerDireccion(arregloVerbos[contador]))
                        .fitCenter()
                        .into(imagen);
                contador++;


                //Programamos el evento onclick

                continuar.setOnClickListener(new View.OnClickListener(){

                    @Override

                    public void onClick(View arg0) {
                        if(contador > 18){
                            continuar.setEnabled(false);
                            MensajeOK("Lección concluida satisfactoriamente...");

                            handler.postDelayed(new Runnable() { @Override public void run() {
                                intentoIda = new Intent(getApplicationContext(), MenuLecciones.class);
                                startActivity(intentoIda);
                            } }, 2500);


                            contador=0;
                        }
                        else{
                            nombre.setText("'"+vg.db.ObtenerNombre(arregloVerbos[contador])+"'");
                            Glide.with(Lecciones.this)
                                    .load(vg.db.ObtenerDireccion(arregloVerbos[contador]))
                                    .fitCenter()
                                    .into(imagen);
                            contador++;
                        }

                    }

                });
                break;
      }



    } // Fin del Oncreate de la Actividad Lecciones





    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};




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
    }




} // [01:33:06 p.m.] Fin de la Clase Actividad LeccionVerbos