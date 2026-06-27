package com.mycompany.ejecutarjuegoderol;

import java.util.ArrayList;

public abstract class Personajes {

    protected String nombre;
    protected int vida;
    protected int ataque;
    protected int recurso;

    protected ArrayList<EstadoAlterado> estados;

    public Personajes(String nombre, int vida, int ataque, int recurso) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.recurso = recurso;
        this.estados = new ArrayList<>();
    }


    public void agregarEstado(EstadoAlterado estado) {
        estados.add(estado);
    }

    public void procesarEstados() {

        ArrayList<EstadoAlterado> eliminar = new ArrayList<>();

        for (EstadoAlterado estado : estados) {

            estado.aplicar(this);

            if (estado.termino()) {
                eliminar.add(estado);
            }
        }

        estados.removeAll(eliminar);
    }

    public boolean puedeAtacar() {

        for (EstadoAlterado estado : estados) {
            if (estado.impideAtacar()) {
                return false;
            }
        }

        return true;
    }


    public void recibirDaño(int daño) {

        vida -= daño;

        if (vida < 0) {
            vida = 0;
        }

        System.out.println(nombre + " recibe " + daño + " de daño.");
        System.out.println("Vida restante: " + vida);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

  

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getRecurso() {
        return recurso;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public abstract void mostrarMenuHabilidades();

    public abstract void usarHabilidad(Personajes enemigo, int opcion);

    public abstract String getTipoRecurso();
}