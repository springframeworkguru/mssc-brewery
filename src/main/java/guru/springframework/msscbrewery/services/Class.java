package guru.springframework.msscbrewery.services;
// File Class.java
public class Class {
    private Class(String s) {

    }

    public static Class createClass(String s) {
        return new Class(s);
    }
}

// File AnotherClass.java
class AnotherClass {
    public void method() {
        Class aClass = Class.createClass("string");
    }
}
//// File Class.java
//public class Class {
//    public Class(String s) {
//        ...
//    }
//}
//
//// File AnotherClass.java
//public class AnotherClass {
//    public void method() {
//        Class aClass = new Class("string");
//    }
//}