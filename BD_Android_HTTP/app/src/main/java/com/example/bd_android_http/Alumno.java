package com.example.bd_android_http;

public class Alumno {

    String numControl;
    String nombre;
    String primerAp;
    String segundoAp;
    String edad;
    String semestre;
    String carrera;

    public Alumno(String numControl, String nombre, String primerAp, String segundoAp, String edad, String semestre, String carrera) {
        this.numControl = numControl;
        this.nombre = nombre;
        this.primerAp = primerAp;
        this.segundoAp = segundoAp;
        this.edad = edad;
        this.semestre = semestre;
        this.carrera = carrera;
    }

    public String getNumControl() {
        return numControl;
    }
    public void setNumControl(String numControl) {
        this.numControl = numControl;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerAp() {
        return primerAp;
    }
    public void setPrimerAp(String primerAp) {
        this.primerAp = primerAp;
    }

    public String getSegundoAp() {
        return segundoAp;
    }
    public void setSegundoAp(String segundoAp) {
        this.segundoAp = segundoAp;
    }

    public String getEdad() {
        return edad;
    }
    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSemestre() {
        return semestre;
    }
    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

}
