package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.gamelib.map.MapMarker;
import sk.tuke.kpi.oop.game.Cooler;
import sk.tuke.kpi.oop.game.DefectiveLight;
import sk.tuke.kpi.oop.game.Light;
import sk.tuke.kpi.oop.game.Reactor;

import java.util.Map;

public class TrainingGameplay extends Scenario {


    @Override
    public void setupPlay(@NotNull Scene scene) {
        Reactor reactor = new Reactor();

        Map<String, MapMarker> markers = scene.getMap().getMarkers();
        // ziskanie referencie na marker nazvany "reactor-area-1"
        MapMarker reactorArea1 = markers.get("reactor-area-1");

        //umiestnenie reaktora na poziciu markera
        scene.addActor(reactor, reactorArea1.getPosX(), reactorArea1.getPosY());
        reactor.turnOn();

        MapMarker coolerArea1 = markers.get("cooler-area-1");
        Cooler cooler1 = new Cooler(reactor);
        scene.addActor(cooler1, coolerArea1.getPosX(), coolerArea1.getPosY());

        MapMarker coolerArea2 = markers.get("cooler-area-2");
        Cooler cooler2 = new Cooler(reactor);
        scene.addActor(cooler2, coolerArea2.getPosX(), coolerArea2.getPosY());

        Light light = new DefectiveLight();
        scene.addActor(light, 60, 40);
        reactor.addDevice(light);
        light.toggle();

        new ActionSequence<>(
                new Wait<>(5),
                new Invoke<>(cooler1::turnOn),
                new Invoke<>(cooler2::turnOn)
        ).scheduleFor(cooler1);

    }
}
