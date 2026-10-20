interface DigitalGame {
    void playDigital();
}

class ModernConsole {
    public void play(DigitalGame game) {
        game.playDigital();
    }
}

class RetroCartridge {
    public void insertCartridge() {
        System.out.println("Retro cartridge inserted. Starting retro game!");
    }
}

class CartridgeAdapter implements DigitalGame {
    private final RetroCartridge cartridge;

    public CartridgeAdapter(RetroCartridge cartridge) {
        this.cartridge = cartridge;
    }

    @Override
    public void playDigital() {
        cartridge.insertCartridge();
    }
}

public class Main {
    public static void main(String[] args) {
        ModernConsole console = new ModernConsole();
        RetroCartridge cartridge = new RetroCartridge();

        DigitalGame adaptedGame = new CartridgeAdapter(cartridge);

        console.play(adaptedGame);
    }
}
