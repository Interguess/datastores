package de.interguess.igdatastores.impl;

import de.interguess.igdatastores.Field;
import de.interguess.igdatastores.Query;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class FieldImpl<T> implements Field<T> {

    private final Query<T> query;

    private final String name;

    private Predicate<Object> condition;

    public FieldImpl(Query<T> query, String name) {
        this.query = query;
        this.name = name;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull Predicate<Object> getCondition() {
        return condition;
    }

    @Override
    public @NotNull Query<T> equal(@NotNull Object value) {
        return complete(o -> o.equals(value));
    }

    @Override
    public @NotNull Query<T> equalIgnoreCase(@NotNull String value) {
        return complete(o -> o.toString().equalsIgnoreCase(value));
    }

    @Override
    public @NotNull Query<T> notEqual(@NotNull Object value) {
        return complete(o -> !o.equals(value));
    }

    @Override
    public @NotNull Query<T> greaterThan(@NotNull Number value) {
        return complete(o -> ((Number) o).doubleValue() > value.doubleValue());
    }

    @Override
    public @NotNull Query<T> greaterOrEqual(@NotNull Number value) {
        return complete(o -> ((Number) o).doubleValue() >= value.doubleValue());
    }

    @Override
    public @NotNull Query<T> lessThan(@NotNull Number value) {
        return complete(o -> ((Number) o).doubleValue() < value.doubleValue());
    }

    @Override
    public @NotNull Query<T> lessOrEqual(@NotNull Number value) {
        return complete(o -> ((Number) o).doubleValue() <= value.doubleValue());
    }

    @Override
    public @NotNull Query<T> between(@NotNull Number value1, @NotNull Number value2) {
        return complete(o -> {
            double doubleValue = ((Number) o).doubleValue();
            return doubleValue >= value1.doubleValue() && doubleValue <= value2.doubleValue();
        });
    }

    @Override
    public @NotNull Query<T> contains(@NotNull String value) {
        return complete(o -> o.toString().contains(value));
    }

    @Override
    public @NotNull Query<T> listContains(@NotNull Object object) {
        return complete(o -> ((List<?>) o).contains(object));
    }

    @Override
    public @NotNull Query<T> mapContainsKey(@NotNull Object object) {
        return complete(o -> ((Map<?, ?>) o).containsKey(object));
    }

    @Override
    public @NotNull Query<T> mapContainsValue(@NotNull Object object) {
        return complete(o -> ((Map<?, ?>) o).containsValue(object));
    }

    @Override
    public @NotNull Query<T> containsIgnoreCase(@NotNull String value) {
        return complete(o -> o.toString().toLowerCase().contains(value.toLowerCase()));
    }

    @Override
    public @NotNull Query<T> startsWith(@NotNull String value) {
        return complete(o -> o.toString().startsWith(value));
    }

    @Override
    public @NotNull Query<T> startsWithIgnoreCase(@NotNull String value) {
        return complete(o -> o.toString().toLowerCase().startsWith(value.toLowerCase()));
    }

    @Override
    public @NotNull Query<T> endsWith(@NotNull String value) {
        return complete(o -> o.toString().endsWith(value));
    }

    @Override
    public @NotNull Query<T> endsWithIgnoreCase(@NotNull String value) {
        return complete(o -> o.toString().toLowerCase().endsWith(value.toLowerCase()));
    }

    @Override
    public @NotNull Query<T> isNull() {
        return complete(Objects::isNull);
    }

    @Override
    public @NotNull Query<T> isNotNull() {
        return complete(Objects::nonNull);
    }

    @Override
    public @NotNull Query<T> isTrue() {
        return complete(o -> (boolean) o);
    }

    @Override
    public @NotNull Query<T> isFalse() {
        return complete(o -> !(boolean) o);
    }

    /**
     * Completes the field with the given condition.
     *
     * @param condition the condition to complete the field with
     * @return the query with the condition applied
     */
    private Query<T> complete(Predicate<Object> condition) {
        this.condition = condition;
        return query.field(this);
    }
}
