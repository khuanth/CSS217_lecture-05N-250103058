interface MovementStrategy {
    void move();
}

class Walking implements MovementStrategy {
    @Override
    public void move() {
        System.out.println("Walking: 5 km/h");
    }
}

class Skateboarding implements MovementStrategy {
    @Override
    public void move() {
        System.out.println("Skateboarding: 15 km/h");
    }
}

class Jetpack implements MovementStrategy {
    @Override
    public void move() {
        System.out.println("Jetpack: 60 km/h");
    }
}

class Player {
    private MovementStrategy movementStrategy;

    public Player(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public void move() {
        movementStrategy.move();
    }
}

public class Main {
    public static void main(String[] args) {
        Player player = new Player(new Walking());
        player.move();

        player.setMovementStrategy(new Jetpack());
        player.move();

        player.setMovementStrategy(new Skateboarding());
        player.move();
    }
}
