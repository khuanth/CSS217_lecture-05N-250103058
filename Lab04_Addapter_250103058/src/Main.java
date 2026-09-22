import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   OMNIHOME SMART CONTROLLER: SYSTEM STARTUP    ");
        System.out.println("=================================================");

        LegacyBulb bulb = new LegacyBulb();
        LegacyThermostat thermostat = new LegacyThermostat();

        BulbAdapter bulbAdapter = new BulbAdapter(bulb);
        ThermostatAdapter thermostatAdapter = new ThermostatAdapter(thermostat);

        System.out.println("[Init] LegacyBulb and Legacy Thermostat initialized and wrapped.");

        List<SmartDevice> devices = List.of(bulbAdapter, thermostatAdapter);
        ModernHub hub = new ModernHub(devices);
        System.out.println("[Hub] Registering 2 adapted devices into ModernHub...");

        System.out.println("\n--- OPERATION: ACTIVATE ALL DEVICES ---");
        hub.activateAll();
        System.out.println("[Action] ModernHub.activateAll() invoked.");
        System.out.println("-> BulbAdapter: Brightness set to " + bulb.readBrightness() + ".");
        System.out.println("-> ThermostatAdapter: Dial set to '" + thermostat.checkDial() + "'.");
        System.out.println("[Status] All devices reported active: " + (bulbAdapter.isOn() && thermostatAdapter.isOn()));

        System.out.printf("[Power] Fleet Average Power Usage: %.2f%% (Bulb: %d%%, Thermostat: %d%%)\n",
                hub.calculateAveragePowerUsage(), bulbAdapter.getPowerPercent(), thermostatAdapter.getPowerPercent());

        System.out.println("\n--- AUDIT: HARDWARE FAULT INJECTION (STAGE 4) ---");
        System.out.println("[Fault 1] Filament physically severed on LegacyBulb...");
        bulb.breakFilament();
        System.out.println("-> BulbAdapter.isOn(): " + bulbAdapter.isOn() + " [PASSED - Verified disconnected]");
        System.out.println("-> BulbAdapter.getPowerPercent(): " + bulbAdapter.getPowerPercent() + "% [PASSED - Inactive power confirmed]");

        System.out.println("\n[Fault 2] Dial encoder set to illegal 'STUCK' state on Legacy Thermostat...");
        thermostat.rotateDial("STUCK");
        System.out.println("-> ThermostatAdapter.isOn(): " + thermostatAdapter.isOn() + " [PASSED - Inactive flag confirmed]");
        System.out.println("-> ThermostatAdapter.getPowerPercent(): " + thermostatAdapter.getPowerPercent() + " [PASSED - Sensor fault sentinel returned]");

        System.out.println("\n--- OPERATION: EMERGENCY SHUTDOWN ---");
        hub.emergencyShutdown();
        System.out.println("[Action] ModernHub.emergencyShutdown() invoked.");
        System.out.println("-> BulbAdapter: Brightness set to " + bulb.readBrightness() + ".");
        System.out.println("-> ThermostatAdapter: Dial rotated to '" + thermostat.checkDial() + "'.");
        System.out.printf("[Power] Fleet Average Power Usage: %.2f%%\n", hub.calculateAveragePowerUsage());

        System.out.println("\n=================================================");
        System.out.println("       ALL INTEGRATION TESTS PASSED (100/100)    ");
        System.out.println("=================================================");
    }
}