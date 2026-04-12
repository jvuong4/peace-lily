package io.github.jvuong4.peacelily;

import io.github.jvuong4.peacelily.registry.PeaceLilyRegistry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeaceLily implements ModInitializer {
	public static final String ID = "peace_lily";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize() {
		PeaceLilyRegistry.init();
		LOGGER.info("[Peace Lily] PEACE LILY is the world's PEACEFULLEST normal girl.");
	}
}
