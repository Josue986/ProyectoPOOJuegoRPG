/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejecutarjuegoderol;

/**
 *
 * @author jotue
 */
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
