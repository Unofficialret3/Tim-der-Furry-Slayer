package Game.Shop;

import Game.Objects.Weapon;

public class WeaponItem {
    final String name;
    final int price;
    final Weapon weapon;
    private int damageLvl = 0;
    private int firerateLvl = 0;
    private int bulletHealthLvl = 0;
    private double growthfactor;

    public WeaponItem(String name, int price, int damageLvl,int firerateLvl,int bulletHealthLvl,double growthfactor, Weapon weapon) {
        this.name = name;
        this.price = price;
        this.weapon = weapon;
        this.damageLvl=damageLvl;
        this.bulletHealthLvl=bulletHealthLvl;
        this.firerateLvl=firerateLvl;
        this.growthfactor=growthfactor;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getFirerateLvl() {
        return firerateLvl;
    }
    public int getDamageLvl() {
        return damageLvl;
    }
    public int getBulletHealthLvl() {
        return bulletHealthLvl;
    }

    public double getGrowthfactor(){return growthfactor;}

    public void setFirerateLvl(int level) {
        this.firerateLvl = level;
    }
    public void setDamageLvl(int level) {
        this.damageLvl = level;
    }
    public void setBulletHealthLvl(int level) {
        this.bulletHealthLvl = level;
    }
}
