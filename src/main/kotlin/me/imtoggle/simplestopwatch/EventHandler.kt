package me.imtoggle.simplestopwatch

import cc.polyfrost.oneconfig.events.EventManager
import cc.polyfrost.oneconfig.events.event.ChatReceiveEvent
import cc.polyfrost.oneconfig.events.event.RenderEvent
import cc.polyfrost.oneconfig.events.event.Stage
import cc.polyfrost.oneconfig.events.event.WorldLoadEvent
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe
import cc.polyfrost.oneconfig.utils.hypixel.HypixelUtils
import cc.polyfrost.oneconfig.utils.hypixel.LocrawInfo
import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil

object EventHandler {

    val bedwars = listOf(
        "§r§f                                  §r§f§lBed Wars§r",
        "§r",
        "§r§f     §r§e§lProtect your bed and destroy the enemy beds.§r",
        "§r§f      §r§e§lUpgrade yourself and your team by collecting§r",
        "§r§f    §r§e§lIron, Gold, Emerald and Diamond from generators§r",
        "§r§f                  §r§e§lto access powerful upgrades.§r",
        "§r",
    )

    val skywars = listOf(
        "§r",
        "§r§f         §r§e§lGather resources and equipment on your§r",
        "§r§f     §r§e§lisland in order to eliminate every other player.§r",
        "§r§f        §r§e§lGo to the center island for special chests§r",
        "§r§f                           §r§e§lwith special items!§r",
    )

    val gamesMap = HashMap<LocrawInfo.GameType, List<String>>()

    var currentIndex = 0

    var lastPressed = false

    fun initialize() {
        gamesMap[LocrawInfo.GameType.BEDWARS] = bedwars
        gamesMap[LocrawInfo.GameType.SKYWARS] = skywars
        EventManager.INSTANCE.register(this)
    }

    @Subscribe
    fun onWorldChange(event: WorldLoadEvent) {
        currentIndex = 0
        if (ModConfig.speedrunMode) ModConfig.hud.reset()
    }

    @Subscribe
    fun onRenderTick(event: RenderEvent) {
        if (event.stage != Stage.START) return
        val pressed = ModConfig.keyBind.isActive
        if (pressed != lastPressed) {
            lastPressed = pressed
            if (pressed) ModConfig.hud.onPress()
        }
        ModConfig.hud.setText()
    }

    @Subscribe
    fun onReceive(event: ChatReceiveEvent) {
        if (!ModConfig.speedrunMode) return
        if (!HypixelUtils.INSTANCE.isHypixel) return
        if (!LocrawUtil.INSTANCE.isInGame) return
        LocrawUtil.INSTANCE.locrawInfo ?: return
        val gameType = LocrawUtil.INSTANCE.locrawInfo!!.gameType
        if (gameType !in gamesMap.keys) return
        if (event.message.formattedText.equals(gamesMap[gameType]!![currentIndex])) {
            currentIndex++
            if (currentIndex == gamesMap[gameType]!!.size - 1) {
                currentIndex = 0
                ModConfig.hud.start()
            }
        }
    }

}