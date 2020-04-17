package com.in28minutes.rest.webservices.restfulwebservices.versioning;
@lombok.ToString
@lombok.Getter
@lombok.Setter
@lombok.Builder
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Name {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
