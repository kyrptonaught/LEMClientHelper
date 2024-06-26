package net.kyrptonaught.lemclienthelper.hud;

import net.kyrptonaught.kyrptconfig.config.AbstractConfigFile;

public class HudConfig implements AbstractConfigFile {
    public boolean enabled = true;

    public boolean alwaysEnabled = false;

    public float armorHudScale = 1;

    public float xOffset = 20;

    public float transparency = .75f;
}
