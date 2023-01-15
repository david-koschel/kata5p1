package kata5p1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MailListReader {

    private final Pattern emailPattern = Pattern.compile("[a-zA-Z\\d](_[a-zA-Z\\d]+|-[a-zA-Z\\d]+|\\.[a-zA-Z\\d]+" +
            "|[a-zA-Z\\d])*@[a-zA-Z\\d](([a-zA-Z\\d]+|-[a-zA-Z\\d]+)+\\.)+[a-z]{2,}");

    public List<String> read(String fileName) {
        List<String> result = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.stream().filter(this::stringIsMail).collect(Collectors.toList());
    }

    private boolean stringIsMail(String s) {
        return emailPattern.matcher(s).matches();
    }
}
