package de.igweb.igdatastores;

import com.google.gson.Gson;
import lombok.Getter;

@Getter
public class Conditional {

    private static final Gson GSON = new Gson();

    private final String object;

    private final String operator;

    public Conditional(Object object, String operator) {
        this.object = GSON.toJson(object);
        this.operator = operator;
    }

    public boolean check(Object object) {
        String obj = GSON.toJson(object);
        switch (operator) {
            case "==" -> {
                return this.object.equals(obj);
            }

            case ">" -> {
                return Integer.parseInt(this.object) > Integer.parseInt(obj);
            }

            case "<" -> {
                return Integer.parseInt(this.object) < Integer.parseInt(obj);
            }

            case ">=" -> {
                return Integer.parseInt(this.object) >= Integer.parseInt(obj);
            }

            case "<=" -> {
                return Integer.parseInt(this.object) <= Integer.parseInt(obj);
            }

            case "!=" -> {
                return !this.object.equals(obj);
            }

            case "contains" -> {
                return this.object.contains(obj);
            }

            case "startsWith" -> {
                return this.object.startsWith(obj);
            }

            case "endsWith" -> {
                return this.object.endsWith(obj);
            }

            case "qualIgnoreCase" -> {
                return this.object.equalsIgnoreCase(obj);
            }

            case "containsIgnoreCase" -> {
                return this.object.toLowerCase().contains(obj.toLowerCase());
            }

            default -> throw new RuntimeException("Unknown operator " + operator);
        }
    }

}
