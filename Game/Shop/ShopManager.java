package Game.Shop;

import Game.Objects.Player;
import Game.Objects.Weapon;

public class ShopManager {
     protected    int itemCount = 1;

    public WeaponItem[] items = new WeaponItem[itemCount];

    private boolean IsUpgradingMainWeapon = false;


    public ShopManager(Player player) {
        //Upgrade für main erstellen
        items[0]= new WeaponItem("Main",1,0,new Weapon(player.getMainWeapon()));
        possibleUpgradeMain(player);



    }

    public void buyItem(int index, Player player) {
        if(items[index].price <= player.money) {
            //ask where to save weapon and add it to Game.Objects.Player
           player.money -= items[index].price;
            if(IsUpgradingMainWeapon) {
                //Upgrade geben
                player.setMainWeapon(items[index].getWeapon());
                IsUpgradingMainWeapon=false;
                //nächstes bereitstellen
                possibleUpgradeMain(player);
            }
        }
    }

    private void possibleUpgradeMain(Player player){
        //upgrade main weapon
        int newLvl = items[0].getLvl()+1 ;
        int newPrice = calculatePrice(20,1.3,newLvl);

        WeaponItem upgradedMainWeapon = new WeaponItem("UpgradedMainWeapon", newPrice,newLvl ,new Weapon(player.getMainWeapon()));
        upgradedMainWeapon.getWeapon().setFireRate(2 * player.getMainWeapon().getFireRate());
        items[0]=upgradedMainWeapon;

    }

    public int calculatePrice(int price , double growthFactor, int level) {


        return (int) Math.round(price * Math.pow(growthFactor, level));
    }

    public boolean isUpgradingMainWeapon() {
        return IsUpgradingMainWeapon;
    }

    public void setUpgradingMainWeapon(boolean upgradingMainWeapon) {
        IsUpgradingMainWeapon = upgradingMainWeapon;
    }
}
