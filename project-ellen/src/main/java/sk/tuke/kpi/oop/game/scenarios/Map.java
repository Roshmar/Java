package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorFactory;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.Cooler;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.*;
import sk.tuke.kpi.oop.game.items.*;
import sk.tuke.kpi.oop.game.openables.Door;



public class Map implements SceneListener {

    private Ripley ellen;

    @Override
    public void sceneInitialized(Scene scene) {
        ellen = scene.getFirstActorByType(Ripley.class);
        scene.follow(ellen);

        scene.getGame().pushActorContainer(ellen.getBackpack());

        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> scene.getGame().getOverlay().drawText("Ripley Died!", 100, 100));
        scene.getGame().pushActorContainer(ellen.getBackpack());


    }

    @Override
    public void sceneUpdating(Scene scene) {
        ellen.showRipleyState();
    }

    @Override
    public void sceneCreated(Scene scene) {

    }

    public static class Factory implements ActorFactory {

        Reactor reactor;
        @Nullable
        @Override
        public Actor create(String type, String name) {
            if (name.equals("ellen"))                   return new Ripley();
            if (name.equals("Vertical door"))           return new Door("door", Door.Orientation.VERTICAL);
            if (name.equals("Horizontal door"))         return new Door("door", Door.Orientation.HORIZONTAL);
            if (name.equals("access card"))             return new AccessCard();
            if (name.equals("ventilator"))              return new Ventilator();
            if (name.equals("energy"))                  return new Energy();
            if (name.equals("ammo"))                    return new Ammo();
            if (name.equals("alien"))                   return new Alien();
            if (name.equals("reactor")){               reactor = new Reactor(); return reactor; }
            if (name.equals("cooler"))                  return new Cooler(reactor);
            if (name.equals("hummer"))                  return new Hammer();
            if (name.equals("FireExtinguisher"))        return new FireExtinguisher();
            else
                return null;
        }
    }
}
