package de.igweb.igdatastores;

import java.util.ArrayList;
import java.util.List;

public class DataStore<D> {

    private final List<D> data = new ArrayList<>();

    public Query<D> createQuery() {
        return new Query<>(this);
    }

    public void save(D object) {
        data.add(object);
        onDataStored(object);
    }

    public List<D> get(Query<D> query) {
        List<D> result = new ArrayList<>();
        for (D object : data) {
            if (query.allConditionsTrue(object)) {
                result.add(object);
            }
        }
        onDataRetrieved(query, result);
        return result;
    }

    public native void onDataStored(D data);

    public native void onDataRetrieved(Query<D> query, List<D> data);

}
