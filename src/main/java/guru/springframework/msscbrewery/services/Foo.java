package guru.springframework.msscbrewery.services;

public class Foo {
    public Foo(String firstName, String lastName, int age, boolean married) {}
    public static void main(String[] args) {
        Foo joe = new FooBuilder().setFirstName("Joe").setLastName("Smith").setAge(42).setMarried(false).createFoo();
    }
}
//The Replace Constructor with Builder refactoring helps hide a constructor,
// replacing its usages with the references to a newly generated builder class,
// or to an existing builder class.

// public Foo createFoo() {
//        return new Foo(firstName, lastName, age, married);
//    }

