package de.kxmpetentes.engine.listener;

import de.kxmpetentes.engine.DiscordCore;
import de.kxmpetentes.engine.model.GuiButton;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ButtonListener extends ListenerAdapter {

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        String id = event.getButton().getId();

        GuiButton buttonById = DiscordCore.getInstance().getButtonManager().getButtonById(id);
        if (buttonById == null) return;

        buttonById.getEvent().accept(event);
    }

}