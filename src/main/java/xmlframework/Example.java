package xmlframework;

import java.nio.file.Paths;

public class Example {
    public static void main(String[] args) throws Exception {
        XmlEditor editor = XmlEditor.newDocument("person");

        editor.addElement("/person", "name", "Alice");
        editor.addElement("/person", "age", 30);

        Integer age = editor.getValue("/person/age", Integer.class);
        System.out.println("Age: " + age);

        editor.updateValue("/person/name", "Bob");
        editor.delete("/person/age");

        editor.save(Paths.get("example.xml"));
    }
}
