package app.cr.ac.una.lescoparatodos;

public class VariablesGlobales {

    DBAdapter db;
    private static VariablesGlobales instance = null;

    protected VariablesGlobales(){
     }

    public static VariablesGlobales getInstance(){

        if(instance == null){
            instance = new VariablesGlobales();
        }
        return instance;

    }



}// fin de la clase de variables globales
