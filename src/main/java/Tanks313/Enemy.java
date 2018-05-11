package Tanks313;

public class Enemy {

        private int health;
        public Enemy(int health) {
            this.health = health;
        }

        public void hit(int damage) {
            health -= damage;
        }

        public int getHealth() {
            return health;
        }

}
