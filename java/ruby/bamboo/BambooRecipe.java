package ruby.bamboo;

import static ruby.bamboo.BambooInit.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import ruby.bamboo.api.crafting.grind.IGrindRecipe;
import ruby.bamboo.block.BlockLiangBamboo;
import ruby.bamboo.block.BlockLiangBamboo.EnumBlock;
import ruby.bamboo.entity.EnumSlideDoor;
import ruby.bamboo.item.EnumShavedIce;
import ruby.bamboo.item.crafting.CookingManager;
import ruby.bamboo.item.crafting.GrindManager;
import ruby.bamboo.tileentity.spa.BoilDye;
import ruby.bamboo.tileentity.spa.BoilManager;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class BambooRecipe {
    public final String BAMBOO = "bamboo";
    public final String COOKING_RICE = "cookingRice";
    public final String TUDURA = "tudura";
    public final String BUSKET = "bambooBasket";
    public final String CROP_RICE = "cropRice";
    public final String LOG_SAKURA = "logCherry";
    public final String CROP_STRAW = "cropStraw";
    public final String NATTO = "natto";
    public final String ZUNDA = "zunda";
    public final String SOY_BEANS = "soybeans";
    public final String RED_BEANS = "redbeans";
    public final String MOCHI = "mochi";
    public final String COOKED_MOCHI = "cookedMochi";
    public final String FLOUR = "flour";
    public final int WILD_CARD = Short.MAX_VALUE;

    public BambooRecipe() {
        recipeInit();
    }

    private void recipeInit() {
        // 竹製品
        addShapedOreRecipe(new ItemStack(tudura, 1, 0), " # ", "# #", " # ", '#', BAMBOO);
        addShapedOreRecipe(new ItemStack(Items.paper, 1), "###", '#', BAMBOO);
        addShapedOreRecipe(new ItemStack(Items.stick, 2), "#", "#", '#', BAMBOO);
        addShapedOreRecipe(new ItemStack(singleTexDeco, 2, 0), "##", "##", '#', BAMBOO);
        // きつねび
        addShapedOreRecipe(new ItemStack(kitunebi, 6, 0), "YYY", "#X#", "YYY", 'Y', "dyeBlue", '#', TUDURA, 'X', Blocks.lit_pumpkin);
        addShapedOreRecipe(new ItemStack(kitunebi, 6, 0), "Y#Y", "YXY", "Y#Y", 'Y', "dyeBlue", '#', TUDURA, 'X', Blocks.lit_pumpkin);
        addShapedOreRecipe(new ItemStack(kitunebi, 6, 0), "YYY", "#X#", "YYY", 'Y', Items.ender_pearl, '#', TUDURA, 'X', Blocks.lit_pumpkin);
        addShapedOreRecipe(new ItemStack(kitunebi, 6, 0), "Y#Y", "YXY", "Y#Y", 'Y', Items.ender_pearl, '#', TUDURA, 'X', Blocks.lit_pumpkin);
        // うつわ
        addShapedOreRecipe(new ItemStack(bambooBasket, 1, 0), "# #", " # ", '#', BAMBOO);
        // 竹槍
        GameRegistry.addRecipe(new ItemStack(bambooSpear, 2, 0), "##", '#', new ItemStack(singleTexDeco, 1, WILD_CARD));
        // 引き戸類
        addShapedOreRecipe(new ItemStack(slideDoors, 2, EnumSlideDoor.HUSUMA.getId()), "XYX", "X#X", "XYX", 'X', Items.stick, 'Y', Items.paper, '#', TUDURA);
        addShapedOreRecipe(new ItemStack(slideDoors, 2, EnumSlideDoor.SHOZI.getId()), "XYX", "Y#Y", "XYX", '#', TUDURA, 'X', Items.stick, 'Y', Items.paper);
        addShapedOreRecipe(new ItemStack(slideDoors, 2, EnumSlideDoor.GLASS.getId()), "XYX", "X#X", "XYX", '#', TUDURA, 'X', Blocks.iron_bars, 'Y', Blocks.glass_pane);
        addShapedOreRecipe(new ItemStack(slideDoors, 2, EnumSlideDoor.GGLASS.getId()), "XYX", "X#X", "XYX", '#', TUDURA, 'X', Items.stick, 'Y', Blocks.glass_pane);
        addShapedOreRecipe(new ItemStack(slideDoors, 2, EnumSlideDoor.YUKI.getId()), "XYX", "X#X", "XZX", '#', TUDURA, 'X', Items.stick, 'Y', Items.paper, 'Z', Blocks.glass_pane);
        addShapedOreRecipe(new ItemStack(slideDoors, 2, EnumSlideDoor.AMADO.getId()), "XYX", "X#X", "XYX", '#', TUDURA, 'X', Items.stick, 'Y', "plankWood");
        // パネル類
        addShapedOreRecipe(new ItemStack(bamboopane, 8, 0), "###", "###", '#', BAMBOO);
        addShapedOreRecipe(new ItemStack(bamboopane, 8, 1), "#X#", "###", '#', Blocks.planks, 'X', TUDURA);
        addShapedOreRecipe(new ItemStack(bamboopane, 6, 2), "#X#", "#X#", '#', BAMBOO, 'X', new ItemStack(bamboopane, 1, 0));
        addShapedOreRecipe(new ItemStack(bamboopane, 6, 3), "#X#", "#X#", '#', BAMBOO, 'X', new ItemStack(bamboopane, 1, 2));
        addShapedOreRecipe(new ItemStack(bamboopane, 8, 4), "#X#", "###", '#', new ItemStack(Blocks.wool, 1, 11), 'X', TUDURA);
        addShapedOreRecipe(new ItemStack(bamboopane, 8, 5), "#X#", "###", '#', new ItemStack(Blocks.wool, 1, 10), 'X', TUDURA);
        addShapedOreRecipe(new ItemStack(bamboopane, 8, 6), "#X#", "#X#", '#', BAMBOO, 'X', TUDURA);
        // 爆竹類
        addShapedOreRecipe(new ItemStack(firecracker, 10, 0), " # ", " X ", " # ", '#', BUSKET, 'X', Items.gunpowder);
        addShapedOreRecipe(new ItemStack(firecracker, 1, 1), " # ", "XYX", " # ", '#', BUSKET, 'X', Items.gunpowder, 'Y', new ItemStack(firecracker, 1, 0));
        addShapedOreRecipe(new ItemStack(firecracker, 1, 2), " # ", "XYX", " # ", '#', BUSKET, 'X', Items.gunpowder, 'Y', new ItemStack(firecracker, 1, 1));

        // 箪笥
        addShapedOreRecipe(new ItemStack(jpchest, 1, 0), "YYY", "Y#Y", "YYY", '#', TUDURA, 'Y', "logWood");
        // 畳 茅葺き
        addShapedOreRecipe(new ItemStack(dSquare, 4, 0), " X ", "X#X", " X ", '#', TUDURA, 'X', CROP_STRAW);
        addShapedOreRecipe(new ItemStack(dSquare, 8, 4), "XXX", "X#X", "XXX", '#', TUDURA, 'X', Items.wheat);
        GameRegistry.addShapelessRecipe(new ItemStack(dSquare, 1, 12), new ItemStack(dSquare, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(dSquare, 1, 0), new ItemStack(dSquare, 1, 12));
        addSlabRecipe(new ItemStack(dHalfSquare, 6, 0), new ItemStack(dSquare, 1, 0));
        addSlabRecipe(new ItemStack(dHalfSquare, 6, 4), new ItemStack(dSquare, 1, 4));
        addSlabRecipe(new ItemStack(dHalfSquare, 6, 8), new ItemStack(dSquare, 1, 8));
        addSlabRecipe(new ItemStack(dHalfSquare, 6, 10), new ItemStack(dSquare, 1, 10));
        addSlabRecipe(new ItemStack(dHalfSquare, 6, 12), new ItemStack(dSquare, 1, 12));
        //ゴザ類
        addCarpetRecipe(new ItemStack(decoCarpet, 3, 0), new ItemStack(dSquare, 1, 0));
        addCarpetRecipe(new ItemStack(decoCarpet, 3, 2), new ItemStack(dSquare, 1, 4));
        addCarpetRecipe(new ItemStack(decoCarpet, 3, 4), new ItemStack(dSquare, 1, 8));
        addCarpetRecipe(new ItemStack(decoCarpet, 3, 6), new ItemStack(dSquare, 1, 10));
        addCarpetRecipe(new ItemStack(decoCarpet, 3, 8), new ItemStack(dSquare, 1, 12));
        addCarpetRecipe(new ItemStack(sakuraCarpet, 3, 0), new ItemStack(sakuraleavs, 1, 15));
        addCarpetRecipe(new ItemStack(sakuraCarpet, 3, 1), new ItemStack(sakuraleavs, 1, 1));
        addCarpetRecipe(new ItemStack(sakuraCarpet, 3, 2), new ItemStack(sakuraleavs, 1, 14));
        // 掛け軸
        addShapedOreRecipe(new ItemStack(kakeziku, 1, 0), "YYY", "X#X", "XXX", '#', TUDURA, 'Y', Items.stick, 'X', Items.paper);
        // 行灯
        addShapedOreRecipe(new ItemStack(andon, 1, 0), "###", "#Y#", "#X#", '#', Items.stick, 'X', Blocks.torch, 'Y', TUDURA);
        // ふとん
        addShapedOreRecipe(new ItemStack(huton, 1, 0), " # ", "XXX", "XXX", '#', TUDURA, 'X', Blocks.wool);
        // 茅葺屋根
        addStairRecipe(new ItemStack(rooftile, 6, 0), new ItemStack(dSquare, 1, 4));
        addStairRecipe(new ItemStack(rooftile, 6, 0), Items.wheat, TUDURA);
        // 温泉ユニット
        addShapedOreRecipe(new ItemStack(spa_unit, 1, 0), "#Z#", "#X#", "#Y#", 'Z', TUDURA, '#', Blocks.cobblestone, 'Y', Items.lava_bucket, 'X', Items.water_bucket);
        // かき氷
        EnumShavedIce[] esis = EnumShavedIce.values();

        for (EnumShavedIce esi : esis) {
            if (esi.material != null) {
                addShapelessOreRecipe(new ItemStack(shavedIce, 1, esi.id), esi.material, Blocks.snow, Items.sugar, BUSKET);
                GameRegistry.addShapelessRecipe(new ItemStack(shavedIce, 1, esi.id), esi.material, new ItemStack(shavedIce, 1, 0));
            } else {
                addShapelessOreRecipe(new ItemStack(shavedIce, 1, esi.id), Blocks.snow, Items.sugar, BUSKET);
            }

            GameRegistry.addShapelessRecipe(new ItemStack(shavedIce, 1, esi.id + esis.length), Items.milk_bucket, new ItemStack(shavedIce, 1, esi.id));
        }

        // 風鈴
        addShapedOreRecipe(new ItemStack(windChime, 1, 0), "#", "X", "B", '#', Items.string, 'X', TUDURA, 'B', Items.paper);
        // ふくろ
        addShapedOreRecipe(new ItemStack(itemSack, 1, 0), "###", "WXW", "WWW", 'X', TUDURA, '#', Items.string, 'W', Blocks.wool);
        GameRegistry.addShapelessRecipe(new ItemStack(itemSack, 1, 0), new ItemStack(itemSack, 1, WILD_CARD));
        // 雪球
        GameRegistry.addRecipe(new ItemStack(snowBall, 32, 0), "###", "#X#", "###", '#', Blocks.snow, 'X', Blocks.cobblestone);
        GameRegistry.addRecipe(new ItemStack(snowBall, 32, 1), "###", "#X#", "###", '#', Blocks.snow, 'X', Blocks.ice);
        GameRegistry.addRecipe(new ItemStack(snowBall, 32, 2), "###", "#X#", "###", '#', Blocks.snow, 'X', Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(snowBall, 01, 3), "###", "#X#", "###", '#', Blocks.snow, 'X', Items.gold_nugget);
        GameRegistry.addRecipe(new ItemStack(snowBall, 32, 4), "###", "#X#", "###", '#', Blocks.snow, 'X', Items.diamond);
        GameRegistry.addRecipe(new ItemStack(snowBall, 32, 5), "###", "#X#", "###", '#', Blocks.snow, 'X', Items.water_bucket);
        GameRegistry.addRecipe(new ItemStack(snowBall, 32, 6), "###", "#X#", "###", '#', Blocks.snow, 'X', Items.ender_pearl);
        GameRegistry.addRecipe(new ItemStack(snowBall, 04, 7), "###", "#X#", "###", '#', Blocks.snow, 'X', Blocks.red_mushroom);
        GameRegistry.addRecipe(new ItemStack(snowBall, 04, 8), "###", "#X#", "###", '#', Blocks.snow, 'X', Items.spider_eye);
        GameRegistry.addRecipe(new ItemStack(snowBall, 01, 9), "###", "#X#", "###", '#', Blocks.snow, 'X', Items.melon);
        // 扇子
        addShapedOreRecipe(new ItemStack(fan, 1, 0), "XYY", "XYY", "XXX", 'X', BAMBOO, 'Y', Items.paper);
        // 武器
        addShapedOreRecipe(new ItemStack(katana, 1, 0), "X", "X", "Y", 'X', Items.iron_ingot, 'Y', TUDURA);
        GameRegistry.addRecipe(new ItemStack(bambooBow, 1, 0), " XY", "X Y", " XY", 'X', singleTexDeco, 'Y', Items.string);
        addShapedOreRecipe(new ItemStack(bambooSword, 1, 0), " X ", " X ", " Y ", 'X', BAMBOO, 'Y', TUDURA);
        // サクラから桜木材
        // カメレオン類
        addShapedOreRecipe(new ItemStack(delude_width, 6, 0), " # ", "YXY", " # ", '#', TUDURA, 'X', Blocks.lit_pumpkin, 'Y', Blocks.soul_sand);
        addShapedOreRecipe(new ItemStack(delude_height, 6, 0), " Y ", "#X#", " Y ", '#', TUDURA, 'X', Blocks.lit_pumpkin, 'Y', Blocks.soul_sand);
        addShapedOreRecipe(new ItemStack(delude_stair, 8, 0), "Y  ", "YX ", "#YY", '#', TUDURA, 'X', Blocks.lit_pumpkin, 'Y', Blocks.soul_sand);
        addShapedOreRecipe(new ItemStack(delude_stair, 8, 0), "  Y", " XY", "YY#", '#', TUDURA, 'X', Blocks.lit_pumpkin, 'Y', Blocks.soul_sand);
        addShapedOreRecipe(new ItemStack(delude_plate, 1, 0), "##", '#', new ItemStack(delude_width, 1, 0));
        // いろり
        addShapedOreRecipe(new ItemStack(campfire, 1, 0), " # ", "ZXZ", "YYY", '#', TUDURA, 'Z', Blocks.iron_bars, 'X', Items.flint_and_steel, 'Y', new ItemStack(Items.coal, 1, 1));
        // 風車水車
        addShapedOreRecipe(new ItemStack(windmill, 1, 0), "YXY", "X#X", "YXY", '#', TUDURA, 'X', BAMBOO, 'Y', singleTexDeco);
        addShapedOreRecipe(new ItemStack(windmill, 1, 1), "YXY", "X#X", "YXY", '#', TUDURA, 'X', BAMBOO, 'Y', Blocks.wool);
        addShapedOreRecipe(new ItemStack(waterWheel, 1, 0), "YXY", "X#X", "YXY", '#', TUDURA, 'X', BAMBOO, 'Y', Blocks.planks);
        // 方向なしデコブロック
        addShapedOreRecipe(new ItemStack(decoration, 8, 0), "###", "#X#", "###", '#', Blocks.sand, 'X', TUDURA);
        addShapedOreRecipe(new ItemStack(decoration, 8, 1), "Y#Y", "#X#", "Y#Y", '#', new ItemStack(decoration, 8, 5), 'X', TUDURA, 'Y', new ItemStack(decoration, 8, 0));
        addShapedOreRecipe(new ItemStack(decoration, 8, 2), "###", "#X#", "###", '#', new ItemStack(Blocks.planks, 1, 0), 'X', TUDURA);
        addShapedOreRecipe(new ItemStack(decoration, 8, 3), "###", "#X#", "###", '#', new ItemStack(Blocks.planks, 1, 1), 'X', TUDURA);
        addShapedOreRecipe(new ItemStack(decoration, 8, 4), "###", "#X#", "###", '#', new ItemStack(Blocks.planks, 1, 2), 'X', TUDURA);
        addShapedOreRecipe(new ItemStack(decoration, 8, 5), "###", "#X#", "###", '#', Items.brick, 'X', TUDURA);
        // デコハーフ
        addSlabRecipe(new ItemStack(decoration_half, 6, 0), new ItemStack(decoration, 1, 0));
        addSlabRecipe(new ItemStack(decoration_half, 6, 1), new ItemStack(decoration, 1, 1));
        addSlabRecipe(new ItemStack(decoration_half, 6, 2), new ItemStack(decoration, 1, 2));
        addSlabRecipe(new ItemStack(decoration_half, 6, 3), new ItemStack(decoration, 1, 3));
        addSlabRecipe(new ItemStack(decoration_half, 6, 4), new ItemStack(decoration, 1, 4));
        addSlabRecipe(new ItemStack(decoration_half, 6, 5), new ItemStack(decoration, 1, 5));
        // 方向付きデコ（桜木材等）
        addShapedOreRecipe(new ItemStack(decoration_dir, 8, 0), "###", "#X#", "###", '#', new ItemStack(decoration_dir, 8, 2), 'X', TUDURA);
        GameRegistry.addShapelessRecipe(new ItemStack(decoration_dir, 4, 2), sakuralog);
        addShapedOreRecipe(new ItemStack(decoration_dir, 4, 4), "###", "###", "###", '#', CROP_STRAW);
        // 方向付きデコハーフ
        addSlabRecipe(new ItemStack(decoration_dir_half, 6, 0), new ItemStack(decoration_dir, 1, 0));
        addSlabRecipe(new ItemStack(decoration_dir_half, 6, 2), new ItemStack(decoration_dir, 1, 2));
        addSlabRecipe(new ItemStack(decoration_dir_half, 6, 4), new ItemStack(decoration_dir, 1, 4));
        //藁葺階段
        addStairRecipe(new ItemStack(wara_stair, 4, 0), new ItemStack(decoration_dir, 1, 4));
        // 瓦
        // 瓦階段
        addStairRecipe(new ItemStack(kawara_stair, 4, 0), new ItemStack(decoration, 1, 5));
        // おぼん
        GameRegistry.addRecipe(new ItemStack(obon, 1, 0), "###", "# #", '#', new ItemStack(decoration_dir, 1, 2));
        // 柱
        addShapedOreRecipe(new ItemStack(thickSakuraPillar, 8, 0), "#", "X", "#", '#', new ItemStack(decoration_dir, 1, 2), 'X', TUDURA);
        addShapedOreRecipe(new ItemStack(thinSakuraPillar, 4, 0), "#", "X", "#", '#', new ItemStack(thickSakuraPillar, 1, 0), 'X', TUDURA);
        addShapedOreRecipe(new ItemStack(thickOrcPillar, 8, 0), "#", "X", "#", '#', new ItemStack(Blocks.planks, 1, 0), 'X', TUDURA);
        addShapedOreRecipe(new ItemStack(thinOrcPillar, 4, 0), "#", "X", "#", '#', new ItemStack(thickOrcPillar, 1, 0), 'X', TUDURA);
        addShapedOreRecipe(new ItemStack(thickSprucePillar, 8, 0), "#", "X", "#", '#', new ItemStack(Blocks.log, 1, 1), 'X', TUDURA);
        addShapedOreRecipe(new ItemStack(thinSprucePillar, 4, 0), "#", "X", "#", '#', new ItemStack(thickSprucePillar, 1, 0), 'X', TUDURA);
        addShapedOreRecipe(new ItemStack(thickBirchPillar, 8, 0), "#", "X", "#", '#', new ItemStack(Blocks.log, 1, 2), 'X', TUDURA);
        addShapedOreRecipe(new ItemStack(thinBirchPillar, 4, 0), "#", "X", "#", '#', new ItemStack(thickBirchPillar, 1, 0), 'X', TUDURA);
        //梁
        for (EnumBlock b : BlockLiangBamboo.EnumBlock.values()) {
            addShapedOreRecipe(new ItemStack(liangBambooThick, 8, b.getId()), "#X#", '#', new ItemStack(b.getBlock(), 1, b.getMeta()), 'X', TUDURA);
            addShapedOreRecipe(new ItemStack(liangBambooThin, 4, b.getId()), "#X#", '#', new ItemStack(liangBambooThick, 1, b.getId()), 'X', TUDURA);
        }
        for (int i = 0; i < 4; i++) {
            addShapedOreRecipe(new ItemStack(liangVanillaLogThick, 8, i), "#X#", '#', new ItemStack(Blocks.log, 1, i), 'X', TUDURA);
            addShapedOreRecipe(new ItemStack(liangVanillaLogThin, 4, i), "#X#", '#', new ItemStack(liangVanillaLogThick, 1, i), 'X', TUDURA);
        }
        for (int i = 0; i < 2; i++) {
            addShapedOreRecipe(new ItemStack(liangVanillaLog2Thick, 8, i), "#X#", '#', new ItemStack(Blocks.log2, 1, i), 'X', TUDURA);
            addShapedOreRecipe(new ItemStack(liangVanillaLog2Thin, 4, i), "#X#", '#', new ItemStack(liangVanillaLog2Thick, 1, i), 'X', TUDURA);
        }
        for (int i = 0; i < 6; i++) {
            addShapedOreRecipe(new ItemStack(liangVanillaWoodThick, 8, i), "#X#", '#', new ItemStack(Blocks.planks, 1, i), 'X', TUDURA);
            addShapedOreRecipe(new ItemStack(liangVanillaWoodThin, 4, i), "#X#", '#', new ItemStack(liangVanillaWoodThick, 1, i), 'X', TUDURA);
        }
        //石臼
        addShapedOreRecipe(new ItemStack(millStone, 1, 0), "###", "X#X", "###", '#', new ItemStack(Blocks.cobblestone), 'X', TUDURA);
        //ざぶとん
        for (int i = 0; i < 16; i++) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BambooInit.zabuton, 1, i), "###", "#X#", "###", '#', new ItemStack(Blocks.wool, 1, 15 - i), 'X', TUDURA));
        }
        //うえきばち
        addShapedOreRecipe(new ItemStack(multiPot), "#X#", " # ", '#', new ItemStack(Items.brick), 'X', TUDURA);
        //箒目
        addShapedOreRecipe(new ItemStack(blockBroom, 8, 0), "#X#", "#X#", "#X#", '#', new ItemStack(Blocks.sand), 'X', TUDURA);
        addShapedOreRecipe(new ItemStack(blockBroom, 8, 1), "#X#", "#XX", "###", '#', new ItemStack(Blocks.sand), 'X', TUDURA);
        //粉粘土から粘土へ
        addShapedOreRecipe(new ItemStack(Items.clay_ball, 4, 0), "###", "#X#", "###", '#', "dustClay", 'X', Items.water_bucket);
        //縮小なんとかさん
        addShapedOreRecipe(new ItemStack(multiBlock, 1, 2), "Y#Y", "#X#", "Y#Y", '#', Items.redstone, 'Y', "dustGlowstone", 'X', TUDURA);
        addShapedOreRecipe(new ItemStack(multiBlock, 1, 2), "Y#Y", "#X#", "Y#Y", '#', Items.redstone, 'Y', "dyeBlue", 'X', TUDURA);
        GameRegistry.addShapelessRecipe(new ItemStack(alphaMultiBlock, 1, 2), new ItemStack(multiBlock, 1, 2));
        GameRegistry.addShapelessRecipe(new ItemStack(multiBlock, 1, 2), new ItemStack(alphaMultiBlock, 1, 2));
        for (int i = 2; i < 5; i++) {
            addShapedOreRecipe(new ItemStack(multiBlock, 1, i + 1), "Y#Y", "#X#", "Y#Y", '#', Items.redstone, 'Y', "dustGlowstone", 'X', new ItemStack(multiBlock, 1, i));
            addShapedOreRecipe(new ItemStack(multiBlock, 1, i + 1), "Y#Y", "#X#", "Y#Y", '#', Items.redstone, 'Y', "dyeBlue", 'X', new ItemStack(multiBlock, 1, i));
            GameRegistry.addShapelessRecipe(new ItemStack(alphaMultiBlock, 1, i + 1), new ItemStack(multiBlock, 1, i + 1));
            GameRegistry.addShapelessRecipe(new ItemStack(multiBlock, 1, i + 1), new ItemStack(alphaMultiBlock, 1, i + 1));
        }
        //ちゅるはし
        addShapedOreRecipe(new ItemStack(bambooPickaxe, 1, 0), "YXY", " # ", " Z ", 'Y', Items.nether_star, 'X', Blocks.dragon_egg, '#', TUDURA, 'Z', Blocks.diamond_block);
        //もち
        addShapedOreRecipe(new ItemStack(foods, 2, 22), "##", "##", '#', COOKING_RICE);
        //めしEX
        addShapelessOreRecipe(new ItemStack(foods, 1, 28), NATTO, COOKING_RICE);
        addShapelessOreRecipe(new ItemStack(foods, 1, 29), NATTO, COOKING_RICE, Items.egg);
        addShapelessOreRecipe(new ItemStack(foods, 1, 31), new ItemStack(foods, 1, 1), Items.egg);
        addShapelessOreRecipe(new ItemStack(foods, 1, 7), Items.egg, COOKING_RICE);
        /*焼き海苔増えたらにしよ
        addShapelessOreRecipe(new ItemStack(foods, 1, 34), new ItemStack(foods, 1, 33), itemSeaweed);
        addShapelessOreRecipe(new ItemStack(foods, 1, 11), COOKING_RICE, itemSeaweed);
        addShapelessOreRecipe(new ItemStack(foods, 1, 14), new ItemStack(foods, 1,3), itemSeaweed);
        addShapelessOreRecipe(new ItemStack(foods, 1, 15), new ItemStack(foods, 1,6), itemSeaweed);
        */
        //飾りつき漆喰
        addShapedOreRecipe(new ItemStack(decoPlaster_I, 6, 0), "###", "###", "XXX", '#', new ItemStack(decoration, 8, 0), 'X', "plankWood");
        addShapedOreRecipe(new ItemStack(decoPlaster_L, 4, 0), "##X", "##X", "XXX", '#', new ItemStack(decoration, 8, 0), 'X', "plankWood");
        addShapedOreRecipe(new ItemStack(decoPlaster_D, 4, 0), "##X", "#XX", "XXX", '#', new ItemStack(decoration, 8, 0), 'X', "plankWood");
        //レシピ本
        addShapelessOreRecipe(new ItemStack(cookBook, 1, 0), new ItemStack(Items.book), BAMBOO);
        // 鉱石辞書
        addOreDictionary();
        // 粉砕レシピ
        addGrindRecipe();
        //煮る系
        addBileRecipe();
        //料理
        addCookingRecipe();
        // やきもの
        GameRegistry.addSmelting(singleTexDeco, new ItemStack(Items.coal, 1, 1), 0.15F);
        GameRegistry.addSmelting(sakuralog, new ItemStack(Items.coal, 1, 1), 0.15F);
        GameRegistry.addSmelting(rawrice, new ItemStack(foods, 1, 0), 0.15F);
        GameRegistry.addSmelting(new ItemStack(foods, 2, 22), new ItemStack(foods, 2, 23), 0.15F);
        //燃料
        addFuel();
    }

    private void addCookingRecipe() {
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 1), Items.beef, CROP_RICE);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 2), Items.porkchop, CROP_RICE);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 3), Blocks.brown_mushroom, CROP_RICE);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 2, 4), Items.porkchop, BAMBOO);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 2, 5), Items.beef, BAMBOO);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 6), bambooShoot, CROP_RICE);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 7), Items.egg, CROP_RICE);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 8), Items.egg, Items.chicken, CROP_RICE);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 9), Items.fish, CROP_RICE);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 2, 10), Items.chicken, BAMBOO);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 11), CROP_RICE, itemSeaweed);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 12), CROP_RICE, itemSeaweed, new ItemStack(Items.fish, 1, 1));
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 13), CROP_RICE, itemSeaweed, new ItemStack(Items.fish, 1, 0), Items.egg);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 14), CROP_RICE, itemSeaweed, Blocks.brown_mushroom);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 15), CROP_RICE, itemSeaweed, bambooShoot);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 16), CROP_RICE, itemSeaweed, itemSeaweed);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 17), new ItemStack(foods, 1, 22), BAMBOO, Items.sugar, Items.sugar, SOY_BEANS);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 18), new ItemStack(foods, 1, 22), BAMBOO, Items.sugar, RED_BEANS);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 19), new ItemStack(foods, 1, 22), BAMBOO, Items.sugar, Items.sugar);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 20), new ItemStack(foods, 1, 22), BAMBOO, Items.sugar, new ItemStack(sakuraleavs, 1, WILD_CARD), new ItemStack(Blocks.tallgrass, 1, WILD_CARD));
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 21), new ItemStack(foods, 1, 22), BAMBOO, Items.sugar, ZUNDA, ZUNDA);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 24), new ItemStack(foods, 1, 22), Items.sugar, Items.sugar, SOY_BEANS);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 25), new ItemStack(foods, 1, 22), Items.sugar, RED_BEANS);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 26), new ItemStack(foods, 1, 22), Items.sugar, ZUNDA, ZUNDA);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 27), CROP_STRAW, SOY_BEANS);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 28), CROP_STRAW, SOY_BEANS, CROP_RICE);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 29), CROP_STRAW, SOY_BEANS, CROP_RICE, Items.egg);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 30), new ItemStack(foods, 1, 22), new ItemStack(sakuraleavs, 1, WILD_CARD));
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 31), Items.beef, CROP_RICE, Items.egg);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 32), Items.porkchop, CROP_RICE, Items.egg);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 33), CROP_RICE, RED_BEANS);
        CookingManager.addShapelessRecipe(new ItemStack(foods, 1, 34), CROP_RICE, RED_BEANS, itemSeaweed);
    }

    private void addGrindRecipe() {
        GrindManager.addRecipe(new ItemStack(Blocks.gravel), new ItemStack(Blocks.sand, 2), new ItemStack(Blocks.stone, 1), 0.2F);
        GrindManager.addRecipe(new ItemStack(Blocks.gravel), new ItemStack(Blocks.sand), Blocks.cobblestone, 0.1F);
        GrindManager.addRecipe(new ItemStack(Blocks.sand), new ItemStack(Items.flint), Blocks.gravel, 0.14F);
        GrindManager.addRecipe(new ItemStack(Items.dye, 3, 15), new ItemStack(Items.dye, 2, 15), Items.bone, 0.5F);
        GrindManager.addRecipe(new ItemStack(Items.blaze_powder, 2, 0), new ItemStack(Items.blaze_powder, 1, 0), Items.blaze_rod, 0.5F);
        GrindManager.addRecipe(new ItemStack(BambooInit.dustClay, 2, 0), new ItemStack(Blocks.hardened_clay, 1, IGrindRecipe.WILD_CARD));
        GrindManager.addRecipe(new ItemStack(BambooInit.dustClay, 2, 0), new ItemStack(Blocks.stained_hardened_clay, 1, IGrindRecipe.WILD_CARD));
        GrindManager.addRecipe(new ItemStack(BambooInit.dustClay), new ItemStack(Blocks.sand, 2));
        GrindManager.addRecipe(new ItemStack(rawrice, 1, 0), new ItemStack(riceSeed, 4, 0));
        GrindManager.addRecipe(new ItemStack(Blocks.sand, 4, 0), new ItemStack(Blocks.sandstone, 1, 0));
        GrindManager.addRecipe(new ItemStack(Items.dye, 1, 2), new ItemStack(Blocks.leaves, 4, IGrindRecipe.WILD_CARD));
        GrindManager.addRecipe(new ItemStack(moss, 1, 0), new ItemStack(Blocks.gravel, 64, 0), new ItemStack(Blocks.mossy_cobblestone, 64, 0), 1F);
        GrindManager.addRecipe(new ItemStack(BambooInit.flour), new ItemStack(Items.wheat, 2));
    }

    private void addBileRecipe() {
        BoilManager.addSimpleBoilItem(BambooInit.boiledEgg, 3600, Items.egg);
        BoilManager.addSimpleBoilItem(Items.golden_axe, 5990, Items.iron_axe);
        ItemStack is = new ItemStack(Items.dye);
        for (int i = 0; i < 15; i++) {
            is.setItemDamage(i);
            BoilManager.addBoilItem(new BoilDye(), is);
        }
    }

    private void addOreDictionary() {
        OreDictionary.registerOre(BAMBOO, itembamboo);
        OreDictionary.registerOre(COOKING_RICE, new ItemStack(foods, 1, 0));
        OreDictionary.registerOre(TUDURA, tudura);
        OreDictionary.registerOre(BUSKET, bambooBasket);
        OreDictionary.registerOre("treeSapling", sakura);
        OreDictionary.registerOre(LOG_SAKURA, sakuralog);
        OreDictionary.registerOre("logWood", sakuralog);
        OreDictionary.registerOre("plankWood", new ItemStack(decoration_dir, 1, 2));
        OreDictionary.registerOre(CROP_RICE, rawrice);
        OreDictionary.registerOre("rice", rawrice);
        OreDictionary.registerOre(CROP_STRAW, straw);
        OreDictionary.registerOre("dustClay", dustClay);
        OreDictionary.registerOre(NATTO, new ItemStack(foods, 1, 27));
        OreDictionary.registerOre(ZUNDA, bean);
        OreDictionary.registerOre(SOY_BEANS, bean);
        OreDictionary.registerOre(RED_BEANS, bean);
        OreDictionary.registerOre(MOCHI, new ItemStack(foods, 2, 22));
        OreDictionary.registerOre(COOKED_MOCHI, new ItemStack(foods, 2, 23));
        OreDictionary.registerOre(FLOUR, BambooInit.flour);
        OreDictionary.registerOre("cropWheat", Items.stick);

    }

    private void addFuel() {
        GameRegistry.registerFuelHandler(new IFuelHandler() {
            @Override
            public int getBurnTime(ItemStack fuel) {
                if (fuel.getItem() == straw) {
                    return 30;
                }
                if (fuel.getItem() == Item.getItemFromBlock(decoration_dir) && fuel.getItemDamage() == 4) {
                    return 270;
                }
                return 0;
            }
        });
    }

    //1個目は主素材のみ、2個めはサブ素材、だいたいツヅラ
    private void addStairRecipe(ItemStack output, Object... input) {
        if (input.length == 0) {
            throw new IllegalArgumentException();
        }
        if (input.length == 1) {
            addShapedOreRecipe(output, "#  ", "## ", "###", '#', input[0]);
            addShapedOreRecipe(output, "  #", " ##", "###", '#', input[0]);
        } else {
            addShapedOreRecipe(output, "#  ", "#X ", "###", '#', input[0], 'X', input[1]);
            addShapedOreRecipe(output, "  #", " X#", "###", '#', input[0], 'X', input[1]);
        }
    }

    private void addSlabRecipe(ItemStack output, ItemStack input) {
        GameRegistry.addRecipe(output, "###", '#', input);
    }

    private void addCarpetRecipe(ItemStack output, ItemStack input) {
        GameRegistry.addRecipe(output, "##", '#', input);
    }

    private void addShapedOreRecipe(ItemStack itemStack, Object... objects) {
        GameRegistry.addRecipe(new ShapedOreRecipe(itemStack, objects));
    }

    private void addShapelessOreRecipe(ItemStack itemStack, Object... objects) {
        GameRegistry.addRecipe(new ShapelessOreRecipe(itemStack, objects));
    }
}
