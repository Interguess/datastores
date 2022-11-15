import de.igweb.igdatastores.DataStore;

public class Demo {

    public static void main(String[] args) {
        DataStore<User> userStorage = new DataStore<>();
        userStorage.save(new User("John", 19, "john@datastor.es"));
        userStorage.save(new User("Jane", 29, "jane@datastor.es"));
        userStorage.save(new User("Jack", 46, "jack@datastor.es"));
        userStorage.save(new User("Jill", 23, "jill@datastor.es"));

        System.out.println(
                userStorage.createQuery()
                        .field("age")
                        .greaterThan(20)
                        .field("age").lessThan(24)
                        .get()
        );
    }

}
