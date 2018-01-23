import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test 
{
    public static void main(final String[] args) throws IOException {
        final Path firstFile = Paths.get("C://TEMP//A//1.xml");
        final Path secondFile = Paths.get("C://TEMP//AA//1.xml");
        
        final List<String> firstFileContent = Files.readAllLines(firstFile,Charset.defaultCharset());
        final List<String> secondFileContent = Files.readAllLines(secondFile,Charset.defaultCharset());
        
        System.out.println(firstFile.getName(0).toString()+"/"+firstFile.getName(1).toString()+"/"+firstFile.getFileName()+" COMPARED WITH "+secondFile.getName(0).toString()+"/"+secondFile.getName(1).toString()+"/"+secondFile.getFileName());
     
        System.out.println(diffFiles(firstFileContent, secondFileContent));
        System.out.println(diffFiles(secondFileContent, firstFileContent));
    }

    private static List<String> diffFiles(final List<String> firstFileContent,
        final List<String> secondFileContent) {
        final List<String> diff = new ArrayList<String>();
        for (final String line : firstFileContent) {
            if (!secondFileContent.contains(line)) {
                diff.add(line);
            }
        }
        return diff;
    }
}