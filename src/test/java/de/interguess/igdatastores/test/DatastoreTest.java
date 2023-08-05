package de.interguess.igdatastores.test;

import de.interguess.igdatastores.Datastore;
import de.interguess.igdatastores.impl.DatastoreImpl;
import de.interguess.igdatastores.sort.SortType;

import java.util.List;

public class DatastoreTest {

    public static void main(String[] args) {

        //Create a new datastore for the type User
        Datastore<User> datastore = new DatastoreImpl<>();

        //Save some users to the datastore
        datastore.saveAll(
                new User("Tim", "Mustermann", 16),
                new User("Max", "Mustermann", 19),
                new User("Anna", "Mustermann", 18)
        );

        //Select all users with the age of 18 or higher, sort them by age in descending order and limit the result to 10 users
        List<User> users = datastore.createQuery()
                .field("age").greaterOrEqual(18)
                .sorted("age", SortType.DESCEND)
                .limit(10)
                .list();

        //Print the users
        users.forEach(user -> System.out.println(user.toString()));
    }
}