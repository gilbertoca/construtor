package helpdesk.model;

import helpdesk.model.auto._HelpDeskDataMap;

public class HelpDeskDataMap extends _HelpDeskDataMap {

    private static HelpDeskDataMap instance;

    private HelpDeskDataMap() {}

    public static HelpDeskDataMap getInstance() {
        if(instance == null) {
            instance = new HelpDeskDataMap();
        }

        return instance;
    }
}
