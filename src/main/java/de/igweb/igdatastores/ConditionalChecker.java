package de.igweb.igdatastores;

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

}
