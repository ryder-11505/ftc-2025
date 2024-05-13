package com.example.visionkitvis.ui.colorscheme.scheme

import com.example.visionkitvis.ui.colorscheme.ColorManager
import java.awt.Color

open class ColorSchemeRedDark : ColorSchemeRedLight() {
    override val isDark: Boolean = true

    override val AXIS_X_COLOR: Color = ColorManager.COLOR_PALETTE.GRAY_300
    override val AXIS_Y_COLOR: Color = ColorManager.COLOR_PALETTE.GRAY_300

    override val AXIS_NORMAL_OPACITY: Double = 0.2

    override val TRAJECTORY_SLIDER_BG: Color = ColorManager.COLOR_PALETTE.GRAY_800
    override val TRAJECTORY_TEXT_COLOR: Color = ColorManager.COLOR_PALETTE.GRAY_100

    override val UI_MAIN_BG: Color = ColorManager.COLOR_PALETTE.GRAY_800
}