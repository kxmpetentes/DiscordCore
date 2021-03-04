package de.kxmpetentes.engine.utils.minecraft

import java.util.*

class NameHistoryUtil {

    companion object {
        private val cacheHistory = HashMap<String, NameHistory?>()


        fun getNameHistory(name: String): NameHistory? {
            if (cacheHistory.containsKey(name)) {
                return cacheHistory[name]
            }
            val history: NameHistory? = NameHistoryUtil.requestHistory(name)
            cacheHistory[name] = history
            return history
        }

        private fun requestHistory(name: String): NameHistory? {
            val uuid: UUID = UUIDFetcher.getUUID(name) ?: return null
            return UUIDFetcher.getHistory(uuid)
        }
    }

}