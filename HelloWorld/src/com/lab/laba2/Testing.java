
import com.lab.laba1.Laba1;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class TestLaba1 {
    private Laba1 instance;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    public Testing() {
        this.instance = new Laba1();
    }

    @Test
    public void testParseString() {
        assertEquals("1+1+1", this.instance.parseString("a,b,c"));
        assertEquals("1+3+1", this.instance.parseString("a,\"b,c\",c"));
        assertEquals("2+2+0+11", this.instance.parseString("11,AU,\"\",Aus\"\"tralia"));
        assertEquals("2+2+11", this.instance.parseString("\"13\",\"AU\",\"Aus\"\"tralia\""));
        //"12","AU"," ","Australia"
        assertEquals("2+2+0+9", this.instance.parseString("\"12\",\"AU\",\"\",\"Australia\""));
        assertEquals("2+2+13+14", this.instance.parseString("\"13\",\"AU\",\"A\"\"us\"\"tralia\"," +
                "\"A\"\"us,\"\"tralia\""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionsTestParseString() {
        this.instance.parseString("a,b,\"c");
    }

    @Test
    public void testParseFile() throws IOException {
        final File tempFile = tempFolder.newFile("input.csv");
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
        bw.write("a,\"b,c\",c");
        bw.close();

        final File tempOutputFile = tempFolder.newFile("output.csv");
        this.instance.parseFile(tempFile.getAbsolutePath(), tempOutputFile.getAbsolutePath());

        BufferedReader br = new BufferedReader(new FileReader(tempOutputFile));
        assertEquals("1+3+1", br.readLine());
        br.close();
    }

    @Test
    public void testGettersAndSetters() {
        Laba1 testInstance = new Laba1("1", "2");
        assertEquals("1", testInstance.getInputDelimiter());
        assertEquals("2", testInstance.getOutputDelimiter());

        testInstance.setInputDelimiter(",");
        assertEquals(",", testInstance.getInputDelimiter());

        testInstance.setOutputDelimiter("+");
        assertEquals("+", testInstance.getOutputDelimiter());
    }
}