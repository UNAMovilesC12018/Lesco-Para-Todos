package app.cr.ac.una.lescoparatodos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MenuAcercaDe extends AppCompatActivity {
    Intent intento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_acercade);


        OnclickDelButton(R.id.buttonEscuelas);
        OnclickDelButton(R.id.buttonEstudiantes);
    }

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

                    case R.id.buttonEscuelas:
                        intento = new Intent(getApplicationContext(), UbicacionesEscuelas.class);
                        startActivity(intento);
                        break;

                    case R.id.buttonEstudiantes:
                        intento = new Intent(getApplicationContext(), InfoEstudiantes.class);
                        startActivity(intento);
                        break;

                    default:
                        break;
                }// fin de casos
            }// fin del onclick
        });
    }// fin de OnclickDelButton
}
