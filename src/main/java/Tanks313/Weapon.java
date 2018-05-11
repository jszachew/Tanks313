package Tanks313;

public class Weapon {

        private int damageMin;
        private int damageMax;

        public Weapon(int damageMin, int damageMax) {
            this.damageMax = damageMax;
            this.damageMin = damageMin;
        }

        public int rollDamage() {
            int range = damageMax - damageMin;

            return damageMin + (int)(Math.random() * range);
        }

}
