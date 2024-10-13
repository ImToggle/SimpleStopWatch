package me.imtoggle.simplestopwatch

import cc.polyfrost.oneconfig.config.annotations.Exclude
import cc.polyfrost.oneconfig.hud.SingleTextHud
import java.text.DecimalFormat
import kotlin.math.floor

class StopWatchHud : SingleTextHud("Time", true) {

    @Exclude
    private val format = DecimalFormat("00.000")

    @Exclude
    private var lastTime = System.currentTimeMillis()

    @Exclude
    private var runTime = 0L

    @Exclude
    private var running = false

    @Exclude
    private var text = ""

    fun start() {
        running = true
        lastTime = System.currentTimeMillis()
    }

    fun reset() {
        running = false
        runTime = 0L
    }

    fun onPress() {
        running = !running
        if (running) lastTime = System.currentTimeMillis()
    }

    fun setText() {
        if (running) runTime += System.currentTimeMillis() - lastTime
        lastTime = System.currentTimeMillis()
        var second = runTime / 1000f
        val minute = floor(second / 60).toInt()
        second %= 60
        text = "$minute:${format.format(second)}"
    }

    override fun getText(example: Boolean): String {
        return text
    }

}