// Interface representing the state and operations of a TV
interface TVoperation {
    boolean isOn();                
    // Check if the TV is on
    void enable();                 
    // Turn on the TV
    int getVolume();               
    // Get the current volume
    void disable();               
     // Turn off the TV
    void setVolume(int vol);       
    // Set the volume
    void setChannel(int channel);  
    // Set the channel
    int getChannel();              
    // Get the current channel
}

// Concrete implementation of TVoperation
class newTV implements TVoperation {
    private boolean powerOn;     
      // Represents the power state of the TV
    private int vol;               
    // Represents the volume level
    private int channel;          
     // Represents the TV channel

    @Override
    public boolean isOn() {
        return powerOn;
    }

    @Override
    public void enable() {
        powerOn = true;
        System.out.println("Powered on");
    }

    @Override
    public void disable() {
        powerOn = false;
        System.out.println("Powered off");
    }

    @Override
    public int getVolume() {
        return vol;
    }

    @Override
    public void setVolume(int vol) {
        this.vol = vol;
        System.out.println("Volume is " + vol);
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("Channel is " + channel);
    }
}

// Interface representing basic TV operations
interface TV {
    void performOperation();
}

// GeneralTV class implementing the TV interface
class GeneralTV implements TV {
    private TVoperation state;  
    // Represents the TV state and operations

    public GeneralTV(TVoperation state) {
        this.state = state;
    }

    @Override
    public void performOperation() {
        System.out.println("General TV operation");
    }
}

// SmartTV class implementing the TV interface
class SmartTV implements TV {
    private TVoperation state;  
    // Represents the TV state and operations

    public SmartTV(TVoperation state) {
        this.state = state;
    }

    public void youtube() {
        if (state.isOn()) {
            System.out.println("Showing YouTube on Smart TV");
        } else {
            System.out.println("Smart TV is off. Turn it on and try again.");
        }
    }

    @Override
    public void performOperation() {
        System.out.println("Smart TV operation");
    }
}

// Interface representing basic remote control operations
interface Remote {
    void operate();
}

// Interface for implementing remote control operations on a TV
interface RemoteImplementer {
    void togglePower();
    void volumeUp();
    void volumeDown();
    void channelUp();
    void channelDown();
}

// Implementation of remote control operations on a TV
class TVRemoteImplementer implements RemoteImplementer {
    private TVoperation state;  
    // Represents the TV state and operations

    public TVRemoteImplementer(TVoperation state) {
        this.state = state;
    }

    @Override
    public void togglePower() {
        if (state.isOn()) {
            state.disable();
        } else {
            state.enable();
        }
    }

    @Override
    public void volumeUp() {
        int currentVolume = state.getVolume();
        state.setVolume(currentVolume + 1);
    }

    @Override
    public void volumeDown() {
        int currentVolume = state.getVolume();
        state.setVolume(currentVolume - 1);
    }

    @Override
    public void channelUp() {
        int currentChannel = state.getChannel();
        state.setChannel(currentChannel + 1);
    }

    @Override
    public void channelDown() {
        int currentChannel = state.getChannel();
        state.setChannel(currentChannel - 1);
    }
}

// Proxy class implementing Remote, delegating to RemoteImplementer
class newProxy implements Remote {
    private RemoteImplementer implementer;  
    // Represents the remote control operations

    public newProxy(RemoteImplementer implementer) {
        this.implementer = implementer;
    }

    @Override
    public void operate() {
        implementer.togglePower();
        implementer.volumeUp();
        implementer.volumeDown();
        implementer.channelUp();
        implementer.channelDown();
    }
}

// SmartRemote class implementing Remote
class SmartRemote implements Remote {
    private RemoteImplementer implementer;  
    // Represents the remote control operations

    public SmartRemote(RemoteImplementer implementer) {
        this.implementer = implementer;
    }

    public void showYoutube() {
        if (implementer instanceof TVRemoteImplementer) {
            ((TVRemoteImplementer) implementer).togglePower();
            ((TVRemoteImplementer) implementer).channelUp();
            System.out.println("Showing YouTube on Smart Remote");
        }
    }

    @Override
    public void operate() {
        implementer.togglePower();
        implementer.volumeUp();
        implementer.volumeDown();
        implementer.channelUp();
        implementer.channelDown();
    }
}

// Main class to demonstrate the usage of the TV and remote control system
public class Main {
    public static void main(String[] args) {
        TVoperation sharedState = new newTV();

        TV general = new GeneralTV(sharedState);
        TV smartTV = new SmartTV(sharedState);

        RemoteImplementer generalTV = new TVRemoteImplementer(sharedState);
        RemoteImplementer smarttvimplement = new TVRemoteImplementer(sharedState);

        Remote generalTVRemote = new newProxy(generalTV);
        Remote smartTVRemote = new newProxy(smarttvimplement);

        Remote smartRemote = new SmartRemote(generalTV);
        smartTV.performOperation(); 
         // Smart TV operation
        general.performOperation(); 
         // General TV operation
        ((SmartTV) smartTV).youtube();
          // Show YouTube on SmartTV
        generalTVRemote.operate();  
        // General TV remote operations
        smartTVRemote.operate();  
        // Smart TV remote operations
        smartRemote.operate();  
        // Smart Remote operations
        ((SmartRemote) smartRemote).showYoutube();
          // Show YouTube on Smart Remote
    }
}
