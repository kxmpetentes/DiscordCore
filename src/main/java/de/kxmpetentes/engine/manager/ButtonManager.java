package de.kxmpetentes.engine.manager;

import de.kxmpetentes.engine.model.GuiButton;

import java.util.ArrayList;
import java.util.List;

public class ButtonManager {

    private final List<GuiButton> guiButtons;

    public ButtonManager() {
        guiButtons = new ArrayList<>();
    }

    public List<GuiButton> getButtons() {
        return guiButtons;
    }

    public void registerButton(GuiButton guiButton) {
        guiButtons.add(guiButton);
    }

    public GuiButton getButtonById(String id) {
        for (GuiButton guiButton : guiButtons) {
            if (!guiButton.getButtonId().equals(id)) continue;

            return guiButton;
        }

        return null;
    }

}