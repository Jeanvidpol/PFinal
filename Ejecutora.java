package PFinal;

public class Ejecutora {
    public static void main(String[] args) {
        // Crear el sistema de gestión de productos
        SistemaGestionProductos sistema = new SistemaGestionProductos();

        // Registrar productos (verificación y actualización)
        sistema.verificarProducto("V001", "Bife de Chorizo Vacuno", 10, true);
        sistema.verificarProducto("V002", "Costilla de Res Vacuna", 15, true);
        sistema.verificarProducto("V003", "Lomo de Res Vacuno", 8, true);
        sistema.verificarProducto("V004", "Carne Molida Vacuna", 20, true);
        sistema.verificarProducto("V005", "Cuadril Vacuno", 5, true);

        sistema.verificarProducto("P001", "Lomo de Cerdo Porcino", 12, true);
        sistema.verificarProducto("P002", "Costilla de Cerdo Porcino", 25, true);
        sistema.verificarProducto("P003", "Pernil de Cerdo Porcino", 6, true);
        sistema.verificarProducto("P004", "Chuletas de Cerdo Porcino", 18, true);
        sistema.verificarProducto("P005", "Jamon Porcino", 10, true);

        sistema.verificarProducto("C001", "Salchichon", 30, true);
        sistema.verificarProducto("C002", "Chorizo", 40, true);
        sistema.verificarProducto("C003", "Morcilla", 25, true);
        sistema.verificarProducto("C004", "Bacon", 20, true);
        sistema.verificarProducto("C005", "Longaniza", 15, true);

        // Mostrar inventario
        sistema.mostrarInventario();

        // Registrar movimientos (entradas y salidas)
        try {
            sistema.registrarMovimiento("V001", -3); // Venta de 3 Bife de Chorizo Vacuno
            sistema.registrarMovimiento("P002", 5);  // Entrada de 5 Costilla de Cerdo Porcino
            sistema.registrarMovimiento("C003", -10); // Venta de 10 Morcillas
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Mostrar inventario después de movimientos
        sistema.mostrarInventario();

        // Exportar el inventario a un archivo CSV
        sistema.exportarInventarioACSV("inventario_productos.csv");

    }
}
