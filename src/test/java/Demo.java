import de.igweb.igdatastores.DataStore;
import de.igweb.igdatastores.snapshot.DataStoreSnapshot;

import java.util.List;

public class Demo {

    public static void x(String[] args) {
        DataStore<User> userStorage = new DataStore<>();
//        userStorage.save(new User("John", 19, "john@datastores.i"));
//        userStorage.save(new User("Jane", 29, null));
//        userStorage.save(new User("Jack", 46, "jack@datastores.i"));
//        userStorage.save(new User("Jill", 23, "jill@datastores.i"));
//
//        userStorage.createQuery()
//                .field("email").isNotNull()
//                .get().forEach(user -> System.out.println(user.getName()));

        if (!userStorage.createQuery().field("age").lessThan(18).containsAny()) {
            System.out.println("There are no users under 18!");
        }

        userStorage.createQuery()
                .field("age").lessThan(18).clear();

        System.out.println(userStorage.createSnapshot());

        //userStorage.clearAll();

        String snapShot = userStorage.createSnapshot().toString();

        DataStoreSnapshot snapshot = DataStoreSnapshot.fromString(snapShot);
        System.out.println("Snapshot ID: " + snapshot.getId());
        System.out.println("Snapshot Timestamp: " + snapshot.getTimestamp());
        userStorage.loadSnapshot(snapshot);

//        userStorage.createQuery()
//                .field("email").isNotNull()
//                .get().forEach(user -> System.out.println(user.getName()));

        userStorage.clearAll();
    }

    public static void main(String[] args) {
        DataStore<User> dataStore = new DataStore<>();
        for (int i = 0; i <= 100000; i++) {
            String firstName = generateFirstName();
            String lastName = generateLastName();
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@interguessweb.de";
            int age = generateRandomInt(10, 50);
            dataStore.save(new User(firstName, lastName, email, age));
        }
        long start = System.currentTimeMillis();

        dataStore.createQuery().field("age").greaterThan(10)
                .field("age").lessThan(25)
                .get().forEach(user -> System.out.println(user.getFirstName()));

        System.out.println("Time elapsed: " + (System.currentTimeMillis() - start) + "ms");
    }

    public static String generateFirstName() {
        List<String> list = List.of("John", "Jonas", "Tim", "Tom", "Lara", "Rudi", "Kim", "Jack", "Andreas",
                "Marlon", "Phillip", "Lukas", "David", "Elias", "Leon", "Max", "Finn", "Noah", "Paul", "Luca", "Ben",
                "Emil", "Luis", "Felix", "Liam", "Nico", "Nils", "Jan", "Janosch", "Jannik", "Jannis", "Jano", "Janos", "Tom",
                "Tommy", "Timo", "Timon", "Timothy", "Timur", "Tino", "Tobias", "Tobi", "Tobias", "Tobias", "Tobias", "Tobias",
                "Vincent", "Noah", "Liam", "Mason", "Jacob", "William", "Ethan", "Michael", "Alexander", "James", "Daniel", "Elijah",
                "Benjamin", "Logan", "Aiden", "Jayden", "Matthew", "Jackson", "David", "Joseph", "Anthony", "Joshua", "Andrew",
                "Lucas", "Gabriel", "Samuel", "Christopher", "John", "Dylan", "Isaac", "Ryan", "Carter", "Nathan", "Isaiah",
                "Owen", "Sebastian", "Caleb", "Christian", "Hunter", "Jack", "Luke", "Henry", "Wyatt", "Jonathan", "Eli", "Isaiah",
                "Landon", "Julian", "Levi", "Aaron", "Jaxon", "Julian", "Grayson", "Jason", "Brandon", "Angel", "Kevin", "Jack",
                "Connor", "Justin", "Charles", "Josiah", "Chase", "Thomas", "Austin", "Adrian", "Jordan", "Robert", "Zachary",
                "Easton", "Nolan", "Cole", "Tyler", "Jeremiah", "Blake", "Brayden", "Jordan", "Dominic", "Austin", "Ian", "Adam",
                "Carson", "Evan", "Elias", "Maverick", "Bryson", "Jace", "Cooper", "Xavier", "Parker", "Roman", "Jason", "Santiago");
        return list.get(generateRandomInt(0, list.size() - 1));
    }

    public static String generateLastName() {
        List<String> list = List.of("Schnell", "Kaste", "Neumann", "Berger", "Müller", "Schmidt", "Schneider", "Fischer",
                "Weber", "Meyer", "Wagner", "Becker", "Schulz", "Hoffmann", "Schäfer", "Koch", "Bauer", "Richter", "Klein",
                "Wolf", "Schröder", "Neumann", "Schwarz", "Zimmermann", "Braun", "Krüger", "Hofmann", "Schmitt", "Hartmann",
                "Lange", "Werner", "Schmitz", "Krause", "Meier", "Lehmann", "Maier", "Schmid", "Möller", "Köhler", "Herrmann",
                "König", "Walter", "Mayer", "Huber", "Kaiser", "Fuchs", "Peters", "Lang", "Scholz", "Meyer", "Weber", "Becker",
                "Schulz", "Hoffmann", "Schäfer", "Koch", "Bauer", "Richter", "Klein", "Wolf", "Schröder", "Neumann", "Schwarz",
                "Zimmermann", "Braun", "Krüger", "Hofmann", "Schmitt", "Hartmann", "Lange", "Werner", "Schmitz", "Krause",
                "Meier", "Lehmann", "Maier", "Schmid", "Möller", "Köhler", "Herrmann", "König", "Walter", "Mayer", "Huber",
                "Kaiser", "Fuchs", "Peters", "Lang", "Scholz", "Meyer", "Weber", "Becker", "Schulz", "Hoffmann", "Schäfer",
                "Koch", "Bauer", "Richter", "Klein", "Wolf", "Schröder", "Neumann", "Schwarz", "Zimmermann", "Braun", "Krüger",
                "Hofmann", "Schmitt", "Hartmann", "Lange", "Werner", "Schmitz", "Krause", "Meier", "Lehmann", "Maier");
        return list.get(generateRandomInt(0, list.size() - 1));
    }

    public static int generateRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

}
