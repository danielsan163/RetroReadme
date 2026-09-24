package com.retroreadme.core

/** Tone of a text section: sets its stripe and marker color. */
enum class Tone { NORMAL, WARNING, SECRET }

/** A headed block of lines. Shared by every game's plain-text pages. */
data class Section(
    val heading: String?,
    val lines: List<String>,
    val numbered: Boolean = false,
    val tone: Tone = Tone.NORMAL,
)
