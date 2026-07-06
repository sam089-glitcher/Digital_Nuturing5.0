public class Computer {

    // Attributes
    private String CPU;
    private int RAM;
    private int Storage;
    private String GPU;
    private boolean Bluetooth;
    private boolean WiFi;

    // Private constructor
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.Storage = builder.Storage;
        this.GPU = builder.GPU;
        this.Bluetooth = builder.Bluetooth;
        this.WiFi = builder.WiFi;
    }

    // Display computer configuration
    public void showConfiguration() {
        System.out.println("Computer Configuration");
        System.out.println("----------------------");
        System.out.println("CPU       : " + CPU);
        System.out.println("RAM       : " + RAM + " GB");
        System.out.println("Storage   : " + Storage + " GB");
        System.out.println("GPU       : " + GPU);
        System.out.println("Bluetooth : " + (Bluetooth ? "Yes" : "No"));
        System.out.println("WiFi      : " + (WiFi ? "Yes" : "No"));
    }

    // Static Nested Builder Class
    public static class Builder {

        private String CPU;
        private int RAM;
        private int Storage;
        private String GPU;
        private boolean Bluetooth;
        private boolean WiFi;

        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public Builder setRAM(int RAM) {
            this.RAM = RAM;
            return this;
        }

        public Builder setStorage(int Storage) {
            this.Storage = Storage;
            return this;
        }

        public Builder setGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }

        public Builder setBluetooth(boolean Bluetooth) {
            this.Bluetooth = Bluetooth;
            return this;
        }

        public Builder setWiFi(boolean WiFi) {
            this.WiFi = WiFi;
            return this;
        }

        // Build method
        public Computer build() {
            return new Computer(this);
        }
    }
}