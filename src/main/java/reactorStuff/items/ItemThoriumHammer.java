package reactorStuff.items;

import java.util.List;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemThoriumHammer extends ItemPickaxe{

	public static final ToolMaterial THORIUM=EnumHelper.addToolMaterial("thorium", 2, 200, 5F, 2F, 10);
	
	protected ItemThoriumHammer() {
		super(THORIUM);
		setRegistryName("thorium_hammer");
		setUnlocalizedName(getRegistryName().toString());
		setCreativeTab(CreativeTabs.COMBAT);
		this.attackSpeed=-3F;
		this.attackDamage=8F;
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel(){
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return super.canApplyAtEnchantingTable(stack, enchantment)||enchantment.type==EnumEnchantmentType.WEAPON||enchantment==Enchantments.POWER;
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);
        return true;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		int[] ids=OreDictionary.getOreIDs(repair);
		for(int id:ids){
			if(OreDictionary.getOreName(id).equals("ingotThorium"))
				return true;
		}
		return false;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		// TODO Auto-generated method stub
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		int power=EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, itemstack);
		if(power==0){
			playerIn.getCooldownTracker().setCooldown(this, 100);
			if(worldIn.isRemote){
				worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1F, 1F);
			}
		}else{
			RayTraceResult rayTrace=getRayTraceFromEntity(worldIn, playerIn, false, 128);
			if(rayTrace.typeOfHit==RayTraceResult.Type.MISS)
				return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
			else{
				EntityLightningBolt lightning=new EntityLightningBolt(worldIn, rayTrace.hitVec.x-0.5D, rayTrace.hitVec.y, rayTrace.hitVec.z-0.5D, true);
				if(!worldIn.isRemote){
					List<Entity> entitys=worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, new AxisAlignedBB(rayTrace.hitVec.x-3D, rayTrace.hitVec.y-3D, rayTrace.hitVec.z-3D, rayTrace.hitVec.x+3D, rayTrace.hitVec.y+9D, rayTrace.hitVec.z+3D));
					worldIn.spawnEntity(lightning);
					for(Entity entity:entitys){
						double distance=Math.max(Math.abs(entity.posX-rayTrace.hitVec.x), Math.abs(entity.posZ-rayTrace.hitVec.z));
						double strenght=Math.min(1, Math.max(0D,(3D-distance)/2D));
						entity.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 0F);
						entity.attackEntityFrom(DamageSource.LIGHTNING_BOLT, (float)(5*power*strenght));
						entity.onStruckByLightning(lightning);
					}
				}else
					worldIn.spawnEntity(lightning);
				playerIn.getCooldownTracker().setCooldown(this, 100);
				if(!playerIn.capabilities.isCreativeMode)
					itemstack.damageItem(power, playerIn);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}
	
	public static RayTraceResult getRayTraceFromEntity(World worldIn, Entity entityIn, boolean useLiquids, double range)
    {
        Vec3d eyesVec = new Vec3d(entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ);
        Vec3d rangedLookRot = entityIn.getLook(1f).scale(range);
        Vec3d lookVec = eyesVec.add(rangedLookRot);

        RayTraceResult result = worldIn.rayTraceBlocks(eyesVec, lookVec, useLiquids, false, false);

        if (result == null)
        {
            result = new RayTraceResult(RayTraceResult.Type.MISS, Vec3d.ZERO, EnumFacing.UP, BlockPos.ORIGIN);
        }

        AxisAlignedBB bb = entityIn.getEntityBoundingBox().expand(rangedLookRot.x, rangedLookRot.y, rangedLookRot.z).expand(1d, 1d, 1d);
        List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity(entityIn, bb);

        double closest = result.typeOfHit == RayTraceResult.Type.BLOCK ? eyesVec.distanceTo(result.hitVec) : Double.MAX_VALUE;
        RayTraceResult entityTrace = null;
        Entity targetEntity = null;

        for (int i = 0; i < list.size(); i++)
        {
            Entity entity = list.get(i);
            bb = entity.getEntityBoundingBox();
            RayTraceResult traceTmp = bb.calculateIntercept(lookVec, eyesVec);

            if (traceTmp != null)
            {
                double distance = eyesVec.distanceTo(traceTmp.hitVec);

                if (distance <= closest)
                {
                    targetEntity = entity;
                    entityTrace = traceTmp;
                    closest = distance;
                }
            }
        }

        if (targetEntity != null)
        {
            result = new RayTraceResult(targetEntity, entityTrace.hitVec);
        }

        return result;
    }

}
