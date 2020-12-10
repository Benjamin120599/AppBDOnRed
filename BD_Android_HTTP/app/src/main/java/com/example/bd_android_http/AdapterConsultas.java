package com.example.bd_android_http;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterConsultas extends RecyclerView.Adapter<AdapterConsultas.ViewHolder> {

    ArrayList<Alumno> listaAlumnos;

    public AdapterConsultas(ArrayList<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    @NonNull
    @Override
    public AdapterConsultas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consultas, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterConsultas.ViewHolder holder, int i) {
        holder.numControl.setText("Num. Control: "+listaAlumnos.get(i).getNumControl());
        holder.nombre.setText("Nombre: "+listaAlumnos.get(i).getNombre());
        holder.pa.setText("Primer Apellido: "+listaAlumnos.get(i).getPrimerAp());
        holder.sa.setText("Segundo Apellido: "+listaAlumnos.get(i).getSegundoAp());
        holder.edad.setText("Edad: "+listaAlumnos.get(i).getEdad());
        holder.semestre.setText("Semestre: "+listaAlumnos.get(i).getSemestre());
        holder.carrera.setText("Carrera: "+listaAlumnos.get(i).getCarrera());
    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView numControl, nombre, pa, sa, edad, semestre, carrera;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            numControl = itemView.findViewById(R.id.txt_num_control_c);
            nombre = itemView.findViewById(R.id.txt_nombre_c);
            pa = itemView.findViewById(R.id.txt_pa_c);
            sa = itemView.findViewById(R.id.txt_sa_c);
            edad = itemView.findViewById(R.id.txt_edad_c);
            semestre = itemView.findViewById(R.id.txt_semestre_c);
            carrera = itemView.findViewById(R.id.txt_carrera_c);

        }
    }
}
