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
        int oldLvl=0;
        int newLvl = 0;
        int startPrice = 0;
        double growthFactor = items[itemNumber].getGrowthfactor();
        // levelwählen
        switch (itemNumber) {
            case 0: newLvl = items[0].getFirerateLvl()+1; oldLvl=items[0].getFirerateLvl(); break; // FireRate
            case 1: newLvl = items[1].getDamageLvl()+1; oldLvl=items[1].getFirerateLvl();break;// Damage
            case 2: newLvl = items[2].getBulletHealthLvl()+1; oldLvl=items[2].getFirerateLvl();break; // BulletHealth
        }


        //  Startpreis
        switch (itemNumber) {
            case 0: startPrice = 20; break; // FireRate
            case 1: startPrice = 20; break; // Damage
            case 2: startPrice = 25; break; // BulletHealth
        }
        int oldPrice = calculatePrice(startPrice,growthFactor,oldLvl);
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
        WeaponItem copyUpgradedMainWeapon = null;
        switch (itemNumber) {

            case 0:
                upgradedMainWeapon = new WeaponItem(items[itemNumber].getName(), newPrice,items[0].getDamageLvl(), newLvl,items[0].getBulletHealthLvl(), growthFactor, newWeapon);
                 copyUpgradedMainWeapon =new WeaponItem(items[itemNumber].getName(),oldPrice, oldLvl,items[1].getFirerateLvl(),items[1].getBulletHealthLvl(),growthFactor,  newWeapon);
                items[0]= upgradedMainWeapon;
                items[1]= copyUpgradedMainWeapon;
                items[2]= copyUpgradedMainWeapon;
                break; // FireRate
            case 1:
                upgradedMainWeapon = new WeaponItem(items[itemNumber].getName(), newPrice, newLvl,items[1].getFirerateLvl(),items[1].getBulletHealthLvl(),growthFactor,  newWeapon);
                copyUpgradedMainWeapon =new WeaponItem(items[itemNumber].getName(),oldPrice, oldLvl,items[1].getFirerateLvl(),items[1].getBulletHealthLvl(),growthFactor,  newWeapon);
                items[0]= copyUpgradedMainWeapon;
                items[1]= upgradedMainWeapon;
                items[2]= copyUpgradedMainWeapon;

                break;// Damage
            case 2:
                upgradedMainWeapon = new WeaponItem(items[itemNumber].getName(), newPrice,items[2].getDamageLvl(),items[2].getFirerateLvl(), newLvl,growthFactor, newWeapon);
                copyUpgradedMainWeapon =new WeaponItem(items[itemNumber].getName(),oldPrice, oldLvl,items[1].getFirerateLvl(),items[1].getBulletHealthLvl(),growthFactor,  newWeapon);
                items[0]= copyUpgradedMainWeapon;
                items[1]= copyUpgradedMainWeapon;
                items[2]= upgradedMainWeapon;


                break; // BulletHealth
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
