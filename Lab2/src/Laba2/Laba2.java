package Laba2;

import java.io.*;

public class Laba2 {
    private String delimIn;
    private String delimOut ;

    public Laba2() {
    }

    private String delimInp = ",";
    private String delimOutp = "+";

    public Laba2(String delimInp, String delimOutp) {
        this.delimIn = delimInp;
        this.delimOut = delimOutp;
    }



    public void fileParsing(String fileIn, String fileOut) {
        BufferedReader reader;
        BufferedWriter writer;
        try {
            reader = new BufferedReader(new FileReader(fileIn));
            writer = new BufferedWriter(new FileWriter(fileOut));
            String string = reader.readLine();
            while (string != null) {
                writer.write(this.strParsing(string) + "\n");
                writer.flush();
                string = reader.readLine();
            }
            reader.close();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String strParsing(String in) {
        String copyIn = in;
        StringBuilder result = new StringBuilder();

        while (true) {
            String res = "";
            int index = this.workWithCsv(copyIn);
            if (copyIn.charAt(0) == '"') {
                res = copyIn.substring(1, index);
                index += 2;
            } else if (copyIn.contains(",")) {
                index = copyIn.indexOf(',') + 1;
                res = copyIn.substring(0, index - 1);
            }
            if (index != -1 && index < copyIn.length()) {
                result.append(String.format("%d%s", res.length(), delimOut));
                copyIn = copyIn.substring(index);
            } else {
                result.append(String.format("%d", copyIn.length() - (copyIn.charAt(0) == '"' ? 2 : 0)));
                break;
            }
        }
        if(checkComment(in)==3) {
            return "here comment";
        }else{
            return result.toString();
        }
    }
//hello
    private int checkComment(String in) {
        if (in.charAt(0) == '/' && in.charAt(1)=='*' && in.charAt(in.length()-2)=='*'&& in.charAt(in.length()-1)=='/') {
            return 3;
        } else {
            return 2;
        }
    }

    private int workWithCsv(String in) {
        if (in.charAt(0) == '"') {
            long numberQuotes = in.chars().filter(ch -> ch == '"').count();
            if (numberQuotes%2 != 0)
                //JVM пытается передать методу неподходящий аргумент или аргумент неправильного типа
                throw new IllegalArgumentException("!!!No close!!!! \"");
            String temp = in.substring(1);
            int cutLeng = 1;
            int numQuote = 1;
            while (true) {
                int indexQuote = temp.indexOf('"');
                if (indexQuote == temp.length() - 1 || (numQuote%2 != 0 && temp.charAt(indexQuote + 1) == ','))
                    return indexQuote + cutLeng;
                else {
                    numQuote++;
                    cutLeng += indexQuote + 1;
                    temp = temp.substring(indexQuote + 1);
                }
            }
        } else {
            return in.indexOf(",");
        }
    }



    public String getDelimInp() {
        return delimInp;
    }

    public String getDelimOutp() {
        return delimOutp;
    }

    public void setDelimInp(String delimInp) {
        this.delimInp = delimInp;
    }

    public void setDelimOutp(String delimOutp) {
        this.delimOutp = delimOutp;
    }
}