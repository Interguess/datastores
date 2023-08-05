package de.interguess.igdatastores;

import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

/**
 * Represents a field in a query.
 * The field can be used to add conditions to the query.
 *
 * @param <T> the type of the objects in the datastore and the query
 */
public interface Field<T> {

    /**
     * Returns the name of the field.
     *
     * @return the name of the field
     */
    @NotNull String getName();

    /**
     * Returns the condition of the field.
     *
     * @return the condition which must be fulfilled to select the object in the query
     */
    @NotNull Predicate<Object> getCondition();

    /**
     * Adds the equal condition to the field.
     * The field value must be equal to the given value.
     *
     * @param value the value which the field value must be equal to
     * @return the query with the added condition
     */
    @NotNull Query<T> equal(@NotNull Object value);

    /**
     * Adds the equalIgnoreCase condition to the field.
     * The field value must be equal to the given value ignoring the case.
     *
     * @param value the value which the field value must be equal to ignoring the case
     * @return the query with the added condition
     */
    @NotNull Query<T> equalIgnoreCase(@NotNull String value);

    /**
     * Adds the notEqual condition to the field.
     * The field value must not be equal to the given value.
     *
     * @param value the value which the field value must not be equal to
     * @return the query with the added condition
     */
    @NotNull Query<T> notEqual(@NotNull Object value);

    /**
     * Adds the greaterThan condition to the field.
     *
     * @param value the value which the field value must be greater than (must be a number)
     * @return the query with the added condition
     */
    @NotNull Query<T> greaterThan(@NotNull Number value);

    /**
     * Adds the greaterOrEqual condition to the field.
     * The field value must be greater than or equal to the given value.
     *
     * @param value the value which the field value must be greater than or equal to (must be a number)
     * @return the query with the added condition
     */
    @NotNull Query<T> greaterOrEqual(@NotNull Number value);

    /**
     * Adds the lessThan condition to the field.
     * The field value must be less than the given value.
     *
     * @param value the value which the field value must be less than (must be a number)
     * @return the query with the added condition
     */
    @NotNull Query<T> lessThan(@NotNull Number value);

    /**
     * Adds the lessOrEqual condition to the field.
     * The field value must be less than or equal to the given value.
     *
     * @param value the value which the field value must be less than or equal to (must be a number)
     * @return the query with the added condition
     */
    @NotNull Query<T> lessOrEqual(@NotNull Number value);

    /**
     * Adds the between condition to the field.
     * The field value must be between the given values.
     *
     * @param value1 the first value which the field value must be between (must be a number)
     * @param value2 the second value which the field value must be between (must be a number)
     * @return the query with the added condition
     */
    @NotNull Query<T> between(@NotNull Number value1, @NotNull Number value2);

    /**
     * Adds the contains (String) condition to the field.
     * The field value (String) must contain the given String value.
     *
     * @param value the string which the field value must contain
     * @return the query with the added condition
     */
    @NotNull Query<T> contains(@NotNull String value);

    /**
     * Adds the contains (List) condition to the field.
     * The field value (List) must contain the given object.
     *
     * @param object the object which the field value must contain
     * @return the query with the added condition
     */
    @NotNull Query<T> listContains(@NotNull Object object);

    /**
     * Adds the contains (Map) condition to the field.
     * The field value (Map) must contain the given object as key.
     *
     * @param object the object which the field value must contain as key
     * @return the query with the added condition
     */
    @NotNull Query<T> mapContainsKey(@NotNull Object object);

    /**
     * Adds the contains (Map) condition to the field.
     * The field value (Map) must contain the given object as value.
     *
     * @param object the object which the field value must contain as value
     * @return the query with the added condition
     */
    @NotNull Query<T> mapContainsValue(@NotNull Object object);

    /**
     * Adds the containsIgnoreCase condition to the field.
     * The field value (String) must contain the given String value ignoring the case.
     *
     * @param value the string which the field value must contain ignoring the case
     * @return the query with the added condition
     */
    @NotNull Query<T> containsIgnoreCase(@NotNull String value);

    /**
     * Adds the startsWith condition to the field.
     * The field value (String) must start with the given String value.
     *
     * @param value the string which the field value must start with
     * @return the query with the added condition
     */
    @NotNull Query<T> startsWith(@NotNull String value);

    /**
     * Adds the startsWithIgnoreCase condition to the field.
     * The field value (String) must start with the given String value ignoring the case.
     *
     * @param value the string which the field value must start with ignoring the case
     * @return the query with the added condition
     */
    @NotNull Query<T> startsWithIgnoreCase(@NotNull String value);

    /**
     * Adds the endsWith condition to the field.
     * The field value (String) must end with the given String value.
     *
     * @param value the string which the field value must end with
     * @return the query with the added condition
     */
    @NotNull Query<T> endsWith(@NotNull String value);

    /**
     * Adds the endsWithIgnoreCase condition to the field.
     * The field value (String) must end with the given String value ignoring the case.
     *
     * @param value the string which the field value must end with ignoring the case
     * @return the query with the added condition
     */
    @NotNull Query<T> endsWithIgnoreCase(@NotNull String value);

    /**
     * Adds the isNull condition to the field.
     * The field value must be null.
     *
     * @return the query with the added condition
     */
    @NotNull Query<T> isNull();

    /**
     * Adds the isNotNull condition to the field.
     * The field value must not be null.
     *
     * @return the query with the added condition
     */
    @NotNull Query<T> isNotNull();

    /**
     * Adds the isTrue condition to the field.
     * The field value (Boolean) must be true.
     *
     * @return the query with the added condition
     */
    @NotNull Query<T> isTrue();

    /**
     * Adds the isFalse condition to the field.
     * The field value (Boolean) must be false.
     *
     * @return the query with the added condition
     */
    @NotNull Query<T> isFalse();
}
