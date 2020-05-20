package com.pixelsky.varityaddon.init;

import cn.mccraft.pangu.core.loader.AutoWired;
import cn.mccraft.pangu.core.loader.annotation.RegItem;
import cn.mccraft.pangu.core.loader.creativetabs.SharedCreativeTab;
import com.pixelsky.varityaddon.item.BaseFlint;
import com.pixelsky.varityaddon.item.BaseWand;
import com.pixelsky.varityaddon.item.flint.*;
import com.pixelsky.varityaddon.item.wand.*;

@AutoWired
@SharedCreativeTab("varityaddon")
public class ModItems {
    //wand
    @RegItem
    public BaseWand firewand=new WandFire();
    @RegItem
    public BaseWand orderwand=new WandOrder();

    @RegItem
    public BaseWand discordwand=new WandDiscord();
    @RegItem
    public BaseWand waterwand=new WandWater();
    @RegItem
    public BaseWand windwand=new WandWind();
    //flint
    @RegItem
    public BaseFlint fireflint=new FlintFire();
    @RegItem
    public BaseFlint orderflint=new FlintOrder();
    @RegItem
    public BaseFlint discordflint=new FlintDiscord();
    @RegItem
    public  BaseFlint waterflint=new FlintWater();
    @RegItem
    public  BaseFlint windflint=new FlintWind();
}
