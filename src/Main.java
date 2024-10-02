import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {

        long tempoInicial = System.currentTimeMillis();

        // Define o caminho do arquivo de texto
        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\calibracao\\new_calibration_text.txt");

        // Lê todas as linhas do arquivo
        List<String> calibrations = Files.readAllLines(path);

        int soma = 0;
        int count = 0;

        // Processa cada linha do arquivo
        for (String line : calibrations) {
            int valor = Calibracao.valorCalibracao(line);
            soma += valor;
            System.out.println("Linha " + (count + 1) + ": " + line + " -> Calibração: " + valor);
            count++;
        }

        System.out.println("A soma total dos valores de calibração é: " + soma);
        System.out.println("Total de linhas: " + count);

        long tempoFinal = System.currentTimeMillis();

        // Exibe o tempo de execução
        System.out.printf("%.3f ms%n", (tempoFinal - tempoInicial) / 1000d);
    }
}
