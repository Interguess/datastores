package de.igweb.igdatastores;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Query<T> {

    private final DataStore<T> dataStore;

    private final List<Field> requiredFields;

    /**
     * Creates a new Query for the given DataStore.
     *
     * @param dataStore The DataStore to create the Query for.
     */
    public Query(DataStore<T> dataStore) {
        this.dataStore = dataStore;
        this.requiredFields = new ArrayList<>();
    }

    /**
     * Adds a new field to the Query.
     *
     * @param name The name of the field.
     * @return The new Field.
     */
    public Field field(String name) {
        return new Field(this, name);
    }

    /**
     * @param object The object to check.
     * @return Whether all conditions of the Query are true for the given object.
     */
    public boolean allConditionsTrue(Object object) {
        try {
            for (Field field : requiredFields) {
                java.lang.reflect.Field memoryField = object.getClass().getDeclaredField(field.getName());
                memoryField.setAccessible(true);
                if (!ConditionalChecker.isTrue(memoryField.get(object), field.getRequiredValue(), field.getCondition())) {
                    return false;
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to check conditions for " + object.getClass() + "!", exception);
        }
        return true;
    }

    /**
     * @return A list of all objects in the DataStore that match with this Query.
     */
    public List<T> get() {
        return dataStore.get(this);
    }

    /**
     * Clears all objects from the DataStore that match with this Query.
     */
    public void clear() {
        dataStore.clear(this);
    }

    /**
     * @return whether the DataStore contains at least one object that matches with this Query.
     */
    public boolean containsAny() {
        return dataStore.containsAny(this);
    }

    @Getter
    @SuppressWarnings("unused")
    public class Field {

        private final Query<T> query;

        private final String name;

        private String condition;

        private Object requiredValue;

        /**
         * Creates a new Field for the given Query.
         *
         * @param query The Query to create the Field for.
         * @param name  The name of the Field.
         */
        public Field(Query<T> query, String name) {
            this.query = query;
            this.name = name;
        }

        /**
         * Adds the condition "equal" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<T> equal(Object requiredValue) {
            return addCondition("=", requiredValue);
        }

        /**
         * Adds the condition "equalIgnoreCases" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<T> equalIgnoreCase(Object requiredValue) {
            return addCondition("=?^", requiredValue);
        }

        /**
         * Adds the condition "notEqual" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<T> notEqual(Object requiredValue) {
            return addCondition("!=", requiredValue);
        }

        /**
         * Adds the condition "greaterThan" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<T> greaterThan(Object requiredValue) {
            return addCondition(">", requiredValue);
        }

        /**
         * Adds the condition "lessThan" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<T> lessThan(Object requiredValue) {
            return addCondition("<", requiredValue);
        }

        /**
         * Adds the condition "contains" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<T> contains(Object requiredValue) {
            return addCondition("contains", requiredValue);
        }

        /**
         * Adds the condition "containsIgnoreCase" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<T> containsIgnoreCase(Object requiredValue) {
            return addCondition("containsIgnoreCase", requiredValue);
        }

        /**
         * Adds the condition "startsWith" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<T> startsWith(Object requiredValue) {
            return addCondition("startsWith", requiredValue);
        }

        /**
         * Adds the condition "startsWithIgnoreCase" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<T> startsWithIgnoreCase(Object requiredValue) {
            return addCondition("startsWithIgnoreCase", requiredValue);
        }

        /**
         * Adds the condition "endsWith" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<T> endsWith(Object requiredValue) {
            return addCondition("endsWith", requiredValue);
        }

        /**
         * Adds the condition "endsWithIgnoreCase" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<T> endsWithIgnoreCase(Object requiredValue) {
            return addCondition("endsWithIgnoreCase", requiredValue);
        }

        /**
         * Adds the condition "isNull" to the Field.
         *
         * @return The Query.
         */
        public Query<T> isNull() {
            return addCondition("?null", true);
        }

        /**
         * Adds the condition "isNotNull" to the Field.
         *
         * @return The Query.
         */
        public Query<T> isNotNull() {
            return addCondition("?null", false);
        }

        /**
         * Adds the condition "isTrue" to the Field.
         *
         * @return The Query.
         */
        public Query<T> isTrue() {
            return addCondition("?bool_val", true);
        }

        /**
         * Adds the condition "isFalse" to the Field.
         *
         * @return The Query.
         */
        public Query<T> isFalse() {
            return addCondition("?bool_val", false);
        }

        /**
         * Packs the field into the Query.
         *
         * @return The Query.
         */
        public Query<T> addCondition(String condition, Object requiredValue) {
            this.condition = condition;
            this.requiredValue = requiredValue;
            requiredFields.add(this);
            return query;
        }

    }

}
