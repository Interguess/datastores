package de.igweb.igdatastores;

import java.util.ArrayList;
import java.util.List;

public class DataStore<D> {

    private final List<D> data = new ArrayList<>();

    /**
     * Creates a new Query for this DataStore.
     *
     * @return The new Query.
     */
    public Query<D> createQuery() {
        return new Query<>(this);
    }

    /**
     * Saves the given object to the DataStore.
     *
     * @param object The object to save.
     */
    public void save(D object) {
        data.add(object);
    }

    /**
     * @return A list of all objects in the DataStore that match the given query.
     */
    public List<D> get(Query<D> query) {
        List<D> result = new ArrayList<>();
        for (D object : data) {
            if (query.allConditionsTrue(object)) {
                result.add(object);
            }
        }
        return result;
    }

}
