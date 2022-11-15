package de.igweb.igdatastores;

import java.util.ArrayList;
import java.util.List;

public class Query<T> {

    private final DataStore<T> dataStore;

    private final List<Field> requiredFields = new ArrayList<>();

    public Query(DataStore<T> dataStore) {
        this.dataStore = dataStore;
    }

    public Field field(String name) {
        return new Field(this, name);
    }

    public void addField(Field field) {
        this.requiredFields.add(field);
    }

    public boolean allConditionsTrue(Object object) {
        try {
            for (Field field : requiredFields) {
                java.lang.reflect.Field memoryField = object.getClass().getDeclaredField(field.getName());
                memoryField.setAccessible(true);
                if (!ConditionalChecker.isTrue(memoryField.get(object), field.getRequiredValue(), field.getCondition())) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<T> get() {
        return dataStore.get(this);
    }

}
