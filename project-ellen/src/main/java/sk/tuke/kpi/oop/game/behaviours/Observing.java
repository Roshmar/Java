//package sk.tuke.kpi.oop.game.behaviours;
//
//import sk.tuke.kpi.gamelib.Actor;
//import sk.tuke.kpi.gamelib.messages.Topic;
//
//import java.util.function.Predicate;
//
//public class Observing <A extends Actor,T> implements Behaviour<A> {
//
//    private Topic<T> topic;
//    private Predicate<T> predicate;
//    private Behaviour<A> behaviour;
//
//    public Observing(Topic<T> topic, Predicate<T> predicate,Behaviour<A> behaviour){
//        this.topic = topic;
//        this.predicate = predicate;
//        this.behaviour = behaviour;
//    }
//
//    @Override
//    public void setUp(A actor) {
//        if(actor != null){
////
//        }
//    }
//}
