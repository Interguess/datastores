package de.igweb.igdatastores.test;

import de.igweb.igdatastores.DataStore;
import de.igweb.igdatastores.test.model.User;

public class DataStoresTest {

    public static void main(String[] args) {
        DataStore<User> userStorage = new DataStore<>();
        userStorage.save(
                new User("John", "john@datastores.i", 19),
                new User("Jane", "jane@datastores.i", 29),
                new User("Jack", "jack@datastores.i", 46),
                new User("Jill", "jill@datastores.i", 23)
        );

        userStorage.createQuery()
                .field("age").greaterThan(20)
                .field("age").lessThan(24)
                .field("email").endsWith("es.i")
                .list().forEach(user -> System.out.println(user.getName()));
    }
}