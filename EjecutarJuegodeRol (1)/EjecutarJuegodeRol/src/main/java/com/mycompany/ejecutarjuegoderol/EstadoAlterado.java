package com.mycompany.ejecutarjuegoderol;

public abstract class EstadoAlterado {

    public abstract void aplicar(Personajes personaje);

    public abstract boolean impideAtacar();

    public abstract boolean termino();
}