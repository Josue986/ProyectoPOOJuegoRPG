package com.mycompany.ejecutarjuegoderol;

public class Congelado extends EstadoAlterado {

    private int turnos;

    public Congelado(int turnos) {
        this.turnos = turnos;
    }

    @Override
    public void aplicar(Personajes personaje) {
        turnos--;
    }

    @Override
    public boolean impideAtacar() {
        return turnos > 0;
    }

    @Override
    public boolean termino() {
        return turnos <= 0;
    }
}