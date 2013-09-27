package ruby.bamboo;

import static ruby.bamboo.BambooInit.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ruby.bamboo.entity.EnumSlideDoor;
import ruby.bamboo.item.EnumFood;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.resources.Locale;
import net.minecraft.client.resources.ResourceManager;
import net.minecraft.client.resources.ResourceManagerReloadListener;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.util.StringTranslate;

@SideOnly(Side.CLIENT)
public class BambooLocalize implements ResourceManagerReloadListener
{
    private String lang;
    private Properties prop;
    public static BambooLocalize instance = new BambooLocalize();
    static
    {
        instance = new BambooLocalize();
    }
    public static void init()
    {
        for (Field field: Minecraft.getMinecraft().func_110442_L().getClass().getDeclaredFields())
        {
            try
            {
                if (field.getType() == List.class)
                {
                    field.setAccessible(true);
                    ((List)field.get(Minecraft.getMinecraft().func_110442_L())).add(0, instance);
                    instance.func_110549_a(Minecraft.getMinecraft().func_110442_L());
                }
            }
            catch (Exception e)
            {
                FMLLog.log(Level.WARNING, "BambooMod localize exception");
                ((SimpleReloadableResourceManager)Minecraft.getMinecraft().func_110442_L()).func_110542_a(instance);
                return;
            }
        }
    }

    public void load(String lang)
    {
        try
        {
            this.lang = lang;
            prop = new Properties();
            InputStream stream = this.getClass().getResourceAsStream("/ruby/bamboo/lang/" + lang + ".properties");

            if (stream != null)
            {
                prop.load(new InputStreamReader(stream, "UTF-8"));
            }
            else
            {
                System.out.printf("[mod_bamboo]lang %s not found \n", lang);
                return;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        int id;
        //サブタイプ無しは一括
        for(Field field:BambooInit.class.getDeclaredFields()){
        	if(field.getName().matches(".*BID")||field.getName().matches(".*IID")){
        		try {
					id=(int) field.get(null);
					if(Item.itemsList[id]!=null&&!Item.itemsList[id].getHasSubtypes()){
						addName(id);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        }
        //サブタイプありは個別
        addName(new ItemStack(bambooBlockBID, 1, 0));
        addName(new ItemStack(firecrackerIID, 0, 0));
        addName(new ItemStack(firecrackerIID, 0, 1));
        addName(new ItemStack(firecrackerIID, 0, 2));

        for (id = 0; id < EnumFood.values().length; id++)
        {
            addName(new ItemStack(foodsIID, 1, id));
        }

        addName(new ItemStack(bambooSpearIID, 1, 0));
        addName(new ItemStack(bambooSpearIID, 1, 1));

        for (id = 0; id < EnumSlideDoor.values().length; id++)
        {
            addName(new ItemStack(slideDoorsIID, 1, id));
        }

        addName(new ItemStack(dSquareBID, 1, 0));
        addName(new ItemStack(dSquareBID, 1, 4));
        addName(new ItemStack(dSquareBID, 1, 8));
        addName(new ItemStack(dSquareBID, 1, 10));
        addName(new ItemStack(dSquareBID, 1, 12));
        addName(new ItemStack(dHalfSquareBID, 1, 0));
        addName(new ItemStack(dHalfSquareBID, 1, 4));
        addName(new ItemStack(dHalfSquareBID, 1, 8));
        addName(new ItemStack(dHalfSquareBID, 1, 10));
        addName(new ItemStack(dHalfSquareBID, 1, 12));
        addName(new ItemStack(bamboopaneBID, 1, 0));
        addName(new ItemStack(bamboopaneBID, 1, 1));
        addName(new ItemStack(bamboopaneBID, 1, 2));
        addName(new ItemStack(bamboopaneBID, 1, 3));
        addName(new ItemStack(bamboopaneBID, 1, 4));
        addName(new ItemStack(bamboopaneBID, 1, 5));
        for (id = 0; id < 18; id++)
        {
            addName(new ItemStack(shavedIceIID, 1, id));
        }

        for (id = 0; id < 10; id++)
        {
            addName(new ItemStack(shavedIceIID, 1, id));
        }

        addName(new ItemStack(windmillIID, 1, 0));
        addName(new ItemStack(windmillIID, 1, 1));
        addName(new ItemStack(decoBID, 1, 0));
        addName(new ItemStack(decoBID, 1, 1));
        addName(new ItemStack(decoBID, 1, 2));
        addName(new ItemStack(decoBID, 1, 3));
        addName(new ItemStack(decoBID, 1, 4));
        addName(new ItemStack(decoBID, 1, 5));
        addName(new ItemStack(halfDecoBID, 1, 0));
        addName(new ItemStack(halfDecoBID, 1, 1));
        addName(new ItemStack(halfDecoBID, 1, 2));
        addName(new ItemStack(halfDecoBID, 1, 3));
        addName(new ItemStack(halfDecoBID, 1, 4));
        addName(new ItemStack(halfDecoBID, 1, 5));
        addName(new ItemStack(twoDirDecoBID, 1, 0));
        addName(new ItemStack(twoDirDecoBID, 1, 2));
        addName(new ItemStack(halfTwoDirDecoBID, 1, 0));
        addName(new ItemStack(halfTwoDirDecoBID, 1, 2));
        addName(new ItemStack(decoCarpetBID,1,0));
        addName(new ItemStack(decoCarpetBID,1,2));
        addName(new ItemStack(decoCarpetBID,1,4));
        addName(new ItemStack(decoCarpetBID,1,6));
        addName(new ItemStack(decoCarpetBID,1,8));
    }
    private void addName(int itemId){
    	try
        {
            LanguageRegistry.instance().addNameForObject(Item.itemsList[itemId], lang, prop.getProperty(Item.itemsList[itemId].getUnlocalizedName()));
        }
        catch (NullPointerException e)
        {
            ModLoader.getLogger().fine(Item.itemsList[itemId].getUnlocalizedName() + " translation mistake");
        }
    }
    private void addName(ItemStack is)
    {
        try
        {
            LanguageRegistry.instance().addNameForObject(is, lang, prop.getProperty(is.getItem().getUnlocalizedName() + "." + is.getItemDamage()));
        }
        catch (NullPointerException e)
        {
            ModLoader.getLogger().fine(is.getItem().getUnlocalizedName() + "." + is.getItemDamage() + " translation mistake");
        }
    }
    @Override
    public void func_110549_a(ResourceManager resourcemanager)
    {
        Language lang = Minecraft.getMinecraft().func_135016_M().func_135041_c();
        load(lang.func_135034_a());
    }
}