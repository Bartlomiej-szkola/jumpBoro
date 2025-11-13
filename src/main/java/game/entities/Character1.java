package game.entities;

public class Character1 extends Player {

    public Character1() {
        // Wywołanie konstruktora Player z predefiniowanymi parametrami
        super("/character1.png", "/character1jumping.png", "/character1beforejump.png", 3.5 , 30 , 1.0);
    }
}
