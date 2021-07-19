package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.openables.Door;


public class MissionImpossible implements SceneListener {

    private Ripley ellen;


    @Override
    public void sceneInitialized(Scene scene) {
//        for (Actor actor : scene.getActors()){
//            if (actor.getClass() == Movable.class){
//                MovableController key = new MovableController((Movable) actor);
//                scene.getInput().registerListener(key);
//            }
//        }
//        for (Actor actor : scene.getActors()){
//            if (actor.getClass() == Keeper.class){
//                CollectorController collectorController = new CollectorController((Keeper<Collectible>) actor);
//                scene.getInput().registerListener(collectorController);
//            }
//        }
//        ellen.getClass().cast(ellen.getContainer());
//        Hammer hammer = new Hammer();
        //AccessCard card = new AccessCard();
        //scene.addActor(card, 120, 64);


        ellen = scene.getFirstActorByType(Ripley.class);


//        ellen.getContainer().add(hammer);
//        ellen.getContainer().add(card);

        scene.getGame().pushActorContainer(ellen.getBackpack());
        scene.follow(ellen);


        MovableController movableController = new MovableController(ellen);
        Disposable movController = scene.getInput().registerListener(movableController);

        KeeperController keeperController = new KeeperController(ellen);
        Disposable colController = scene.getInput().registerListener(keeperController);

        Disposable zivot = scene.getMessageBus().subscribe(Door.DOOR_OPENED, (Door) -> new Loop<>(new ActionSequence<>(new Invoke<Actor>(() -> ellen.getHealth().drain(50)), new Wait<>(1))).scheduleOn(scene));



        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> movController.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> colController.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> new ActionSequence<>(new Invoke<Actor>(zivot::dispose),
                new Invoke<Actor>(() -> scene.getGame().getOverlay().drawText("Ripley Died!", 100, 100).showFor(2)),
                new Wait<>(2)));



//

//        scene.getMessageBus().subscribe(Door.DOOR_OPENED, (Ripley) -> ellen.getScene().scheduleAction(new Loop<>(new ActionSequence<>(new Wait<>(1), ellen.setEnergy(health)))));
    }

    @Override
    public void sceneUpdating(Scene scene) {
        ellen.showRipleyState();
    }

    public static class Factory implements ActorFactory {

        @Nullable
        @Override
        public Actor create(String type, String name) {
            if (name.equals("ellen")) return new Ripley();
            if (name.equals("door")) return new Door("door", Door.Orientation.VERTICAL);
            if (name.equals("access card")) return new AccessCard();
            if (name.equals("ventilator")) return new Ventilator();
            else return null;
        }
    }
}
