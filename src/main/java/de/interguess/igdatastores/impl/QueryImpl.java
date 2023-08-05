package de.interguess.igdatastores.impl;

import de.interguess.igdatastores.Datastore;
import de.interguess.igdatastores.Field;
import de.interguess.igdatastores.Query;
import de.interguess.igdatastores.sort.SortType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class QueryImpl<T> implements Query<T> {

    private final Datastore<T> datastore;

    private final List<Field<T>> requirements;

    private String sortFieldId;

    private SortType sortType;

    private int limit;

    public QueryImpl(@NotNull Datastore<T> datastore) {
        this.datastore = datastore;
        this.requirements = new ArrayList<>();
        this.limit = -1;
    }

    @Override
    public @NotNull Field<T> field(@NotNull String field) {
        return new FieldImpl<>(this, field);
    }

    @Override
    public @NotNull Query<T> field(@NotNull Field<T> requirement) {
        this.requirements.add(requirement);
        return this;
    }

    @Override
    public @NotNull Query<T> sorted(@NotNull String field, SortType sortType) {
        this.sortFieldId = field;
        this.sortType = sortType;
        return this;
    }

    @Override
    public @NotNull Query<T> limit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public @NotNull List<T> list() {
        Stream<T> filteredData = datastore.listAll().stream()
                .filter(d -> requirements.stream().allMatch(req -> req.getCondition().test(accessField(d, req.getName()))));

        if (sortFieldId != null && sortType != null) {
            filteredData = filteredData.sorted((o1, o2) -> {
                int value1 = (int) accessField(o1, sortFieldId);
                int value2 = (int) accessField(o2, sortFieldId);

                return sortType == SortType.ASCEND ? Integer.compare(value1, value2) : Integer.compare(value2, value1);
            });
        }

        return limit == -1 ? filteredData.toList() : filteredData.limit(limit).toList();
    }

    @Override
    public @Nullable T get() {
        return list().stream().findFirst().orElse(null);
    }

    @Override
    public void delete() {
        datastore.listAll().removeAll(list());
    }

    @Override
    public int amount() {
        return list().size();
    }

    private Object accessField(Object object, String fieldName) {
        try {
            java.lang.reflect.Field field = object.getClass().getDeclaredField(fieldName);

            if (!field.trySetAccessible()) {
                return 0;
            }

            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to access field " + fieldName + " in " + object.getClass(), e);
        }
    }
}
