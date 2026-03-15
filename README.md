# codex_ki_test

This project demonstrates a small XML editing framework written in Java. The
framework is located under `src/main/java/xmlframework` and provides simple
methods to add, update or remove elements with automatic type conversion.

## Building and running

Compile the example using `javac` and run it with `java`:

```bash
javac -d out $(find src/main/java -name '*.java')
java -cp out xmlframework.Example
```

Running the example will create `example.xml` in the project directory.

The `XmlEditor` also provides `toXmlString()` to retrieve the current
document as a formatted string without saving it to disk.
