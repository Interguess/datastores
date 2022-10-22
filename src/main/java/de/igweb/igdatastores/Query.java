package de.igweb.igdatastores;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class Query {

    private final DataStore dataStore;

    private final Map<Object, Object> conditions = new HashMap<>();

    public Field field(String id) {
        return new Field(id);
    }

    public List<? extends Entity> get() {
        return dataStore.get(this);
    }

    @Getter
    public class Field {

        private final String field;

        private Conditional conditional;

        public Field(String field) {
            this.field = field;
        }

        public Query equal(Object value) {
            conditional = new Conditional(value, "==");
            conditions.put(field, conditional);
            return Query.this;
        }

        public Query greaterThan(Object value) {
            conditional = new Conditional(value, ">");
            conditions.put(field, conditional);
            return Query.this;
        }

        public Query lessThan(Object value) {
            conditional = new Conditional(value, "<");
            conditions.put(field, conditional);
            return Query.this;
        }

        public Query greaterThanOrEqual(Object value) {
            conditional = new Conditional(value, ">=");
            conditions.put(field, conditional);
            return Query.this;
        }

        public Query lessThanOrEqual(Object value) {
            conditional = new Conditional(value, "<=");
            conditions.put(field, conditional);
            return Query.this;
        }

        public Query notEqual(Object value) {
            conditional = new Conditional(value, "!=");
            conditions.put(field, conditional);
            return Query.this;
        }

        public Query contains(Object value) {
            conditional = new Conditional(value, "contains");
            conditions.put(field, conditional);
            return Query.this;
        }

        public Query startsWith(Object value) {
            conditional = new Conditional(value, "startsWith");
            conditions.put(field, conditional);
            return Query.this;
        }

        public Query endsWith(Object value) {
            conditional = new Conditional(value, "endsWith");
            conditions.put(field, conditional);
            return Query.this;
        }

        public Query qualIgnoreCase(Object value) {
            conditional = new Conditional(value, "qualIgnoreCase");
            conditions.put(field, conditional);
            return Query.this;
        }

        public Query containsIgnoreCase(Object value) {
            conditional = new Conditional(value, "containsIgnoreCase");
            conditions.put(field, conditional);
            return Query.this;
        }
    }

}
