package de.igweb.igdatastores;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import de.igweb.igdatastores.snapshot.DataStoreSnapshot;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataStore<D> {

    private static final Gson GSON = new Gson();

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
     * Saves all given objects to the DataStore.
     *
     * @param object The objects to save.
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
     * @param query The Query to check.
     * @return A list of all objects in the DataStore that match the given query.
     */
    public List<D> get(Query<D> query) {
        return data.stream().filter(query::allConditionsTrue).toList();
    }

    /**
     * Clears all objects from the DataStore that match with the given Query.
     *
     * @param query The Query with the conditions.
     */
    public void clear(Query<D> query) {
        get(query).forEach(data::remove);
    }

    /**
     * @param query The Query with the conditions.
     * @return whether the DataStore contains at least one object that matches with the given Query.
     */
    public boolean containsAny(Query<D> query) {
        if (get(query) == null) return false;
        return !get(query).isEmpty();
    }

    /**
     * Creates a snapshot of the DataStore.
     *
     * @return A snapshot of the DataStore.
     */
    public DataStoreSnapshot createSnapshot() {
        try {
            return DataStoreSnapshot.builder()
                    .id(System.currentTimeMillis() * this.hashCode())
                    .timestamp(System.currentTimeMillis())
                    .dataClassName(data.get(0).getClass().getName())
                    .data(GSON.toJson(data))
                    .build();
        } catch (Exception exception) {
            throw new RuntimeException("Failed to create snapshot!", exception);
        }
    }

    /**
     * Restores the DataStore from the given snapshot.
     *
     * @param snapshot The snapshot to restore from.
     */
    public void loadSnapshot(DataStoreSnapshot snapshot) {
        try {
            JsonArray jsonArray = new JsonArray();
            jsonArray.addAll(GSON.fromJson(snapshot.getData(), JsonArray.class));
            jsonArray.asList().forEach(element -> {
                try {
                    data.add(GSON.fromJson(element, (Type) Class.forName(snapshot.getDataClassName())));
                } catch (Exception exception) {
                    throw new RuntimeException("Failed to load snapshot!", exception);
                }
            });
        } catch (Exception exception) {
            throw new RuntimeException("Failed to load snapshot!", exception);
        }
    }

}
