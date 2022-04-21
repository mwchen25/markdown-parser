import static org.junit.Assert.*;

import java.nio.file.Files;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.*;
public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void getLinkTest1() {
        MarkdownParse parse = new MarkdownParse();

        Path directory = Path.of("test-file.md");
        ArrayList<String> actual = new ArrayList<String>();
        try {
            actual = parse.getLinks(Files.readString(directory));
        }
        catch (IOException e) {
        }
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("https://something.com");
        expected.add("some-thing.html");
        assertEquals(expected, actual);
    }
}