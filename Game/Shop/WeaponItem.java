package Game.Shop;

import Game.Objects.Weapon;

public class WeaponItem {
    final String name;
    final int price;
    final Weapon weapon;

    public WeaponItem(String name, int price, Weapon weapon) {
        this.name = name;
        this.price = price;
        this.weapon = weapon;
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
}
