package com.retroreadme.core

/**
 * The system a guide is written for. The launcher groups games under these,
 * in this order, and only shows platforms that have at least one guide.
 * Reorder or add entries freely; nothing is saved against them.
 */
enum class Platform(val label: String) {
    SNES("SNES"),
    GBA("GBA"),
    NES("NES"),
    GB("Game Boy"),
    GBC("Game Boy Color"),
    N64("N64"),
    GENESIS("Genesis"),
    PS1("PlayStation"),
}
