package de.kxmpetentes.engine.build

import de.kxmpetentes.engine.DiscordCore
import de.kxmpetentes.engine.command.Command
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
    private val gatewayInents: Collection<GatewayIntent>
    private val eventListeners: Collection<EventListener>
    private val commandExecutors: Collection<Command>

    /**
     * @param discordCore       the discordcore the bot should run with
     * @param token             the token that should be used to build the bot
     * @param onlineStatus      the onlineStatus of the bot
     * @param activity          the activity of the bot
     */
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

    /**
     * @param discordCore       the discordcore the bot should run with
     * @param token             the token that should be used to build the bot
     * @param onlineStatus      the onlineStatus of the bot
     * @param activity          the activity of the bot
     * @param gatewayInents     the gatewayintents of the bot
     * @param eventListeners    all eventlisteners of the bot
     * @param commandExecutors  all commandexecutors of the bot
     */
    constructor(
        discordCore: DiscordCore,
        token: String,
        onlineStatus: OnlineStatus,
        activity: Activity,
        gatewayInents: Collection<GatewayIntent>,
        eventListeners: Collection<EventListener>,
        commandExecutors: Collection<Command>,
    ) {
        this.discordCore = discordCore
        this.token = token
        this.onlineStatus = onlineStatus
        this.activity = activity
        this.gatewayInents = gatewayInents
        this.eventListeners = eventListeners
        this.commandExecutors = commandExecutors
    }

    /**
     * @return the token of the bot
     */
    fun getToken(): String {
        return token
    }

    /**
     * @return the activity of the bot
     */
    fun getActivity(): Activity {
        return activity
    }

    /**
     * @return the online status of the bot
     */
    fun getOnlineStatus(): OnlineStatus {
        return onlineStatus
    }

    /**
     * @return the shardmanager of the bot
     */
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

    /**
     * @return the commandshardmanager of the bot
     */
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

    /**
     * @return the discordcore the bot is running with
     */
    fun getDiscordCore(): DiscordCore {
        return discordCore
    }

}