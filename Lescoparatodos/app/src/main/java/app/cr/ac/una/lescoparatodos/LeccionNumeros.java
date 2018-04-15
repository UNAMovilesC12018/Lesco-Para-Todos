package app.cr.ac.una.lescoparatodos;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

public class LeccionNumeros extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leccion_numeros);

        // A continuación mi código para OnCreate

/*        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
        ViewPager viewPager=(ViewPager)findViewById(R.id.viewPager);

        viewPager.setAdapter(new SectionPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    } // Fin del Oncreate de la Actividad LeccionesNumeros


    public class SectionPagerAdapter extends FragmentPagerAdapter{

     public SectionPagerAdapter(FragmentManager fm){
         super(fm);
     }

        @Override
        public android.support.v4.app.Fragment getItem(int position){
            switch (position){
                case 0:
                    return  new Numeros1al90();

                case 1:
                    return  new Numeros100al10000();

                    default: return  new Numeros1al90();

            }

        }


        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return "Numeros del 1-90";

                case 1:
                    return  "Numeros del 100-10000";

                default: return  "";

            }
        }

    }




    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();};

} // [09:52:26 a.m.] Fin de la Clase Actividad LeccionesNumeros