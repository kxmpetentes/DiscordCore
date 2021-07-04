package de.kxmpetentes.engine.model;

import de.kxmpetentes.engine.DiscordCore;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.ButtonStyle;

import java.util.function.Consumer;

@Getter
public class GuiButton {

    private final String buttonId;
    private final String label;

    private String emojiUnicode = "";
    private String url;
    private boolean enabled = true;
    private ButtonStyle buttonStyle = ButtonStyle.PRIMARY;
    private Consumer<ButtonClickEvent> event;

    public GuiButton(String buttonId, String label) {
        this.buttonId = buttonId;
        this.label = label;
    }

    public GuiButton withEmoji(String emojiUnicode) {
        this.emojiUnicode = emojiUnicode;
        return this;
    }

    public GuiButton withUrl(String url) {
        this.url = url;
        return this;
    }

    public GuiButton withEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public GuiButton withButtonStyle(ButtonStyle buttonStyle) {
        this.buttonStyle = buttonStyle;
        return this;
    }

    public GuiButton withAction(Consumer<ButtonClickEvent> event) {
        this.event = event;
        return this;
    }

    public Button getButton() {
        if (event != null) {
            DiscordCore.getInstance().getButtonManager().registerButton(this);
        }

        Button button = Button.of(buttonStyle, buttonId, label).withDisabled(!enabled);
        if (!emojiUnicode.isEmpty()) {
            button = button.withEmoji(Emoji.fromUnicode(emojiUnicode));
        }

        if (url == null) return button;
        return button.withUrl(url);
    }

}