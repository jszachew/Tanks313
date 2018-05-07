package Tanks313;

public class Player {
    private Weapon weapon;
    private int damageBase;

    public Player(int damageBase, Weapon weapon) {
        this.damageBase = damageBase;
        this.weapon = weapon;
    }

    public void attack(Enemy enemy) {
        int damage = damageBase + weapon.rollDamage();
        enemy.hit(damage);

        System.out.printf("Player hit enemy for %s damage, enemy is at %s health\n", damage, enemy.getHealth());
    }
}
