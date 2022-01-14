package jockeyCreator;

import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JockeyCreator implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "jockeycreator_lostallmymoney";
    public static final String MOD_NAME = "JockeyCreator";

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing Jockey Creator");
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}