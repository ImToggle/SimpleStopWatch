package me.imtoggle.simplestopwatch

import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.data.*

object ModConfig : Config(Mod(SimpleStopWatch.NAME, ModType.UTIL_QOL), "${SimpleStopWatch.MODID}.json") {

    init {
        initialize()
    }
}