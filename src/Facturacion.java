import java.util.ArrayList;
import java.util.Scanner;

// Clase DetalleFactura
class DetalleFactura {
    private String codigoArticulo;
    private String nombreArticulo;
    private int cantidad;
    private double precioUnitario;
    private double descuentoItem;
    private double subtotal;

    public DetalleFactura(String codigoArticulo, String nombreArticulo, int cantidad, double precioUnitario, double descuentoItem, double subtotal) {
        this.codigoArticulo = codigoArticulo;
        this.nombreArticulo = nombreArticulo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuentoItem = descuentoItem;
        this.subtotal = subtotal;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return codigoArticulo + "\t" + nombreArticulo + "\t" + cantidad + "\t" + precioUnitario + "\t" + descuentoItem + "\t" + subtotal;
    }
}

// Clase Factura
class Factura {
    private String fechaFactura;
    private long numeroFactura;
    private String cliente;
    private double totalCalculadoFactura;
    private ArrayList<DetalleFactura> detallesFactura;

    public Factura() {
        this.detallesFactura = new ArrayList<>();
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public void setNumeroFactura(long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void agregarDetalle(DetalleFactura detalle) {
        detallesFactura.add(detalle);
    }

    public void calcularMontoTotal() {
        totalCalculadoFactura = 0.0;
        for (DetalleFactura detalle : detallesFactura) {
            totalCalculadoFactura += detalle.getSubtotal();
        }
    }

    public void imprimirFactura() {
        System.out.println("Fecha: " + fechaFactura);
        System.out.println("Número: " + numeroFactura);
        System.out.println("Cliente: " + cliente);
        System.out.println("Código\tNombre\tCantidad\tPrecio\tDescuento\tSubtotal");
        for (DetalleFactura detalle : detallesFactura) {
            System.out.println(detalle);
        }
        System.out.println("Total: " + totalCalculadoFactura);
    }
}

// Clase principal Facturacion
public class Facturacion {
    private static final String[][] articulos = {
            {"101", "Leche", "25"},
            {"102", "Gaseosa", "30"},
            {"103", "Fideos", "15"},
            {"104", "Arroz", "28"},
            {"105", "Vino", "120"},
            {"106", "Manteca", "20"},
            {"107", "Lavandina", "18"},
            {"108", "Detergente", "46"},
            {"109", "Jabón en Polvo", "96"},
            {"110", "Galletas", "60"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Factura factura = new Factura();

        // Ingresar datos de la factura
        System.out.println("Ingrese la fecha de la factura:");
        factura.setFechaFactura(scanner.nextLine());

        System.out.println("Ingrese el número de la factura:");
        long numeroFactura = scanner.nextLong();
        while (numeroFactura <= 0) {
            System.out.println("El número de factura debe ser mayor a 0. Ingrese nuevamente:");
            numeroFactura = scanner.nextLong();
        }
        factura.setNumeroFactura(numeroFactura);

        scanner.nextLine(); // Limpiar buffer
        System.out.println("Ingrese el nombre del cliente:");
        String cliente = scanner.nextLine();
        while (cliente.isEmpty()) {
            System.out.println("El nombre del cliente no puede estar vacío. Ingrese nuevamente:");
            cliente = scanner.nextLine();
        }
        factura.setCliente(cliente);

        // Cargar detalles de la factura
        boolean continuar = true;
        while (continuar) {
            System.out.println("Ingrese el código del artículo a facturar:");
            String codigoArticulo = scanner.nextLine();
            boolean encontrado = false;

            for (String[] articulo : articulos) {
                if (articulo[0].equals(codigoArticulo)) {
                    encontrado = true;
                    System.out.println("Ingrese la cantidad a facturar:");
                    int cantidad = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    double precioUnitario = Double.parseDouble(articulo[2]);
                    double descuentoItem = (cantidad > 5) ? precioUnitario * 0.1 : 0.0;
                    double subtotal = (precioUnitario - descuentoItem) * cantidad;

                    DetalleFactura detalle = new DetalleFactura(codigoArticulo, articulo[1], cantidad, precioUnitario, descuentoItem, subtotal);
                    factura.agregarDetalle(detalle);
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("El código ingresado no existe. Intente nuevamente.");
            }

            System.out.println("¿Desea agregar otro artículo? (true/false):");
            continuar = scanner.nextBoolean();
            scanner.nextLine(); // Limpiar buffer
        }

        // Calcular total y mostrar factura
        factura.calcularMontoTotal();
        factura.imprimirFactura();

        scanner.close();
    }
}
