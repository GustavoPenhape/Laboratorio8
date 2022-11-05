package com.example.laboratorio8_2022.model.beans;

public class Heroe {
    private int heroeId;
    private String nombre;
    private String genero;
    private int edad;
    private String clase;
    private String puntos_experiencia;
    private int ataque;
    private int nivel_inicial;

    public int getHeroeId() {
        return heroeId;
    }

    public void setHeroeId(int heroeId) {
        this.heroeId = heroeId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getPuntos_experiencia() {
        return puntos_experiencia;
    }

    public void setPuntos_experiencia(String puntos_experiencia) {
        this.puntos_experiencia = puntos_experiencia;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getNivel_inicial() {
        return nivel_inicial;
    }

    public void setNivel_inicial(int nivel_inicial) {
        this.nivel_inicial = nivel_inicial;
    }
}

