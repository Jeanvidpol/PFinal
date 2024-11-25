package PFinal;

public class ProductoPorcino extends ProductoBase{
    private String categoria;


    public ProductoPorcino(String codigo, String nombre, int stock, boolean activo) {
        super(codigo, nombre, stock, activo);
    }
}
