package net.claimviz.integration;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.claimviz.config.ClaimVizConfigScreen;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenuIntegration implements ModMenuApi {

    private static final boolean CLOTH_PRESENT =
        FabricLoader.getInstance().isModLoaded("cloth-config");

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (!CLOTH_PRESENT) {
            // Without Cloth Config the settings button is greyed out.
            // Install cloth-config-fabric to enable the in-game config screen.
            return parent -> null;
        }
        // ClaimVizConfigScreen is only classloaded after this lambda is invoked,
        // which only happens when CLOTH_PRESENT is true — so no NoClassDefFoundError.
        return ClaimVizConfigScreen::build;
    }
}
