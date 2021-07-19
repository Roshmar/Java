package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;


public class Cooler extends AbstractActor implements Switchable {

    private boolean isRunning;
    private Reactor reactor;
    private Animation fan_on = new Animation("sprites/fan.png", 32, 32, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation fan_off = new Animation("sprites/fan.png", 32, 32, 0.2f, Animation.PlayMode.ONCE);


    public Cooler(Reactor reactor) {
        this.reactor = reactor;
        isRunning = false;
        setAnimation(fan_off);
    }

    public void setReactor(Reactor reactor){
        this.reactor = reactor;
    }
    public Reactor getReactor(){
        return reactor;
    }

    @Override
    public boolean isOn() {
        return isRunning;
    }

    @Override
    public void turnOn() {
        isRunning = true;
    }

    @Override
    public void turnOff() {
        isRunning = false;
    }

    public void coolReactor() {
        if(reactor != null){
            if (isOn() && this.reactor.getTemperature() < 6000) {
                this.reactor.decreaseTemperature(1);
                if(getAnimation().getPlayMode().equals(fan_off.getPlayMode())){
                    setAnimation(fan_on);
                }
            } else {
                if (getAnimation().getPlayMode().equals(fan_on.getPlayMode())){
                    setAnimation(fan_off);
                }
            }
        }
    }

    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }
}
