package ruby.bamboo.proxy;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import ruby.bamboo.BambooInit;
import ruby.bamboo.CustomRenderHandler;
import ruby.bamboo.PlayerDisplayNameHandler;
import ruby.bamboo.entity.*;
import ruby.bamboo.entity.magatama.EntityClock;
import ruby.bamboo.entity.magatama.EntityDummy;
import ruby.bamboo.entity.magatama.EntityFlareEffect;
import ruby.bamboo.entity.magatama.EntityGravityHole;
import ruby.bamboo.entity.magatama.EntityMagatama;
import ruby.bamboo.entity.magatama.EntityRuneEffect;
import ruby.bamboo.entity.magatama.EntityShield;
import ruby.bamboo.entity.magatama.EntityThunderEffect;
import ruby.bamboo.entity.magatama.EntityThunderStorm;
import ruby.bamboo.entity.villager.EntityTrueVillager;
import ruby.bamboo.entity.villager.EntityVillagerHead;
import ruby.bamboo.entity.villager.RenderVillagerHead;
import ruby.bamboo.render.*;
import ruby.bamboo.render.magatama.RenderClock;
import ruby.bamboo.render.magatama.RenderDummy;
import ruby.bamboo.render.magatama.RenderFlareEffect;
import ruby.bamboo.render.magatama.RenderGravityHole;
import ruby.bamboo.render.magatama.RenderMagatama;
import ruby.bamboo.render.magatama.RenderRuneEffect;
import ruby.bamboo.render.magatama.RenderShield;
import ruby.bamboo.render.magatama.RenderThunderEffect;
import ruby.bamboo.render.tileentity.RenderAndon;
import ruby.bamboo.render.tileentity.RenderCampfire;
import ruby.bamboo.render.tileentity.RenderHuton;
import ruby.bamboo.render.tileentity.RenderManeki;
import ruby.bamboo.render.tileentity.RenderMillStone;
import ruby.bamboo.render.tileentity.RenderVillagerBlock;
import ruby.bamboo.tileentity.TileEntityAndon;
import ruby.bamboo.tileentity.TileEntityCampfire;
import ruby.bamboo.tileentity.TileEntityHuton;
import ruby.bamboo.tileentity.TileEntityManeki;
import ruby.bamboo.tileentity.TileEntityMillStone;
import ruby.bamboo.tileentity.TileEntityVillagerBlock;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
        super.preInit();
        CustomRenderHandler.init();
        addRenderer();
    }

    @Override
    public void registerTESTileEntity() {
        ClientRegistry.registerTileEntity(TileEntityCampfire.class, "Camp fire", new RenderCampfire());
        ClientRegistry.registerTileEntity(TileEntityAndon.class, "Andon", new RenderAndon());
        ClientRegistry.registerTileEntity(TileEntityMillStone.class, "MillStone", new RenderMillStone());
        ClientRegistry.registerTileEntity(TileEntityManeki.class, "MManeki", new RenderManeki());
        ClientRegistry.registerTileEntity(TileEntityHuton.class, "Huton", new RenderHuton());
        ClientRegistry.registerTileEntity(TileEntityVillagerBlock.class, "ClientBambooVillagerBlock", new RenderVillagerBlock());
    }

    private void addRenderer() {

        MinecraftForgeClient.registerItemRenderer(BambooInit.bambooBow, (IItemRenderer) BambooInit.bambooBow);
        RenderingRegistry.registerEntityRenderingHandler(EntityBambooSpear.class, new RenderBSpear());
        RenderingRegistry.registerEntityRenderingHandler(EntitySlideDoor.class, new RenderSlideDoor());
        RenderingRegistry.registerEntityRenderingHandler(EntityKakeziku.class, new RenderKakeziku());
        RenderingRegistry.registerEntityRenderingHandler(EntitySakuraPetal.class, new RenderPetal());
        RenderingRegistry.registerEntityRenderingHandler(EntityFirecracker.class, new RenderFirecracker());
        RenderingRegistry.registerEntityRenderingHandler(EntityWindChime.class, new RenderWindBell());
        RenderingRegistry.registerEntityRenderingHandler(EntityWind.class, dummyRender);
        RenderingRegistry.registerEntityRenderingHandler(EntityWindmill.class, new RenderWindmill());
        RenderingRegistry.registerEntityRenderingHandler(EntityWaterwheel.class, new RenderWaterwheel());
        RenderingRegistry.registerEntityRenderingHandler(EntityKaginawa.class, new RenderKaginawa());
        RenderingRegistry.registerEntityRenderingHandler(EntityObon.class, new RenderObon());
        RenderingRegistry.registerEntityRenderingHandler(EntityMagatama.class, new RenderMagatama());
        RenderingRegistry.registerEntityRenderingHandler(EntityFlareEffect.class, new RenderFlareEffect());
        RenderingRegistry.registerEntityRenderingHandler(EntityThunderEffect.class, new RenderThunderEffect());
        RenderingRegistry.registerEntityRenderingHandler(EntityGravityHole.class, new RenderGravityHole());
        RenderingRegistry.registerEntityRenderingHandler(EntityRuneEffect.class, new RenderRuneEffect());
        RenderingRegistry.registerEntityRenderingHandler(EntityClock.class, new RenderClock());
        RenderingRegistry.registerEntityRenderingHandler(EntityShield.class, new RenderShield());
        RenderingRegistry.registerEntityRenderingHandler(EntityDummy.class, new RenderDummy());
        RenderingRegistry.registerEntityRenderingHandler(EntityZabuton.class, new RenderZabuton());
        RenderingRegistry.registerEntityRenderingHandler(EntityThrowZabuton.class, new RenderZabuton());
        RenderingRegistry.registerEntityRenderingHandler(EntityFirefly.class, new RenderFirefly());
        RenderingRegistry.registerEntityRenderingHandler(EntityDummyChair.class, dummyRender);
        RenderingRegistry.registerEntityRenderingHandler(EntityThunderStorm.class, dummyRender);
        RenderingRegistry.registerEntityRenderingHandler(EntityTrueVillager.class, new RenderTrueVillager());
        RenderingRegistry.registerEntityRenderingHandler(EntityVillagerHead.class, new RenderVillagerHead());
    }

    private static Render dummyRender = new Render() {
        @Override
        public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
        }

        @Override
        protected ResourceLocation getEntityTexture(Entity entity) {
            return null;
        }
    };

    @Override
    public void init() {
        super.init();
        new PlayerDisplayNameHandler();
    }
}
