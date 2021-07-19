package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;


public class DefectiveLight extends Light implements Repairable{

    private Disposable disposable;
    private boolean blinking;

    public DefectiveLight(){
        super();
        blinking = false;
    }
    public void defectiveLight() {
        if (((int) (Math.random() * (20 - 1 + 1)) + 1) == 1) {
            super.toggle();
        }
    }

    private void changeBlinking(){
        this.blinking = !blinking;
    }


    @Override
    public boolean  repair() {
        if(!blinking){
            disposable.dispose();
            blinking = true;
            new ActionSequence<>(
                    new Wait<>(10),
                    new Invoke<>(this::setDisposable),
                    new Invoke<>(this::changeBlinking)
            ).scheduleFor(this);
            return true;
        }
        return false;
    }

    public void setDisposable(){
        disposable =  new Loop<>(new Invoke<>(this::defectiveLight)).scheduleFor(this);
    }

    @Override
    public boolean extinguish() {
        return false;
    }

    @Override
    public void addedToScene(Scene scene) {
        super.addedToScene(scene);
        setDisposable();
    }

}
