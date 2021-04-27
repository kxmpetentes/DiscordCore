package de.kxmpetentes.engine.utils.trycatch;

/**
 * @author ScaredDev
 * created on Dez, 2020
 * implemented by kxmpetentes on 26/04/21
 */
@FunctionalInterface
public interface TryCatchRunnable {

    static TryCatchRunnable of(TryCatchRunnable tryRunnable) {
        return tryRunnable;
    }

    void run() throws Throwable;

    default Runnable ignored() {
        return () -> {
            try {
                run();
            } catch (final Throwable ignored) {
                //Ignore the exception
            }
        };
    }
}
