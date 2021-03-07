package de.kxmpetentes.engine.utils.minecraft

import org.apache.commons.lang3.ArrayUtils
import java.util.*

class NameHistory(private var uuid: UUID?, private var changes: Array<UUIDFetcher>) {

    init {
        ArrayUtils.reverse(this.changes)
    }

    fun getChanges(): Array<UUIDFetcher> {
        return changes
    }

    fun getUUID(): UUID? {
        return uuid
    }
}