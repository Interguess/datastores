package de.interguess.igdatastores.test;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private final String firstName;

    private final String lastName;

    private final int age;
}
