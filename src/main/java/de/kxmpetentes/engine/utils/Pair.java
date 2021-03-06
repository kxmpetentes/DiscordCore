package de.kxmpetentes.engine.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 07.03.2021 um 00:35
 */

@Getter
@Setter
public class Pair<K, T> {

    private K first;
    private T second;

    public Pair() {
    }

    /**
     * Creates a pair
     *
     * @param first  is the key of the pair
     * @param second is the value of the pair
     */
    public Pair(K first, T second) {
        this.first = first;
        this.second = second;
    }

}