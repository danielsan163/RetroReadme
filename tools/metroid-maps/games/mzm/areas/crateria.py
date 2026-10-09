# Map data for one Metroid: Zero Mission area. See ../README.md for the format.
# rooms: (cells, style, note). style N normal, S hidden (not on the Map Station map), H heated.
#   note: "save", "map", "elev:<Area>:<up|down|left|right>", "boss:<Name>" (joined with ";").
# doors: (row, col, kind) = door on the wall between (col, row) and (col + 1, row).
#   kind: n normal, r Missile, g Super Missile, y Power Bomb.
# items: checklist id -> (col, row) of its icon.
def R(c0,c1,r0,r1): return [(c,r) for c in range(c0,c1+1) for r in range(r0,r1+1)]
N,S,H=0,1,2
rooms=[
 (R(-12,-10,-21,-20)+R(-12,-9,-19,-16)+R(-13,-13,-17,-17)+R(-13,-9,-15,-15),N,"ship"),(R(-13,-13,-16,-16),S,""),
 (R(-15,-15,-15,-12)+R(-14,-14,-15,-15),N,""),(R(-14,-13,-12,-12),N,""),(R(-12,-12,-12,-12),N,"elev:Brinstar:down"),
 (R(-16,-16,-15,-15),S,""),(R(-17,-17,-15,-10),S,""),(R(-18,-18,-10,-10),S,"elev:Tourian:down"),
 (R(-8,-7,-19,-18),S,""),(R(-6,-5,-19,-16),S,""),(R(-7,-7,-16,-16),S,""),(R(-8,-7,-15,-15),S,""),
 (R(-6,-5,-15,-15),N,"elev:Norfair:down"),
 (R(-4,1,-20,-18)+R(-4,-3,-17,-17)+R(0,1,-17,-17),S,""),(R(-2,-1,-17,-17),S,""),
 (R(-4,0,-16,-16)+R(-4,1,-15,-14),S,""),(R(2,2,-19,-19),S,"elev:Chozodia:right"),(R(2,2,-15,-15),S,"elev:Chozodia:right"),
]
doors=[(-10,-18,'g'),(-15,-14,'n'),(-18,-9,'n'),(-15,-9,'n'),(-12,-15,'n'),(-12,-13,'n'),(-19,-7,'n'),(-15,-7,'n'),
 (-19,-5,'n'),(-15,-5,'n'),(-17,-3,'n'),(-19,1,'y'),(-15,1,'y')]
items={"u_plasma":(-1,-17),"u_grip":(-7,-16),"cr_m1":(-4,-14),"cr_m2":(-2,-17),"cr_m3":(0,-17),"cr_s1":(1,-20),"cr_p1":(-12,-18)}
