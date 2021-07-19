package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;

import java.util.HashSet;
import java.util.Set;


public class Reactor extends AbstractActor implements Switchable,Repairable {

    private double temperature;
    private int damage;
    private boolean state;
    private Set<EnergyConsumer> devices;


    private Animation reactor_off = new Animation("sprites/reactor.png", 80, 80);
    private Animation reactor_on = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation reactor_hot = new Animation("sprites/reactor_hot.png", 80, 80,0.2f,Animation.PlayMode.LOOP_PINGPONG);
    private Animation reactor_broken = new Animation("sprites/reactor_broken.png",80,80,0.1f,Animation.PlayMode.LOOP_PINGPONG);


    public Reactor() {
        temperature = 0;
        state = true;
        devices = new HashSet<EnergyConsumer>();
        setAnimation(reactor_on);
    }

    public int getTemperature() {
        return (int)temperature;
    }

    public int getDamage() {
        return damage;
    }
    public  boolean getStatus(){return state;}

    public void increaseTemperature(int increment) {
        if(increment > 0 && state ){
            temperature += increment;
            if(temperature>2000){
                if(temperature > 3320) {
                    temperature += increment * 0.5;
                    if(temperature > 4640){
                        temperature += increment * 0.5;
                    }
                }
                if((int) Math.ceil(((temperature - 2000) / 40)) > damage){
                    damage = (int) Math.ceil(((temperature - 2000) / 40));
                }
            }
            if(temperature >= 6000 || damage == 100){
                state = false;
                temperature = 6000;
            }
            updateAnimation();
        }
    }


    public void decreaseTemperature( int decrement){
        if(decrement > 0 && state && (temperature > 0 && temperature < 6000)){
            if(damage < 50){
                temperature -= decrement;
            }else if(damage < 100){
                temperature -= (double)decrement/2;
            }
            updateAnimation();
        }
    }
    public void updateAnimation(){
        if(temperature <= 4000 && !getStatus() && (getAnimation() != reactor_off)){
            setAnimation(reactor_off);
        }
        if(temperature <= 4000 && getStatus() && (getAnimation() != reactor_on)){
            setAnimation(reactor_on);
        }
        if(temperature > 4000 && temperature < 6000 && (getAnimation() != reactor_hot)){
            setAnimation(reactor_hot);
        }
        if(temperature >= 6000 || damage >= 100  && (getAnimation() != reactor_broken)){
            setAnimation(reactor_broken);
        }
    }


    public void addDevice(EnergyConsumer energyConsumer){
        if((energyConsumer != null)){
            if(isOn()){
                devices.add(energyConsumer);
                energyConsumer.setPowered(true);
            }else{
                devices.add(energyConsumer);
                energyConsumer.setPowered(false);
                energyConsumer.turnOff();
            }
        }
    }
    public void removeDevice(EnergyConsumer energyConsumer){
        devices.remove(energyConsumer);
        energyConsumer.setPowered(false);
        energyConsumer.turnOff();
    }

    @Override
    public void turnOn(){
        this.state = true;
        updateAnimation();
    }

    @Override
    public void  turnOff(){
        this.state = false;
        updateAnimation();
    }

    @Override
    public boolean isOn() {
        return this.state;
    }


    @Override
    public boolean repair(){
        if (damage > 0 && damage < 100){
            if(damage>50){
                damage -= 50;
            }else{
                damage = 0;
            }
            temperature = damage*40+2000;
            return true;
        }
        return false;
    }

    @Override
    public boolean extinguish(){
        if(damage >= 100){
            setAnimation(new Animation("sprites/reactor_extinguished.png"));
            temperature = 4000;
            return true;
        }
        return false;
    }

    @Override
    public void addedToScene(Scene scene){
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);
    }

}


