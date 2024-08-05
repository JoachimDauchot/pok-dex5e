package com.example.pokdex.ui.converters

fun AttributesStringConverter(string: String): String {
    when (string.lowercase()) {
        "strength" -> return "STR"
        "dexterity" -> return "DEX"
        "constitution" -> return "CON"
        "intelligence" -> return "INT"
        "wisdom" -> return "WIS"
        "charisma" -> return "CHA"
    }
    return "N/A"
}
