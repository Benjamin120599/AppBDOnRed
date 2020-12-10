package com.example.bd_android_http;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterBajas extends RecyclerView.Adapter<AdapterBajas.ViewHolder> {

    ArrayList<Alumno> listaAlumnos;

    public AdapterBajas(ArrayList<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    @NonNull
    @Override
    public AdapterBajas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bajas, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBajas.ViewHolder holder, int i) {
        holder.numControl.setText("Num. Control: "+listaAlumnos.get(i).getNumControl());
        holder.nombre.setText("Nombre: "+listaAlumnos.get(i).getNombre());
        holder.pa.setText("Primer Apellido: "+listaAlumnos.get(i).getPrimerAp());
        holder.sa.setText("Segundo Apellido: "+listaAlumnos.get(i).getSegundoAp());
        holder.edad.setText("Edad: "+listaAlumnos.get(i).getEdad());
        holder.semestre.setText("Semestre: "+listaAlumnos.get(i).getSemestre());
        holder.carrera.setText("Carrera: "+listaAlumnos.get(i).getCarrera());
        //holder.btnEliminar.setOnClickListener(this);

       holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //BajasActivity ba = new BajasActivity();
                //ba.eliminar(holder.numControl.getText().toString());
                //BajasActivity.eliminar(holder.numControl.getText().toString());

                new BajasActivity.EliminarAlumno().execute(holder.numControl.getText().toString());

                listaAlumnos.remove(i);
                notifyDataSetChanged();

                //Toast.makeText(ba, ba.mensajeResultado?"Registro Eliminado":"No se pudo eliminar", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView numControl, nombre, pa, sa, edad, semestre, carrera;
        Button btnEliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            numControl = itemView.findViewById(R.id.txt_num_control);
            nombre = itemView.findViewById(R.id.txt_nombre);
            pa = itemView.findViewById(R.id.txt_pa);
            sa = itemView.findViewById(R.id.txt_sa);
            edad = itemView.findViewById(R.id.txt_edad);
            semestre = itemView.findViewById(R.id.txt_semestre);
            carrera = itemView.findViewById(R.id.txt_carrera);
            btnEliminar = itemView.findViewById(R.id.btn_eliminar);

        }
    }
}
