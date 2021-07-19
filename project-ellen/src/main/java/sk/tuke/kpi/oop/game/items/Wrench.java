package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;
import sk.tuke.kpi.oop.game.Repairable;

public class Wrench extends BreakableTool<Repairable> implements Collectible{
    public Wrench() {
        super(2);
        setAnimation(new Animation("sprites/wrench.png"));
    }
    @Override
    public void useWith(Repairable repairable){
        if(repairable!=null && repairable.repair() && getRemainingUses()>0 ){
            super.useWith(repairable);
        }
    }

    @Override
    public Class<Repairable> getUsingActorClass() {
        return Repairable.class;
    }
}
