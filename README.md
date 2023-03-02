# IgDataStores

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.IgWebDE</groupId>
        <artifactId>IgDataStores</artifactId>
        <version>LATEST</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```

```java
import de.igweb.igdatastores.DataStore;

public class Demo {

    public static void main(String[] args) {
        DataStore<User> userStorage = new DataStore<>();
        userStorage.save(
                new User("John", 19, "john@datastores.i"),
                new User("Jane", 29, "jane@datastores.i"),
                new User("Jack", 46, "jack@datastores.i"),
                new User("Jill", 23, "jill@datastores.i")
        );

        userStorage.createQuery()
                .field("age").greaterThan(20)
                .field("age").lessThan(24)
                .field("email").endsWith("es.i")
                .list().forEach(user -> System.out.println(user.getName()));
    }

}
```
