package com.mycompany.ejecutarjuegoderol;

public class AumentarFuerza extends EstadoAlterado {

    private int turnos;
    private boolean aplicado;

    public AumentarFuerza(int turnos) {
        this.turnos = turnos;
        this.aplicado = false;
    }

    @Override
    public void aplicar(Personajes personaje) {

        if (!aplicado) {
            personaje.setAtaque(personaje.getAtaque() + 20);
            aplicado = true;
            System.out.println(personaje.getNombre()
                    + " aumenta su fuerza.");
        }

        turnos--;

        if (turnos == 0) {
            personaje.setAtaque(personaje.getAtaque() - 20);
        }
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