package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;



public abstract class BreakableTool <T extends Actor> extends AbstractActor implements Usable<T>{
    private int remainingUses;

    public BreakableTool(int remainingUses){
        this.remainingUses = remainingUses;
    }
    public int getRemainingUses(){return remainingUses;}
    public void setRemainingUses(int remainingUses){
        this.remainingUses = remainingUses;
    }
    @Override
    public void useWith(T actor) {
        if (this.remainingUses > 0) {
            this.remainingUses--;
        }
        if(this.remainingUses <=0) {
            Scene scene = getScene();
            assert scene != null;
            scene.removeActor(this);
            System.gc();
        }
    }

    public int getCount(){ return remainingUses;}

}
