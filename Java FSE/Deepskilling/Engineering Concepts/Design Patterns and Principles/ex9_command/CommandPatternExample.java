// ==================== Command.java (Command Interface) ====================
public interface Command {
    void execute();
    void undo(); // bonus: undo support
}

// ==================== Light.java (Receiver Class) ====================
class Light {
    private String location;
    private boolean isOn;

    public Light(String location) {
        this.location = location;
        this.isOn = false;
    }

    public void turnOn() {
        isOn = true;
        System.out.println("[Light - " + location + "] Turned ON  💡");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("[Light - " + location + "] Turned OFF 🌑");
    }

    public boolean isOn() { return isOn; }
}

// ==================== Fan.java (Another Receiver) ====================
class Fan {
    private String location;
    private boolean isOn;

    public Fan(String location) {
        this.location = location;
    }

    public void turnOn()  { isOn = true;  System.out.println("[Fan - " + location + "] Turned ON  🌀"); }
    public void turnOff() { isOn = false; System.out.println("[Fan - " + location + "] Turned OFF ⛔"); }
}

// ==================== LightOnCommand.java (Concrete Command 1) ====================
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() { light.turnOn(); }

    @Override
    public void undo() { light.turnOff(); } // undo = turn off
}

// ==================== LightOffCommand.java (Concrete Command 2) ====================
class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() { light.turnOff(); }

    @Override
    public void undo() { light.turnOn(); } // undo = turn on
}

// ==================== FanOnCommand.java ====================
class FanOnCommand implements Command {
    private Fan fan;
    public FanOnCommand(Fan fan) { this.fan = fan; }

    @Override public void execute() { fan.turnOn(); }
    @Override public void undo()    { fan.turnOff(); }
}

// ==================== FanOffCommand.java ====================
class FanOffCommand implements Command {
    private Fan fan;
    public FanOffCommand(Fan fan) { this.fan = fan; }

    @Override public void execute() { fan.turnOff(); }
    @Override public void undo()    { fan.turnOn(); }
}

// ==================== RemoteControl.java (Invoker Class) ====================
import java.util.Stack;

class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private Stack<Command> commandHistory = new Stack<>();

    public RemoteControl(int slots) {
        onCommands  = new Command[slots];
        offCommands = new Command[slots];
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot]  = onCommand;
        offCommands[slot] = offCommand;
    }

    public void pressOn(int slot) {
        System.out.println("Remote: ON button pressed (slot " + slot + ")");
        onCommands[slot].execute();
        commandHistory.push(onCommands[slot]);
    }

    public void pressOff(int slot) {
        System.out.println("Remote: OFF button pressed (slot " + slot + ")");
        offCommands[slot].execute();
        commandHistory.push(offCommands[slot]);
    }

    public void pressUndo() {
        if (!commandHistory.isEmpty()) {
            System.out.println("Remote: UNDO pressed");
            commandHistory.pop().undo();
        } else {
            System.out.println("Remote: Nothing to undo.");
        }
    }
}

// ==================== CommandTest.java ====================
class CommandTest {
    public static void main(String[] args) {

        System.out.println("=== Command Pattern Demo ===\n");

        // Receivers
        Light livingRoomLight = new Light("Living Room");
        Light bedroomLight    = new Light("Bedroom");
        Fan   ceilingFan      = new Fan("Ceiling");

        // Commands
        Command livingRoomOn  = new LightOnCommand(livingRoomLight);
        Command livingRoomOff = new LightOffCommand(livingRoomLight);
        Command bedroomOn     = new LightOnCommand(bedroomLight);
        Command bedroomOff    = new LightOffCommand(bedroomLight);
        Command fanOn         = new FanOnCommand(ceilingFan);
        Command fanOff        = new FanOffCommand(ceilingFan);

        // Remote with 3 slots
        RemoteControl remote = new RemoteControl(3);
        remote.setCommand(0, livingRoomOn, livingRoomOff);
        remote.setCommand(1, bedroomOn, bedroomOff);
        remote.setCommand(2, fanOn, fanOff);

        System.out.println("--- Pressing buttons ---");
        remote.pressOn(0);
        remote.pressOn(1);
        remote.pressOn(2);

        System.out.println();
        remote.pressOff(1);
        remote.pressOff(2);

        System.out.println("\n--- Undo last 2 actions ---");
        remote.pressUndo(); // undo fan off -> fan on
        remote.pressUndo(); // undo bedroom off -> bedroom on
    }
}
