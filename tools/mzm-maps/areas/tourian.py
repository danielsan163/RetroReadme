# Map data for one Metroid: Zero Mission area. See ../README.md for the format.
# rooms: (cells, style, note). style N normal, S hidden (not on the Map Station map), H heated.
#   note: "save", "map", "elev:<Area>:<up|down|left|right>", "boss:<Name>" (joined with ";").
# doors: (row, col, kind) = door on the wall between (col, row) and (col + 1, row).
#   kind: n normal, r Missile, g Super Missile, y Power Bomb.
# items: checklist id -> (col, row) of its icon.
def R(c0,c1,r0,r1): return [(c,r) for c in range(c0,c1+1) for r in range(r0,r1+1)]
N,S,H=0,1,2
rooms=[(R(-15,-15,-2,7),N,"elev:Crateria:up"),(R(-16,-16,7,7),S,""),(R(-13,-13,-1,2),N,"elev:Brinstar:up"),
 (R(-12,-12,-1,-1),N,"save"),(R(-11,-11,-1,-1),N,""),(R(-10,-10,-1,0),N,""),(R(-12,-11,0,0),N,""),(R(-12,-8,2,2),N,""),
 (R(-7,-6,2,3),N,""),(R(-5,-5,2,3),N,""),(R(-4,-4,2,2),N,"save"),(R(-4,-4,3,7),N,""),(R(-14,-12,6,7),N,"boss:Mother Brain"),
 (R(-14,-14,8,8),S,""),(R(-11,-8,7,7),N,""),(R(-7,-7,7,7),N,"save"),(R(-6,-5,7,9),N,"")]
doors=[(-1,-13,'n'),(-1,-12,'n'),(-1,-11,'n'),(0,-13,'n'),(0,-11,'n'),(2,-13,'n'),(2,-8,'n'),(2,-6,'n'),(2,-5,'n'),(3,-5,'n'),
 (7,-15,'n'),(7,-12,'r'),(7,-8,'n'),(7,-7,'n'),(7,-5,'n')]
items={"to_m1":(-16,7),"to_p1":(-14,8)}
