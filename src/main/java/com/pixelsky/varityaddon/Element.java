package com.pixelsky.varityaddon;

public enum Element {
    //秩序 风 火 水 混沌 地
    Order,Wind,Fire,Water,Discord,Earth;

    /*
    * 火->方块=自然传播
    * 火->实体=消耗后消灭自己
    *
    * Boss=发射魔杖的大实体
    *
    * 魔杖->实体|方块=爆炸+修改
    *
    * 流体->随机周围产生对应火,直接接触流体将会过度浓缩(失明+呕吐)
    * */
    public String getDescription(){
        switch (this) {
            /*
             * 矿石->矿石块
             * 冰->浮冰
             * 沙子->沙石
             * 圆石->石头->石砖
             *
             * 玩家->修复物品
             * */
            //order
            case Order:
                return "将矿石转化为矿石块";
                /*
                * 草->泥土->沙子
                * 矿石->石头->圆石
                * 石砖->破碎石砖
                * 沙石->破碎沙石
                * 石英->破碎石英
                * 水->空气
                * 草->枯草
                *
                * 非亡灵族->凋零
                * */
            //air
            case Wind:
                return "将生命,转化为沙漠";
                /*
                * 自动搜索熔炼合成表,只要是可以熔炼的,都可以燃烧,如果结果Block|ItemBLock就替换方块
                * Item就掉落物品
                *
                * 非地狱系->着火
                * */
            case Fire:
                return "熔炼一切";
                /*
                * 草->泥土->地狱岩
                * 沙砾->灵魂沙
                * 沙子->玻璃->空气
                * 石砖->地狱砖
                * 石头->地狱岩
                * 木头->空气
                * 水->黑曜石->岩浆
                *
                * JJ怪->高压JJ怪
                * 僵尸->巨人
                * 骷髅->凋零骷髅
                * 猪->僵尸猪
                * 玩家死亡->恶魂
                * */
            //discord
            case Discord:
                return "地狱降至";
            /**
             *
             *
             * 草->胡萝卜|小麦|土豆
             *
             * 动物->克隆
             *
             * 所有亡灵类收到伤害,其他回血,速度,饱和,抗性
             *
             * 僵村民->村民
             * 僵尸猪->僵尸
             */
            //water
            case Earth:
                return "生命源泉";

                /*
                *
                * 草|灌木|作物->雪
                * 水->冰->浮冰
                * 岩浆->黑曜石
                *
                * 实体->缓慢
                * 北极熊|雪傀儡->加速,生命恢复
                * */
            //
            case Water:
                return "寒冰之力";
        }
        return "未知元素";
    }
}