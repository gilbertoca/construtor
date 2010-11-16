package com.google.constructor.extras.security.cayenne.domain;

import com.google.constructor.extras.security.cayenne.domain.auto._CayenneDomainMap;

public class CayenneDomainMap extends _CayenneDomainMap {

    private static CayenneDomainMap instance;

    private CayenneDomainMap() {}

    public static CayenneDomainMap getInstance() {
        if(instance == null) {
            instance = new CayenneDomainMap();
        }

        return instance;
    }
}
