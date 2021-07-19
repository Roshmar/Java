package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health {

    private int initHealth;
    private int maxHealth;
    private List<ExhaustionEffect> effects;
    private boolean called;

    public Health(int initHealth, int maxHealth){
        this.initHealth = initHealth;
        this.maxHealth = maxHealth;
        effects = new ArrayList<>();
        called =false;
    }

    public Health(int health){
        this(health, health);
    }

    public void exhaust(){
        setInitHealth(0);
        if (!called){
            effects.forEach(ExhaustionEffect::apply);
            setCalledTrue();
        }

    }

    public int getValue() {
        return initHealth;
    }

    public void refill(int amount){
        if (getSum(initHealth,amount) > maxHealth){
            setInitHealth(maxHealth);
        } else{
            setInitHealth(getSum(initHealth,amount));
        }
    }

    public void restore(){
        initHealth = maxHealth;
    }
    public boolean isDead(){
            if(getValue() <= 0){
                return true;
            }else {
                return false;
            }
    }
    public void drain(int amount){
        if (getSub(initHealth,amount) <= 0){
            exhaust();
        }else{
            setInitHealth(getSub(initHealth,amount));
        }
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setInitHealth( int count){
        initHealth = count;
    }

    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    public void onExhaustion(ExhaustionEffect effect){
        effects.add(effect);
    }

    private void setCalledTrue(){
        called = true;
    }
    private int getSum(int a,int b){
        return a+b;
    }
    private int getSub(int a, int b){
        return a-b;}
}
