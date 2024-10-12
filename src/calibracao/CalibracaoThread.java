package calibracao;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CalibracaoThread extends Thread {
    private List<String> linhas;
    private int inicio;
    private int fim;
    private int somaParcial;
    private CountDownLatch latch;

    public CalibracaoThread(List<String> linhas, int inicio, int fim, CountDownLatch latch) {
        this.linhas = linhas;
        this.inicio = inicio;
        this.fim = fim;
        this.latch = latch;
        this.somaParcial = 0;
    }

    public int getSomaParcial() {
        return somaParcial;
    }

    @Override
    public void run() {
        for (int i = inicio; i < fim; i++) {
            String linha = linhas.get(i);
            int valor = Calibracao.valorCalibracao(linha);
            somaParcial += valor;
            System.out.println("Linha " + (i + 1) + ": " + linha + " -> Calibração: " + valor);
        }
        latch.countDown();
    }
}
