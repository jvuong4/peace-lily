package io.github.jvuong4.peacelily.registry;

import io.github.jvuong4.peacelily.AnanasBlock;
import io.github.jvuong4.peacelily.PeaceLily;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

import static net.minecraft.world.level.block.Blocks.flowerPotProperties;

public class PLBlocks {
	public static FlowerBlock PEACE_LILY = register("peace_lily", p -> new FlowerBlock(MobEffects.POISON, 11.0F, p),
		BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY), true);

	public static FlowerBlock BIRD_OF_PARADISE = register("bird_of_paradise", p -> new FlowerBlock(MobEffects.JUMP_BOOST, 5.0F, p),
		BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY), true);

	public static FlowerBlock RED_SNAPDRAGON = register("red_snapdragon", p -> new FlowerBlock(MobEffects.FIRE_RESISTANCE, 3.0F, p),
		BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY), true);

	public static FlowerBlock YELLOW_SNAPDRAGON = register("yellow_snapdragon", p -> new FlowerBlock(MobEffects.FIRE_RESISTANCE, 3.0F, p),
		BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY), true);

	public static FlowerBlock WHITE_SNAPDRAGON = register("white_snapdragon", p -> new FlowerBlock(MobEffects.FIRE_RESISTANCE, 3.0F, p),
		BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY), true);

	public static FlowerBlock MAGENTA_SNAPDRAGON = register("magenta_snapdragon", p -> new FlowerBlock(MobEffects.FIRE_RESISTANCE, 3.0F, p),
		BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY), true);

	public static FlowerPotBlock POTTED_PEACE_LILY = register_pot("potted_peace_lily", p -> new FlowerPotBlock(PEACE_LILY, p),flowerPotProperties(), false);
	public static FlowerPotBlock POTTED_BIRD_OF_PARADISE = register_pot("potted_bird_of_paradise", p -> new FlowerPotBlock(BIRD_OF_PARADISE, p),flowerPotProperties(), false);
	public static FlowerPotBlock POTTED_RED_SNAPDRAGON = register_pot("potted_red_snapdragon", p -> new FlowerPotBlock(RED_SNAPDRAGON, p),flowerPotProperties(), false);
	public static FlowerPotBlock POTTED_YELLOW_SNAPDRAGON = register_pot("potted_yellow_snapdragon", p -> new FlowerPotBlock(YELLOW_SNAPDRAGON, p),flowerPotProperties(), false);
	public static FlowerPotBlock POTTED_WHITE_SNAPDRAGON = register_pot("potted_white_snapdragon", p -> new FlowerPotBlock(WHITE_SNAPDRAGON, p),flowerPotProperties(), false);
	public static FlowerPotBlock POTTED_MAGENTA_SNAPDRAGON = register_pot("potted_magenta_snapdragon", p -> new FlowerPotBlock(MAGENTA_SNAPDRAGON, p),flowerPotProperties(), false);

	public static AnanasBlock ANANAS = register("ananas",
		properties -> new AnanasBlock(properties, Block.box(5, 0, 5, 11, 6, 11)),
		BlockBehaviour.Properties.of()
			.instabreak()
			.noOcclusion()
			.pushReaction(PushReaction.DESTROY)
			.noLootTable()
		,true);

	private static <T extends Block> T register(String name, Function<BlockBehaviour.Properties, T> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
		// Create a registry key for the block
		ResourceKey<Block> blockKey = keyOfBlock(name);
		// Create the block instance
		T block = blockFactory.apply(settings.setId(blockKey));

		// Sometimes, you may not want to register an item for the block.
		// Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
		if (shouldRegisterItem) {
			// Items need to be registered with a different type of registry key, but the ID
			// can be the same.
			ResourceKey<Item> itemKey = keyOfItem(name);

			BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
			Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
		}

		return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
	}
	private static FlowerPotBlock register_pot(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
		// Create a registry key for the block
		ResourceKey<Block> blockKey = keyOfBlock(name);
		// Create the block instance
		FlowerPotBlock block = (FlowerPotBlock)blockFactory.apply(settings.setId(blockKey));

		// Sometimes, you may not want to register an item for the block.
		// Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
		if (shouldRegisterItem) {
			// Items need to be registered with a different type of registry key, but the ID
			// can be the same.
			ResourceKey<Item> itemKey = keyOfItem(name);

			BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
			Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
		}

		return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
	}

	private static ResourceKey<Block> keyOfBlock(String name) {
		return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(PeaceLily.ID, name));
	}

	private static ResourceKey<Item> keyOfItem(String name) {
		return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(PeaceLily.ID, name));
	}

	public static void init() {}
}
