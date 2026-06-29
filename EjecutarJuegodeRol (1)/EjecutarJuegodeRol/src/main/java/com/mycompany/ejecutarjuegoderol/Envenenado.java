package com.mycompany.ejecutarjuegoderol;

public class Envenenado extends EstadoAlterado {

    private int turnos;

    public Envenenado(int turnos) {
        this.turnos = turnos;
    }

    @Override
    public void aplicar(Personajes personaje) {

        personaje.recibirDaño(10);
        System.out.println(personaje.getNombre()
                + " recibe 10 de daño por veneno.");

        turnos--;
    }

    @Override
    public boolean impideAtacar() {
        return false;
    }

    @Override
    public boolean termino() {
        return turnos <= 0;
    }
}