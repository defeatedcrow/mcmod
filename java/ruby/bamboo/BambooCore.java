package ruby.bamboo;

import net.minecraft.block.BlockDispenser;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.oredict.RecipeSorter;
import ruby.bamboo.dispenser.DispenserBehaviorBambooSpear;
import ruby.bamboo.dispenser.DispenserBehaviorDirtySnowball;
import ruby.bamboo.dispenser.DispenserBehaviorFireCracker;
import ruby.bamboo.gui.GuiHandler;
import ruby.bamboo.proxy.CommonProxy;
import ruby.bamboo.world.WorldProviderBamboo;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.CoreModManager;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper.UnableToAccessFieldException;

@Mod(modid = "BambooMod", name = "BambooMod",
        version = "Minecraft1.7.2 ver2.6.4.11",
        guiFactory = "ruby.bamboo.BambooConfigGuiFactory")
/*@NetworkMod(channels = { "B_Entity", "bamboo", "bamboo2" },
        packetHandler = NetworkHandler.class,
        connectionHandler = NetworkHandler.class)*/
public class BambooCore {
    public static final String MODID;
    public static final String resourceDomain;
    public static final boolean DEBUGMODE;

    @SidedProxy(serverSide = "ruby.bamboo.proxy.CommonProxy",
            clientSide = "ruby.bamboo.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Instance("BambooMod")
    public static BambooCore instance;

    static {
        MODID = "BambooMod";
        resourceDomain = "bamboo:";
        DEBUGMODE = isDevelopment();
    }

    @Mod.EventHandler
    public void preLoad(FMLPreInitializationEvent e) {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent e) {
        proxy.init();
        proxy.registerTESTileEntity();
        registDispencer();

        // debug
        if (DEBUGMODE) {
            System.out.println("DEBUG MODE Enable");
        }

    }

    private int provideId;

    @Mod.EventHandler
    public void serverStarted(FMLServerStartedEvent event) {
        ManekiHandler.instance.clearManekiList();
        Config.reloadWorldConfig();
        provideId = 0;
        while (!DimensionManager.registerProviderType(++provideId, WorldProviderBamboo.class, false)) {
        }
        System.out.println("dimId:" + Config.dimensionId + " provId:" + provideId);
        DimensionManager.registerDimension(Config.dimensionId, provideId);
    }

    @Mod.EventHandler
    public void serverStoped(FMLServerStoppedEvent event) {
        DimensionManager.unregisterDimension(Config.dimensionId);
        DimensionManager.unregisterProviderType(provideId);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(BambooCore.getInstance(), new GuiHandler());
    }

    @Mod.EventHandler
    public void onAvalible(FMLLoadCompleteEvent event) {
        RecipeSorter.sortCraftManager();
    }

    private void registDispencer() {
        BlockDispenser.dispenseBehaviorRegistry.putObject(BambooInit.snowBall, new DispenserBehaviorDirtySnowball());
        BlockDispenser.dispenseBehaviorRegistry.putObject(BambooInit.firecracker, new DispenserBehaviorFireCracker());
        BlockDispenser.dispenseBehaviorRegistry.putObject(BambooInit.bambooSpear, new DispenserBehaviorBambooSpear());
    }

    public static BambooCore getInstance() {
        return instance;
    }

    private static boolean isDevelopment() {
        boolean result;
        try {
            result = ReflectionHelper.getPrivateValue(CoreModManager.class, null, "deobfuscatedEnvironment");
        } catch (UnableToAccessFieldException e) {
            FMLLog.warning("Debug mode forced false!");
            result = false;
        }
        return result;
    }
}