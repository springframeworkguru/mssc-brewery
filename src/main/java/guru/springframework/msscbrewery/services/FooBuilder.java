package guru.springframework.msscbrewery.services;

public class FooBuilder {
    private String firstName;
    private String lastName;
    private int age;
    private boolean married;

    public FooBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public FooBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public FooBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public FooBuilder setMarried(boolean married) {
        this.married = married;
        return this;
    }

    public Foo createFoo() {
        return new Foo(firstName, lastName, age, married);
    }
}