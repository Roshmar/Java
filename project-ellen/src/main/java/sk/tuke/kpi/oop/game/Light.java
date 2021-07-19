package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Light extends AbstractActor implements Switchable,EnergyConsumer{

    private Animation light_on = new Animation("sprites/light_on.png");
    private Animation light_off = new Animation("sprites/light_off.png");
    private boolean electricityInLight;
    private boolean state;

    public Light() {
        state = false;
        electricityInLight = false;
        setAnimation(light_off);
    }


    public void toggle(){
        if(isOn()){
            turnOff();
        }else{
            turnOn();
        }
    }

    public boolean isElectricityInLight(){
        return electricityInLight;
    }
    @Override
    public void turnOn() {
        if (isElectricityInLight()) {
            setAnimation(light_on);
        }
        state = true;
    }

    @Override
    public void setPowered(boolean computerPower) {
        this.electricityInLight= computerPower;
        if(isOn() && electricityInLight){
            setAnimation(light_on);
        }
    }

    @Override
    public void turnOff() {
        setAnimation(light_off);
        state = false;
    }

    @Override
    public boolean isOn() {
        return state;
    }

}
