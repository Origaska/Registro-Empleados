package com.team.registroempleados.models;

public class Empleado {

    private String RFC;
    private String Nombre;
    private String Apellido;
    private String Cargo;


    public Empleado() {
    }

    public Empleado(String RFC, String nombre, String apellido, String cargo) {
        this.RFC = RFC;
        Nombre = nombre;
        Apellido = apellido;
        Cargo = cargo;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        Cargo = cargo;
    }

    @Override
    public String toString() {
        return "RFC=" + RFC + '\n' +
                "Nombre:" + Nombre + ' ' +Apellido;
    }
}
