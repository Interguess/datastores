package de.interguess.igdatastores.impl;

import de.interguess.igdatastores.Datastore;
import de.interguess.igdatastores.Query;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DatastoreImpl<T> implements Datastore<T> {

    private final List<T> data;

    public DatastoreImpl() {
        this.data = new ArrayList<>();
    }

    @Override
    public void saveAll(@NotNull T... data) {
        this.data.addAll(List.of(data));
    }

    @Override
    public void deleteAll(@NotNull T... data) {
        this.data.removeAll(List.of(data));
    }

    @Override
    public @NotNull Query<T> createQuery() {
        return new QueryImpl<>(this);
    }

    @Override
    public @NotNull List<T> listAll() {
        return data;
    }

    @Override
    public void deleteAll() {
        data.clear();
    }

    @Override
    public int size() {
        return data.size();
    }
}
