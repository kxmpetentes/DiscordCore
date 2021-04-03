package de.kxmpetentes.engine.model

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.PrivateChannel
import net.dv8tion.jda.api.entities.TextChannel
import org.jetbrains.annotations.Nullable
import java.awt.Color
import java.time.Duration
import java.time.OffsetDateTime

/**
 * @param title         the title of the Embed
 * @param author        the author of the Embed
 * @param image         the image of the Embed
 * @param color         the color of the Embed
 * @param text          the text to be in the Embed
 * @param thumbnail     the thumbnail right in the top right corner
 * @param footer        the footer of the Embed
 */
data class EmbedModel(
    @Nullable var title: String,
    @Nullable var author: String,
    @Nullable var image: String,
    @Nullable var color: Color,
    @Nullable var text: String,
    @Nullable var thumbnail: String,
    @Nullable var footer: String,
) {

    private var embedBuilder: EmbedBuilder = EmbedBuilder()
    private lateinit var authorLink: String
    private lateinit var description: String
    private lateinit var footerIcon: String
    private var timestamp: OffsetDateTime = OffsetDateTime.now()

    /**
     * creates the Message (must be run, if the embed should look correctly)
     */
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

    /**
     * @param name          the name of the field
     * @param value         the value of the field
     * @param inline        should the field be inline with other fields
     */
    fun addField(name: String, value: String, inline: Boolean): EmbedModel {
        embedBuilder.addField(name, value, inline)

        return this
    }

    /**
     * sends the embed to a private channel
     */
    fun sendPrivate(privateChannel: PrivateChannel) {
        privateChannel.sendMessage(embedBuilder.build()).queue()
    }

    /**
     * sends the embed to a text channel
     */
    fun sendToTextChannel(textChannel: TextChannel) {
        textChannel.sendMessage(embedBuilder.build()).queue()
    }

    /**
     * sends the embed to a text channel and deletes it after a given delay
     */
    fun sendToTextChannelAndDeleteLater(textChannel: TextChannel, delay: Long) {
        textChannel.sendMessage(embedBuilder.build()).delay(Duration.ofSeconds(delay)).flatMap(Message::delete).queue()
    }

    /**
     * replies to a message
     */
    fun reply(message: Message, mention: Boolean) {
        message.reply(embedBuilder.build()).mentionRepliedUser(mention).queue()
    }

    /**
     * replies to a message and deletes it after a given delay
     */
    fun replyAndDeleteLater(message: Message, delay: Long) {
        message.reply(embedBuilder.build()).delay(Duration.ofSeconds(delay)).flatMap(Message::delete).queue()
    }

}
