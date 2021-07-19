package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;



public class PowerSwitch extends AbstractActor {
    private Switchable switchable;

    public PowerSwitch(Switchable switchable){
        this.switchable = switchable;
        setAnimation(new Animation("sprites/switch.png"));
    }
    public Switchable getDevice(){
        return switchable;
    }

    public void switchOn(){
        if(switchable!= null){
            switchable.turnOn();
        }
    }

    public void switchOff(){
        if (switchable != null){
            switchable.turnOff();
        }
    }
}
