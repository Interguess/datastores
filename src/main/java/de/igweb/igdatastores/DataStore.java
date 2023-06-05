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
     * Saves the given objects to the DataStore.
     *
     * @param objects The objects to save.
     */
    @SafeVarargs
    public final void save(D... objects) {
        data.addAll(List.of(objects));
    }

    /**
     * Clears the DataStore.
     */
    public void clearAll() {
        data.clear();
    }

    /**
     * Gets a list of all objects in the DataStore matching with the given Query.
     *
     * @param query The Query with the conditions.
     * @return A list of all objects in the DataStore matching with the given Query.
     */
    public List<D> list(Query<D> query) {
        return data.stream().filter(q -> ConditionalChecker.checkAll(query.getRequirements(), q)).toList();
    }

    /**
     * Gets the first object in the DataStore that matches with the given Query.
     *
     * @param query The Query with the conditions.
     * @return The first object in the DataStore that matches with the given Query.
     */
    public D get(Query<D> query) {
        return list(query).get(0);
    }

    /**
     * Gets all objects in the DataStore.
     *
     * @return A list of all objects in the DataStore.
     */
    public List<D> list() {
        return data;
    }

    /**
     * Clears all objects from the DataStore that match with the given Query.
     *
     * @param query The Query with the conditions.
     */
    public void clear(Query<D> query) {
        list(query).forEach(data::remove);
    }

    /**
     * @param query The Query with the conditions.
     * @return whether the DataStore contains at least one object that matches with the given Query.
     */
    public boolean containsAny(Query<D> query) {
        return !list(query).isEmpty();
    }
}
