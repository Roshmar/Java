//package sk.tuke.kpi.oop.game.behaviours;
//
//
//import sk.tuke.kpi.gamelib.Actor;
//import sk.tuke.kpi.gamelib.Disposable;
//import sk.tuke.kpi.gamelib.actions.ActionSequence;
//import sk.tuke.kpi.gamelib.actions.Invoke;
//import sk.tuke.kpi.gamelib.actions.Wait;
//import sk.tuke.kpi.gamelib.framework.actions.Loop;
//import sk.tuke.kpi.oop.game.Direction;
//import sk.tuke.kpi.oop.game.Movable;
//import sk.tuke.kpi.oop.game.actions.Move;
//
//import java.util.Random;
//
//public class RandomlyMoving<A extends Movable> implements Behaviour<A>{
//
//    private Movable movable;
//    private Disposable disposable;
//
//    public RandomlyMoving(Movable movable){
//        this.movable = movable;
//    }
//
//    @Override
//    public void setUp(A actor) {
//        if(actor != null){
//            movable.startedMoving(Direction.NONE);
//            loopSetUp();
//        }
//    }
//
//    private Direction randomMove(){
//        int random = new Random().nextInt(4);
//        return helpDirection(random);
//    }
//
//    private Direction helpDirection(int value){
//        if(value == 0){
//            return Direction.EAST;
//        }
//        if(value == 1){
//            return Direction.SOUTH;
//        }
//        if(value == 2)
//            return Direction.WEST;
//        if(value == 3)
//            return Direction.NORTH;
//        return Direction.NONE;
//    }
//    private void loopSetUp(){
//        new Loop<>(
//            new ActionSequence<>(
//                new Wait<>(1),
//                new Invoke<Actor>(() -> {
//                    if (disposable != null)
//                        disposable.dispose();
//                    disposable = new Move<Movable>(randomMove(), 99999999f).scheduleFor(movable);
//
//                })
//            )
//        ).scheduleFor(movable);
//    }
//}
