import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class User {

    private String firstName;

    private String lastName;

    private String email;

    private int age;

}
