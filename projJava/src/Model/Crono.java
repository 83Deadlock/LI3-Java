package Model;

import static java.lang.System.nanoTime;

public class Crono {

    private static long inicio = 0L;
    private static long fim = 0L;

    /** Metodo que começa a contagem do tempo.
     *
     */
    public static void start() {
        fim = 0L; inicio = nanoTime();
    }

    /** Metodo que para a contagem do tempo.
     *
     * @return o tempo lido no final.
     */
    public static double stop() {
        fim = nanoTime();
        long elapsedTime = fim - inicio;
        // segundos
        return elapsedTime / 1.0E09;
    }

    /** Metodo que para a contagem do tempo com o método stop()
     *
     * @return uma String com o valor lido do stop().
     */
    public static String getTime() {
        return "" + stop();
    }

    /** Metodo que cria a linha de informaçao com o tempo contado pelos metodos start() e stop()
     *
     * @return String com a informacao do tempo contado.
     */
    public static String getTImeString() {
        return "Elapsed Time: " +getTime() + " s";
    }
}