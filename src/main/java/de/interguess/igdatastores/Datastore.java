package de.interguess.igdatastores;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A datastore is a collection of objects which can be saved, queried and deleted.
 *
 * @param <T> the type of the objects in the datastore
 */
public interface Datastore<T> {

    /**
     * Saves the given data to the datastore.
     * The data will be appended to the existing data.
     *
     * @param data the data to save to the datastore
     */
    void saveAll(@NotNull T... data);

    /**
     * Deletes the given data from the datastore.
     * If the data is not found, nothing is deleted.
     *
     * @param data the data to delete from the datastore
     */
    void deleteAll(@NotNull T... data);

    /**
     * Creates a new query for the datastore.
     * The query can be used to select objects from the datastore.
     *
     * @return the query for the datastore
     */
    @NotNull Query<T> createQuery();

    /**
     * Returns all objects from the datastore.
     * You can use the {@link Query} to filter the objects.
     *
     * @return all objects from the datastore
     */
    @NotNull List<T> listAll();

    /**
     * Deletes all objects from the datastore.
     * If no objects are found, nothing is deleted.
     */
    void deleteAll();

    /**
     * Returns the number of objects in the datastore.
     * If no objects are found, 0 is returned.
     *
     * @return the number of objects in the datastore
     */
    int size();
}
