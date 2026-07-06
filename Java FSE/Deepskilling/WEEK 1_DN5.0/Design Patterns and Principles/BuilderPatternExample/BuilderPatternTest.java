public class BuilderPatternTest {

    public static void main(String[] args) {

        System.out.println("=== Builder Pattern Demo ===\n");

        // Gaming Computer
        Computer gamingPC = new Computer.Builder()
                .setCPU("Intel Core i9")
                .setRAM(32)
                .setStorage(1000)
                .setGPU("NVIDIA RTX 4080")
                .setBluetooth(true)
                .setWiFi(true)
                .build();

        System.out.println("Gaming Computer:");
        gamingPC.showConfiguration();

        System.out.println();

        // Office Computer
        Computer officePC = new Computer.Builder()
                .setCPU("Intel Core i5")
                .setRAM(16)
                .setStorage(512)
                .setGPU("Integrated Graphics")
                .setBluetooth(false)
                .setWiFi(true)
                .build();

        System.out.println("Office Computer:");
        officePC.showConfiguration();
    }
}