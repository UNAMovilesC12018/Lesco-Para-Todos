package app.cr.ac.una.lescoparatodos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class LeccionAbecedario extends AppCompatActivity {
    Intent intento;
    ImageView imagen;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef=storage.getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leccion_abecedario);
        // A continuación mi código para OnCreate
        imagen=findViewById(R.id.letra_a_mostrar);


/*        StorageReference pathReference = storageRef.child("abecedario/a.jpg");

        StorageReference gsReference = storage.getReferenceFromUrl("gs://lesco-para-todos-64d10.appspot.com/abecedario/a.jpg");
        Mensaje(pathReference.getMetadata().toString());

        Glide.with(this)
                .using(new FirebaseImageLoader())

                .load(pathReference)
                .into(imagen);*/


                Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/lesco-para-todos-64d10.appspot.com/o/abecedario%2Fa.jpg?alt=media&token=de84758d-15ba-4501-8240-eb15eb7e6962")
                .fitCenter()

                .into(imagen);

    } // Fin del Oncreate de la Actividad LeccionAbecedario

    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();};


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

} // [07:20:46 a.m.] Fin de la Clase Actividad LeccionAbecedario