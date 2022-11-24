package de.igweb.igdatastores;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Query<T> {

    private final DataStore<T> dataStore;

    private final List<Field> requiredFields = new ArrayList<>();

    /**
     * Creates a new Query for the given DataStore.
     *
     * @param dataStore The DataStore to create the Query for.
     */
    public Query(DataStore<T> dataStore) {
        this.dataStore = dataStore;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @return A list of all objects in the DataStore that match with this Query.
     */
    public List<T> get() {
        return dataStore.get(this);
    }

    @Getter
    @SuppressWarnings("unused")
    public class Field {

        private final Query<?> query;

        private final String name;

        private String condition;

        private Object requiredValue;

        /**
         * Creates a new Field for the given Query.
         *
         * @param query The Query to create the Field for.
         * @param name  The name of the Field.
         */
        public Field(Query<?> query, String name) {
            this.query = query;
            this.name = name;
        }

        /**
         * Adds the condition "equal" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<?> equal(Object requiredValue) {
            this.condition = "=";
            this.requiredValue = requiredValue;
            return pack();
        }

        /**
         * Adds the condition "equalIgnoreCases" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<?> equalIgnoreCase(Object requiredValue) {
            this.condition = "=?^";
            this.requiredValue = requiredValue;
            return pack();
        }

        /**
         * Adds the condition "notEqual" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<?> notEqual(Object requiredValue) {
            this.condition = "!=";
            this.requiredValue = requiredValue;
            return pack();
        }

        /**
         * Adds the condition "greaterThan" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<?> greaterThan(Object requiredValue) {
            this.condition = ">";
            this.requiredValue = requiredValue;
            return pack();
        }

        /**
         * Adds the condition "lessThan" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<?> lessThan(Object requiredValue) {
            this.condition = "<";
            this.requiredValue = requiredValue;
            return pack();
        }

        /**
         * Adds the condition "contains" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<?> contains(Object requiredValue) {
            this.condition = "contains";
            this.requiredValue = requiredValue;
            return pack();
        }

        /**
         * Adds the condition "containsIgnoreCase" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<?> containsIgnoreCase(Object requiredValue) {
            this.condition = "containsIgnoreCase";
            this.requiredValue = requiredValue;
            return pack();
        }

        /**
         * Adds the condition "startsWith" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<?> startsWith(Object requiredValue) {
            this.condition = "startsWith";
            this.requiredValue = requiredValue;
            return pack();
        }

        /**
         * Adds the condition "startsWithIgnoreCase" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<?> startsWithIgnoreCase(Object requiredValue) {
            this.condition = "startsWithIgnoreCase";
            this.requiredValue = requiredValue;
            return pack();
        }

        /**
         * Adds the condition "endsWith" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<?> endsWith(Object requiredValue) {
            this.condition = "endsWith";
            this.requiredValue = requiredValue;
            return pack();
        }

        /**
         * Adds the condition "endsWithIgnoreCase" to the Field.
         *
         * @param requiredValue The required value.
         * @return The Query.
         */
        public Query<?> endsWithIgnoreCase(Object requiredValue) {
            this.condition = "endsWithIgnoreCase";
            this.requiredValue = requiredValue;
            return pack();
        }

        /**
         * Adds the condition "isNull" to the Field.
         *
         * @return The Query.
         */
        public Query<?> isNull() {
            this.condition = "?null";
            this.requiredValue = null;
            return pack();
        }

        /**
         * Adds the condition "isNotNull" to the Field.
         *
         * @return The Query.
         */
        public Query<?> isNotNull() {
            this.condition = "?!null";
            this.requiredValue = null;
            return pack();
        }

        /**
         * Adds the condition "isTrue" to the Field.
         *
         * @return The Query.
         */
        public Query<?> isTrue() {
            this.condition = "?true";
            this.requiredValue = null;
            return pack();
        }

        /**
         * Adds the condition "isFalse" to the Field.
         *
         * @return The Query.
         */
        public Query<?> isFalse() {
            this.condition = "?false";
            this.requiredValue = null;
            return pack();
        }

        /**
         * Packs the field into the Query.
         *
         * @return The Query.
         */
        public Query<?> pack() {
            requiredFields.add(this);
            return this.query;
        }

    }

}
