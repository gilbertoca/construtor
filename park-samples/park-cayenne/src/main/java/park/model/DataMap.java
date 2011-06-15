package park.model;

import park.model.auto._DataMap;

public class DataMap extends _DataMap {

    private static DataMap instance;

    private DataMap() {}

    public static DataMap getInstance() {
        if(instance == null) {
            instance = new DataMap();
        }

        return instance;
    }
}
