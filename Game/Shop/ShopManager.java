package Game.Shop;

import Game.Objects.Player;

public class ShopManager {
    int itemCount = 1;
    WeaponItem[] items = new WeaponItem[itemCount];

    public ShopManager() {

    }

    public void buyItem(int index, Player player) {
        if(items[index].price <= player.money) {
            //ask where to save weapon and add it to Game.Objects.Player
            player.money -= items[index].price;
        }
    }
}
