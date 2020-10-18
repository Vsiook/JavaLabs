package Laba2;

import org.junit.Rule;
        import org.junit.Test;
        import org.junit.rules.TemporaryFolder;

        import java.io.*;

        import static org.junit.Assert.assertEquals;

public class TestLaba2 {
    private Laba2 instance;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    public TestLaba2() {
        this.instance = new Laba2();
    }

    @Test
    public void testStrParsing() {
        assertEquals("1+1+1", this.instance.strParsing("a,b,c"));
        assertEquals("1+3+1", this.instance.strParsing("a,\"b,c\",c"));
        assertEquals("2+2+0+11", this.instance.strParsing("11,AU,\"\",Aus\"\"tralia"));
        assertEquals("2+2+11", this.instance.strParsing("\"13\",\"AU\",\"Aus\"\"tralia\""));
        //"12","AU"," ","Australia"
        assertEquals("2+2+0+9", this.instance.strParsing("\"12\",\"AU\",\"\",\"Australia\""));
        assertEquals("2+2+13+14", this.instance.strParsing("\"13\",\"AU\",\"A\"\"us\"\"tralia\"," +
                "\"A\"\"us,\"\"tralia\""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionsTestParseString() {
        this.instance.strParsing("a,b,\"c");
    }

    @Test
    public void testParseFile() throws IOException {
        final File tempFile = tempFolder.newFile("inp.csv");
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
        bw.write("a,\"b,c\",c");
        bw.close();

        final File tempOutputFile = tempFolder.newFile("outp.csv");
        this.instance.fileParsing(tempFile.getAbsolutePath(), tempOutputFile.getAbsolutePath());

        BufferedReader br = new BufferedReader(new FileReader(tempOutputFile));
        assertEquals("1+3+1", br.readLine());
        br.close();
    }

    @Test
    public void getAndSetTest() {
        Laba2 testInstance = new Laba2("1", "2");
        assertEquals("1", testInstance.getDelimInp());
        assertEquals("2", testInstance.getDelimOutp());

        testInstance.setDelimOutp(",");
        assertEquals(",", testInstance.getDelimInp());

        testInstance.setDelimOutp("+");
        assertEquals("+", testInstance.getDelimOutp());
    }
}