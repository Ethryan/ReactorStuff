package reactorStuff;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import reactorStuff.core.CommonProxy;

@Mod(modid=ReactorStuff.MODID, name=ReactorStuff.MODNAME, version=ReactorStuff.VERSION, dependencies="required-after:ic2;after:optifine")
public class ReactorStuff {
	
	public static final String MODID="reactor_stuff";
	public static final String MODNAME="ReactorStuff";
	public static final String VERSION="1.9";
	
	@Mod.Instance
	public static ReactorStuff instance;
	
	@SidedProxy(clientSide="reactorStuff.core.ClientProxy", serverSide="reactorStuff.core.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e){
		proxy.preInit(e);
	}
	@EventHandler
	public void init(FMLInitializationEvent e){
		proxy.init(e);
	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent e){
		proxy.postInit(e);
	}
	
}
