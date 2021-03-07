package de.kxmpetentes.engine.utils;

import de.kxmpetentes.engine.DiscordCore;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.internal.utils.PermissionUtil;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 07.03.2021 um 01:18
 */

public class DiscordUtil {

    private DiscordUtil() {
    }

    private static final Pattern mentionUserPattern = Pattern.compile("<@!?([0-9]{8,})>");
    private static final Pattern channelPattern = Pattern.compile("<#!?([0-9]{4,})>");
    private static final Pattern rolePattern = Pattern.compile("<@&([0-9]{4,})>");
    private static final Pattern anyMention = Pattern.compile("<[@#][&!]?([0-9]{4,})>");
    private static final Pattern discordId = Pattern.compile("(\\d{9,})");


    /**
     * find a text channel by name
     *
     * @param guild       the guild to search in
     * @param channelName the channel to search for
     * @return TextChannel || null
     */
    public static TextChannel findChannel(Guild guild, String channelName) {
        for (TextChannel channel : guild.getTextChannels()) {
            if (channel.getName().equalsIgnoreCase(channelName)) {
                return channel;
            }
        }
        return null;
    }

    public static String extractId(String id) {
        Matcher matcher = discordId.matcher(id);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    /**
     * check if a string has any mention
     *
     * @param search the text to match
     * @return contains mention?
     */
    public static boolean hasMention(String search) {
        return anyMention.matcher(search).matches();
    }

    public static boolean hasPermission(MessageChannel channel, User user, Permission permission) {
        if (channel == null) {
            return false;
        }
        switch (channel.getType()) {
            case PRIVATE:
                return true;
            case TEXT:
                TextChannel textChannel = (TextChannel) channel;
                return PermissionUtil.checkPermission(textChannel, textChannel.getGuild().getMember(user), permission);
            default:
                return false;
        }
    }

    /**
     * find a voice channel by name
     *
     * @param guild       the guild to search in
     * @param channelName the channel to search for
     * @return VoiceChannel || null
     */
    public static VoiceChannel findVoiceChannel(Guild guild, String channelName) {
        for (VoiceChannel channel : guild.getVoiceChannels()) {
            if (channel.getName().equalsIgnoreCase(channelName)) {
                return channel;
            }
        }
        return null;
    }

    /**
     * Gets the first channel in a guild where the bot has permission to write
     *
     * @param guild the guild to search in
     * @return TextChannel || null
     */
    public static TextChannel findFirstWriteableChannel(Guild guild) {
        for (TextChannel channel : guild.getTextChannels()) {
            if (channel.canTalk()) {
                return channel;
            }
        }
        return null;
    }

    /**
     * Checks if the string contains a mention for a role
     *
     * @param input string to check for mentions
     * @return found a mention
     */
    public static boolean isUserMention(String input) {
        return mentionUserPattern.matcher(input).find();
    }

    public static boolean isRoleMention(String input) {
        return rolePattern.matcher(input).find();
    }

    /**
     * Attempts to find a user in a channel, first look for account name then for nickname
     *
     * @param channel    the channel to look in
     * @param searchText the name to look for
     * @return IUser | null
     */
    public static Member findUserIn(TextChannel channel, String searchText) {
        List<Member> users = channel.getGuild().getMembers();
        List<Member> potential = new ArrayList<>();
        int smallestDiffIndex = 0, smallestDiff = -1;
        for (Member u : users) {
            String nick = u.getEffectiveName();
            if (nick.equalsIgnoreCase(searchText)) {
                return u;
            }
            if (nick.toLowerCase().contains(searchText)) {
                potential.add(u);
                int d = Math.abs(nick.length() - searchText.length());
                if (d < smallestDiff || smallestDiff == -1) {
                    smallestDiff = d;
                    smallestDiffIndex = potential.size() - 1;
                }
            }
        }
        if (!potential.isEmpty()) {
            return potential.get(smallestDiffIndex);
        }
        return null;
    }

    /**
     * Attempts to find a user from mention, if that fails see {@link DiscordUtil#findUserIn(TextChannel, String)}
     *
     * @param channel    the channel context
     * @param searchText the search argument
     * @return user || null
     */
    public static User findUser(TextChannel channel, String searchText) {
        if (isUserMention(searchText)) {
            return channel.getJDA().getUserById(mentionToId(searchText));
        } else {
            Member member = findUserIn(channel, searchText);
            if (member != null) {
                return member.getUser();
            }
        }
        return null;
    }

    /**
     * @param input string to check for mentions
     * @return found a mention
     */
    public static boolean isChannelMention(String input) {
        return channelPattern.matcher(input).matches();
    }

    /**
     * Converts any mention to an id
     *
     * @param mention the mention to filter
     * @return a stripped down version of the mention
     */
    public static String mentionToId(String mention) {
        String id = "";
        Matcher matcher = anyMention.matcher(mention);
        if (matcher.find()) {
            id = matcher.group(1);
        }
        return id;
    }

    /**
     * Retrieve all mentions from an input
     *
     * @param input text to check for mentions
     * @return list of all found mentions
     */
    public static List<String> getAllMentions(String input) {
        List<String> list = new ArrayList<>();
        Matcher matcher = anyMention.matcher(input);
        while (matcher.find()) {
            list.add(matcher.group(1));
        }
        return list;
    }

    /**
     * Gets a list of users with a certain role within a guild
     *
     * @param guild guild to search in
     * @param role  the role to search for
     * @return list of user with specified role
     */
    public static List<Member> getUsersByRole(Guild guild, Role role) {
        return guild.getMembersWithRoles(role);
    }

    /**
     * Checks if a user has a guild within a guild
     *
     * @param user       the user to check
     * @param guild      the guild to check in
     * @param permission the permission to check for
     * @return permission found
     */
    public static boolean hasPermission(User user, Guild guild, Permission permission) {
        return PermissionUtil.checkPermission(guild.getMember(user), permission);
    }

    /**
     * attempts to find a role within a guild
     *
     * @param guild    the guild to search in
     * @param roleName the role name to search for
     * @return role or null
     */
    public static Role findRole(Guild guild, String roleName) {
        return getRole(guild, roleName);
    }

    @Nullable
    private static Role getRole(Guild guild, String roleName) {
        List<Role> roles = guild.getRoles();
        Role containsRole = null;
        for (Role role : roles) {
            if (role.getName().equalsIgnoreCase(roleName)) {
                return role;
            }
            if (containsRole == null && role.getName().contains(roleName)) {
                containsRole = role;
            }
        }
        return containsRole;
    }

    public static Role hasRole(Guild guild, String roleName) {
        return getRole(guild, roleName);
    }

    public static BufferedImage getUserAvatar(User user) throws IOException {

        URLConnection connection = new URL(user.getAvatarUrl() != null ? user.getAvatarUrl() : user.getDefaultAvatarUrl()).openConnection();
        connection.setRequestProperty("User-Agent", "bot emily-bot");
        BufferedImage profileImg;
        try {
            profileImg = ImageIO.read(connection.getInputStream());
        } catch (Exception e) {
            DiscordCore.getInstance().getLogger().error(e.getMessage(), e);
            return null;
        }
        return profileImg;
    }
}