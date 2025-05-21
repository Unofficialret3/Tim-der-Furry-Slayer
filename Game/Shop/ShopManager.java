package Game.Shop;

import Game.Objects.Player;
import Game.Objects.Weapon;
import Game.Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20;

public class ShopManager {
    int itemCount = 1;
    WeaponItem[] items = new WeaponItem[itemCount];


    public ShopManager(Player player) {

        //upgrade main weapon
        WeaponItem upgradedMainWeapon = new WeaponItem("UpgradedMainWeapon", 20, new Weapon(player.getMainWeapon()));
        upgradedMainWeapon.getWeapon().setFireRate(2 * player.getMainWeapon().getFireRate());
        items[0]=upgradedMainWeapon;

    }

    public void buyItem(int index, Player player) {
        if(items[index].price <= player.money) {
            //ask where to save weapon and add it to Game.Objects.Player
            player.money -= items[index].price;
           player.setMainWeapon(items[index].getWeapon());

        }
    }

}
