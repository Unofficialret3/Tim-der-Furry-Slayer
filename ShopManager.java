public class ShopManager {
    int itemCount = 0;
    WeaponItem[] items = new WeaponItem[itemCount];

    public ShopManager() {

    }

    public void buyItem(int index, Player player) {
        if(items[index].price <= player.money) {
            //ask where to save weapon and add it to Player
            player.money -= items[index].price;
        }
    }
}
