package PFinal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SistemaGestionProductos {
    private List<ProductoBase> inventario;

    public SistemaGestionProductos() {
        this.inventario = new ArrayList<>();
    }

    public void verificarProducto(String codigo, String nombre, int stock, boolean activo) {
        ProductoBase productoExistente = buscarProductoPorCodigo(codigo);

        if (productoExistente != null) {
            // Actualiza el producto existente
            productoExistente.setStock(stock);
            productoExistente.setActivo(activo);
            System.out.println("Producto actualizado: " + productoExistente.getNombre());
        } else {
            // Agrega un nuevo producto
            ProductoBase nuevoProducto = new ProductoCarnico(codigo, nombre, stock, activo); // Cambiar el tipo según la categoría
            if (nombre.toLowerCase().contains("vacuno")) {
                nuevoProducto = new ProductoVacuno(codigo, nombre, stock, activo);
            } else if (nombre.toLowerCase().contains("porcino")) {
                nuevoProducto = new ProductoPorcino(codigo, nombre, stock, activo);
            }
            inventario.add(nuevoProducto);
            System.out.println("Nuevo producto registrado: " + nuevoProducto.getNombre());
        }
    }

    // Buscar producto por código (manejo de excepciones)
    private ProductoBase buscarProductoPorCodigo(String codigo) {
        for (ProductoBase producto : inventario) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }
        return null;  // Producto no encontrado
    }

    // Actualizar stock por movimientos (entradas y salidas)
    public void registrarMovimiento(String codigo, int cantidad) throws ProductoNoEncontradoException {
        ProductoBase producto = buscarProductoPorCodigo(codigo);

        if (producto != null) {
            if (cantidad > 0 || (cantidad < 0 && producto.getStock() + cantidad >= 0)) {
                int nuevoStock = producto.getStock() + cantidad;
                producto.setStock(nuevoStock);
                System.out.println("Movimiento registrado. Nuevo stock de " + producto.getNombre() + ": " + nuevoStock);
            } else {
                System.out.println("No hay suficiente stock para realizar esta operación.");
            }
        } else {
            throw new ProductoNoEncontradoException("Producto con código " + codigo + " no encontrado.");
        }
    }

    // Mostrar inventario
    public void mostrarInventario() {
        System.out.println("=== Inventario Actual ===");
        for (ProductoBase producto : inventario) {
            producto.mostrarProducto();
        }
    }

    // Metodo para exportar a CSV
    public void exportarInventarioACSV(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            // Escribir cabecera del CSV
            writer.write("Codigo, Nombre, Stock, Activo\n");

            // Escribir los productos en el CSV
            for (ProductoBase producto : inventario) {
                String estadoActivo = producto.isActivo() ? "Si" : "No";
                writer.write(producto.getCodigo() + ", " + producto.getNombre() + ", " +
                        producto.getStock() + ", " + estadoActivo + "\n");
            }

            System.out.println("Inventario exportado a " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("Error al exportar el inventario a CSV: " + e.getMessage());
        }
    }
}
