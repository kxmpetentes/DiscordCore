package de.kxmpetentes.engine.utils.trycatch;

import lombok.AllArgsConstructor;

import java.util.function.Consumer;

/**
 * @author ScaredDev
 * created on Dez, 2020
 * implemented by kxmpetentes on 26/04/21
 */
@AllArgsConstructor
public class Failure {

    private final Throwable throwable;

    /**
     * used for outprints after the try/catch blocks
     *
     * @param throwableConsumer
     */
    public final void onFailure(final Consumer<Throwable> throwableConsumer) {
        if (throwable == null) return;
        throwableConsumer.accept(throwable);
    }

}