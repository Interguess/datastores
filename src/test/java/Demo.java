import de.igweb.igdatastores.DataStore;
import de.igweb.igdatastores.snapshot.DataStoreSnapshot;

public class Demo {

    public static void main(String[] args) {
        DataStore<User> userStorage = new DataStore<>();
        userStorage.save(new User("John", 19, "john@datastores.i"));
        userStorage.save(new User("Jane", 29, null));
        userStorage.save(new User("Jack", 46, "jack@datastores.i"));
        userStorage.save(new User("Jill", 23, "jill@datastores.i"));

        userStorage.createQuery()
                .field("email").isNotNull()
                .get().forEach(user -> System.out.println(user.getName()));

        if (!userStorage.createQuery().field("age").lessThan(18).containsAny()) {
            System.out.println("There are no users under 18!");
        }

        userStorage.createQuery()
                .field("age").lessThan(18).clear();

        System.out.println(userStorage.createSnapshot());

        //userStorage.clearAll();

        String snapShot = userStorage.createSnapshot().toString();
        userStorage.loadSnapshot(DataStoreSnapshot.fromString(snapShot));

        userStorage.createQuery()
                .field("email").isNotNull()
                .get().forEach(user -> System.out.println(user.getName()));

        userStorage.clearAll();
    }

}
