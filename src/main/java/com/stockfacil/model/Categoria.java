/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stockfacil.model;

/**
 *
 * @author raque
 */
public class Categoria {

    //llamar los atributos q estan en la tabla categorias
    //
    private int idCategoria;
    private String nombre;
    private String descripcion;
    private boolean activa = true;//aqui siempre va a estar activa, para indicar si esta activa o no la categoria

    //metodos q reciben valor y lo guardan 
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activa;
    }

    public void setActivo(boolean activa) {
        this.activa = activa;
    }
    

}
