package games.bevs.minecraftbut.senerario.senerarios;

import org.bukkit.Material;
import org.bukkit.block.Block;

import games.bevs.minecraftbut.commons.XMaterial;
import games.bevs.minecraftbut.commons.utils.MathUtils;
import games.bevs.minecraftbut.senerario.Senerario;
import games.bevs.minecraftbut.senerario.options.Optional;
import games.bevs.minecraftbut.world.ButWorld;

public class AnvilRain extends Senerario
{
	@Optional
	private int heightFromGround = 50;
	@Optional
	private int secondsBetweenAnvilFall = 60;
	
	
	public AnvilRain(ButWorld butWorld) 
	{
		super("Anvil Rain", butWorld, XMaterial.ANVIL.parseMaterial(), new String[] {"Anvils will spawn from the sky every minute"} );
	}

	@Override
	public void onStart()
	{
		this.repeat(() -> {
			
			
			for(int x = this.getButWorld().getMinLocation().getBlockX(); x < this.getButWorld().getMaxLocation().getBlockX(); x+= MathUtils.getRandom().nextInt(8))
			{
				for(int z = this.getButWorld().getMinLocation().getBlockZ(); z < this.getButWorld().getMaxLocation().getBlockZ(); z+= MathUtils.getRandom().nextInt(8))
				{
					int highest = this.getButWorld().getWorld().getHighestBlockYAt(x, z);
					int y = highest + this.heightFromGround;
					Block block = this.getButWorld().getWorld().getBlockAt( x, y, z);
					
					Material mat = Material.ANVIL;
					block.getWorld().spawnFallingBlock(block.getLocation(), mat, (byte) 0);
				}
			}
		}, 20l * secondsBetweenAnvilFall);
	}
	
	@Override
	public void onFinish()
	{
		
	}
}
