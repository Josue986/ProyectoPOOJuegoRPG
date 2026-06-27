package com.mycompany.ejecutarjuegoderol;

public class Mago extends Personajes {

    public Mago(String nombre) {
        super(nombre, 200, 15, 150);
    }

    @Override
    public void mostrarMenuHabilidades() {
        System.out.println("\n--- Mago ---");
        System.out.println("1. Hechizo");
        System.out.println("2. Congelar");
    }

    @Override
    public void usarHabilidad(Personajes enemigo, int opcion) {

        switch (opcion) {

            case 1:
                System.out.println(getNombre() + " lanza Hechizo");
                enemigo.recibirDaño(getAtaque() + 10);
                break;

            case 2:
                System.out.println(getNombre() + " congela al enemigo");
                enemigo.agregarEstado(new Congelado(1));
                break;

            default:
                System.out.println("Opción inválida");
        }
    }

    @Override
    public String getTipoRecurso() {
        return "Mana";
    }
}