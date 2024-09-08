package com.adoptaamor.adoptaamor.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pets") // Asumiendo que la tabla en la base de datos se llama "pets"
public class Pets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Asumiendo que el ID es autogenerado
    private int id;

    private String tipo;
    private String nombre;
    private String raza;
    private String tamano;
    private String cuidadosEspeciales;
    private String ubicacion;
    private int edad;
    private String gastosDeGestion;
    private String imagen;

    // Constructor vacío requerido por JPA
    public Pets() {
    }

    // Constructor completo
    public Pets(int id, String tipo, String nombre, String raza, String tamano, String cuidadosEspeciales,
            String ubicacion, int edad, String gastosDeGestion, String imagen) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.raza = raza;
        this.tamano = tamano;
        this.cuidadosEspeciales = cuidadosEspeciales;
        this.ubicacion = ubicacion;
        this.edad = edad;
        this.gastosDeGestion = gastosDeGestion;
        this.imagen = imagen;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getCuidadosEspeciales() {
        return cuidadosEspeciales;
    }

    public void setCuidadosEspeciales(String cuidadosEspeciales) {
        this.cuidadosEspeciales = cuidadosEspeciales;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGastosDeGestion() {
        return gastosDeGestion;
    }

    public void setGastosDeGestion(String gastosDeGestion) {
        this.gastosDeGestion = gastosDeGestion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    // Método para mostrar la información del Pet
    @Override
    public String toString() {
        return "Pets{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", raza='" + raza + '\'' +
                ", tamano='" + tamano + '\'' +
                ", cuidadosEspeciales='" + cuidadosEspeciales + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", edad=" + edad +
                ", gastosDeGestion='" + gastosDeGestion + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
