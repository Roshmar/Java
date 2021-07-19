package sk.tuke.kpi.oop.game.openables;


import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;

public class Door extends AbstractActor implements Openable, Usable<Actor> {

    private Animation animation;
    private int sixteen = 16;
    private boolean opened;
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);
    public enum Orientation {  HORIZONTAL, VERTICAL}


    private Orientation orientation;

    public Door(String name, Orientation orientation){
        super(name);
        getHelpAnimatin(orientation);
        this.orientation = orientation;
        setAnimation(animation);
        animation.stop();
        opened = false;
    }

    @Override
    public void useWith(Actor actor) {
        if (opened){
            close();
        }else{
            open();
        }
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }

    @Override
    public void open() {
        animation.setPlayMode(Animation.PlayMode.ONCE_REVERSED);
        animation.play();
        animation.stop();
        opened = true;
        int x = this.getPosX() / sixteen;
        int y = this.getPosY() / sixteen;

        if(getScene() != null){
            getScene().getMap().getTile(x,y).setType(MapTile.Type.CLEAR);
            if (orientation == Orientation.VERTICAL) {
                getScene().getMap().getTile(x, y + 1).setType(MapTile.Type.CLEAR);
            }else{
                getScene().getMap().getTile(x+1,y).setType(MapTile.Type.CLEAR);
            }
            getScene().getMessageBus().publish(DOOR_OPENED, this);
        }
    }

    @Override
    public void close() {
        animation.setPlayMode(Animation.PlayMode.ONCE);
        animation.play();
        animation.stop();
        opened = false;
        if(getScene() != null){
           helpFunction();
        }
    }

    @Override
    public boolean isOpen() {
        return opened;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        if(getScene() != null){
            getScene().getMessageBus().publish(DOOR_CLOSED, this);
           helpFunction();
        }
    }

    private void helpFunction(){
        getScene().getMap().getTile(this.getPosX()/sixteen,this.getPosY()/sixteen).setType(MapTile.Type.WALL);
        if (orientation == Orientation.VERTICAL) {
            getScene().getMap().getTile(this.getPosX() / sixteen, this.getPosY() / sixteen + 1).setType(MapTile.Type.WALL);
        }else{
            getScene().getMap().getTile(this.getPosX()/sixteen+1,this.getPosY()/sixteen).setType(MapTile.Type.WALL);
        }
    }
    private void getHelpAnimatin(Orientation orientation){
        if (orientation == Orientation.VERTICAL) {
            animation = new Animation("sprites/vdoor.png", 16, 32, 0.2f);
        }else{
            animation = new Animation("sprites/hdoor.png", 32, 16, 0.2f);
        }
    }

}
