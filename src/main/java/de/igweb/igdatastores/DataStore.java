package de.igweb.igdatastores;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public abstract class DataStore {

    private Class<? extends Entity> entityClass;

    public Query createQuery() {
        return new Query(this);
    }

    public abstract List<? extends Entity> get(Query query);

    public abstract void save(Entity object);

}
