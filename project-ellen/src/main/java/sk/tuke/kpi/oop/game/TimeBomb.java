package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;




public class TimeBomb extends AbstractActor {
    private float time_bomb;
    private boolean state_bomb;
    private Disposable disposable;

//    private Timer time;

    public TimeBomb(){
        time_bomb = (float)10;
        state_bomb = false;
        setAnimation(new Animation( "sprites/bomb.png"));
    }

    private void  changeState(){
        this.state_bomb = !state_bomb;
    }

    public void activate() {
        state_bomb = true;
        setAnimation(new Animation("sprites/bomb_activated.png", 16, 16, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
        if (isActivated()) {
            disposable.dispose();
            state_bomb = false;
            new ActionSequence<>(
                    new Wait<>(10),
                    new Invoke<>(this::setDisposable),
                    new Invoke<>(this::changeState)
            ).scheduleFor(this);
            setAnimation(new Animation("sprites/small_explosion.png", 16, 16, 0.1f, Animation.PlayMode.LOOP_PINGPONG));

        }
    }
    public boolean isActivated(){
        return state_bomb;
    };
    public void setDisposable(){
        disposable =  new Loop<>(new Invoke<>(this::activate)).scheduleFor(this);
    }

    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        setDisposable();
    }

}
