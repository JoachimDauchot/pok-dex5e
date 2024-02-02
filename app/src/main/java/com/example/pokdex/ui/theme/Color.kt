package com.example.pokdex.ui.theme

import androidx.compose.ui.graphics.Color

val Gray100 = Color(0xff303030)
val Gray20 = Color(0xFF9c9c9c)
val Blue100 = Color(0xFF000a45)
val Blue20 = Color(0xFF0216c7)
val Red100 = Color(0xFF360000)
val Red20 = Color(0xFFc70202)

val BugBrush = Color(148, 188, 74)
val DarkBrush = Color(115, 108, 117)
val DragonBrush = Color(106, 123, 175)
val ElectricBrush = Color(229, 197, 49)
val FairyBrush = Color(227, 151, 209)
val FightingBrush = Color(203, 95, 72)
val FireBrush = Color(234, 122, 60)
val FlyingBrush = Color(125, 166, 222)
val GhostBrush = Color(132, 106, 182)
val GrassBrush = Color(113, 197, 88)
val GroundBrush = Color(204, 159, 79)
val IceBrush = Color(112, 203, 212)
val NormalBrush = Color(170, 176, 159)
val PoisonBrush = Color(180, 104, 183)
val PsychicBrush = Color(229, 112, 155)
val RockBrush = Color(178, 160, 97)
val SteelBrush = Color(137, 161, 176)
val WaterBrush = Color(83, 154, 226)

fun convertTypeToColor(type: String): Color {
    when (type.lowercase()) {
        "dark" -> return DarkBrush
        "dragon" -> return DragonBrush
        "electric" -> return ElectricBrush
        "fairy" -> return FairyBrush
        "fighting" -> return FightingBrush
        "fire" -> return FireBrush
        "flying" -> return FlyingBrush
        "ghost" -> return GhostBrush
        "grass" -> return GrassBrush
        "ground" -> return GroundBrush
        "ice" -> return IceBrush
        "normal" -> return NormalBrush
        "poison" -> return PoisonBrush
        "psychic" -> return PsychicBrush
        "rock" -> return RockBrush
        "steel" -> return SteelBrush
        "water" -> return WaterBrush
    }
    return NormalBrush
}
