package ruby.bamboo.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import ruby.bamboo.BambooCore;
import ruby.bamboo.entity.EntityWaterwheel;

public class RenderWaterwheel extends Render {
    private final ModelWaterwheel model;
    private static final ResourceLocation RESOURCE = new ResourceLocation(BambooCore.resourceDomain + "textures/entitys/waterwheel.png");

    public RenderWaterwheel() {
        model = new ModelWaterwheel();
    }

    @Override
    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
        renderWatermill((EntityWaterwheel) var1, var2, var4, var6, var8, var9);
    }

    private void renderWatermill(EntityWaterwheel var1, double var2, double var4, double var6, float var8, float var9) {
        GL11.glPushMatrix();
        bindEntityTexture(var1);
        byte dir = var1.getDir();

        if (dir == 0 || dir == 2) {
            if (dir == 0) {
                GL11.glTranslatef((float) var2, (float) var4, (float) var6 - (var1.getSize() - 1) / 2F);
            } else {
                GL11.glTranslatef((float) var2, (float) var4, (float) var6 + (var1.getSize() - 1) / 2F);
            }
        } else {
            if (dir == 3) {
                GL11.glTranslatef((float) var2 - (var1.getSize() - 1) / 2F, (float) var4, (float) var6);
            } else {
                GL11.glTranslatef((float) var2 + (var1.getSize() - 1) / 2F, (float) var4, (float) var6);
            }
        }

        GL11.glScalef(1 + (var1.getSize() / 2), 1 + (var1.getSize() / 2), 1 + (var1.getSize() / 2));

        dir += (dir == 1 || dir == 3 ? -1 : 1);
        GL11.glRotatef(dir * 90, 0, 1, 0);
        GL11.glRotatef(var1.getRoll(), 1, 0, 0);
        model.render(var1, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return RESOURCE;
    }
}
