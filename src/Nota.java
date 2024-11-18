import java.util.ArrayList;
import java.util.Scanner;

// Clase Nota
class Nota {
    private String catedra;
    private double notaExamen;

    public Nota(String catedra, double notaExamen) {
        this.catedra = catedra;
        this.notaExamen = notaExamen;
    }

    public double getNotaExamen() {
        return notaExamen;
    }

    @Override
    public String toString() {
        return "Cátedra: " + catedra + ", Nota: " + notaExamen;
    }
}

// Clase Alumno
class Alumno {
    private String nombreCompleto;
    private long legajo;
    private ArrayList<Nota> notas;

    public Alumno(String nombreCompleto, long legajo) {
        this.nombreCompleto = nombreCompleto;
        this.legajo = legajo;
        this.notas = new ArrayList<>();
    }

    public void agregarNota(Nota nota) {
        notas.add(nota);
    }

    public double calcularPromedio() {
        if (notas.isEmpty()) return 0.0;

        double suma = 0.0;
        for (Nota nota : notas) {
            suma += nota.getNotaExamen();
        }
        return suma / notas.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombreCompleto)
                .append(", Legajo: ").append(legajo)
                .append("\nNotas:\n");

        for (Nota nota : notas) {
            sb.append(nota.toString()).append("\n");
        }
        sb.append("Promedio: ").append(calcularPromedio());
        return sb.toString();
    }
}

// Class principal CargaNotas
class CargaNotas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Alumno> alumnos = new ArrayList<>();

        System.out.println("¿Cuántos alumnos desea cargar?");
        int cantidadAlumnos = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.println("Ingrese el nombre completo del alumno:");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el legajo del alumno:");
            long legajo = scanner.nextLong();
            scanner.nextLine(); // Limpiar buffer

            Alumno alumno = new Alumno(nombre, legajo);

            System.out.println("¿Cuántas notas desea cargar para este alumno?");
            int cantidadNotas = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            while (cantidadNotas <= 0) {
                System.out.println("Debe ingresar al menos una nota.");
                cantidadNotas = scanner.nextInt();
                scanner.nextLine();
            }

            for (int j = 0; j < cantidadNotas; j++) {
                System.out.println("Ingrese el nombre de la cátedra:");
                String catedra = scanner.nextLine();
                System.out.println("Ingrese la nota del examen:");
                double notaExamen = scanner.nextDouble();
                scanner.nextLine(); // Limpiar buffer

                alumno.agregarNota(new Nota(catedra, notaExamen));
            }

            alumnos.add(alumno);
        }

        // Mostrar datos cargados
        System.out.println("\n--- Datos Cargados ---");
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
            System.out.println();
        }

        scanner.close();
    }
}
