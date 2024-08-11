package cn.crtlprototypestudios.ovslib;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OverseerLib implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(OverseerLib.class);
    public static final String MOD_ID = "ovslib";

    @Override
    public void onInitialize() {

    }

    public static Identifier of(String path){
        return Identifier.of(MOD_ID, path);
    }

    public static String template(String templateName, Identifier path){
        return String.format("%s@%s", templateName, path);
    }
}
