package de.igweb.igdatastores;

import lombok.Getter;

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

    @Getter
    @SuppressWarnings("unused")
    public static class Field {

        private final Query<?> query;

        private final String name;

        private String condition;

        private Object requiredValue;

        public Field(Query<?> query, String name) {
            this.query = query;
            this.name = name;
        }

        public Query<?> equal(Object requiredValue) {
            this.condition = "=";
            this.requiredValue = requiredValue;
            return pack();
        }

        public Query<?> equalIgnoreCase(Object requiredValue) {
            this.condition = "=?^";
            this.requiredValue = requiredValue;
            return pack();
        }

        public Query<?> notEqual(Object requiredValue) {
            this.condition = "!=";
            this.requiredValue = requiredValue;
            return pack();
        }

        public Query<?> greaterThan(Object requiredValue) {
            this.condition = ">";
            this.requiredValue = requiredValue;
            return pack();
        }

        public Query<?> lessThan(Object requiredValue) {
            this.condition = "<";
            this.requiredValue = requiredValue;
            return pack();
        }

        public Query<?> contains(Object requiredValue) {
            this.condition = "contains";
            this.requiredValue = requiredValue;
            return pack();
        }

        public Query<?> containsIgnoreCase(Object requiredValue) {
            this.condition = "containsIgnoreCase";
            this.requiredValue = requiredValue;
            return pack();
        }

        public Query<?> startsWith(Object requiredValue) {
            this.condition = "startsWith";
            this.requiredValue = requiredValue;
            return pack();
        }

        public Query<?> startsWithIgnoreCase(Object requiredValue) {
            this.condition = "startsWithIgnoreCase";
            this.requiredValue = requiredValue;
            return pack();
        }

        public Query<?> endsWith(Object requiredValue) {
            this.condition = "endsWith";
            this.requiredValue = requiredValue;
            return pack();
        }

        public Query<?> endsWithIgnoreCase(Object requiredValue) {
            this.condition = "endsWithIgnoreCase";
            this.requiredValue = requiredValue;
            return pack();
        }

        public Query<?> isNull() {
            this.condition = "?null";
            this.requiredValue = null;
            return pack();
        }

        public Query<?> isNotNull() {
            this.condition = "?!null";
            this.requiredValue = null;
            return pack();
        }

        public Query<?> isTrue() {
            this.condition = "?true";
            this.requiredValue = null;
            return pack();
        }

        public Query<?> isFalse() {
            this.condition = "?false";
            this.requiredValue = null;
            return pack();
        }

        public Query<?> pack() {
            this.query.addField(this);
            return this.query;
        }

    }

}
