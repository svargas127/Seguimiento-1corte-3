package Problema1;

public enum Articulo {
    PAN(1.5),
    LECHE(1.2),
    HUEVOS(2.0),
    ARROZ(1.8),
    CARNE(5.0),
    VERDURAS(3.0),
    FRUTAS(2.5),
    JUGO(1.3);

    private final double precio;

    Articulo(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }
}


