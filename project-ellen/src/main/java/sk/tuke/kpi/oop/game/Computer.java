package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {
    private boolean computerPower;
    public Computer() {
        computerPower = false;
        setAnimation(new Animation("sprites/computer.png", 80, 48, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
    }

    public int add(int a, int b){
        if(computerPower){
            return a+b;
        }
        return 0;
    }
    public float add(float a, float b){
        if(computerPower){
            return a+b;
        }
        return 0;}
    public int sub(int a, int b){
        if(computerPower){
            return a-b;
        }
        return 0;
    }
    public float sub(float a, float b){
        if(computerPower){
            return a-b;
        }
        return 0;
    }

    @Override
    public void setPowered(boolean computerPower){
        this.computerPower = computerPower;
    }

    @Override
    public void turnOff() {
        this.computerPower = false;
    }

    public boolean isComputerPower() {
        return computerPower;
    }
}
