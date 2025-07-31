// package Behavioural.Command;
import java.util.*;


/*
// [1] without command pattern
//turn on and turn off are commands
// for light.

//Receiver
class Light{
    public void on(){
        System.out.println("Light turned ON");
    }
    public void off(){
        System.out.println("Light turned OFF");
    }
}

class AC{
    public void on(){
        System.out.println("AC turned ON");
    }
    public void off(){
        System.out.println("AC turned OFF");
    }
}

class NaiveRemoteControl{
    // problem
    // this naive control is tightly coupled
    // with the receivers or the device.
    private Light light;
    private AC ac;
    private String lastAction = "";

    public NaiveRemoteControl(Light light, AC ac){
        this.light = light;
        this.ac = ac;
    }
    public void pressLightOn(){
        // we have individual methods for on and off.
        // this is not scalable.
        light.on();
        lastAction = "LIGHT_ON";
    }
    public void pressLightOff(){
        light.off();
        lastAction = "LIGHT_OFF";
    }
    public void turnACOn(){
        ac.on();
        lastAction = "AC_ON";
    }
    public void turnACOff(){
        ac.off();
        lastAction = "AC_OFF";
    }
    public void pressUndo(){
        //complex.
        switch (lastAction) {
            case "LIGHT_ON": light.off(); lastAction = "LIGHT_OFF"; break;
            case "LIGHT_OFF": light.on(); lastAction = "LIGHT_ON"; break;
            case "AC_ON": ac.off(); lastAction = "AC_OFF"; break;
            case "AC_OFF": ac.on(); lastAction = "AC_ON"; break;
        }
    }
}
*/


class Light{
    public void on(){
        System.out.println("Light turned ON");
    }
    public void off(){
        System.out.println("Light turned OFF");
    }
}

class AC{
    public void on(){
        System.out.println("AC turned ON");
    }
    public void off(){
        System.out.println("AC turned OFF");
    }
}

interface Command{
    void execute();
    void undo();
}

//concrete commands.
class LightOnCommand implements Command{ //request turned into separate objects.
    private Light light;//since specific to light
    public LightOnCommand(Light light){
        this.light = light;
    }
    public void execute(){// does not say on or off. only execute.
        light.on();
    }
    public void undo(){
        light.off();// opposite of execute.
    }
}

class LightOffCommand implements Command{
    private Light light;
    public LightOffCommand(Light light){
        this.light = light;
    }
    public void execute(){
        light.off();
    }
    public void undo(){
        light.on();
    }
}

class ACOnCommand implements Command{
    private AC ac;
    public ACOnCommand(AC ac){
        this.ac = ac;
    }
    public void execute(){
        ac.on();
    }
    public void undo(){
        ac.off();
    }
}

class ACOffCommand implements Command{
    private AC ac;
    public ACOffCommand(AC ac){
        this.ac = ac;
    }
    public void execute(){
        ac.off();
    }
    public void undo(){
        ac.on();
    }
}

class RemoteControl{
    private Command[] buttons = new Command[4];// not aware of device, not aware of on or off.

    //with undo option-
    private Stack<Command> commandHistory = new Stack<>();

    public void setCommand(int slot, Command command){
        buttons[slot] = command;
    }
    public void pressButton(int slot){
        if(buttons[slot]!=null){
            buttons[slot].execute(); // just executes the command.
            commandHistory.push(buttons[slot]);
        }
        else{
            System.out.println("No command set to this slot - " + slot);
        }
    }

    public void pressUndo(){
        if(!commandHistory.empty()){
            commandHistory.pop().undo();
        }
        else{
            System.out.println("No commands to undo");
        }
    }
}
public class Main {
    public static void main(String[] args) {
        /*
        //[1] without command pattern
        Light light = new Light();
        light.on();
        // typically we wont want the client to create instance of light and then turn it on and off.
        // we'll introduce something as a remote.
        // dont want the client to directly interact with the device or the receiver.

        //after introdcuong remote-
        AC ac = new AC();
        NaiveRemoteControl remote = new NaiveRemoteControl(light, ac);
        remote.pressLightOn();
        remote.pressLightOff();
        remote.turnACOn();
        remote.turnACOff();
        //problem
        //this is not scalable
        // these are tightly coupled, not scalable.
        // the commands ON and OFF are tightly coupled to the remote.
        // dont want that.
        // if we have more commands and more devices should be easy to integrate.

        //command pattern comes in.
        //want to decouple the receiver and the sender.
        
        //formal defn
        // behav d.p. that turns a request into a separate object, allowing you to 
        // decouple the code that issues the request from the code that performs it.

        // this lets you add features like undo, redo, logging and dynamic command execution without
        // changing the business logic.

        // four key components
        //client->sets the command it wants
        //invoker->remote here. invokes command
        //command->on,off
        //receiver->light,ac
        */
        Light light = new Light();
        AC ac = new AC();

        //commands are the ones tighty coupled with the device as it should be.
        Command lightsOn = new LightOnCommand(light);
        Command lightsOff = new LightOffCommand(light);
        Command acOn = new ACOnCommand(ac);
        Command acOff = new ACOffCommand(ac);
        RemoteControl remoteControl = new RemoteControl();
        remoteControl.setCommand(0, lightsOn);
        remoteControl.setCommand(1, lightsOff);
        remoteControl.setCommand(2, acOn);
        remoteControl.setCommand(3, acOff);

        remoteControl.pressButton(0);
        remoteControl.pressButton(1);
        remoteControl.pressButton(2);
        remoteControl.pressUndo();
        remoteControl.pressUndo();


        //without the pattern, what happens?
        //tight coupling between invoker and receiver
        // no resusability or abstraction for actions.
        // undo/redo or any other operation is poorly supported.
        // hard to implemnent BATCH (Night mode) actions. say night mode means light off and ac on.
        // like a macro command which calls light and ac command.
        // all for client is that 5th button is nightmode command button.

        //no plug and play flexibility.
        //scalability breaks down


        //when to use it?
        //you need to decouple the sender from the revceiver
        // need undo/redo support.
        // want batch operations
        // want plugin architecture.
        // want create macro or composite commands.

        //pros
        // decouples sender and receiver
        //suports undo redo
        // easily extensible and reusable

        //cons
        // increases number of classes
        // can add unnecessary complexity for simpler tasks
        // requires coupled designs for undo/redo.
    }
}
