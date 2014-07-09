package ruby.bamboo.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ruby.bamboo.BambooCore;
import ruby.bamboo.BambooUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDecoPlasterCurve extends Block implements IExOnBLockPlacedBy {
    private IIcon[] icons;

    public BlockDecoPlasterCurve() {
        super(Material.ground);
        this.setHardness(0.2F);
        this.setResistance(1.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 4));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        String[] iconName = new String[] { "decoplaster_l", "decoplaster_d" };
        this.icons = new IIcon[iconName.length * 4];
        for (int i = 0; i < iconName.length; i++) {
            for (int j = 0; j < 4; j++) {
                this.icons[(i << 2) | j] = p_149651_1_.registerIcon(BambooCore.resourceDomain + iconName[i] + "_" + j);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return icons[meta % icons.length];
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, EntityLivingBase entity, ItemStack itemStack, int meta) {
        int iconMeta = meta & 12;
        int dir = 0;
        ForgeDirection fd = ForgeDirection.VALID_DIRECTIONS[side];
        if (fd != ForgeDirection.DOWN && fd != ForgeDirection.UP) {
            int playerDir = BambooUtil.getPlayerDir(entity);
            if (playerDir == 0 || playerDir == 3) {
                dir = 3;
                if (0.5F <= hitY) {
                    dir = 2;
                }
            } else {
                if (0.5F <= hitY) {
                    dir = 1;
                }
            }
        } else {
            int playerDir = BambooUtil.getPlayerDir(entity);
            dir = 2;
            if (playerDir == 0 || playerDir == 2) {
                if (0.5F < hitX) {
                    dir = 1;
                }
            } else {
                if (0.5F < hitZ) {
                    dir = 1;
                }
            }
        }
        world.setBlockMetadataWithNotify(x, y, z, iconMeta | dir, 2);
    }

    @Override
    public int damageDropped(int meta) {
        return meta & 12;
    }
}