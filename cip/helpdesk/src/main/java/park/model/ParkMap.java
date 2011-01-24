package park.model;

import park.model.auto._ParkMap;

public class ParkMap extends _ParkMap {

    private static ParkMap instance;

    private ParkMap() {}

    public static ParkMap getInstance() {
        if(instance == null) {
            instance = new ParkMap();
        }

        return instance;
    }
}
