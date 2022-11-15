package de.igweb.igdatastores;

import lombok.Getter;

@Getter
@SuppressWarnings("unused")
public class Field {

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
