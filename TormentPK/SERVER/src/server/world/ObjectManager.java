package server.world;

import java.util.ArrayList;

import server.model.objects.Object;
import server.util.Misc;
import server.model.players.Client;
import server.Server;

/**
 * @author Sanity
 */

public class ObjectManager {

	public ArrayList<Object> objects = new ArrayList<Object>();
	private ArrayList<Object> toRemove = new ArrayList<Object>();
	public void process() {
		for (Object o : objects) {
			if (o.tick > 0)
				o.tick--;
			else {
				updateObject(o);
				toRemove.add(o);
			}		
		}
		for (Object o : toRemove) {
			if (isObelisk(o.newId)) {
				int index = getObeliskIndex(o.newId);
				if (activated[index]) {
					activated[index] = false;
					teleportObelisk(index);
				}
			}
			objects.remove(o);	
		}
		toRemove.clear();
	}
	
	public void removeObject(int x, int y) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(-1, x, y, 0, 10);			
			}	
		}	
	}
	
	public void updateObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(o.newId, o.objectX, o.objectY, o.face, o.type);			
			}	
		}	
	}
	
	public void placeObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				if (c.distanceToPoint(o.objectX, o.objectY) <= 60)
					c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
			}	
		}
	}
	
	public Object getObject(int x, int y, int height) {
		for (Object o : objects) {
			if (o.objectX == x && o.objectY == y && o.height == height)
				return o;
		}	
		return null;
	}
	
	public void loadObjects(Client c) {
		if (c == null)
			return;
		for (Object o : objects) {
			if (loadForPlayer(o,c))
				c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
		}
		loadCustomSpawns(c);
		if (c.distanceToPoint(2813, 3463) <= 60) {
			c.getFarming().updateHerbPatch();
		}
	}
	
	private int[][] customObjects = {{}};
	public void loadCustomSpawns(Client c) {
		c.getPA().checkObjectSpawn(1814, 3086, 3483, 3, 5);

		c.getPA().checkObjectSpawn(10545, 3089, 3495, 2, 10);

		c.getPA().checkObjectSpawn(8767, 3091, 3500, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3094, 3500, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3095, 3500, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3096, 3500, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3097, 3500, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3098, 3500, 0, 10);

		c.getPA().checkObjectSpawn(8767, 3090, 3513, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3089, 3513, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3088, 3513, 0, 10);

		c.getPA().checkObjectSpawn(8767, 3086, 3513, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3085, 3513, 0, 10);

		c.getPA().checkObjectSpawn(8767, 3084, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3083, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3082, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3081, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3080, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3079, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3078, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3077, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3076, 3514, 0, 10);

		c.getPA().checkObjectSpawn(8767, 3075, 3513, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3075, 3512, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3075, 3511, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3075, 3510, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3075, 3509, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3075, 3508, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3075, 3507, 0, 10);

		c.getPA().checkObjectSpawn(8767, 3076, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3077, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3078, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3079, 3506, 0, 10);

		c.getPA().checkObjectSpawn(8767, 3081, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3082, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3083, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3084, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3085, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3086, 3506, 0, 10);

		c.getPA().checkObjectSpawn(8767, 3088, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3089, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3090, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3091, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3092, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3093, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3094, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3095, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3096, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3097, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3098, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3099, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3100, 3506, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3101, 3506, 0, 10);

		c.getPA().checkObjectSpawn(8767, 3101, 3507, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3101, 3508, 0, 10);

		c.getPA().checkObjectSpawn(8767, 3101, 3511, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3101, 3512, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3101, 3513, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3101, 3514, 0, 10);

		c.getPA().checkObjectSpawn(8767, 3090, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3091, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3092, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3093, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3094, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3095, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3096, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3097, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3098, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3099, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3100, 3514, 0, 10);
		c.getPA().checkObjectSpawn(8767, 3101, 3514, 0, 10);

		c.getPA().checkObjectSpawn(2561, 3099, 3498, 0, 10);
		c.getPA().checkObjectSpawn(2560, 3099, 3495, 0, 10);
		c.getPA().checkObjectSpawn(2564, 3099, 3492, 0, 10);
		c.getPA().checkObjectSpawn(2562, 3099, 3489, 0, 10);
		c.getPA().checkObjectSpawn(1755, 3055, 9774, 0, 0);
		c.getPA().checkObjectSpawn(1596, 3008, 3850, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3008, 3849, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10307, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10308, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10311, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10312, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10341, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10342, 1, 0);
		c.getPA().checkObjectSpawn(2213, 3047, 9779, 1, 10);
		c.getPA().checkObjectSpawn(2213, 3080, 9502, 1, 10);
		c.getPA().checkObjectSpawn(2475, 3233, 9312, 1, 10);
		c.getPA().checkObjectSpawn(4551, 2522, 3595, 1, 10);
		c.getPA().checkObjectSpawn(409, 3090, 3499, 1, 10);
		c.getPA().checkObjectSpawn(409, 2539, 4718, 1, 10);
		if (c.heightLevel == 0)
			c.getPA().checkObjectSpawn(2492, 2911, 3614, 1, 10);
		else
			c.getPA().checkObjectSpawn(-1, 2911, 3614, 1, 10);
	}
	
	public final int IN_USE_ID = 14825;
	public boolean isObelisk(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return true;
		}
		return false;
	}
	public int[] obeliskIds = {14829,14830,14827,14828,14826,14831};
	public int[][] obeliskCoords = {{3154,3618},{3225,3665},{3033,3730},{3104,3792},{2978,3864},{3305,3914}};
	public boolean[] activated = {false,false,false,false,false,false};
	
	public void startObelisk(int obeliskId) {
		int index = getObeliskIndex(obeliskId);
		if (index >= 0) {
			if (!activated[index]) {
				activated[index] = true;
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
			}
		}	
	}
	
	public int getObeliskIndex(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return j;
		}
		return -1;
	}
	
	public void teleportObelisk(int port) {
		int random = Misc.random(5);
		while (random == port) {
			random = Misc.random(5);
		}
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				int xOffset = c.absX - obeliskCoords[port][0];
				int yOffset = c.absY - obeliskCoords[port][1];
				if (c.goodDistance(c.getX(), c.getY(), obeliskCoords[port][0] + 2, obeliskCoords[port][1] + 2, 1)) {
					c.getPA().startTeleport2(obeliskCoords[random][0] + xOffset, obeliskCoords[random][1] + yOffset, 0);
				}
			}		
		}
	}
	
	public boolean loadForPlayer(Object o, Client c) {
		if (o == null || c == null)
			return false;
		return c.distanceToPoint(o.objectX, o.objectY) <= 60 && c.heightLevel == o.height;
	}
	
	public void addObject(Object o) {
		if (getObject(o.objectX, o.objectY, o.height) == null) {
			objects.add(o);
			placeObject(o);
		}	
	}




}