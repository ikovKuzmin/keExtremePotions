import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.Loader;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import org.rev317.min.api.methods.Bank;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Items.Option;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.SceneObject;

@ScriptManifest(author = "Kuzmin", category = Category.HERBLORE, description = "Creates EXTREME POTIONS (3)", name = "kuExtremePotions", servers = { "Ikov" }, version = 1.0)
public class unfPotMaker extends Script implements MessageListener, Paintable {
	// paint section
	public final Timer RAN = new Timer(); // Timer
	int potProgress = 0;

	public final int bankID = 2213;
	static int bankInterface = 5292;
	static Random random = new Random();
	static boolean sleepBoolean = false;

	int cleanAvantoe = 262;
	int cleanDwarf = 268;
	int cleanLantadyme = 2482;
	int grenwall = 12540;
	int groundMudRune = 9595;
	int cleanTorstol = 270;

	int attPot = 146;
	int strPot = 158;
	int defPot = 164;
	int rangePot = 170;
	int magicPot = 3043;

	int extremeAtt = 15310;
	int extremeStr = 15314;
	int extremeDef = 15318;
	int extremeRange = 15326;
	int extremeMagic = 15322;
	int overload3 = 15334;

	int pestle_mortar = 234;
	int mudRune = 4699;

	private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	public static int option = 0;
	GUI gui;

	public boolean onExecute() {
		gui = new GUI();
		gui.setVisible(true);
		while (gui.isVisible()) {
			sleep(20 + random.nextInt(40));
		}
		option = gui.option;
		strategies.add(new createUnfPot());
		provide(strategies);
		return true;
	}

	public void onFinish() {

	}

	public class createUnfPot implements Strategy {
		public boolean activate() {
			return true;
		}

		public void execute() {
			try {
				SceneObject bank = SceneObjects.getClosest(bankID);
				if (bank != null) {
					Bank.open(bank);
					// sleepBoolean = Game.getOpenInterfaceId() ==
					// bankInterface;
					sleep(Game.getOpenInterfaceId() == bankInterface, 1000);
				}
			} catch (Exception e) {
				System.out.println("Trouble with banking)");
			}

			if (option == 4) {
				Bank.depositAllExcept(grenwall);
			} else if (option == 7) {
				Bank.depositAllExcept(pestle_mortar, mudRune);
			} else {
				Bank.depositAllExcept();
			}
			sleep(Game.getOpenInterfaceId() == bankInterface, 1000);

			if (option == 1) {
				createExtreme(attPot, cleanAvantoe, extremeAtt);
			} else if (option == 2) {
				createExtreme(strPot, cleanDwarf, extremeStr);
			} else if (option == 3) {
				createExtreme(defPot, cleanLantadyme, extremeDef);
			} else if (option == 4) {
				if (Game.getOpenInterfaceId() == bankInterface
						&& Bank.getCount(rangePot) > 0) {
					if (!Inventory.contains(grenwall)
							&& Bank.getCount(grenwall) > 5) {
						Bank.withdraw(grenwall, 10000, 100);
						// sleepBoolean = Inventory.getCount(grenwall) > 0;
						sleep(Inventory.getCount(grenwall) > 0, 1000);
					}

					Bank.withdraw(rangePot, 27, 100);
					// sleepBoolean = Inventory.getCount(rangePot) > 0;
					sleep(Inventory.getCount(rangePot) > 0, 1000);
				} else {
					System.out
							.println("You are out of Range Potions (3) or Grenwalls");
					setState(STATE_STOPPED);
				}

				if (Inventory.contains(grenwall)
						&& Inventory.contains(rangePot)) {
					Bank.close();
					Item gren = Inventory.getItem(grenwall);
					Item range = Inventory.getItem(rangePot);
					gren.interact(Option.USE);
					Time.sleep(750 + random.nextInt(100));
					range.interact(Option.USE_WITH);
					Time.sleep(750 + random.nextInt(100));
					if (Game.getOpenBackDialogId() == 4429) {
						makeAllPotions();
					}
					// sleepBoolean = Inventory.getCount(extremeRange) == 27;
					sleep(Inventory.getCount(extremeRange) == 27, 35000);
				}
			} else if (option == 5) {
				createExtreme(magicPot, groundMudRune, extremeMagic);
			} else if (option == 6) {
				if (Game.getOpenInterfaceId() == bankInterface
						&& Bank.getCount(extremeAtt) > 0
						&& Bank.getCount(extremeStr) > 0
						&& Bank.getCount(extremeDef) > 0
						&& Bank.getCount(extremeRange) > 0
						&& Bank.getCount(extremeMagic) > 0
						&& Bank.getCount(cleanTorstol) > 0) {
					Bank.withdraw(extremeAtt, 4, 0);
					// sleepBoolean = Inventory.getCount(extremeAtt) > 0;
					sleep(Inventory.getCount(extremeAtt) > 0, 1000);

					Bank.withdraw(extremeStr, 4, 100);
					// sleepBoolean = Inventory.getCount(extremeStr) > 0;
					sleep(Inventory.getCount(extremeStr) > 0, 1000);

					Bank.withdraw(extremeDef, 4, 100);
					// sleepBoolean = Inventory.getCount(extremeDef) > 0;
					sleep(Inventory.getCount(extremeDef) > 0, 1000);

					Bank.withdraw(extremeRange, 4, 100);
					// sleepBoolean = Inventory.getCount(extremeRange) > 0;
					sleep(Inventory.getCount(extremeRange) > 0, 1000);

					Bank.withdraw(extremeMagic, 4, 100);
					// sleepBoolean = Inventory.getCount(extremeMagic) > 0;
					sleep(Inventory.getCount(extremeMagic) > 0, 1000);

					Bank.withdraw(cleanTorstol, 4, 100);
					// sleepBoolean = Inventory.getCount(cleanTorstol) > 0;
					sleep(Inventory.getCount(cleanTorstol) > 0, 1000);
				} else {
					System.out
							.println("You are out of Extremes or clean torstols");
				}

				if (Inventory.contains(extremeAtt)
						&& Inventory.contains(extremeStr)
						&& Inventory.contains(extremeDef)
						&& Inventory.contains(extremeRange)
						&& Inventory.contains(extremeMagic)
						&& Inventory.contains(cleanTorstol)) {
					Bank.close();
					Item extreme = Inventory.getItem(extremeMagic);
					Item torstol = Inventory.getItem(cleanTorstol);
					torstol.interact(Option.USE);
					Time.sleep(750 + random.nextInt(100));
					extreme.interact(Option.USE_WITH);
					Time.sleep(750 + random.nextInt(100));
					if (Game.getOpenBackDialogId() == 4429) {
						makeAllPotions();
					}
					sleepBoolean = Inventory.getCount(overload3) == 4;
					sleep(Inventory.getCount(overload3) == 4, 6000);
				}
			} else if (option == 7) {
				if (Game.getOpenInterfaceId() == bankInterface
						&& Bank.getCount(pestle_mortar) > 0
						&& Bank.getCount(mudRune) > 0) {
					if (!Inventory.contains(pestle_mortar)
							&& !Inventory.contains(mudRune)) {
						Bank.withdraw(pestle_mortar, 1, 100);
						// sleepBoolean = Inventory.getCount(pestle_mortar) > 0;
						sleep(Inventory.getCount(pestle_mortar) > 0, 1000);

						Bank.withdraw(mudRune, 10000, 400);
						// sleepBoolean = Inventory.getCount(mudRune) > 0;
						sleep(Inventory.getCount(mudRune) > 0, 1000);
					}
				} else {
					System.out
							.println("You are out of Magic Potions (3) or Ground Mud Runes");
				}

				if (Inventory.contains(pestle_mortar)
						&& Inventory.contains(mudRune)) {
					Bank.close();
					Item pestle = Inventory.getItem(pestle_mortar);
					Item mRune = Inventory.getItem(mudRune);
					pestle.interact(Option.USE);
					Time.sleep(750 + random.nextInt(100));
					mRune.interact(Option.USE_WITH);
					Time.sleep(750 + random.nextInt(100));
					if (Game.getOpenBackDialogId() == 4429) {
						// Menu.sendAction(315,145,16,1747,2213,1);
						makeAllPotions();
					}
					// sleepBoolean = Inventory.getCount(groundMudRune) == 27;
					sleep(Inventory.getCount(groundMudRune) == 27, 40000);
				}
			} else if (option == 8) {
				int potion3 = overload3;
				if (Game.getOpenInterfaceId() == bankInterface
						&& Bank.getCount(potion3) > 0) {
					if (!Inventory.contains(potion3)) {
						Bank.withdraw(potion3, 28, 100);
						// sleepBoolean = Inventory.getCount(potion3) > 0;
						sleep(Inventory.getCount(potion3) > 0, 1000);
					}
				} else {
					System.out.println("You are out of Overload (3)");
				}

				if (Inventory.contains(potion3)) {
					Bank.close();
					Item[] overloads = Inventory.getItems();
					overloads[0].interact(Option.USE);
					Time.sleep(800 + random.nextInt(100));
					overloads[1].interact(Option.USE_WITH);
					Time.sleep(800 + random.nextInt(100));
				}
			} else {
				System.out.println("Option is: " + option);
			}
			Time.sleep(1000);
		}
	}

	private final Color white = new Color(255, 255, 255);
	private final Color green = new Color(0, 255, 5);
	private final Font font = new Font("Comic Sans", 0, 14);

	public void paint(Graphics g) {

		g.setColor(green);
		g.fillRect(3, 480, 515, 20);
		g.setColor(white);
		g.setFont(font);
		g.drawString("Runtime " + RAN, 6, 497);
		g.drawString("Potions Created: " + potProgress, 180, 497);
		g.drawString("kuExtremePotions By Kuzmin", 330, 497);
	}

	@Override
	public void messageReceived(MessageEvent m) {
		if (m.getMessage().contains("You make a ")) {
			potProgress += 1;
		}
	}

	public static void forceLogout() {
		try {
			Class<?> c = Loader.getClient().getClass();
			Method m = c.getDeclaredMethod("am");
			m.setAccessible(true);
			m.invoke(Loader.getClient());
		} catch (NoSuchMethodException | IllegalAccessException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void makeAllPotions() {
		Mouse.getInstance().click(280, 410, false); // left click
		Time.sleep(100);
		Mouse.getInstance().click(280, 479, true); // right click
	}

	public static void createExtreme(final int potion, final int ingredient,
			final int extreme) {
		if (Game.getOpenInterfaceId() == bankInterface
				&& Bank.getCount(potion) > 0 && Bank.getCount(ingredient) > 0) {
			Bank.withdraw(potion, 14, 0);
			sleepBoolean = Inventory.getCount(potion) > 0;
			sleep(sleepBoolean, 1000);

			Bank.withdraw(ingredient, 14, 100);
			sleepBoolean = Inventory.getCount(ingredient) > 0;
			sleep(sleepBoolean, 1000);
		} else {
			System.out.println("You are out of required materials");
		}

		if (Inventory.contains(potion) && Inventory.contains(ingredient)) {
			Bank.close();
			Item pot = Inventory.getItem(potion);
			Item ingr = Inventory.getItem(ingredient);
			pot.interact(Option.USE);
			Time.sleep(750 + random.nextInt(100));
			ingr.interact(Option.USE_WITH);
			Time.sleep(750 + random.nextInt(100));
			if (Game.getOpenBackDialogId() == 4429) {
				// Menu.sendAction(315,145,16,1747,2213,1);
				makeAllPotions();
			}
			// sleepBoolean = Inventory.getCount(extreme) == 14;
			System.out.println("There are " + Inventory.getCount(extreme)
					+ " in the inventory");
			sleep(Inventory.getCount(extreme) == 14, 16000);
		}
	}

	public static void sleep(final boolean valid, int time) {
		Time.sleep(new SleepCondition() {
			public boolean isValid() {
				return valid;
			}
		}, time);
	}

}
