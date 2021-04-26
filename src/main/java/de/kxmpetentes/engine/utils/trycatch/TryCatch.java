package de.kxmpetentes.engine.utils.trycatch;

/**
 * @author ScaredDev
 * created on Dez, 2020
 * implemented by kxmpetentes on 26/04/21
 */
public class TryCatch {

    private TryCatch() {
    }

    /**
     * creates a new Failure if the try-catch throws any exception
     *
     * @param tryCatchRunnable try-catch run
     * @return new failure with the exception if the try-catch throws any exception
     */
    public static Failure runTryCatch(TryCatchRunnable tryCatchRunnable) {
        if (tryCatchRunnable == null) return new Failure(new NullPointerException("Try Catch cannot be null"));

        try {
            tryCatchRunnable.run();
            return new Failure(null);
        } catch (Throwable throwable) {
            return new Failure(throwable);
        }
    }
}
