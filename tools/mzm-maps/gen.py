"""Generates app/.../games/mzm/MzmMapData.kt from the area files in areas/.

Run from anywhere:  python tools/mzm-maps/gen.py
Room codes are numbered per area in the order the 100% route (MzmRoute.kt) reaches each room,
starting from the room where you first enter the area (the entry cells below).
"""
import re, collections
from pathlib import Path
HERE = Path(__file__).resolve().parent
MZM = HERE.parents[1] / 'app/src/main/java/com/retroreadme/games/mzm'
route=re.findall(r'"([a-z]{1,2}_[a-z]+\d*)"', open(MZM/'MzmRoute.kt',encoding='utf-8').read())
AREAS=[("BRINSTAR","BR","brinstar",(0,13)),("KRAID","KR","kraid",(-22,15)),("NORFAIR","NO","norfair",(28,16)),
       ("RIDLEY","RI","ridley",(2,20)),("TOURIAN","TO","tourian",(-13,-1)),("CRATERIA","CR","crateria",(-6,-15)),
       ("CHOZODIA","CZ","chozodia",(35,-10))]
def rects(cells):
    runs=[]
    for r in sorted({r for c,r in cells}):
        cs=sorted(c for c,rr in cells if rr==r); s=cs[0]; p=s
        for c in cs[1:]+[None]:
            if c is not None and c==p+1: p=c; continue
            runs.append([s,r,p,r]);
            if c is not None: s=p=c
    out=[]
    for a in runs:
        for b in out:
            if b[0]==a[0] and b[2]==a[2] and b[3]+1==a[1]: b[3]=a[3]; break
        else: out.append(a)
    return out
def number(rooms,doors,items,entry):
    own={c:i for i,r in enumerate(rooms) for c in r[0]}
    adj=collections.defaultdict(set)
    for (c,r),i in own.items():
        for n in ((c+1,r),(c,r+1)):
            j=own.get(n)
            if j is not None and j!=i: adj[i].add(j); adj[j].add(i)
    key=lambda i:(min(r for c,r in rooms[i][0]),min(c for c,r in rooms[i][0]))
    order=[own[entry]]; seen={own[entry]}
    def path_to(t):
        prev={s:None for s in seen}; q=collections.deque(sorted(seen,key=key))
        while q:
            u=q.popleft()
            if u==t: break
            for v in sorted(adj[u],key=key):
                if v not in prev: prev[v]=u; q.append(v)
        p=[]; u=t
        while u is not None and u not in seen: p.append(u); u=prev.get(u)
        return p[::-1]
    targets=[own[items[i]] for i in route if i in items]
    for t in targets:
        for u in path_to(t):
            if u not in seen: seen.add(u); order.append(u)
    q=collections.deque(order[:])
    while q:
        u=q.popleft()
        for v in sorted(adj[u],key=key):
            if v not in seen: seen.add(v); order.append(v); q.append(v)
    for i in sorted(range(len(rooms)),key=key):
        if i not in seen: order.append(i)
    return {i:n+1 for n,i in enumerate(order)}, own
STY={0:"NORMAL",1:"HIDDEN",2:"HEATED"}
DK={'n':"NORMAL",'r':"MISSILE",'g':"SUPER",'y':"POWER_BOMB"}
out=["package com.retroreadme.games.mzm","",
"// Room layouts transcribed from the in-game map grid (one cell = one map square).",
"// Rooms are numbered in the order the 100% route reaches them. Never renumber a released",
"// room code without updating the item steps that mention it.","",
"import com.retroreadme.games.mzm.DoorKind.MISSILE","import com.retroreadme.games.mzm.DoorKind.POWER_BOMB","import com.retroreadme.games.mzm.DoorKind.SUPER",
"import com.retroreadme.games.mzm.ExitDir.DOWN","import com.retroreadme.games.mzm.ExitDir.LEFT","import com.retroreadme.games.mzm.ExitDir.RIGHT","import com.retroreadme.games.mzm.ExitDir.UP",
"import com.retroreadme.games.mzm.RoomStyle.HEATED","import com.retroreadme.games.mzm.RoomStyle.HIDDEN","",
"object MzmMapData {",""]
summary=[]
for enum,pre,mod,entry in AREAS:
    ns={}; exec(open(HERE/'areas'/(mod+'.py'),encoding='utf-8').read(),ns)
    rooms,doors,items=ns['rooms'],ns['doors'],ns['items']
    code,own=number(rooms,doors,items,entry)
    out.append(f"    val {mod} = AreaMap(")
    out.append(f"        Area.{enum}, \"{pre}\",")
    out.append("        rooms = listOf(")
    for i in sorted(range(len(rooms)),key=lambda i:code[i]):
        cells,sty,note=rooms[i]
        args=[f'"{pre}-{code[i]:02d}"']
        if sty: args.append(STY[sty])
        args.append(", ".join(f"r({a},{b},{c},{d})" for a,b,c,d in rects(cells)).join(["listOf(",")"]))
        for part in note.split(';'):
            if part=="save": args.append("save = true")
            elif part=="map": args.append("map = true")
            elif part.startswith("elev"):
                _,dest,d=part.split(":"); args.append(f'exit = Exit("{dest}", {d.upper()})')
        out.append(f"            room({', '.join(args)}),")
    out.append("        ),")
    out.append("        doors = listOf(")
    ds=[f"d({r},{c}{'' if k=='n' else ', '+DK[k]})" for r,c,k in doors]
    for j in range(0,len(ds),8): out.append("            "+", ".join(ds[j:j+8])+",")
    out.append("        ),")
    out.append("        items = mapOf(")
    for iid,(c,r) in items.items(): out.append(f'            "{iid}" to cell({c}, {r}),')
    out.append("        ),")
    out.append("    )")
    out.append("")
    summary.append((pre,len(rooms),len(items),{iid:f"{pre}-{code[own[cr]]:02d}" for iid,cr in items.items()}))
out.append("    val all: List<AreaMap> = listOf("+", ".join(a[2] for a in AREAS)+")")
out.append("}")
open(MZM/'MzmMapData.kt','w',encoding='utf-8').write("\n".join(out)+"\n")
tot=0
for pre,n,ni,m in summary:
    tot+=ni; print(pre,n,"rooms",ni,"items:",", ".join(f"{k}={v}" for k,v in list(m.items())[:6]),"...")
print("total items",tot)
