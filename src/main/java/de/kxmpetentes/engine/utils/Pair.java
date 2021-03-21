package de.kxmpetentes.engine.utils;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 07.03.2021 um 00:35
 */

@Getter
@Setter
public class Pair<K, T> implements Serializable {

    private K key;
    private T value;

    public Pair(K key, T value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + "=" + value.getClass().getSimpleName();
    }

}