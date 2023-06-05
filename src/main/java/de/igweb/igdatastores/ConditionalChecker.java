package de.igweb.igdatastores;

import java.util.List;

public class ConditionalChecker {

    /**
     * Checks whether the given value matches the given condition.
     *
     * @param objectA             The first value.
     * @param objectB             The second value.
     * @param conditionIdentifier The condition to check.
     * @return Whether the given value matches the given condition.
     */
    public static boolean isTrue(Object objectA, Object objectB, String conditionIdentifier) {
        return switch (conditionIdentifier) {
            case "=" -> objectA.equals(objectB);
            case "=?^" -> objectA.toString().equalsIgnoreCase(objectB.toString());
            case "!=" -> !objectA.equals(objectB);
            case ">" -> (int) objectA > (int) objectB;
            case "<" -> (int) objectA < (int) objectB;
            case "contains" -> objectA.toString().contains(objectB.toString());
            case "containsIgnoreCase" -> objectA.toString().toLowerCase().contains(objectB.toString().toLowerCase());
            case "startsWith" -> objectA.toString().startsWith(objectB.toString());
            case "startsWithIgnoreCase" ->
                    objectA.toString().toLowerCase().startsWith(objectB.toString().toLowerCase());
            case "endsWith" -> objectA.toString().endsWith(objectB.toString());
            case "endsWithIgnoreCase" -> objectA.toString().toLowerCase().endsWith(objectB.toString().toLowerCase());
            case "?null" -> (objectA == null) == ((boolean) objectB);
            case "?bool_val" -> (boolean) objectA == (boolean) objectB;
            default -> false;
        };
    }

    /**
     * @param object The object to check.
     * @return Whether all conditions of the Query are true for the given object.
     */
    public static <D> boolean checkAll(List<Query<D>.Field> requirements, D object) {
        try {
            for (Query<?>.Field field : requirements) {
                java.lang.reflect.Field memoryField = object.getClass().getDeclaredField(field.getName());
                if (!memoryField.trySetAccessible()) {
                    throw new RuntimeException("Failed to set field accessible");
                }
                if (!ConditionalChecker.isTrue(memoryField.get(object), field.getRequiredValue(), field.getCondition())) {
                    return false;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }
}
