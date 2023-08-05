package de.interguess.igdatastores;

import de.interguess.igdatastores.sort.SortType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Queries are used to select objects from a datastore.
 * The query can be used to filter the objects.
 *
 * @param <T> the type of the objects in the datastore and the query
 */
public interface Query<T> {

    /**
     * Adds a field requirement to the query.
     * The field requirement must be fulfilled by the object to be selected.
     *
     * @param field the name of the field which must be fulfilled
     * @return the query with the added field requirement
     */
    @NotNull Field<T> field(@NotNull String field);

    /**
     * Adds a field requirement to the query.
     * The field requirement must be fulfilled by the object to be selected.
     *
     * @param requirement the field requirement which must be fulfilled
     * @return the query with the added field requirement
     */
    @NotNull Query<T> field(@NotNull Field<T> requirement);

    /**
     * Sets the field name to sort the result by.
     * The result will be sorted by the sortType.
     *
     * @param field    the name of the field to sort the result by (Must be a number field)
     * @param sortType the sort type to sort the result by
     * @return the query with the added sort requirement
     */
    @NotNull Query<T> sorted(@NotNull String field, SortType sortType);

    /**
     * Sets the limit of objects to be returned by the query.
     * The limit must be greater than 0.
     *
     * @param limit the limit of objects to be returned by the query
     * @return the query with the added limit
     */
    @NotNull Query<T> limit(int limit);

    /**
     * Returns all found objects matching the query.
     * If no objects are found, an empty list is returned.
     *
     * @return all found objects matching the query
     */
    @NotNull List<T> list();

    /**
     * Returns the first found object matching the query.
     * If no object is found, null is returned.
     *
     * @return the first found object matching the query
     */
    @Nullable T get();

    /**
     * Deletes all objects matching the query from the datastore.
     * If no objects are found, nothing is deleted.
     */
    void delete();

    /**
     * Returns the amount of objects matching the query.
     * If no objects are found, 0 is returned.
     *
     * @return the number of objects matching the query
     */
    int amount();
}
