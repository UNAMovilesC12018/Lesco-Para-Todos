package app.cr.ac.una.lescoparatodos;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

public class LeccionesPracticasAdapter extends ArrayAdapter<LeccionesPracticas> {

    Context myContext;
    int myLayoutResourceID;
    LeccionesPracticas myData[];

    public LeccionesPracticasAdapter(Context context, int layoutResourceID, LeccionesPracticas[] data) {
        super(context, layoutResourceID,data);
        this.myContext = context;
        this.myLayoutResourceID=layoutResourceID;
        this.myData=data;

    }

public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        LeccionesPracticasHolder holder=null;

        if(row==null){
            LayoutInflater inflater=((Activity)myContext).getLayoutInflater();
            row=inflater.inflate(myLayoutResourceID,parent,false);
            holder=new LeccionesPracticasHolder();
            holder.image=(ImageView) row.findViewById(R.id.imageView);
            holder.textView =(TextView)row.findViewById(R.id.textView);
            row.setTag(holder);
        }else{
           holder=(LeccionesPracticasHolder)row.getTag();
        }
        LeccionesPracticas leccionesPracticas=myData[position];
        holder.textView.setText(leccionesPracticas.title);
        holder.image.setImageResource(leccionesPracticas.icon);
        return row;
     }
   static class LeccionesPracticasHolder{
        ImageView image;
        TextView textView;
    }
}
