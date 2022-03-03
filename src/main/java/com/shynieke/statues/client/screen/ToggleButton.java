package com.shynieke.statues.client.screen;

import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.TranslatableComponent;

public class ToggleButton extends Button {

	private boolean value;

	public ToggleButton(int x, int y, int width, int height, boolean defaultValue, OnPress pressedAction) {
		super(x, y, width, height, defaultValue ? new TranslatableComponent("gui.yes") : new TranslatableComponent("gui.no"), pressedAction);
		this.value = defaultValue;
	}

	public boolean getValue() {
		return this.value;
	}

	public void setValue(boolean value) {
		this.value = value;
		this.setMessage(value ? new TranslatableComponent("gui.yes") : new TranslatableComponent("gui.no"));
	}
}
