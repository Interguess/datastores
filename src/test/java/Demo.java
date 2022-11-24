import de.igweb.igdatastores.DataStore;

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
    }

}
