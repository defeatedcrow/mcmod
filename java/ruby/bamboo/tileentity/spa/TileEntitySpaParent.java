package ruby.bamboo.tileentity.spa;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntitySpaParent extends TileEntity implements ITileEntitySpa {
    private EntityLivingBase bathingEntity = null;
    private short bathingTime;
    private short lastBathingTime;
    private boolean stay;
    private int color = 0xffffff;

    @Override
    public void addColor(int deycolor) {
        int i = (deycolor & 0xff0000) >> 16;
        int j = (deycolor & 0x00ff00) >> 8;
        int k = (deycolor & 0x0000ff);
        i &= 0xff;
        j &= 0xff;
        k &= 0xff;

        if (((color & 0xff0000) - (i << 16)) >= 0) {
            color -= i << 16;
        } else if (i > 0) {
            color &= 0x00ffff;
        }

        if (((color & 0x00ff00) - (j << 8)) >= 0) {
            color -= j << 8;
        } else if (j < 0) {
            color &= 0xff00ff;
        }

        if (((color & 0x0000ff) - k) >= 0) {
            color -= k;
        } else if (j < 0) {
            color &= 0xffff00;
        }
    }

    @Override
    public void colorUpdate() {
        int meta = (getBlockMetadata() & 4) != 0 ? getBlockMetadata() - 4 : getBlockMetadata() + 4;
        this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, meta, 3);
        /*
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.worldObj.markBlockForRenderUpdate(this.xCoord, this.yCoord + 1, this.zCoord);*/
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public boolean isStay() {
        return stay;
    }

    public void setStay(boolean flg) {
        stay = flg;
    }

    @Override
    public int[] getParentPosition() {
        return new int[] { this.xCoord, this.yCoord, this.zCoord };
    }

    @Override
    public void updateEntity() {
        if (bathingEntity != null) {
            // 入浴キャンセル判定
            if (!bathingEntity.isInWater()) {
                bathingEntity = null;
            }
            lastBathingTime = bathingTime;
        }
    }

    // ブロックからの温泉の接触呼び出し(1tick毎？)
    @Override
    public void onEntityLivingCollision(EntityLivingBase entity) {
        if (bathingEntity == null) {
            bathingEntity = entity;
            lastBathingTime = bathingTime = 0;
        } else {
            if (lastBathingTime == bathingTime) {
                // 20tick1秒で10秒計算
                if (++bathingTime > 200) {
                    int effectNum = this.getEffectNum();
                    if (effectNum == 0) {
                        entity.heal(1);
                        bathingTime = 180;
                    } else if (effectNum == 1) {
                        onFoodEaten(new ItemStack(Items.potionitem, 1, 8289), entity.worldObj, entity);
                        bathingTime = 0;
                    } else if (effectNum == 2) {
                        onFoodEaten(new ItemStack(Items.potionitem, 1, 8297), entity.worldObj, entity);
                        bathingTime = 0;
                    } else if (effectNum == 4) {
                        onFoodEaten(new ItemStack(Items.potionitem, 1, 8195), entity.worldObj, entity);
                        bathingTime = 0;
                    } else if (effectNum == 8) {
                        onFoodEaten(new ItemStack(Items.potionitem, 1, 8290), entity.worldObj, entity);
                        bathingTime = 0;
                    } else if (effectNum == 15) {
                        onFoodEaten(new ItemStack(Items.potionitem, 1, 8292), entity.worldObj, entity);
                    }
                }
            }
        }
    }

    @Override
    public void onEntityItemCollision(EntityItem entity) {
        BoilManager.boil(this, entity);
    }

    private void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityLivingBase par3EntityPlayer) {
        List var4 = Items.potionitem.getEffects(par1ItemStack);

        if (var4 != null) {
            Iterator var5 = var4.iterator();

            while (var5.hasNext()) {
                PotionEffect var6 = (PotionEffect) var5.next();
                par3EntityPlayer.addPotionEffect(new PotionEffect(var6));
            }
        }
    }

    private int getEffectNum() {
        int result = 0;
        int wc = this.getColor();
        int r = (wc & 0xff0000) >> 16;
        int g = (wc & 0x00ff00) >> 8;
        int b = (wc & 0x0000ff);

        if (r < 0xaf && g < 0xaf && b < 0xaf) {
            result = 1;
        }

        if (g < 0x7f && b < 0x7f) {
            result += 2;
        }

        if (r < 0x7f && b < 0x7f) {
            result += 4;
        }

        if (r < 0x7f && g < 0x7f) {
            result += 8;
        }
        return result;
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, var1);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.func_148857_g());
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        stay = nbttagcompound.getBoolean("stay");
        color = nbttagcompound.getInteger("color");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        writeToParentNBT(nbttagcompound);
        nbttagcompound.setBoolean("stay", stay);
    }

    // クライアント送信用NBT
    private void writeToParentNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.setInteger("color", color);
    }

    @Override
    public boolean isTickScheduled() {
        return false;
    }

    @Override
    public void setTickSchedule(boolean flg) {

    }

    @Override
    public int getLastTickMeta() {
        return -1;
    }

    @Override
    public void setLastTickMeta(int meta) {

    }
}
