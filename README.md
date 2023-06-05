# IgDataStores

## Use with Gradle
```gradle
repositories {
  maven { url 'https://jitpack.io' }
}
```

```gradle
dependencies {
  implementation 'com.github.IgWebDE:IgDataStores:latest'
}
```


## Use with Maven
```xml
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>
```

```xml
<dependency>
  <groupId>com.github.IgWebDE</groupId>
  <artifactId>IgDataStores</artifactId>
  <version>latest</version>
</dependency>
```

```java
import de.igweb.igdatastores.DataStore;

public class de.igweb.igdatastores.test.DataStoresTest {

    public static void main(String[] args) {
        DataStore<de.igweb.igdatastores.test.model.User> userStorage = new DataStore<>();
        userStorage.save(
                new de.igweb.igdatastores.test.model.User("John", 19, "john@datastores.i"),
                new de.igweb.igdatastores.test.model.User("Jane", 29, "jane@datastores.i"),
                new de.igweb.igdatastores.test.model.User("Jack", 46, "jack@datastores.i"),
                new de.igweb.igdatastores.test.model.User("Jill", 23, "jill@datastores.i")
        );

        userStorage.createQuery()
                .field("age").greaterThan(20)
                .field("age").lessThan(24)
                .field("email").endsWith("es.i")
                .list().forEach(user -> System.out.println(user.getName()));
    }

}
```
