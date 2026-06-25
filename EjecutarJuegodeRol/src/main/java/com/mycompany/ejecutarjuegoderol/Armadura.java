package com.mycompany.ejecutarjuegoderol;

public class Armadura extends Objeto{
    private int bonificadorDefensa;

    public Armadura(String nombre, int bonificadorDefensa) {
        super(nombre);
        this.bonificadorDefensa = bonificadorDefensa;
    }

    public int getBonificadorDefensa() {
        return bonificadorDefensa;
    }
}
