package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.Repairable;

public class FireExtinguisher extends BreakableTool<Repairable> implements Collectible {
    public FireExtinguisher() {
        super(1);
        setAnimation(new Animation("sprites/extinguisher.png"));
    }

   @Override
    public void useWith(Repairable repairable){
        if(repairable!=null && repairable.extinguish() && getRemainingUses()>0 ){
            super.useWith(repairable);
        }
    }

    @Override
    public Class<Repairable> getUsingActorClass() {
        return Repairable.class;
    }

}
