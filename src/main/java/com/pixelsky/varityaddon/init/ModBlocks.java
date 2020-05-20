package com.pixelsky.varityaddon.init;

import cn.mccraft.pangu.core.loader.AutoWired;
import cn.mccraft.pangu.core.loader.annotation.RegBlock;
import cn.mccraft.pangu.core.loader.annotation.RegItem;
import cn.mccraft.pangu.core.loader.creativetabs.SharedCreativeTab;
import com.pixelsky.varityaddon.block.BaseFire;
import com.pixelsky.varityaddon.block.BaseFluid;
import com.pixelsky.varityaddon.block.BaseTorch;
import com.pixelsky.varityaddon.block.fire.*;
import com.pixelsky.varityaddon.block.fluid.FluidDiscord;
import com.pixelsky.varityaddon.block.fluid.FluidOrder;
import com.pixelsky.varityaddon.block.fluid.FluidWater;
import com.pixelsky.varityaddon.block.fluid.FluidWind;
import com.pixelsky.varityaddon.block.torch.TorchBreed;
import com.pixelsky.varityaddon.block.torch.TorchFarm;
import com.pixelsky.varityaddon.block.torch.TorchNoSpawn;
import com.pixelsky.varityaddon.block.torch.TorchTime;
import com.pixelsky.varityaddon.item.BaseWand;

@AutoWired
@SharedCreativeTab("varityaddon")
public class ModBlocks {
    //torch
    @RegBlock
    BaseTorch torch_breed=new TorchBreed();
    @RegBlock
    BaseTorch torch_farm=new TorchFarm();
    @RegBlock
    BaseTorch torch_time=new TorchTime();
    @RegBlock
    BaseTorch torch_nospawn=new TorchNoSpawn();
    //fluid
    @RegBlock
    BaseFluid fluid_discord=new FluidDiscord();
    @RegBlock
    BaseFluid fluid_order=new FluidOrder();
    @RegBlock
    BaseFluid fluid_wind=new FluidWind();
    @RegBlock
    BaseFluid fluid_fire=new FluidWind();
    @RegBlock
    BaseFluid fluid_water=new FluidWater();
    //fire
    @RegBlock
    BaseFire fire_discord=new FireDiscord();
    @RegBlock
    BaseFire fire_fire=new FireFire();
    @RegBlock
    BaseFire fire_order=new FireOrder();
    @RegBlock
    BaseFire fire_water=new FireWater();
    @RegBlock
    BaseFire fire_wind=new FireWind();
}
