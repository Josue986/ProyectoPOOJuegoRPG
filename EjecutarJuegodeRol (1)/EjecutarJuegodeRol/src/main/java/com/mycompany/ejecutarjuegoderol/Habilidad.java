package com.mycompany.ejecutarjuegoderol;

public interface Habilidad {
    void ejecutar(Personaje atacante, Personaje enemigo);
    String getNombre();
    int getCosto();
}