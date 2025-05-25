package Game.Shop;

import Game.Objects.Player;
import Game.Objects.Weapon;

public class ShopManager {
     protected    int itemCount = 3;

    public WeaponItem[] items = new WeaponItem[itemCount];

    private boolean IsUpgradingMainWeapon = false;


    public ShopManager(Player player) {
        //Upgrade für main erstellen
        items[0]= new WeaponItem("Main",20,0,0,0,1.3,new Weapon(player.getMainWeapon()));
        items[1]= new WeaponItem("Main",20,0,0,0,1.5,new Weapon(player.getMainWeapon()));
        items[2]= new WeaponItem("Main",25,0,0,0,1.15,new Weapon(player.getMainWeapon()));

    }

    public void buyItem(int index, Player player) {
        possibleUpgradeMain(player,index);

        if(items[index].price <= player.money) {
            //ask where to save weapon and add it to Game.Objects.Player
           player.money -= items[index].price;
            if(IsUpgradingMainWeapon) {
                //Upgrade geben
                player.setMainWeapon(items[index].getWeapon());
                IsUpgradingMainWeapon=false;
                //nächstes bereitstellen
                possibleUpgradeMain(player, index);
            }
        }
    }

    private void possibleUpgradeMain(Player player, int itemNumber){
        Weapon oldWeapon = player.getMainWeapon();
        Weapon newWeapon = new Weapon(oldWeapon);

        int newLvl = 0;
        int startPrice = 0;
        double growthFactor = items[itemNumber].getGrowthfactor();
        // levelwählen
        switch (itemNumber) {
            case 0: newLvl = items[0].getFirerateLvl()+1; break; // FireRate
            case 1: newLvl = items[1].getFirerateLvl()+1; break;// Damage
            case 2: newLvl = items[2].getFirerateLvl()+1; break; // BulletHealth
        }


        //  Startpreis
        switch (itemNumber) {
            case 0: startPrice = 20; break; // FireRate
            case 1: startPrice = 20; break; // Damage
            case 2: startPrice = 25; break; // BulletHealth
        }

        int newPrice = calculatePrice(startPrice, growthFactor, newLvl);

        switch (itemNumber){
            case 0:
                newWeapon.setFireRate(2 * oldWeapon.getFireRate());
                break;
            case 1:
                newWeapon.setDamage(oldWeapon.getDamage() + 1);
                break;
            case 2:
                newWeapon.setBulletHealth(oldWeapon.getBulletHealth() + 1);
                break;
        }
        WeaponItem upgradedMainWeapon = null;
        switch (itemNumber) {

            case 0:upgradedMainWeapon = new WeaponItem(items[itemNumber].getName(), newPrice,items[0].getDamageLvl(), newLvl,items[0].getBulletHealthLvl(), growthFactor, newWeapon);break; // FireRate
            case 1: upgradedMainWeapon = new WeaponItem(items[itemNumber].getName(), newPrice, newLvl,items[1].getFirerateLvl(),items[1].getBulletHealthLvl(), growthFactor, newWeapon);break;// Damage
            case 2:upgradedMainWeapon = new WeaponItem(items[itemNumber].getName(), newPrice,items[2].getDamageLvl(),items[2].getFirerateLvl(), newLvl, growthFactor, newWeapon); break; // BulletHealth
        }


        for(int i = 0;i<=items.length-1;i++){
            items[i] = upgradedMainWeapon;
        }

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
