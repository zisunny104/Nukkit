# 方塊差異：參考(Protocol Data blocks.json) vs 核心 runtime_block_states.dat

缺少共 258 個（參考 1477 / 核心 1219）。核心 LevelDB palette 為 729 版，更新方塊目前只能原樣保留（custom），顯示為預設方塊。

## shelf（13）
acacia_shelf bamboo_shelf birch_shelf cherry_shelf crimson_shelf dark_oak_shelf jungle_shelf mangrove_shelf oak_shelf pale_oak_shelf poplar_shelf spruce_shelf warped_shelf

## concrete/wool 台階樓梯（96）
black_concrete_double_slab black_concrete_slab black_concrete_stairs black_wool_double_slab black_wool_slab black_wool_stairs blue_concrete_double_slab blue_concrete_slab blue_concrete_stairs blue_wool_double_slab blue_wool_slab blue_wool_stairs brown_concrete_double_slab brown_concrete_slab brown_concrete_stairs brown_wool_double_slab brown_wool_slab brown_wool_stairs cyan_concrete_double_slab cyan_concrete_slab cyan_concrete_stairs cyan_wool_double_slab cyan_wool_slab cyan_wool_stairs gray_concrete_double_slab gray_concrete_slab gray_concrete_stairs gray_wool_double_slab gray_wool_slab gray_wool_stairs green_concrete_double_slab green_concrete_slab green_concrete_stairs green_wool_double_slab green_wool_slab green_wool_stairs light_blue_concrete_double_slab light_blue_concrete_slab light_blue_concrete_stairs light_blue_wool_double_slab light_blue_wool_slab light_blue_wool_stairs light_gray_concrete_double_slab light_gray_concrete_slab light_gray_concrete_stairs light_gray_wool_double_slab light_gray_wool_slab light_gray_wool_stairs lime_concrete_double_slab lime_concrete_slab lime_concrete_stairs lime_wool_double_slab lime_wool_slab lime_wool_stairs magenta_concrete_double_slab magenta_concrete_slab magenta_concrete_stairs magenta_wool_double_slab magenta_wool_slab magenta_wool_stairs orange_concrete_double_slab orange_concrete_slab orange_concrete_stairs orange_wool_double_slab orange_wool_slab orange_wool_stairs pink_concrete_double_slab pink_concrete_slab pink_concrete_stairs pink_wool_double_slab pink_wool_slab pink_wool_stairs purple_concrete_double_slab purple_concrete_slab purple_concrete_stairs purple_wool_double_slab purple_wool_slab purple_wool_stairs red_concrete_double_slab red_concrete_slab red_concrete_stairs red_wool_double_slab red_wool_slab red_wool_stairs white_concrete_double_slab white_concrete_slab white_concrete_stairs white_wool_double_slab white_wool_slab white_wool_stairs yellow_concrete_double_slab yellow_concrete_slab yellow_concrete_stairs yellow_wool_double_slab yellow_wool_slab yellow_wool_stairs

## cinnabar 朱砂（17）
chalkboard chiseled_cinnabar cinnabar cinnabar_brick_double_slab cinnabar_brick_slab cinnabar_brick_stairs cinnabar_brick_wall cinnabar_bricks cinnabar_double_slab cinnabar_slab cinnabar_stairs cinnabar_wall polished_cinnabar polished_cinnabar_double_slab polished_cinnabar_slab polished_cinnabar_stairs polished_cinnabar_wall

## resin 樹脂（8）
chiseled_resin_bricks resin_block resin_brick_double_slab resin_brick_slab resin_brick_stairs resin_brick_wall resin_bricks resin_clump

## sulfur 硫磺（18）
chiseled_sulfur polished_sulfur polished_sulfur_double_slab polished_sulfur_slab polished_sulfur_stairs polished_sulfur_wall potent_sulfur sulfur sulfur_brick_double_slab sulfur_brick_slab sulfur_brick_stairs sulfur_brick_wall sulfur_bricks sulfur_double_slab sulfur_slab sulfur_spike sulfur_stairs sulfur_wall

## 銅 (bars/chain/chest/lantern/statue/lightning_rod/torch)（48）
copper_bars copper_chain copper_chest copper_golem_statue copper_lantern copper_torch exposed_copper_bars exposed_copper_chain exposed_copper_chest exposed_copper_golem_statue exposed_copper_lantern exposed_lightning_rod oxidized_copper_bars oxidized_copper_chain oxidized_copper_chest oxidized_copper_golem_statue oxidized_copper_lantern oxidized_lightning_rod waxed_copper_bars waxed_copper_chain waxed_copper_chest waxed_copper_golem_statue waxed_copper_lantern waxed_exposed_copper_bars waxed_exposed_copper_chain waxed_exposed_copper_chest waxed_exposed_copper_golem_statue waxed_exposed_copper_lantern waxed_exposed_lightning_rod waxed_lightning_rod waxed_oxidized_copper_bars waxed_oxidized_copper_chain waxed_oxidized_copper_chest waxed_oxidized_copper_golem_statue waxed_oxidized_copper_lantern waxed_oxidized_lightning_rod waxed_weathered_copper_bars waxed_weathered_copper_chain waxed_weathered_copper_chest waxed_weathered_copper_golem_statue waxed_weathered_copper_lantern waxed_weathered_lightning_rod weathered_copper_bars weathered_copper_chain weathered_copper_chest weathered_copper_golem_statue weathered_copper_lantern weathered_lightning_rod

## poplar 楊樹系列（21）
orange_poplar_leaves poplar_button poplar_door poplar_double_slab poplar_fence poplar_fence_gate poplar_hanging_sign poplar_log poplar_planks poplar_pressure_plate poplar_sapling poplar_slab poplar_stairs poplar_standing_sign poplar_trapdoor poplar_wall_sign poplar_wood red_poplar_leaves stripped_poplar_log stripped_poplar_wood yellow_poplar_leaves

## pale moss（3）
pale_hanging_moss pale_moss_block pale_moss_carpet

## pale_oak 系列（19）
pale_oak_button pale_oak_door pale_oak_double_slab pale_oak_fence pale_oak_fence_gate pale_oak_hanging_sign pale_oak_leaves pale_oak_log pale_oak_planks pale_oak_pressure_plate pale_oak_sapling pale_oak_slab pale_oak_stairs pale_oak_standing_sign pale_oak_trapdoor pale_oak_wall_sign pale_oak_wood stripped_pale_oak_log stripped_pale_oak_wood

## 其他（15）
bush cactus_flower closed_eyeblossom creaking_heart dried_ghast firefly_bush golden_dandelion leaf_litter open_eyeblossom red_shrub shelf_mushroom short_dry_grass straw_bed tall_dry_grass wildflowers

## 進度（feature/blocks-flora）
已補：植物 12（id 1055–1066）、pale_oak 18（1067–1084）、poplar 20（1085–1104）、物品 pale_oak/poplar 門與告示牌（851–854）。
TODO：樹生成器（樹苗暫不生長）、hanging sign、shelf、船、tall_dry_grass 上半、骨粉；其餘群組（銅、sulfur、cinnabar、resin、pale moss、concrete/wool 台階樓梯）。

## 第二輪（行為）待辦
所有方塊已存在（id 至 1315）。以下只有 id、狀態、物品與放置，行為待補：shelf 13、hanging sign（楊樹/pale_oak，另缺方塊實體與物品）、銅 chain/chest/golem statue 24（chest 與雕像缺方塊實體）、chalkboard、creaking_heart、disc_press、dried_ghast、ice_crystal、icicle、potent_sulfur、sulfur_spike、resin_clump、straw_bed、chiseled_resin_bricks、pale_moss_carpet 四邊與 pale_hanging_moss 末端、銅氧化與打蠟、樹生成器、船、植物骨粉與 tall_dry_grass 上半。

## 未驗證項目總表

All groups were checked by compile, boot against a world copy and a clean merge only. Nothing was placed, read or written in game.

1. All 258 new blocks: placing, save/load round trip, creative inventory and item correspondence. Recipes belong to the recipe session.
2. Shelf, hanging sign, dried ghast and straw bed direction rules are inferred, not captured.
3. Copper oxidation and wax: random tick rate, chest block entity kept after oxidation.
4. Pale oak sapling: 2x2 tree with dark oak shape. Poplar sapling: oak shape with yellow poplar leaves only. TODO official shapes, other leaf colors, creaking heart and resin on trees.
5. Wildflowers and leaf litter stacking follows the packets; bone meal on them is not implemented (TODO).
6. Pale moss carpet and hanging moss keep meta; their growth by bone meal is not implemented (TODO).
7. Round-2 BDS captures still open: stair corners, chorus, shelf and hanging sign directions (see docs/bds-capture-checklist.md).
8. Commit 3f8507a48 is broken in history (729 palette duplicate); fixed forward later.

## Creative items that do not load (126 of 1980)

The creative list file already matches the official one, including all new blocks. Entries are dropped at load when the core has no item class for them (the server now logs the list at start). Chalkboard, disc_press, icicle and ice_crystal are not in the official 1.26.63 creative list. Double slabs and standing/wall signs are not creative items.

No item class yet (TODO, items not blocks in the new-block groups): minecraft:pitcher_pod, mushroom_stem, brown_egg, blue_egg, trial_spawner, vault, trader_llama_spawn_egg, sulfur_cube_spawn_egg, copper_helmet, copper_chestplate, copper_leggings, copper_boots, copper_sword, wooden_spear, stone_spear, copper_spear, iron_spear, golden_spear, diamond_spear, netherite_spear, copper_axe, copper_pickaxe, copper_shovel, copper_hoe, white_harness, light_gray_harness, gray_harness, black_harness, brown_harness, red_harness, orange_harness, yellow_harness, lime_harness, green_harness, cyan_harness, light_blue_harness, blue_harness, purple_harness, magenta_harness, pink_harness, bundle, white_bundle, light_gray_bundle, gray_bundle, black_bundle, brown_bundle, red_bundle, orange_bundle, yellow_bundle, lime_bundle, green_bundle, cyan_bundle, light_blue_bundle, blue_bundle, purple_bundle, magenta_bundle, pink_bundle, copper_horse_armor, netherite_horse_armor, copper_nautilus_armor, iron_nautilus_armor, golden_nautilus_armor, diamond_nautilus_armor, netherite_nautilus_armor, white_cushion, light_gray_cushion, gray_cushion, black_cushion, brown_cushion, red_cushion, orange_cushion, yellow_cushion, lime_cushion, green_cushion, cyan_cushion, light_blue_cushion, blue_cushion, purple_cushion, magenta_cushion, pink_cushion, music_disc_tears, music_disc_lava_chicken, music_disc_bounce, oak_hanging_sign, spruce_hanging_sign, birch_hanging_sign, jungle_hanging_sign, acacia_hanging_sign, dark_oak_hanging_sign, mangrove_hanging_sign, cherry_hanging_sign, bamboo_hanging_sign, crimson_hanging_sign, warped_hanging_sign, sulfur_cube_bucket, copper_nugget, resin_brick, heavy_core, poplar_boat, poplar_chest_boat, crafter, flow_banner_pattern, guster_banner_pattern, angler_pottery_sherd, archer_pottery_sherd, arms_up_pottery_sherd, blade_pottery_sherd, brewer_pottery_sherd, burn_pottery_sherd, danger_pottery_sherd, explorer_pottery_sherd, flow_pottery_sherd, friend_pottery_sherd, guster_pottery_sherd, heart_pottery_sherd, heartbreak_pottery_sherd, howl_pottery_sherd, miner_pottery_sherd, mourner_pottery_sherd, plenty_pottery_sherd, prize_pottery_sherd, scrape_pottery_sherd, sheaf_pottery_sherd, shelter_pottery_sherd, skull_pottery_sherd, snort_pottery_sherd.

## Redstone audit (state counts against the official palette)

Done in feature/redstone-bulb-rod: copper bulb (lit and powered, toggles on a rising power edge, light by oxidation level; the lit/powered states did not exist before) and lightning rod (powered bit in the palette only, the core does not power it when struck, TODO).

Buttons, pressure plates, levers, rails, hoppers, droppers, dispensers, observers, pistons, torches and repeaters/comparators have at least the official number of states in the palette (the core palette has extra padding states). Not yet checked in code, needs a capture or a reading pass: charging directions, scheduled delays, strong versus weak power, piston push limits and block entity, observer pulse, hopper locking, daylight detector inversion, target block signal, sculk sensor, calibrated sculk sensor (1/3 and 1/12 states, no behavior), crafter (1/48, no behavior).
