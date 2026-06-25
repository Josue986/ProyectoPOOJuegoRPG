package com.mycompany.ejecutarjuegoderol;

public class Arma extends Objeto{
    private int bonificadorAtaque;

    public Arma(String nombre, int bonificadorAtaque) {
        super(nombre);
        this.bonificadorAtaque = bonificadorAtaque;
    }

    public int getBonificadorAtaque() {
        return bonificadorAtaque;
    }
}
