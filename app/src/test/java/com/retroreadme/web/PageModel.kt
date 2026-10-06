package com.retroreadme.web

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.retroreadme.core.Section
import com.retroreadme.core.Tone
import com.retroreadme.ui.GuidePalette

/*
 * A renderer-neutral description of a guide for the web app (docs/app.js), built from the same
 * content the Android screens use. Each guide's exporter mirrors its Compose screens with these
 * blocks. Colors are "#RRGGBB" or a palette token: @accent @warn @secret @line @muted @text.
 * Text that counts checkmarks is a template the web app fills in: {d} done, {n} total, {r} left.
 */

const val ACCENT = "@accent"
const val WARN = "@warn"
const val SECRET = "@secret"
const val LINE = "@line"
const val MUTED = "@muted"

fun hex(c: Color): String = "#%06X".format(c.toArgb() and 0xFFFFFF)

fun palette(p: GuidePalette) = mapOf(
    "bg" to hex(p.background), "bar" to hex(p.bar), "panel" to hex(p.panel), "sel" to hex(p.selected),
    "line" to hex(p.line), "accent" to hex(p.accent), "text" to hex(p.text), "muted" to hex(p.muted),
    "warn" to hex(p.warning), "secret" to hex(p.secret),
)

class Row(
    val key: String,
    val title: String,
    val subtitle: String? = null,
    /** Group heading shown above this row. */
    val group: String? = null,
    /** Progress cells: checklist id and color for each. */
    val cells: List<Pair<String, String>>? = null,
    /** Live subtitle, e.g. ids to "{d} of {n} found". Replaces [subtitle]. */
    val count: Pair<List<String>, String>? = null,
    /** Live subtitle from one checkbox: id, text when done, text when not. */
    val subCheck: Triple<String, String, String>? = null,
) {
    fun json() = mapOf(
        "k" to key, "t" to title, "s" to subtitle, "g" to group,
        "cells" to cells?.map { listOf(it.first, it.second) },
        "count" to count?.let { mapOf("ids" to it.first, "fmt" to it.second) },
        "subCheck" to subCheck?.toList(),
    )
}

class Tab(val title: String) {
    val rows = mutableListOf<Row>()
    val pages = linkedMapOf<String, Page>()

    fun row(row: Row, page: Page) {
        rows += row
        pages[row.key] = page
    }

    fun json() = mapOf("title" to title, "rows" to rows.map { it.json() }, "pages" to pages.mapValues { it.value.json() })
}

class Page(val title: String, val subtitle: String?) {
    val blocks = mutableListOf<Map<String, Any?>>()

    fun tags(vararg tags: Pair<String, String>) {
        if (tags.isNotEmpty()) blocks += mapOf("tags" to tags.map { listOf(it.first, it.second) })
    }

    /** Small text outside any panel. */
    fun note(text: String, color: String = MUTED) {
        blocks += mapOf("note" to text, "c" to color)
    }

    /** A panel. With [check], tapping it toggles that checklist id. */
    fun panel(stripe: String = LINE, check: String? = null, build: PanelB.() -> Unit) {
        blocks += mapOf("panel" to mapOf("st" to stripe, "chk" to check, "p" to PanelB().apply(build).parts))
    }

    fun section(s: Section) = panel(toneColor(s.tone)) {
        s.heading?.let { h(it, toneColor(s.tone)) }
        lines(s.lines, numbered = s.numbered, marker = toneColor(s.tone))
    }

    /** Live progress: tags like "Secret 3 of 24", then one meter row per group. */
    fun meters(tags: List<Meter> = emptyList(), rows: List<Meter>) {
        blocks += mapOf("meters" to mapOf("tags" to tags.map { it.json() }, "rows" to rows.map { it.json() }))
    }

    /** The two-step "Clear checklist" button. */
    fun reset() {
        blocks += mapOf("reset" to true)
    }

    fun map(area: String, item: String) {
        blocks += mapOf("map" to mapOf("area" to area, "item" to item))
    }

    fun json() = mapOf("t" to title, "s" to subtitle, "b" to blocks)
}

/** A progress group: label, the ids it counts, and one color per id. */
class Meter(val label: String, val ids: List<String>, val colors: List<String>) {
    fun json() = mapOf("label" to label, "ids" to ids, "colors" to colors)
}

class PanelB {
    val parts = mutableListOf<Map<String, Any?>>()

    fun h(text: String, color: String = ACCENT) { parts += mapOf("h" to text, "c" to color) }

    /** Bullet or numbered lines. [checks] puts a ✓ before each line whose id is checked. */
    fun lines(lines: List<String>, numbered: Boolean = false, marker: String = ACCENT, checks: List<String?>? = null) {
        if (lines.isNotEmpty()) parts += mapOf("lines" to lines, "num" to numbered, "m" to marker, "chk" to checks)
    }

    fun tags(vararg tags: Pair<String, String>) {
        if (tags.isNotEmpty()) parts += mapOf("tags" to tags.map { listOf(it.first, it.second) })
    }

    fun tags(tags: List<Pair<String, String>>) = tags(*tags.toTypedArray())

    fun lab(label: String, text: String, color: String) { parts += mapOf("lab" to label, "x" to text, "c" to color) }

    /** A paragraph. Style: null (body), "muted" or "warn" (small notes). */
    fun x(text: String, style: String? = null) { parts += mapOf("x" to text, "style" to style) }

    /** Header row for a checkable panel: title, status text when done / not done, optional badge. */
    fun check(title: String, done: String, notDone: String, badge: Pair<String, String>? = null) {
        parts += mapOf("check" to mapOf("t" to title, "done" to done, "not" to notDone, "badge" to badge?.toList()))
    }

    /** A numbered step header (MMX2 boss order). */
    fun big(number: Int, title: String, subtitle: String, tag: Pair<String, String>?) {
        parts += mapOf("big" to mapOf("n" to number, "t" to title, "s" to subtitle, "tag" to tag?.toList()))
    }

    /** Live text, e.g. "{d} of {n}". */
    fun count(ids: List<String>, fmt: String, style: String? = "muted") {
        parts += mapOf("count" to mapOf("ids" to ids, "fmt" to fmt), "style" to style)
    }

    fun meter(ids: List<String>, colors: List<String>) { parts += mapOf("meter" to mapOf("ids" to ids, "colors" to colors)) }

    /** Live "next unchecked item" line from an ordered list of ids and labels. */
    fun next(label: String, items: List<Pair<String, String>>) {
        parts += mapOf("next" to mapOf("label" to label, "items" to items.map { listOf(it.first, it.second) }))
    }
}

fun toneColor(t: Tone) = when (t) {
    Tone.NORMAL -> ACCENT
    Tone.WARNING -> WARN
    Tone.SECRET -> SECRET
}

/** Minimal JSON writer (the app has no JSON library). Drops null map values. */
fun toJson(v: Any?, sb: StringBuilder = StringBuilder()): StringBuilder {
    when (v) {
        null -> sb.append("null")
        is String -> {
            sb.append('"')
            v.forEach { ch ->
                when {
                    ch == '"' -> sb.append("\\\"")
                    ch == '\\' -> sb.append("\\\\")
                    ch == '\n' -> sb.append("\\n")
                    ch < ' ' -> sb.append("\\u%04x".format(ch.code))
                    else -> sb.append(ch)
                }
            }
            sb.append('"')
        }
        is Number, is Boolean -> sb.append(v.toString())
        is Map<*, *> -> {
            sb.append('{')
            var first = true
            v.forEach { (k, value) ->
                if (value == null) return@forEach
                if (!first) sb.append(',')
                first = false
                toJson(k.toString(), sb); sb.append(':'); toJson(value, sb)
            }
            sb.append('}')
        }
        is Iterable<*> -> {
            sb.append('[')
            v.forEachIndexed { i, x -> if (i > 0) sb.append(','); toJson(x, sb) }
            sb.append(']')
        }
        is Pair<*, *> -> toJson(listOf(v.first, v.second), sb)
        else -> error("Can't write ${v::class} as JSON")
    }
    return sb
}
