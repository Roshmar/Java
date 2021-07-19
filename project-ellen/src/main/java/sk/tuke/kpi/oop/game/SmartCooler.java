package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class SmartCooler extends Cooler {


    public SmartCooler(Reactor reactor) {
        super(reactor);
        setAnimation(new Animation("sprites/fan.png", 32, 32, 0.2f, Animation.PlayMode.ONCE));
    }


    private void smartCoolerReactor(){
        if(getReactor() != null){
            if(getReactor().getTemperature()> 2500){
                super.turnOn();
            }
            if (getReactor().getTemperature() < 1500){
                super.turnOff();
            }
            super.coolReactor();
        }
    }

    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::smartCoolerReactor)).scheduleFor(this);
    }
}
