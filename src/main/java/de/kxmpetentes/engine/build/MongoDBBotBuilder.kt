package de.kxmpetentes.engine.build

import de.kxmpetentes.engine.DiscordCore
import de.kxmpetentes.engine.command.CommandExecutor
import de.kxmpetentes.engine.manager.BotCreateManager
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.hooks.EventListener
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.sharding.ShardManager
import javax.security.auth.login.LoginException

class MongoDBBotBuilder {

    private val discordCore: DiscordCore
    private val token: String
    private val onlineStatus: OnlineStatus
    private val activity: Activity
    private val gatewayInents: ArrayList<GatewayIntent>
    private val eventListeners: ArrayList<EventListener>
    private val commandExecutors: ArrayList<CommandExecutor>

    constructor(
        discordCore: DiscordCore,
        token: String,
        onlineStatus: OnlineStatus,
        activity: Activity,
    ) {

        this.discordCore = discordCore
        this.token = token
        this.onlineStatus = onlineStatus
        this.activity = activity
        this.gatewayInents = ArrayList()
        this.eventListeners = ArrayList()
        this.commandExecutors = ArrayList()

        discordCore.enableMongoDB()
    }

    constructor(
        discordCore: DiscordCore,
        token: String,
        onlineStatus: OnlineStatus,
        activity: Activity,
        gatewayInents: ArrayList<GatewayIntent>,
        eventListeners: ArrayList<EventListener>,
        commandExecutors: ArrayList<CommandExecutor>,
    ) {
        this.discordCore = discordCore
        this.token = token
        this.onlineStatus = onlineStatus
        this.activity = activity
        this.gatewayInents = gatewayInents
        this.eventListeners = eventListeners
        this.commandExecutors = commandExecutors
    }

    fun getToken(): String {
        return token
    }

    fun getActivity(): Activity {
        return activity
    }

    fun getOnlineStatus(): OnlineStatus {
        return onlineStatus
    }

    @Throws(LoginException::class)
    fun getDefaultShardManager(): ShardManager {
        if (gatewayInents.isNotEmpty() && eventListeners.isNotEmpty() && commandExecutors.isNotEmpty()) {
            return BotCreateManager(discordCore,
                token,
                onlineStatus,
                activity,
                gatewayInents,
                eventListeners,
                commandExecutors).defaultShardManager
        }
        return BotCreateManager(token, activity, onlineStatus).defaultShardManager
    }

    fun getCommandShardManager(): ShardManager {
        if (gatewayInents.isNotEmpty() && eventListeners.isNotEmpty() && commandExecutors.isNotEmpty()) {
            return BotCreateManager(discordCore,
                token,
                onlineStatus,
                activity,
                gatewayInents,
                eventListeners,
                commandExecutors).commandShardManager
        }
        return BotCreateManager(token, activity, onlineStatus).commandShardManager
    }

    fun getDiscordCore(): DiscordCore {
        return discordCore
    }

}