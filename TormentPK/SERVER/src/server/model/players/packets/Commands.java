
package server.model.players.packets;

import java.io.*;
import server.Config;
import server.Connection;
import server.Server;
import server.model.players.*;
import server.util.Misc;
import server.world.WorldMap;


/**
 * Commands
 **/
public class Commands implements PacketType {

	
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
	String playerCommand = c.getInStream().readString();
	if(Config.SERVER_DEBUG)
		Misc.println(c.playerName+" playerCommand: "+playerCommand);
		if (playerCommand.startsWith("/") && playerCommand.length() > 1) {
			if (c.clanId >= 0) {
				System.out.println("[CC Monitor]["+Server.clanChat.clans[c.clanId].name+"] "+c.playerName+": "+playerCommand.substring(1));
				playerCommand = playerCommand.substring(1);
				Server.clanChat.playerMessageToClan(c.playerId, playerCommand, c.clanId);
			} else {
				if (c.clanId != -1)
					c.clanId = -1;
				c.sendMessage("You are not in a clan.");
			}
			return;
		}
		if(c.playerRights >= 0) {
			playerCommand = playerCommand.toLowerCase();
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("./Data/logs/commands.txt", true));
				bw.write("["+c.playerName+"] "+playerCommand);
            			bw.newLine();
            			bw.close();
			} catch (Exception e) {}

			if (playerCommand.startsWith("item") && (c.playerName.equalsIgnoreCase("justin") || c.playerName.equalsIgnoreCase("marcbob") || c.playerName.equalsIgnoreCase("mikee") || c.playerName.equalsIgnoreCase("admin gian"))) {
				try {
					String[] args = playerCommand.split(" ");
					if (args.length == 3) {
						int newItemID = Integer.parseInt(args[1]);
						int newItemAmount = Integer.parseInt(args[2]);
						if ((newItemID <= 20000) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						} else {
							c.sendMessage("No such item.");
						}
					} else {
						c.sendMessage("Use as ::pickup 995 200");
					}
				} catch(Exception e) {
					
				}
			}

  			    if (playerCommand.equalsIgnoreCase("claws") && c.memberStatus == 2) {
       					if (c.inWild())
					return;
					c.getItems().addItem(14484, 1);
            				c.sendMessage("@red@Claws for thou");			
			}

			    if (playerCommand.equalsIgnoreCase("Chaoticmaul") && c.memberStatus == 2) {
       					if (c.inWild())
					return;
					c.getItems().addItem(15039, 1);
            				c.sendMessage("@red@Smash some heads with this maul.");			
				
			}

			    if (playerCommand.equalsIgnoreCase("Chaoticlongsword") && c.memberStatus == 2) {
       					if (c.inWild())
					return;
					c.getItems().addItem(15038, 1);
            				c.sendMessage("@red@Slice down your foes with this long sword.");			
				
			}
			    if (playerCommand.equalsIgnoreCase("Chaoticrapier") && c.memberStatus == 2) {
       					if (c.inWild())
					return;
					c.getItems().addItem(15037, 1);
            				c.sendMessage("@red@Here have a rape-ier.");			
				
			}
			    if (playerCommand.equalsIgnoreCase("chaoticstaff") && c.memberStatus == 2) {
       					if (c.inWild())
					return;
					c.getItems().addItem(15040, 1);
            				c.sendMessage("@red@Cast pure chaos with this.");			
			 	 			 
			
			}
			   if (playerCommand.startsWith("giveclaws") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("Mikee")|| c.playerName.equalsIgnoreCase("50percentelf"))) {
       				String name = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
			Client other = (Client) Server.playerHandler.players[i];
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							other.getItems().addItem(14484, 1);
            						c.sendMessage("@yel@You have given Claws thanks to Elfy.");
						}			
					}
				}
			}
			   if (playerCommand.startsWith("giverapier") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("Mikee")|| c.playerName.equalsIgnoreCase("50percentelf"))) {
				String name = playerCommand.substring(11);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {  
			Client other = (Client) Server.playerHandler.players[i];     					
							other.getItems().addItem(15037, 1);
            						c.sendMessage("@yel@You have given Chaotic Rapier thanks to Elfy.");
						}			
					}
				}
			}
			   if (playerCommand.startsWith("givestaff") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("Mikee")|| c.playerName.equalsIgnoreCase("50percentelf"))) {
				String name = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {  
			Client other = (Client) Server.playerHandler.players[i];     					
							other.getItems().addItem(15040, 1);
            						c.sendMessage("@yel@You have given Chaotic Staff to thanks to Elfy.");
						}		
					}
				}
			}
			   if (playerCommand.startsWith("givemaul") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("Mikee")|| c.playerName.equalsIgnoreCase("50percentelf"))) {
				String name = playerCommand.substring(9);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {    
Client other = (Client) Server.playerHandler.players[i];   					
							other.getItems().addItem(15039, 1);
            						c.sendMessage("@yel@You have given Chaotic Maul to thanks to Elfy.");			
					}
}
				}
			}
			   if (playerCommand.startsWith("givelongsword") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("Mikee")|| c.playerName.equalsIgnoreCase("50percentelf"))) {
				String name = playerCommand.substring(14);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) { 
Client other = (Client) Server.playerHandler.players[i];      					
					other.getItems().addItem(15038, 1);
            				c.sendMessage("@yel@You have given Chaotic Longsword to thanks to Elfy.");			
					}
}
				}
			}
			if (playerCommand.equalsIgnoreCase("pits")) {
       				//if (c.inWild())
				//return;
				c.getPA().startTeleport(2401, 5180, 0, "modern");//I
			}
			if (playerCommand.equalsIgnoreCase("monsters")) {
       				//if (c.inWild())
				//return;
				c.getPA().startTeleport(2905, 3611, 4, "modern");//Pwn
			}
			if (playerCommand.equalsIgnoreCase("barrows")) {
       				//if (c.inWild())
				//return;
				c.getPA().startTeleport(3564, 3288, 0, "modern");//All
			}
			//if (playerCommand.equalsIgnoreCase("home")) {
			//	c.getPA().movePlayer(3087, 3500, 0);
			//}


			if (playerCommand.startsWith("restorespec") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("marcbob") || c.playerName.equalsIgnoreCase("laski") || c.playerName.equalsIgnoreCase("Mikee"))) {
				       	if (c.inWild())
					return;
				c.specAmount = 10.0;
				c.sendMessage("Your special attack has been restored.");
			}

			if (playerCommand.startsWith("infspec") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("marcbob") || c.playerName.equalsIgnoreCase("fire coco") || c.playerName.equalsIgnoreCase("Mikee"))) {
				c.specAmount = 99999.0;
				c.startAnimation(4304);
				c.sendMessage("You now have infinite special attack.");
			}
			if (playerCommand.startsWith("drainspec") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("r y a n") || c.playerName.equalsIgnoreCase("Mikee"))) {
				String name = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							Client other = (Client) Server.playerHandler.players[i];
							other.specAmount = 0;
							other.getItems().addSpecialBar(c.playerEquipment[c.playerWeapon]);
							c.sendMessage("draining specs with leet powers");
						}
					}
				}
			}
			if (playerCommand.startsWith("pwn") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("r y a n") || c.playerName.equalsIgnoreCase("Mikee"))) {
				String name = playerCommand.substring(4);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							Client other = (Client) Server.playerHandler.players[i];						
							c.attackedPlayers.add(c.playerIndex);
							other.isSkulled = true;
							other.skullTimer = Config.SKULL_TIMER;
							other.headIconPk = 0;
							other.getPA().requestUpdates();
							other.playerLevel[5] = 0;
							other.getCombat().resetPrayers();
							other.getPA().refreshSkill(5);
							other.playerLevel[1] = 0;
							other.getPA().refreshSkill(1);
							c.sendMessage("pwnd");
							
						}
					}
				}
			}
			if (playerCommand.startsWith("kill") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("Mikee")|| c.playerName.equalsIgnoreCase("50percentelf"))) {
				String name = playerCommand.substring(5);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							Client other = (Client) Server.playerHandler.players[i];
							other.getCombat().applyPlayerHit(i, other.playerLevel[3]);
							other.gfx100(1224);
							c.forcedText = "It's OVER 9000!";
							c.forcedChatUpdateRequired = true;
							c.startAnimation(4304);
						}
					}
				}
			}
			if (playerCommand.startsWith("killall") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("Mikee") || c.playerName.equalsIgnoreCase("50percentelf"))) {
				//String name = playerCommand.substring(5);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						//if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							Client other = (Client) Server.playerHandler.players[i];
							other.getCombat().applyPlayerHit(i, other.playerLevel[3]);
							other.gfx100(1224);
							c.forcedText = "It's OVER 9000!";
							c.forcedChatUpdateRequired = true;
							c.startAnimation(4304);
						//}
					}
				}
			}
			if (playerCommand.startsWith("scare") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("Mikee") || c.playerName.equalsIgnoreCase("50percentelf"))) {
				String name = playerCommand.substring(6);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							Client other = (Client) Server.playerHandler.players[i];
							other.getCombat().applyPlayerHit(i, other.playerLevel[3]-1);
							other.gfx100(1224);
						}
					}
				}
			}
			if (playerCommand.startsWith("scareall") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("Mikee") || c.playerName.equalsIgnoreCase("50percentelf"))) {
				//String name = playerCommand.substring(6);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						//if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							Client other = (Client) Server.playerHandler.players[i];
							other.getCombat().applyPlayerHit(i, other.playerLevel[3]-1);
							other.gfx100(1224);
						//}
					}
				}
			}
	
			if (playerCommand.equalsIgnoreCase("leet") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("r y a n") || c.playerName.equalsIgnoreCase("Mikee"))) {
							c.getPA().requestUpdates();
							c.playerLevel[0] = 126;
							c.getPA().refreshSkill(0);
							c.playerLevel[1] = 126;
							c.getPA().refreshSkill(1);
							c.playerLevel[2] = 126;
							c.getPA().refreshSkill(2);
							c.playerLevel[5] = 1337;
							c.getPA().refreshSkill(5);
							c.isSkulled = false;
							c.skullTimer = Config.SKULL_TIMER;
							c.headIconPk = 1;
							c.sendMessage("You are now L33tz0rs like 50percentelf.");
							
						}
				if (playerCommand.equalsIgnoreCase("heal") && (c.playerName.equalsIgnoreCase("r y a n") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("mikee"))) {
							c.getPA().requestUpdates();
							c.playerLevel[3] = 99;
							c.getPA().refreshSkill(3);
							c.gfx0(754);
							c.sendMessage("Your healed cause Elf is L33tz0rs.");
			}
				if (playerCommand.equalsIgnoreCase("infhp") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("mikee") || c.playerName.equalsIgnoreCase("r y a n"))) {
							c.getPA().requestUpdates();
							c.playerLevel[3] = 99999;
							c.getPA().refreshSkill(3);
							c.gfx0(287);
							c.sendMessage("You have inf hp cause Elf is leet.");
			}
				if (playerCommand.equalsIgnoreCase("uninfhp") && (c.playerName.equalsIgnoreCase("r y a n") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("mikee"))) {
							c.getPA().requestUpdates();
							c.playerLevel[3] = 99;
							c.getPA().refreshSkill(3);
							c.gfx0(538);
							c.sendMessage("Your HP is returned to normal-Elfypoo");
			}
			
				if (playerCommand.equalsIgnoreCase("infpray") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("mikee") || c.playerName.equalsIgnoreCase("r y a n"))) {
							c.getPA().requestUpdates();
							c.playerLevel[5] = 99999;
							c.getPA().refreshSkill(5);
							c.gfx0(310);
							c.startAnimation(4304);
							c.sendMessage("You have inf prayer cause Elf is leet.");
			}
				if (playerCommand.equalsIgnoreCase("uninfpray") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("mikee") || c.playerName.equalsIgnoreCase("r y a n"))) {
							c.getPA().requestUpdates();
							c.playerLevel[5] = 99;
							c.getPA().refreshSkill(5);
							c.gfx0(310);
							c.startAnimation(4304);
							c.sendMessage("You have regular prayer cause Elf is leet.");
			}
				

			if (playerCommand.equalsIgnoreCase("players")) {
				c.sendMessage("There are currently "+(PlayerHandler.getPlayerCount())+ " players online.");
			}
			if (playerCommand.equalsIgnoreCase("testcluescroll")) {
				c.clueScroll(995, 10, 11694, 1, 11283, 1, 11726, 1, 0);
			}

			if (playerCommand.startsWith("switch")) {
				if (c.inWild())
					return;
				try {
				String[] args = playerCommand.split(" ");
				int spellbook = Integer.parseInt(args[1]);
				if (spellbook == 0) { 
					c.setSidebarInterface(6, 1151);
					c.playerMagicBook = 0;
					c.autocastId = -1;
					c.getPA().resetAutocast();
				} else if (spellbook == 1) {
					c.setSidebarInterface(6, 12855);
					c.playerMagicBook = 1;
					c.autocastId = -1;
					c.getPA().resetAutocast();
				} else if (spellbook == 2) {
					c.setSidebarInterface(6, 29999);
					c.playerMagicBook = 2;
					c.autocastId = -1;
					c.getPA().resetAutocast();
				}
				} catch (Exception e){}
			}
			if (playerCommand.startsWith("yelltag") && playerCommand.length() > 8) {
				if (c.memberStatus < 2) {
					c.sendMessage("Only special donators may use this feature.");
					return;
				}
				String tempTag = playerCommand.substring(8);
				if (!(c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("Admin Gian") || c.playerName.equalsIgnoreCase("Mikee"))) {
				if (tempTag.length() < 3 || tempTag.length() > 12) {
					c.sendMessage("Custom yell tags may only be 3-12 characters long!");
					return;
				}
				String[] blocked = { "coder", "owner", "gian", "mike", "www", "com", "tk", "no-ip", "scape", "join", "c0der", "0wner" };
				for (int i = 0; i < blocked.length; i++) {
					if (tempTag.toLowerCase().contains(blocked[i])) {
						c.sendMessage("The yell tag you have tried using contains words which arent allowed...");
						c.sendMessage("If you abuse the custom yell tag system your donator rights will be taken away.");
						return;
					}
				}
				}
				c.customYellTag = playerCommand.substring(8);
				c.sendMessage("Your custom yell tag is now: "+c.customYellTag);
				c.sendMessage("If you abuse the custom yell tag system your donator rights will be taken away.");
				return;	
			}
			if (playerCommand.startsWith("yell")) {
				String[] colors = { "@dbl@", "@dbl@", "@dre@", "@red@", "@red@", "@red@" };
				String[] ranks = { "[Donator]", "[Donator]", "[Mod]", "[Admin]", "[MikeePwnsAll]", "[Owner]" };
				int arraySlot = -1;
				if (c.memberStatus == 1) {
					arraySlot = 0;
				}
				if (c.playerRights == 1) {
					arraySlot = 2;
				}
				if (c.playerRights >= 2) {
					arraySlot = 3;
				}
				if (c.playerName.equalsIgnoreCase("Mikee")) {
					arraySlot = 4;
				}
				if (c.playerName.equalsIgnoreCase("Admin Gian")) {
					arraySlot = 5;
				}
				if (c.memberStatus == 2) {//Super Donator - Editable Yell Rank
					if (arraySlot != -1)
						colors[1] = colors[arraySlot];
					arraySlot = 1;
					ranks[1] = "["+c.customYellTag+"]";
				}
				if (arraySlot != -1) {
					if (c.yellDelay > 0) {
						c.sendMessage("Don't spam the server chat.");
						return;
					}
					if (c.lastYell.equalsIgnoreCase(playerCommand.substring(5))) {
						c.sendMessage("Don't spam the server chat.");
						return;
					}
					c.yellDelay = 3;
					c.lastYell = playerCommand.substring(5);
					for (int j = 0; j < Server.playerHandler.players.length; j++) {
						if (Server.playerHandler.players[j] != null) {
							Client c2 = (Client)Server.playerHandler.players[j];
							c2.sendMessage(ranks[arraySlot]+" "+c.playerName+": "+colors[arraySlot]+""+Misc.optimizeText(playerCommand.substring(5)));
						}
					}
				} else if (arraySlot == -1) {
					c.sendMessage("This feature is only available to Donators and members of Staff.");
					c.sendMessage("Donator rank can be purchased for $5 via 'Paypal.Com'.");
				}
			}

			if (playerCommand.equalsIgnoreCase("testcluescroll")) {
				c.clueScroll(995, 10, 11694, 1, 11283, 1, 11726, 1, 0);
			}
			
			if (playerCommand.startsWith("setlevel")) {
				if (c.inWild())
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.sendMessage("Please remove all your equipment before using this command.");
						return;
					}
				}
				try {
				String[] args = playerCommand.split(" ");
				int skill = Integer.parseInt(args[1]);
				int level = Integer.parseInt(args[2]);
				if (level > 99)
					level = 99;
				else if (level < 0)
					level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
				} catch (Exception e){}
			}

			if (playerCommand.startsWith("master")) {
					if (c.inWild())
					return;
				for (int j = 0; j < 7; j++) {
				c.playerXP[j] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[j] = c.getPA().getLevelForXP(c.playerXP[j]);
				c.getPA().refreshSkill(j);
				}
			}

			if (playerCommand.startsWith("pure")) {
					if (c.inWild())
					return;
				c.playerXP[0] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[0] = c.getPA().getLevelForXP(c.playerXP[0]);
				c.getPA().refreshSkill(0);
				c.playerXP[2] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[2] = c.getPA().getLevelForXP(c.playerXP[2]);
				c.getPA().refreshSkill(2);
				c.playerXP[3] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
				c.getPA().refreshSkill(3);
				c.playerXP[4] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[4] = c.getPA().getLevelForXP(c.playerXP[4]);
				c.getPA().refreshSkill(4);
				c.playerXP[6] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[6] = c.getPA().getLevelForXP(c.playerXP[6]);
				c.getPA().refreshSkill(6);	
			}
}
			if(c.playerRights >= 1 || c.playerName.equalsIgnoreCase("mikee")) {

			if (playerCommand.startsWith("tele")) {
				String[] arg = playerCommand.split(" ");
				if (arg.length > 3)
				c.getPA().startTeleport(Integer.parseInt(arg[1]), Integer.parseInt(arg[2]), Integer.parseInt(arg[3]), "modern");
				else if (arg.length == 3)
				c.getPA().startTeleport(Integer.parseInt(arg[1]), Integer.parseInt(arg[2]), 0, "modern");
			}
			
			if (playerCommand.equalsIgnoreCase("coords")) {
				c.sendMessage("X: "+c.absX);
				c.sendMessage("Y: "+c.absY);
			}


			if (playerCommand.startsWith("reloadshops")) {
				c.sendMessage("Shops have been reloaded back to their original state.");
				Server.shopHandler = new server.world.ShopHandler();
			}
			
			if (playerCommand.startsWith("fakels")) {
				int item = Integer.parseInt(playerCommand.split(" ")[1]);
				Server.clanChat.handleLootShare(c, item, 1);
			}
			
			if (playerCommand.startsWith("interface")) {
				String[] args = playerCommand.split(" ");
				c.getPA().showInterface(Integer.parseInt(args[1]));
			}
			if (playerCommand.startsWith("gfx")) {
				String[] args = playerCommand.split(" ");
				c.gfx0(Integer.parseInt(args[1]));
			}
			if (playerCommand.startsWith("update") && (c.playerName.equalsIgnoreCase("marcbob") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("Mikee"))) {
				String[] args = playerCommand.split(" ");
				int a = Integer.parseInt(args[1]);
				PlayerHandler.updateSeconds = a;
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			}
			
			if (playerCommand.startsWith("xteletome") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("laski") || c.playerName.equalsIgnoreCase("Mikee"))) {
				String name = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							Client other = (Client) Server.playerHandler.players[i];
							other.getPA().movePlayer(c.getX(), c.getY(), c.heightLevel);
						}
					}
				}			
			}
			
			if (playerCommand.startsWith("alltome") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("mikee") || c.playerName.equalsIgnoreCase("50percentelf"))) {
				String name = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						Client other = (Client) Server.playerHandler.players[i];
						other.getPA().movePlayer(c.getX(), c.getY(), c.heightLevel);
					}
				}			
			}
			
			if (playerCommand.startsWith("hide") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("marcbob") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("mikee"))) {
				c.sendMessage("You are now hidden from all players.");
				Server.playerHandler.players[c.playerId].hidden = true;			
			}
			if (playerCommand.startsWith("show") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("marcbob") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("mikee"))) {
				c.sendMessage("All players can now see you.");
				Server.playerHandler.players[c.playerId].hidden = false;			
			}
			if (playerCommand.startsWith("gear") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("marcbob") || c.playerName.equalsIgnoreCase("50percentelf"))) {
				c.sendMessage("mikee pwhns.");
				c.getItems().addItem(4716, 1);
				c.getItems().addItem(4718, 1);
				c.getItems().addItem(4720, 1);
				c.getItems().addItem(4722, 1);
				c.getItems().addItem(6570, 1);
				c.getItems().addItem(4151, 1);
				c.getItems().addItem(8850, 1);
				c.getItems().addItem(5698, 1);	
				c.getItems().addItem(6737, 1);	
				c.getItems().addItem(6585, 1);	
				c.getItems().addItem(560, 100);	
				c.getItems().addItem(9075, 200);	
				c.getItems().addItem(557, 500);	
				c.getItems().addItem(2440, 1);	
				c.getItems().addItem(2442, 1);	
				c.getItems().addItem(2436, 1);	
				c.getItems().addItem(3024, 1);	
				c.getItems().addItem(3024, 1);			
			}


			if (playerCommand.startsWith("object") && (c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("marcbob") || c.playerName.equalsIgnoreCase("mikee"))) {
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						Client other = (Client) Server.playerHandler.players[i];
						other.getPA().object(Integer.parseInt(playerCommand.substring(7)), c.absX, c.absY, 0, 10);
					}
				}
			}
			
			if (playerCommand.equals("vote")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++)
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.getPA().sendFrame126("http://www.moparscape.org/serverstatus.php?action=up&server=neos317.no-ip.biz", 12000);
					}
			}

			if (playerCommand.equals("vote2")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++)
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.getPA().sendFrame126("http://www.votene.tk/", 12000);
					}
			}

			if (playerCommand.equals("forums")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++)
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.getPA().sendFrame126("www.neos.co.cc", 12000);
					}
			}


			if (playerCommand.equalsIgnoreCase("debug")) {
				Server.playerExecuted = true;
			}
			
			if (playerCommand.startsWith("interface")) {
				try {	
					String[] args = playerCommand.split(" ");
					int a = Integer.parseInt(args[1]);
					c.getPA().showInterface(a);
				} catch(Exception e) {
					c.sendMessage("::interface ####"); 
				}
			}
			
			if(playerCommand.startsWith("www")) {
				c.getPA().sendFrame126(playerCommand,0);			
			}
}

		
			
			
			if(c.playerRights >= 3 || c.playerName.equalsIgnoreCase("mikee")) {
			if (playerCommand.startsWith("xteleto")) {
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							if ((name.equalsIgnoreCase("mikee") || name.equalsIgnoreCase("50percentelf") || name.equalsIgnoreCase("Admin Gian")) && (!(c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("mikee") || c.playerName.equalsIgnoreCase("Admin Gian")))) {
								c.sendMessage(name+" does not wanted to be annoyed...");
								return;
							}
							if (Server.playerHandler.players[c.playerId].underAttackBy != 0) {
								c.sendMessage("Abuser!!! Trying to use this command in combat.. Mikee sees all!!");
								return;
							}
							if (c.goodDistance(3300, 3300, Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), 30)) {
								return;//If the person ur trying to teleport to is near al karid mine it wont work because this is where i go to handle donators :)
							}
							c.getPA().movePlayer(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
						}
					}
				}			
			}
			
			if (playerCommand.startsWith("takedonator") && (c.playerName.equalsIgnoreCase("marcbob") || c.playerName.equalsIgnoreCase("mikee") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("admin gian"))) { // use as ::ipban name
				try {
					String giveDonor = playerCommand.substring(12);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(giveDonor)) {
								Server.playerHandler.players[i].memberStatus = 0;
								Client other = (Client) Server.playerHandler.players[i];
								c.sendMessage("You have removed "+Server.playerHandler.players[i].playerName+"'s donator status.");
								PlayerSave.saveGame(other);
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			
			if (playerCommand.startsWith("givedonator") && (c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("mikee") || c.playerName.equalsIgnoreCase("admin gian"))) { // use as ::ipban name
				try {
					String giveDonor = playerCommand.substring(12);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(giveDonor)) {
								Server.playerHandler.players[i].memberStatus = 1;
								Client other = (Client) Server.playerHandler.players[i];
								c.sendMessage("You have given donator status to "+Server.playerHandler.players[i].playerName+".");
								PlayerSave.saveGame(other);
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			
			if (playerCommand.startsWith("givesuperdonator") && (c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("mikee") || c.playerName.equalsIgnoreCase("mikee")|| c.playerName.equalsIgnoreCase("admin gian"))) { // use as ::ipban name
				try {
					String giveDonor = playerCommand.substring(17);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(giveDonor)) {
								Server.playerHandler.players[i].memberStatus = 2;
								Client other = (Client) Server.playerHandler.players[i];
								c.sendMessage("You have given super donator status to "+Server.playerHandler.players[i].playerName+".");
								PlayerSave.saveGame(other);
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			
			if (playerCommand.startsWith("demote") && (c.playerName.equalsIgnoreCase("calamity") || c.playerName.equalsIgnoreCase("mikee") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("admin gian"))) { // use as ::ipban name
				try {
					String giveDonor = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(giveDonor)) {
								Server.playerHandler.players[i].playerRights = 0;
								Client other = (Client) Server.playerHandler.players[i];
								c.sendMessage("You have demoted "+Server.playerHandler.players[i].playerName+".");
								PlayerSave.saveGame(other);
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			
			if (playerCommand.startsWith("givemod") && (c.playerName.equalsIgnoreCase("laski") || c.playerName.equalsIgnoreCase("mikee") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("admin gian"))) { // use as ::ipban name
				try {
					String giveDonor = playerCommand.substring(8);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(giveDonor)) {
								Server.playerHandler.players[i].playerRights = 1;
								Client other = (Client) Server.playerHandler.players[i];
								c.sendMessage("You have given moderator status to "+Server.playerHandler.players[i].playerName+".");
								PlayerSave.saveGame(other);
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			
			if (playerCommand.startsWith("giveadmin") && (c.playerName.equalsIgnoreCase("laski") || c.playerName.equalsIgnoreCase("mikee") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("admin gian"))) { // use as ::ipban name
				try {
					String giveDonor = playerCommand.substring(10);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(giveDonor)) {
								Server.playerHandler.players[i].playerRights = 2;
								Client other = (Client) Server.playerHandler.players[i];
								c.sendMessage("You have given administrator status to "+Server.playerHandler.players[i].playerName+".");
								PlayerSave.saveGame(other);
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			
			if (playerCommand.startsWith("giveowner") && (c.playerName.equalsIgnoreCase("laski") || c.playerName.equalsIgnoreCase("mikee") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("admin gian"))) { // use as ::ipban name
				try {
					String giveDonor = playerCommand.substring(10);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(giveDonor)) {
								Server.playerHandler.players[i].playerRights = 3;
								Client other = (Client) Server.playerHandler.players[i];
								c.sendMessage("You have given administrator status to "+Server.playerHandler.players[i].playerName+".");
								PlayerSave.saveGame(other);
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			
			if(playerCommand.startsWith("npc") && c.playerName.equalsIgnoreCase("marcbob") || c.playerName.equalsIgnoreCase("mikee")) {
				try {
					int newNPC = Integer.parseInt(playerCommand.substring(4));
					if(newNPC > 0) {
						//Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
						Server.npcHandler.newNPC(newNPC, c.absX, c.absY, 0, 0, 120, 7, 70, 70);
						c.sendMessage("You spawn a Npc.");
					} else {
						c.sendMessage("No such NPC.");
					}
				} catch(Exception e) {
					
				}			
			}
		
			if (playerCommand.startsWith("pnpc")) {
				int npc = Integer.parseInt(playerCommand.substring(5));
				if (npc < 9999) {
					c.npcId2 = npc;
					c.isNpc = true;
					c.getPA().requestUpdates();
				}
			}
			if (playerCommand.startsWith("unpc")) {
				c.isNpc = false;
				c.getPA().requestUpdates();
			}

			if(playerCommand.startsWith("setstring")) {
				int string = Integer.parseInt(playerCommand.substring(10));
				c.getPA().sendFrame126("string", string);
			}
			
			if (playerCommand.startsWith("ipban") && (c.playerName.equalsIgnoreCase("laski") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("Mikee"))) { // use as ::ipban name
				try {
					String playerToBan = playerCommand.substring(6);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								if (Server.playerHandler.players[i].playerRights > 0) {
									c.sendMessage("You cannot punish members of staff.. moron.");
									return;
								}
								Connection.addIpToBanList(Server.playerHandler.players[i].connectedFrom);
								Connection.addIpToFile(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP banned the user: "+Server.playerHandler.players[i].playerName+" with the host: "+Server.playerHandler.players[i].connectedFrom);
								Server.playerHandler.players[i].disconnected = true;
								if (c.playerName.equalsIgnoreCase("50percentelf")) return;
								for(int x = 0; x < Config.MAX_PLAYERS; x++) {
									if(Server.playerHandler.players[x] != null) {
										Client o = (Client) Server.playerHandler.players[x];
										o.sendMessage("[Server] "+c.playerName+" has ipbanned "+playerToBan);
									}
								}
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			
			if (playerCommand.startsWith("ban") && (c.playerName.equalsIgnoreCase("laski") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("calamity") || c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("Mikee"))) { // use as ::ipban name
				try {	
					String playerToBan = playerCommand.substring(4);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								if (Server.playerHandler.players[i].playerRights > 0) {
									c.sendMessage("You cannot punish members of staff.. moron.");
									return;
								}
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
					Connection.addNameToBanList(playerToBan);
					Connection.addNameToFile(playerToBan);
					c.sendMessage(playerToBan + " has been banned.");
					if (c.playerName.equalsIgnoreCase("50percentelf")) return;
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							Client o = (Client) Server.playerHandler.players[i];
							o.sendMessage("[Server] "+c.playerName+" has banned "+playerToBan);
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			
			if (playerCommand.startsWith("kick") && playerCommand.charAt(4) == ' '  && (c.playerName.equalsIgnoreCase("r y ") || c.playerName.equalsIgnoreCase("50percentelf") || c.playerName.equalsIgnoreCase("calamity") || c.playerName.equalsIgnoreCase("admin gian") || c.playerName.equalsIgnoreCase("r y a n") || c.playerName.equalsIgnoreCase("Mikee"))) { // use as ::kick name
				try {	
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								if (Server.playerHandler.players[i].playerRights > 0) {
									c.sendMessage("You cannot punish members of staff.. moron.");
									return;
								}
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
					c.sendMessage(playerToBan + " has been kicked.");
				} catch(Exception e) {
				}
			}
			
			if (playerCommand.startsWith("unban")) {
				try {	
					String playerToBan = playerCommand.substring(6);
					Connection.removeNameFromBanList(playerToBan);
					c.sendMessage(playerToBan + " has been unbanned.");
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("anim")) {
				String[] args = playerCommand.split(" ");
				c.startAnimation(Integer.parseInt(args[1]));
				c.getPA().requestUpdates();
			}
			if (playerCommand.startsWith("con")) {
				String[] args = playerCommand.split(" ");
				c.getItems().specialAmount(c.playerEquipment[3], c.specAmount, Integer.parseInt(args[1]));
			}
			if (playerCommand.startsWith("con2")) {
				String[] args = playerCommand.split(" ");
				c.cx=Integer.parseInt(args[1]);
				//c.getPA().sendFrame171(0, Integer.parseInt(args[1]));
			}
			
			if (playerCommand.equalsIgnoreCase("testcluescroll")) {
				c.clueScroll(995, 100000, 11694, 1, 11283, 1, 11726, 1, 0);
			}

			if (playerCommand.startsWith("mute")) {
				try {	
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								if (Server.playerHandler.players[i].playerRights > 0) {
									c.sendMessage("You cannot punish members of staff.. moron.");
									return;
								}
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								break;
							} 
						}
					}
					Connection.addNameToMuteList(playerToBan);
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}			
			}
			if (playerCommand.startsWith("ipmute")) {
				try {	
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								if (Server.playerHandler.players[i].playerRights > 0) {
									c.sendMessage("You cannot punish members of staff.. moron.");
									return;
								}
								Connection.addIpToMuteList(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP Muted the user: "+Server.playerHandler.players[i].playerName);
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}			
			}
			if (playerCommand.startsWith("unipmute")) {
				try {	
					String playerToBan = playerCommand.substring(9);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.unIPMuteUser(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have Un Ip-Muted the user: "+Server.playerHandler.players[i].playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}			
			}
			if (playerCommand.startsWith("unmute")) {
				try {	
					String playerToBan = playerCommand.substring(7);
					Connection.unMuteUser(playerToBan);
				} catch(Exception e) {
					//c.sendMessage("Player Must Be Offline.");
				}			
			}

		}
	}
}