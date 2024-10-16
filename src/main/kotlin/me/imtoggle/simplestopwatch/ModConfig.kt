package me.imtoggle.simplestopwatch

import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.HUD
import cc.polyfrost.oneconfig.config.annotations.KeyBind
import cc.polyfrost.oneconfig.config.annotations.Switch
import cc.polyfrost.oneconfig.config.core.OneKeyBind
import cc.polyfrost.oneconfig.config.data.*
import cc.polyfrost.oneconfig.libs.universal.UKeyboard

object ModConfig : Config(Mod(SimpleStopWatch.NAME, ModType.UTIL_QOL), "${SimpleStopWatch.MODID}.json") {

    @KeyBind(
        name = "Start / Pause KeyBind"
    )
    var keyBind = OneKeyBind(UKeyboard.KEY_NONE)

    @KeyBind(
        name = "Reset KeyBind"
    )
    var resetKeyBind = OneKeyBind(UKeyboard.KEY_NONE)

    @Switch(
        name = "Speedrun Mode",
        size = 2
    )
    var speedrunMode = false

    @HUD(
        name = "Stopwatch HUD",
        category = "hud"
    )
    var hud = StopWatchHud()

    init {
        initialize()
        registerKeyBind(resetKeyBind) {
            hud.reset()
        }
    }

}