import java.util.ArrayList;
import java.util.Scanner;

// Clase Ingrediente
class Ingrediente {
    private String nombre;
    private double cantidad;
    private String unidadMedida;

    public Ingrediente(String nombre, double cantidad, String unidadMedida) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
    }

    @Override
    public String toString() {
        return nombre + " " + cantidad + " " + unidadMedida;
    }
}

// Clase Plato
class Plato {
    private String nombreCompleto;
    private double precio;
    private boolean esBebida;
    private ArrayList<Ingrediente> ingredientes;

    public Plato(String nombreCompleto, double precio, boolean esBebida) {
        this.nombreCompleto = nombreCompleto;
        this.precio = precio;
        this.esBebida = esBebida;
        this.ingredientes = new ArrayList<>();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        if (!esBebida) {
            ingredientes.add(ingrediente);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombreCompleto).append("\n")
                .append("Precio: $").append(precio).append("\n");

        if (!esBebida) {
            sb.append("Ingredientes:\n");
            for (Ingrediente ingrediente : ingredientes) {
                sb.append(ingrediente.toString()).append("\n");
            }
        } else {
            sb.append("Es una bebida.\n");
        }

        return sb.toString();
    }
}

// Clase principal MenuRestaurant
public class MenuRestaurant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Plato> platosMenu = new ArrayList<>();

        System.out.println("¿Cuántos platos desea cargar?");
        int cantidadPlatos = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        for (int i = 0; i < cantidadPlatos; i++) {
            System.out.println("Ingrese el nombre del plato:");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el precio del plato:");
            double precio = scanner.nextDouble();
            System.out.println("¿Es una bebida? (true/false):");
            boolean esBebida = scanner.nextBoolean();
            scanner.nextLine(); // Limpiar buffer

            Plato plato = new Plato(nombre, precio, esBebida);

            if (!esBebida) {
                System.out.println("¿Cuántos ingredientes desea agregar para este plato?");
                int cantidadIngredientes = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                while (cantidadIngredientes <= 0) {
                    System.out.println("Debe ingresar al menos un ingrediente.");
                    cantidadIngredientes = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                }

                for (int j = 0; j < cantidadIngredientes; j++) {
                    System.out.println("Ingrese el nombre del ingrediente:");
                    String nombreIngrediente = scanner.nextLine();
                    System.out.println("Ingrese la cantidad del ingrediente:");
                    double cantidad = scanner.nextDouble();
                    System.out.println("Ingrese la unidad de medida:");
                    scanner.nextLine(); // Limpiar buffer
                    String unidadMedida = scanner.nextLine();

                    Ingrediente ingrediente = new Ingrediente(nombreIngrediente, cantidad, unidadMedida);
                    plato.agregarIngrediente(ingrediente);
                }
            }

            platosMenu.add(plato);
        }

        // Mostrar menú
        System.out.println("\n--- MENÚ DEL RESTAURANTE ---");
        for (Plato plato : platosMenu) {
            System.out.println(plato);
        }

        scanner.close();
    }
}
