package bigbade.advancedenchantments.tileentities;

import bigbade.advancedenchantments.blocks.simpleenchanter.SimpleEnchanterContainer;
import bigbade.advancedenchantments.blocks.simpleenchanter.SimpleEnchanterRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;

import java.util.Random;

public class SimpleEnchanterTile extends TileEntity implements ITickable, IInteractionObject {
    private static final Random rand = new Random();
    public int tickCount;
    public float pageFlip;
    public float pageFlipPrev;
    private float flipT;
    private float flipA;
    public float bookSpread;
    public float bookSpreadPrev;
    public float bookRotation;
    public float bookRotationPrev;
    private float tRot;
    private String customName;

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        if (this.hasCustomName()) {
            compound.setString("CustomName", this.customName);
        }

        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (compound.hasKey("CustomName", 8)) {
            this.customName = compound.getString("CustomName");
        }
    }

    public void update() {
        this.bookSpreadPrev = this.bookSpread;
        this.bookRotationPrev = this.bookRotation;
        EntityPlayer entityplayer = this.world.getClosestPlayer((double) ((float) this.pos.getX()+0.5F), (double) ((float) this.pos.getY()+0.5F), (double) ((float) this.pos.getZ()+0.5F), 3.0D, false);

        if (entityplayer != null) {
            double d0 = entityplayer.posX-(double) ((float) this.pos.getX()+0.5F);
            double d1 = entityplayer.posZ-(double) ((float) this.pos.getZ()+0.5F);
            this.tRot = (float) MathHelper.atan2(d1, d0);
            this.bookSpread += 0.1F;

            if (this.bookSpread < 0.5F || rand.nextInt(40) == 0) {
                float f1 = this.flipT;

                while (true) {
                    this.flipT += (float) (rand.nextInt(4)-rand.nextInt(4));

                    if (f1 != this.flipT) {
                        break;
                    }
                }
            }
        } else {
            this.tRot += 0.02F;
            this.bookSpread -= 0.1F;
        }

        while (this.bookRotation >= (float) Math.PI) {
            this.bookRotation -= ((float) Math.PI*2F);
        }

        while (this.bookRotation < -(float) Math.PI) {
            this.bookRotation += ((float) Math.PI*2F);
        }

        while (this.tRot >= (float) Math.PI) {
            this.tRot -= ((float) Math.PI*2F);
        }

        while (this.tRot < -(float) Math.PI) {
            this.tRot += ((float) Math.PI*2F);
        }

        float f2;

        for (f2 = this.tRot-this.bookRotation; f2 >= (float) Math.PI; ) {
            f2 -= ((float) Math.PI*2F);
        }

        while (f2 < -(float) Math.PI) {
            f2 += ((float) Math.PI*2F);
        }

        this.bookRotation += f2*0.4F;
        this.bookSpread = MathHelper.clamp(this.bookSpread, 0.0F, 1.0F);
        ++this.tickCount;
        this.pageFlipPrev = this.pageFlip;
        float f = (this.flipT-this.pageFlip)*0.4F;
        f = MathHelper.clamp(f, -0.2F, 0.2F);
        this.flipA += (f-this.flipA)*0.9F;
        this.pageFlip += this.flipA;
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.enchant";
    }

    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customNameIn) {
        this.customName = customNameIn;
    }

    /**
     * Get the formatted ChatComponent that will be used for the sender's username in chat
     */
    public ITextComponent getDisplayName() {
        return (this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName()));
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new SimpleEnchanterContainer(playerInventory, this.world, this.pos);
    }

    public String getGuiID() {
        return "advancedenchantments:simple_table";
    }
}
