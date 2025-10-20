package tp;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
 
        List<Piloto> pilotos = Arrays.asList(
            new Piloto("Max Verstappen", "Red Bull", 90.540),
            new Piloto("Lewis Hamilton", "Mercedes", 90.875),
            new Piloto("Charles Leclerc", "Ferrari", 91.002),
            new Piloto("George Russell", "Mercedes", 90.998),
            new Piloto("Sergio Perez", "Red Bull", 91.120),
            new Piloto("Carlos Sainz", "Ferrari", 91.250),
            new Piloto("Franco Colapinto", "McLaren", 91.560),
            new Piloto("Fernando Alonso", "Aston Martin", 91.805),
            new Piloto("Oscar Piastri", "McLaren", 91.680)
        );

       
        String msgMejorTiempo = pilotos.stream()
            .min(Comparator.comparingDouble(Piloto::getTiempoVuelta))
            .map(p -> "Piloto con el mejor tiempo (Pole Position):\n" + p.toString())
            .orElse("N/A");

        JOptionPane.showMessageDialog(null, msgMejorTiempo, "1. Mejor Tiempo", JOptionPane.INFORMATION_MESSAGE);

       
        String msgPeorTiempo = pilotos.stream()
            .max(Comparator.comparingDouble(Piloto::getTiempoVuelta))
            .map(p -> "Piloto con el peor tiempo:\n" + p.toString())
            .orElse("N/A");

        JOptionPane.showMessageDialog(null, msgPeorTiempo, "2. Peor Tiempo", JOptionPane.INFORMATION_MESSAGE);

        
        double promedioTiempos = pilotos.stream()
            .mapToDouble(Piloto::getTiempoVuelta)
            .average()
            .orElse(0.0);

        String msgPromedio = "Promedio de todos los tiempos de vuelta:\n" + 
                             String.format("%.3f", promedioTiempos) + "s";
                             
        JOptionPane.showMessageDialog(null, msgPromedio, "3. Promedio de Tiempos", JOptionPane.INFORMATION_MESSAGE);

        List<Piloto> clasificacionOrdenada = pilotos.stream()
            .sorted(Comparator.comparingDouble(Piloto::getTiempoVuelta))
            .collect(Collectors.toList());

        String msgClasificacion = clasificacionOrdenada.stream()
            .map(p -> String.format("%-2s", (clasificacionOrdenada.indexOf(p) + 1) + ".") + " " + p.toString())
            .collect(Collectors.joining("\n"));
        
        String tituloClasificacion = "Clasificación Completa (Mejor a Peor):\n";
        JOptionPane.showMessageDialog(null, tituloClasificacion + msgClasificacion, "4. Clasificación Ordenada", JOptionPane.PLAIN_MESSAGE);

        
        Map<String, Long> pilotosPorEscuderia = pilotos.stream()
            .collect(Collectors.groupingBy(
                Piloto::getEscuderia,
                Collectors.counting()
            ));

        String msgPorEscuderia = pilotosPorEscuderia.entrySet().stream()
            .map(entry -> entry.getKey() + ": " + entry.getValue() + " piloto(s)")
            .collect(Collectors.joining("\n"));

        String tituloEscuderia = "Cantidad de pilotos por escudería:\n";
        JOptionPane.showMessageDialog(null, tituloEscuderia + msgPorEscuderia, "5. Pilotos por Escudería", JOptionPane.INFORMATION_MESSAGE);
    }
}