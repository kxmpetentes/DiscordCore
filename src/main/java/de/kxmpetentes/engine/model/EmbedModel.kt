package de.kxmpetentes.engine.model

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.PrivateChannel
import net.dv8tion.jda.api.entities.TextChannel
import java.awt.Color
import java.time.Duration
import java.time.OffsetDateTime

data class EmbedModel(
    var title: String,
    var author: String,
    var image: String,
    var color: Color,
    var text: String,
    var thumbnail: String,
    var footer: String,
) {

    private var embedBuilder: EmbedBuilder = EmbedBuilder()
    private lateinit var authorLink: String
    private lateinit var description: String
    private lateinit var footerIcon: String
    private var timestamp: OffsetDateTime = OffsetDateTime.now()

    fun createMessage(): EmbedModel {

        embedBuilder.setTitle(title)
            .setAuthor(author, authorLink)
            .setColor(color)
            .setImage(image)
            .setDescription(description)
            .setFooter(footer, footerIcon)
            .setThumbnail(thumbnail)
            .setTimestamp(timestamp)
        return this
    }

    fun addField(name: String, value: String, inline: Boolean): EmbedModel {
        embedBuilder.addField(name, value, inline)

        return this
    }

    fun sendPrivate(privateChannel: PrivateChannel) {
        privateChannel.sendMessage(embedBuilder.build()).queue()
    }

    fun sendToTextChannel(textChannel: TextChannel) {
        textChannel.sendMessage(embedBuilder.build()).queue()
    }

    fun sendToTextChannelAndDeleteLater(textChannel: TextChannel, delay: Long) {
        textChannel.sendMessage(embedBuilder.build()).delay(Duration.ofSeconds(delay)).flatMap(Message::delete).queue()
    }

    fun reply(message: Message) {
        message.reply(embedBuilder.build()).queue()
    }

    fun replyAndDeleteLater(message: Message, delay: Long) {
        message.reply(embedBuilder.build()).delay(Duration.ofSeconds(delay)).flatMap(Message::delete).queue()
    }

}
