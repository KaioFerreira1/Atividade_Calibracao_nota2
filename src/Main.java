import calibracao.CalibracaoThread;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        long tempoInicial = System.currentTimeMillis();

        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\calibracao\\new_calibration_text.txt");

        List<String> calibrations = Files.readAllLines(path);
        int totalLinhas = calibrations.size();

        int numThreads = 4;
        int linhasPorThread = totalLinhas / numThreads;
        CountDownLatch latch = new CountDownLatch(numThreads);

        CalibracaoThread[] threads = new CalibracaoThread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int inicio = i * linhasPorThread;
            int fim = (i == numThreads - 1) ? totalLinhas : inicio + linhasPorThread;
            threads[i] = new CalibracaoThread(calibrations, inicio, fim, latch);
            threads[i].start();
        }

        latch.await();

        int somaTotal = 0;
        for (CalibracaoThread thread : threads) {
            somaTotal += thread.getSomaParcial();
        }

        System.out.println("A soma total dos valores de calibração é: " + somaTotal);
        System.out.println("Total de linhas: " + totalLinhas);

        long tempoFinal = System.currentTimeMillis();
        System.out.printf("Tempo total: %.3f ms%n", (tempoFinal - tempoInicial) / 1000d);
    }
}
