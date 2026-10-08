/*
 * Retro README web. Renders the guides exported from the Android app (data/*.json, written by
 * app/src/test/.../web/WebExport.kt). Hash routes: #/ launcher, #/g/<game>/<tab>[/<row key>].
 * Checkmarks live in localStorage per game, so they stay on this device.
 */
(() => {
  "use strict";
  const app = document.getElementById("app");
  const store = {
    get(k, d) { try { const v = localStorage.getItem(k); return v == null ? d : JSON.parse(v); } catch { return d; } },
    set(k, v) { try { localStorage.setItem(k, JSON.stringify(v)); } catch { /* private mode: progress isn't kept */ } },
  };
  const esc = s => String(s ?? "").replace(/[&<>"']/g, c => ({ "&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;", "'": "&#39;" }[c]));
  const TOKENS = { "@accent": "var(--accent)", "@warn": "var(--warn)", "@secret": "var(--secret)", "@line": "var(--line)", "@muted": "var(--muted)", "@text": "var(--text)" };
  const col = c => TOKENS[c] || c || "var(--accent)";

  let index = null;
  const guides = {};

  // ---------------------------------------------------------------- Progress

  const progress = {
    load(id) { return store.get("rr:progress:" + id, {}); },
    isDone(id, key) { return !!this.load(id)[key]; },
    toggle(id, key) { const p = this.load(id); if (p[key]) delete p[key]; else p[key] = true; store.set("rr:progress:" + id, p); },
    reset(id) { store.set("rr:progress:" + id, {}); },
    count(id, ids) { const p = this.load(id); return ids.filter(k => p[k]).length; },
  };
  const fmt = (gid, ids, f) => {
    const d = progress.count(gid, ids), n = ids.length;
    return f.replace("{d}", d).replace("{n}", n).replace("{r}", n - d);
  };

  // ---------------------------------------------------------------- Data

  async function loadJson(path) {
    const r = await fetch(path, { cache: "no-cache" });
    if (!r.ok) throw new Error(path + ": " + r.status);
    return r.json();
  }
  async function getIndex() { return index ??= await loadJson("data/index.json"); }
  async function getGuide(id) { return guides[id] ??= await loadJson(`data/${id}.json`); }

  function setPalette(p) {
    const s = document.documentElement.style;
    Object.entries({ bg: p.bg, bar: p.bar, panel: p.panel, sel: p.sel, line: p.line, accent: p.accent, text: p.text, muted: p.muted, warn: p.warn, secret: p.secret })
      .forEach(([k, v]) => s.setProperty("--" + k, v));
    document.querySelector('meta[name="theme-color"]').setAttribute("content", p.bar);
  }

  // ---------------------------------------------------------------- Routing

  function parse() {
    const parts = location.hash.replace(/^#\/?/, "").split("/").map(decodeURIComponent);
    if (parts[0] === "g" && parts[1]) return { game: parts[1], tab: +parts[2] || 0, key: parts[3] || null };
    return { game: null };
  }
  const go = h => { location.hash = h; };
  const gameHash = (g, t, k) => `#/g/${encodeURIComponent(g)}/${t}` + (k != null ? "/" + encodeURIComponent(k) : "");

  async function render() {
    const r = parse();
    try {
      if (!r.game) await renderLauncher();
      else await renderGuide(r);
    } catch (e) {
      app.innerHTML = `<p class="loading">Couldn't load this page. ${esc(e.message)}</p>`;
    }
  }
  window.addEventListener("hashchange", render);

  // ---------------------------------------------------------------- Launcher

  async function renderLauncher() {
    const idx = await getIndex();
    setPalette(idx.palette);
    document.title = "Retro README";
    const collapsed = store.get("rr:collapsed", []);
    const done = g => g.checklist.length && progress.count(g.id, g.checklist.map(c => c[0])) === g.checklist.length;
    // Gold when every guide is complete, silver when only some are (difficulty groups).
    const starOf = gs => { const n = gs.filter(done).length; return n === 0 ? "" : n === gs.length ? "gold" : "silver"; };
    const title = (text, star) => `<div class="t"><span class="tt"><span>${esc(text)}</span></span><span class="starslot">${star ? pixelStar(star) : ""}</span></div>`;
    const sub = g => g.checklist.length ? `${progress.count(g.id, g.checklist.map(c => c[0]))} of ${g.checklist.length}` : "";
    let html = `<div class="screen launch"><header class="topbar"><span class="badge">RR</span><h1>Retro README</h1></header><div class="pane">`;
    for (const [plat, label] of idx.platforms) {
      const games = idx.games.filter(g => g.platform === plat)
        .sort((a, b) => (a.group || a.title).toLowerCase().localeCompare((b.group || b.title).toLowerCase()));
      if (!games.length) continue;
      const shut = collapsed.includes(plat);
      html += `<button class="plat" data-plat="${plat}"><span class="arrow">${shut ? "▸" : "▾"}</span>${esc(label)}</button>`;
      if (shut) continue;
      const seen = new Set();
      for (const g of games) {
        if (g.group) {
          if (seen.has(g.group)) continue;
          seen.add(g.group);
          const members = games.filter(x => x.group === g.group);
          html += `<div class="game" style="--ga:${g.accent}"><span class="badge">${esc(g.badge.split(" ")[0])}</span><div class="txt">${title(g.group, starOf(members))}<div class="s">${members.length} checklists: ${esc(members.map(m => m.variant).join(" and "))}</div></div></div>`;
          for (const m of members) {
            html += `<button class="game sub" data-game="${m.id}" style="--ga:${m.accent}"><div class="txt">${title(m.variant || m.title, starOf([m]))}<div class="s">${sub(m)}</div></div><span class="chev">›</span></button>`;
          }
        } else {
          html += `<button class="game" data-game="${g.id}" style="--ga:${g.accent}"><span class="badge">${esc(g.badge)}</span><div class="txt">${title(g.title, starOf([g]))}<div class="s">${sub(g)}${sub(g) ? " · " : ""}${esc(g.about[0] || "")}</div></div><span class="chev">›</span></button>`;
        }
      }
    }
    html += `<p class="foot">Checkmarks are saved on this device. Add this page to your Home Screen to use it offline.</p></div></div>`;
    app.innerHTML = html;
    scrollLongTitles();
    app.querySelectorAll("[data-game]").forEach(b => b.onclick = () => go(gameHash(b.dataset.game, 0)));
    app.querySelectorAll("[data-plat]").forEach(b => b.onclick = () => {
      const c = store.get("rr:collapsed", []);
      const p = b.dataset.plat;
      store.set("rr:collapsed", c.includes(p) ? c.filter(x => x !== p) : [...c, p]);
      renderLauncher();
    });
  }

  /** The app's 9x9 pixel star, gold or silver. */
  const STAR = ["....#....", "....#....", "...###...", "####s####", ".###s###.", "..##s##..", "..#sss#..", ".##s.s##.", ".#.....#."];
  function pixelStar(kind) {
    const [fill, shade] = kind === "silver" ? ["#DDE2EA", "#8C95A3"] : ["#F8D830", "#C89010"];
    let rects = "";
    STAR.forEach((row, y) => [...row].forEach((ch, x) => {
      if (ch !== ".") rects += `<rect x="${x}" y="${y}" width="1" height="1" fill="${ch === "#" ? fill : shade}"/>`;
    }));
    return `<svg class="pstar" viewBox="0 0 9 9" shape-rendering="crispEdges" role="img" aria-label="${kind === "silver" ? "Partly complete" : "Complete"}">${rects}</svg>`;
  }

  /** Titles too long for their slot scroll back and forth instead of wrapping. */
  function scrollLongTitles() {
    requestAnimationFrame(() => app.querySelectorAll(".tt").forEach(box => {
      const over = box.firstElementChild.scrollWidth - box.clientWidth;
      if (over > 2) { box.classList.add("scroll"); box.style.setProperty("--over", -over + "px"); }
    }));
  }

  // ---------------------------------------------------------------- Guide

  const listScroll = {};  // remembered list scroll per game/tab

  async function renderGuide(r) {
    const g = await getGuide(r.game);
    setPalette(g.palette);
    document.title = g.title + " · Retro README";
    const tab = g.tabs[r.tab] || g.tabs[0];
    const tabIdx = g.tabs.indexOf(tab);
    const wide = matchMedia("(min-width: 900px)").matches;
    const key = r.key ?? (wide ? tab.rows[0]?.k : null);
    const page = key != null ? tab.pages[key] : null;

    const groups = tab.rows.filter(row => row.g).map(row => row.g);
    let html = `<div class="screen${page && r.key != null ? " show-detail" : ""}">
      <header class="topbar"><button class="iconbtn" data-home aria-label="All games">‹</button><span class="badge">${esc(g.badge)}</span><h1>${esc(g.variant ? g.group + " · " + g.variant : g.title)}</h1></header>
      <nav class="tabs">${g.tabs.map((t, i) => `<button class="tab${i === tabIdx ? " on" : ""}" data-tab="${i}">${esc(t.title)}</button>`).join("")}</nav>
      <div class="body"><div class="pane list">`;
    if (groups.length > 2) {
      html += `<div class="jump"><select data-jump aria-label="Jump to"><option value="">Jump to…</option>${groups.map((x, i) => `<option value="${i}">${esc(x)}</option>`).join("")}</select></div>`;
    }
    html += listHtml(g, tab, key) + `</div><div class="pane detail">`;
    if (page) html += `<div class="backrow"><button data-back>‹ ${esc(tab.title)}</button></div>` + pageHtml(g, page);
    html += `</div></div></div>`;
    app.innerHTML = html;

    const list = app.querySelector(".list");
    const sk = r.game + "/" + tabIdx;
    if (listScroll[sk] != null) list.scrollTop = listScroll[sk];
    list.addEventListener("scroll", () => { listScroll[sk] = list.scrollTop; }, { passive: true });
    if (r.key != null && wide) app.querySelector(".row.sel")?.scrollIntoView({ block: "nearest" });

    app.querySelector("[data-home]").onclick = () => go("#/");
    app.querySelectorAll("[data-tab]").forEach(b => b.onclick = () => go(gameHash(r.game, +b.dataset.tab)));
    app.querySelectorAll("[data-row]").forEach(b => b.onclick = () => go(gameHash(r.game, tabIdx, b.dataset.row)));
    app.querySelector("[data-back]")?.addEventListener("click", () => go(gameHash(r.game, tabIdx)));
    const jump = app.querySelector("[data-jump]");
    if (jump) jump.onchange = () => {
      const el = app.querySelectorAll(".group")[+jump.value];
      if (el) list.scrollTop = el.offsetTop - list.querySelector(".jump").offsetHeight;
      jump.value = "";
    };
    wireDetail(g, r);
    app.querySelector(".detail").scrollTop = 0;
  }

  function cellsHtml(gid, cells) {
    if (!cells || !cells.length) return "";
    const p = progress.load(gid);
    return `<span class="cells">${cells.map(([id, c]) => `<i class="cell" style="background:${col(c)};opacity:${p[id] ? 1 : .22}"></i>`).join("")}</span>`;
  }

  function rowSub(gid, row) {
    if (row.count) return fmt(gid, row.count.ids, row.count.fmt);
    if (row.subCheck) return progress.isDone(gid, row.subCheck[0]) ? row.subCheck[1] : row.subCheck[2];
    return row.s || "";
  }

  function listHtml(g, tab, key) {
    return tab.rows.map(row =>
      (row.g ? `<div class="group">${esc(row.g)}</div>` : "") +
      `<button class="row${row.k === key ? " sel" : ""}" data-row="${esc(row.k)}"><div class="txt"><div class="t">${esc(row.t)}</div>${rowSub(g.id, row) ? `<div class="s">${esc(rowSub(g.id, row))}</div>` : ""}</div>${cellsHtml(g.id, row.cells)}<span class="chev">›</span></button>`,
    ).join("");
  }

  // ---------------------------------------------------------------- Page blocks

  function pageHtml(g, page) {
    return `<h2 class="ptitle">${esc(page.t)}</h2>${page.s ? `<p class="psub">${esc(page.s)}</p>` : ""}` + page.b.map(b => blockHtml(g, b)).join("");
  }

  const tagsHtml = tags => `<div class="tagrow">${tags.map(([t, c]) => `<span class="tag" style="--c:${col(c)}">${esc(t)}</span>`).join("")}</div>`;
  const meterHtml = (gid, ids, colors) => {
    const p = progress.load(gid);
    return `<div class="meter">${ids.map((id, i) => `<i style="background:${col(colors[i] ?? colors[0])};opacity:${p[id] ? 1 : .22}"></i>`).join("")}</div>`;
  };

  function blockHtml(g, b) {
    if (b.tags) return tagsHtml(b.tags);
    if (b.note) return `<p class="note" style="color:${col(b.c)}">${esc(b.note)}</p>`;
    if (b.reset) return `<div class="panel tap" data-reset style="--stripe:var(--warn)"><button class="resetbtn">Clear checklist</button></div>`;
    if (b.meters) {
      const m = b.meters;
      return `<div class="panel" style="--stripe:var(--accent)">` +
        (m.tags.length ? tagsHtml(m.tags.map(t => [`${t.label} ${progress.count(g.id, t.ids)} of ${t.ids.length}`, t.colors[0]])) : "") +
        m.rows.map(r => `<div class="mrow"><div class="ml"><b>${esc(r.label)}</b><span>${progress.count(g.id, r.ids)} of ${r.ids.length}</span></div>${meterHtml(g.id, r.ids, r.colors)}</div>`).join("") +
        `</div>`;
    }
    if (b.map) return mapPanelHtml(g, b.map);
    if (b.panel) {
      const p = b.panel;
      const done = p.chk ? progress.isDone(g.id, p.chk) : false;
      return `<div class="panel${p.chk ? " tap" : ""}" ${p.chk ? `data-check="${esc(p.chk)}"` : ""} style="--stripe:${col(p.st)}">` + p.p.map(x => partHtml(g, x, done)).join("") + `</div>`;
    }
    return "";
  }

  function partHtml(g, x, done) {
    if (x.h != null) return `<div class="ph" style="color:${col(x.c)}">${esc(x.h)}</div>`;
    if (x.lines) {
      const prog = progress.load(g.id);
      const items = x.lines.map((l, i) => {
        const id = x.chk && x.chk[i];
        return `<li>${id && prog[id] ? '<span class="done-mark">✓ </span>' : ""}${esc(l)}</li>`;
      }).join("");
      return x.num ? `<ol class="lines num" style="--m:${col(x.m)}">${items}</ol>` : `<ul class="lines" style="--m:${col(x.m)}">${items}</ul>`;
    }
    if (x.tags) return tagsHtml(x.tags);
    if (x.lab != null) return `<div class="lab" style="--c:${col(x.c)}"><b>${esc(x.lab)}</b><span>${esc(x.x)}</span></div>`;
    if (x.check) {
      const c = x.check;
      const badge = c.badge ? `<span class="tbadge" style="--c:${col(c.badge[1])}">${esc(c.badge[0])}</span>` : "";
      return `<div class="chk${done ? " on" : ""}">${badge}<div class="txt"><div class="t">${esc(c.t)}</div><div class="s">${esc(done ? c.done : c.not)}</div></div><span class="box"></span></div>`;
    }
    if (x.big) {
      const b = x.big;
      return `<div class="big"><span class="n">${b.n}</span><div class="txt"><div class="t">${esc(b.t)}</div><div class="s">${esc(b.s)}</div></div>${b.tag ? tagsHtml([b.tag]) : ""}</div>`;
    }
    if (x.count) return `<div class="x ${x.style || ""}">${esc(fmt(g.id, x.count.ids, x.count.fmt))}</div>`;
    if (x.meter) return meterHtml(g.id, x.meter.ids, x.meter.colors);
    if (x.next) {
      const prog = progress.load(g.id);
      const n = x.next.items.find(([id]) => !prog[id]);
      return `<div class="lab" style="--c:var(--accent)"><b>${esc(x.next.label)}</b><span>${n ? esc(n[1]) : "Everything's collected."}</span></div>`;
    }
    if (x.x != null) return `<div class="x ${x.style || ""}">${esc(x.x)}</div>`;
    return "";
  }

  function wireDetail(g, r) {
    const detail = app.querySelector(".detail");
    detail.querySelectorAll("[data-check]").forEach(el => el.onclick = () => {
      const before = isComplete(g);
      progress.toggle(g.id, el.dataset.check);
      refresh(g, r);
      if (!before && isComplete(g)) celebrate(g);
    });
    const reset = detail.querySelector("[data-reset]");
    if (reset) reset.onclick = () => {
      const btn = reset.querySelector("button");
      if (reset.dataset.armed) { progress.reset(g.id); refresh(g, r); }
      else { reset.dataset.armed = "1"; btn.textContent = "Tap again to clear every checkmark"; }
    };
    detail.querySelectorAll("[data-mapzoom]").forEach(b => b.onclick = ev => {
      ev.stopPropagation();
      mapFit = !mapFit;
      refresh(g, r);
    });
    detail.querySelectorAll(".mapwrap:not(.fit)").forEach(centerMap);
  }

  /** Re-render after a checkmark changes, keeping both panes' scroll positions. */
  function refresh(g, r) {
    const list = app.querySelector(".list"), detail = app.querySelector(".detail");
    const ls = list.scrollTop, ds = detail.scrollTop;
    const tab = g.tabs[r.tab] || g.tabs[0];
    const key = app.querySelector(".row.sel")?.dataset.row ?? r.key;
    const jump = list.querySelector(".jump");
    list.innerHTML = (jump ? jump.outerHTML : "") + listHtml(g, tab, key);
    const page = tab.pages[key];
    if (page) detail.innerHTML = `<div class="backrow"><button data-back>‹ ${esc(tab.title)}</button></div>` + pageHtml(g, page);
    list.scrollTop = ls; detail.scrollTop = ds;
    const tabIdx = g.tabs.indexOf(tab);
    list.querySelectorAll("[data-row]").forEach(b => b.onclick = () => go(gameHash(r.game, tabIdx, b.dataset.row)));
    app.querySelector("[data-back]")?.addEventListener("click", () => go(gameHash(r.game, tabIdx)));
    const j = list.querySelector("[data-jump]");
    if (j) j.onchange = () => {
      const el = list.querySelectorAll(".group")[+j.value];
      if (el) list.scrollTop = el.offsetTop - list.querySelector(".jump").offsetHeight;
      j.value = "";
    };
    wireDetail(g, r);
    detail.scrollTop = ds;
  }

  const isComplete = g => g.checklist.length > 0 && progress.count(g.id, g.checklist.map(c => c[0])) === g.checklist.length;
  function celebrate(g) {
    const t = document.createElement("div");
    t.className = "toast";
    t.innerHTML = `<b>${esc(g.celebrate[0])}</b>${esc(g.celebrate[1])}`;
    document.body.appendChild(t);
    setTimeout(() => t.remove(), 6000);
    t.onclick = () => t.remove();
  }

  // ---------------------------------------------------------------- Map (Metroid: Zero Mission)

  let mapFit = false;
  const CELL = 26;
  const ROOM_FILL = { NORMAL: "#1E3550", HIDDEN: "#1B3A24", HEATED: "#4A3A12" };
  const DOOR_COLOR = { NORMAL: "#4FC3F7", MISSILE: "#D8342C", SUPER: "#3DDC6A", POWER_BOMB: "#F2C94C" };

  function mapPanelHtml(g, m) {
    const area = g.maps[m.area];
    const cells = new Map();
    area.rooms.forEach(room => room.rects.forEach(([c0, r0, c1, r1]) => {
      for (let r = r0; r <= r1; r++) for (let c = c0; c <= c1; c++) cells.set(c + "," + r, room);
    }));
    const [ic, ir] = area.items[m.item];
    const here = cells.get(ic + "," + ir);
    return `<div class="panel" style="--stripe:var(--line)"><div class="mapbar"><div class="ph" style="color:var(--accent)">${esc(area.label)} map · ${esc(here.code)}</div><button data-mapzoom>${mapFit ? "Close-up" : "Whole area"}</button></div>` +
      `<div class="mapwrap${mapFit ? " fit" : ""}">${mapSvg(g, area, cells, here, m.item)}</div></div>`;
  }

  function mapSvg(g, area, cells, here, itemId) {
    const keys = [...cells.keys()].map(k => k.split(",").map(Number));
    const minC = Math.min(...keys.map(k => k[0])) - 1, maxC = Math.max(...keys.map(k => k[0])) + 1;
    const minR = Math.min(...keys.map(k => k[1])) - 1, maxR = Math.max(...keys.map(k => k[1])) + 1;
    const s = CELL, W = (maxC - minC + 1) * s, H = (maxR - minR + 1) * s;
    const X = c => (c - minC) * s, Y = r => (r - minR) * s;
    const prog = progress.load(g.id);
    const colors = Object.fromEntries(g.checklist);
    let fill = "", walls = "", hi = "", doors = "", labels = "", items = "";
    for (const [k, room] of cells) {
      const [c, r] = k.split(",").map(Number);
      fill += `<rect x="${X(c)}" y="${Y(r)}" width="${s}" height="${s}" fill="${ROOM_FILL[room.style]}"/>`;
      if (room === here) fill += `<rect x="${X(c)}" y="${Y(r)}" width="${s}" height="${s}" fill="var(--accent)" fill-opacity=".28"/>`;
      const hidden = room.style === "HIDDEN";
      const stroke = hidden ? `stroke="#7CFC6A" stroke-opacity=".6" stroke-dasharray="3 3"` : `stroke="#E8ECF2" stroke-opacity=".85"`;
      const edge = (nc, nr, x1, y1, x2, y2) => {
        const other = cells.get(nc + "," + nr);
        if (other !== room) walls += `<line x1="${x1}" y1="${y1}" x2="${x2}" y2="${y2}" ${stroke} stroke-width="1.5"/>`;
        if (room === here && other !== here) hi += `<line x1="${x1}" y1="${y1}" x2="${x2}" y2="${y2}" stroke="var(--accent)" stroke-width="2.5"/>`;
      };
      edge(c + 1, r, X(c + 1), Y(r), X(c + 1), Y(r + 1));
      edge(c - 1, r, X(c), Y(r), X(c), Y(r + 1));
      edge(c, r + 1, X(c), Y(r + 1), X(c + 1), Y(r + 1));
      edge(c, r - 1, X(c), Y(r), X(c + 1), Y(r));
    }
    for (const [row, c, kind] of area.doors) {
      doors += `<rect x="${X(c + 1) - 2}" y="${Y(row) + s * .25}" width="4" height="${s * .5}" rx="2" fill="${DOOR_COLOR[kind]}"/>`;
    }
    const itemCells = new Set(Object.values(area.items).map(([c, r]) => c + "," + r));
    for (const room of area.rooms) {
      const own = [];
      room.rects.forEach(([c0, r0, c1, r1]) => { for (let r = r0; r <= r1; r++) for (let c = c0; c <= c1; c++) own.push([c, r]); });
      own.sort((a, b) => a[1] - b[1] || a[0] - b[0]);
      const anchor = own[0];
      if (room.save || room.map) labels += `<text x="${X(anchor[0]) + s / 2}" y="${Y(anchor[1]) + s - 5}" text-anchor="middle" font-size="13" font-weight="700" fill="#F2C94C">${room.save ? "S" : "M"}</text>`;
      if (room.exit) labels += exitSvg(room.exit, own, X, Y, s);
      if (!mapFit) {
        const spot = own.find(([c, r]) => !itemCells.has(c + "," + r) && !((room.save || room.map) && c === anchor[0] && r === anchor[1])) || anchor;
        const txt = room.code.split("-")[1];
        labels += `<rect x="${X(spot[0]) + 1.5}" y="${Y(spot[1]) + 1.5}" width="${txt.length * 6.4 + 4}" height="12" rx="2" fill="#0B0E14" fill-opacity=".85"/>` +
          `<text x="${X(spot[0]) + 3.5}" y="${Y(spot[1]) + 11}" font-size="10" font-weight="700" fill="${room === here ? "var(--accent)" : "#E8ECF2"}">${txt}</text>`;
      }
    }
    for (const [id, [c, r]] of Object.entries(area.items)) {
      const cx = X(c) + s * .6, cy = Y(r) + s * .64, rr = s * .19;
      const color = colors[id] || "#ccc";
      const op = prog[id] ? .3 : 1;
      const kind = id.startsWith("u_") ? "u" : id.split("_")[1][0];
      if (kind === "u") items += `<path d="M${cx} ${cy - rr * 1.25}L${cx + rr * 1.25} ${cy}L${cx} ${cy + rr * 1.25}L${cx - rr * 1.25} ${cy}Z" fill="${color}" opacity="${op}"/>`;
      else if (kind === "e") items += `<rect x="${cx - rr}" y="${cy - rr}" width="${rr * 2}" height="${rr * 2}" rx="${rr / 3}" fill="${color}" opacity="${op}"/>`;
      else items += `<circle cx="${cx}" cy="${cy}" r="${rr}" fill="${color}" opacity="${op}"/>`;
      if (id === itemId) items += `<circle cx="${cx}" cy="${cy}" r="${rr * 2}" fill="none" stroke="var(--accent)" stroke-width="2"/>`;
    }
    const focusX = X(ir(here, 0)) + s / 2, focusY = Y(ir(here, 1)) + s / 2;
    const size = mapFit ? `width="100%" viewBox="0 0 ${W} ${H}"` : `width="${W}" height="${H}"`;
    return `<svg xmlns="http://www.w3.org/2000/svg" ${size} data-fx="${focusX}" data-fy="${focusY}" font-family="system-ui, sans-serif">${fill}${walls}${hi}${doors}${items}${labels}</svg>`;
  }

  /** Average column (axis 0) or row (axis 1) of a room's cells. */
  function ir(room, axis) {
    let sum = 0, n = 0;
    room.rects.forEach(([c0, r0, c1, r1]) => { for (let r = r0; r <= r1; r++) for (let c = c0; c <= c1; c++) { sum += axis ? r : c; n++; } });
    return sum / n;
  }

  function exitSvg([to, dir], own, X, Y, s) {
    const pick = {
      UP: (a, b) => a[1] - b[1] || a[0] - b[0], DOWN: (a, b) => b[1] - a[1] || a[0] - b[0],
      LEFT: (a, b) => a[0] - b[0] || a[1] - b[1], RIGHT: (a, b) => b[0] - a[0] || a[1] - b[1],
    }[dir];
    const [c, r] = [...own].sort(pick)[0];
    const cx = X(c) + s / 2, cy = Y(r) + s / 2, d = s * .95, a = s * .22;
    const [tx, ty, bx, by] = { UP: [cx, cy - d, 0, a * 1.6], DOWN: [cx, cy + d, 0, -a * 1.6], LEFT: [cx - d, cy, a * 1.6, 0], RIGHT: [cx + d, cy, -a * 1.6, 0] }[dir];
    const sx = by ? a : 0, sy = bx ? a : 0;
    const label = { UP: [tx + a * 1.4, ty + 4, "start"], DOWN: [tx + a * 1.4, ty, "start"], LEFT: [tx, ty + a * 1.2 + 10, "middle"], RIGHT: [tx, ty + a * 1.2 + 10, "middle"] }[dir];
    return `<path d="M${tx} ${ty}L${tx + bx + sx} ${ty + by + sy}L${tx + bx - sx} ${ty + by - sy}Z" fill="var(--accent)"/>` +
      `<text x="${label[0]}" y="${label[1]}" text-anchor="${label[2]}" font-size="9" fill="var(--muted)">${esc(to)}</text>`;
  }

  /** Scroll a close-up map so the highlighted room is in the middle. */
  function centerMap(wrap) {
    const svg = wrap.querySelector("svg");
    requestAnimationFrame(() => {
      wrap.scrollLeft = +svg.dataset.fx - wrap.clientWidth / 2;
      wrap.scrollTop = +svg.dataset.fy - wrap.clientHeight / 2;
    });
  }

  // ---------------------------------------------------------------- Start

  if ("serviceWorker" in navigator && location.protocol === "https:") navigator.serviceWorker.register("sw.js");
  render();
})();
