package app.cr.ac.una.lescoparatodos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuPrincipal extends AppCompatActivity {
    Intent intento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        // A continuación mi código para OnCreate
        Mensaje("Bienvenido a: Lesco para todos");


        OnclickDelButton(R.id.button);
        OnclickDelButton(R.id.button2);
        OnclickDelButton(R.id.button3);
    } // Fin del Oncreate de la Actividad 01

    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};
    public void OnclickDelButton(int ref) {

        // Ejemplo  OnclickDelButton(R.id.MiButton);
        // 1 Doy referencia al Button
        View view =findViewById(ref);
        Button miButton = (Button) view;
        //  final String msg = miButton.getText().toString();
        // 2.  Programar el evento onclick
        miButton.setOnClickListener(new View.OnClickListener(){
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
            /*            intento = new Intent(getApplicationContext(), .class);
                        startActivity(intento);*/

                        break;
                    default:break; }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelButton
} // [04:06:13 p.m.] Fin de la Clase Actividad 01
