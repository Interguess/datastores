import de.igweb.igdatastores.DataStore;
import de.igweb.igdatastores.collector.UpdatedDataCollector;

public class Demo {


    public static void main(String[] args) {
        DataStore<User> userStore = new DataStore<>();
        userStore.save(new User("JonasDev", "Jonas", "Dev", 1));
        userStore.save(new User("TestUser", "Test", "User", 2));
        userStore.save(new User("TestUser2", "Test", "User2", 3));
        userStore.save(new User("TestUser3", "Test", "User3", 4));
        userStore.save(new User("TestUser4", "Test", "User4", 5));
        userStore.save(new User("TestUser5", "Test", "User5", 6));
        userStore.save(new User("TestUser6", "Test", "User6", 7));

        userStore.createQuery()
                .field("age").greaterThan(3)
                .get().forEach(user -> System.out.println(user.getFirstName()));


    }

}
