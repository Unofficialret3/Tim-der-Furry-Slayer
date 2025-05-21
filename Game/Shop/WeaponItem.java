package Game.Shop;

import Game.Objects.Weapon;

public class WeaponItem {
    final String name;
    final int price;
    final Weapon weapon;
    private int lvl = 0;

    public WeaponItem(String name, int price, int level, Weapon weapon) {
        this.name = name;
        this.price = price;
        this.weapon = weapon;
        this.lvl=level ;
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

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
}
