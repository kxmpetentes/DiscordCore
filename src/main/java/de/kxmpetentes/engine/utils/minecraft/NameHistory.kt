package de.kxmpetentes.engine.utils.minecraft

import org.apache.commons.lang3.ArrayUtils
import java.util.UUID




class NameHistory {

    private var uuid: UUID? = null
    private lateinit var changes: Array<UUIDFetcher>

    constructor(uuid: UUID?, changes: Array<UUIDFetcher>) {
        this.uuid = uuid
        this.changes = changes
        ArrayUtils.reverse(this.changes)
    }

    fun getChanges(): Array<UUIDFetcher>? {
        return changes
    }

    fun getUUID(): UUID? {
        return uuid
    }
}