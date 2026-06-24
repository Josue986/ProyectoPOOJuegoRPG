/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejecutarjuegoderol;

/**
 *
 * @author jotue
 */
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
