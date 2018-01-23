import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test2 
{
     
	public static void main(final String[] args) throws IOException 
    {
        
    	//Get list of files OR playlists
    	String Playlists[] = new String[400];
    	File folder = new File("C://TEMP//New");
    	File[] listOfFiles = folder.listFiles();

    	    for (int i = 0; i < listOfFiles.length; i++) 
    	    {
    	      if (listOfFiles[i].isFile()&&(listOfFiles[i].getName().endsWith(".xml")||listOfFiles[i].getName().endsWith(".XML"))) 
    	        Playlists[i]=listOfFiles[i].getName();
    	    }
    	
    	    for(String str : Playlists)  {
    	    	if(str==null)
    	    		break;
    	    	else
	    	    	{
    	    		Path firstFile = Paths.get("C://TEMP//New//"+str);
    	            Path secondFile = Paths.get("C://TEMP//Old//"+str);
    	            
    	            List<String> firstFileContent = Files.readAllLines(firstFile,Charset.defaultCharset());
    	            List<String> secondFileContent = Files.readAllLines(secondFile,Charset.defaultCharset());
    	            
    	            System.out.println(firstFile.getName(0).toString()+"/"+firstFile.getName(1).toString()+"/"+firstFile.getFileName()+" COMPARED WITH "+secondFile.getName(0).toString()+"/"+secondFile.getName(1).toString()+"/"+secondFile.getFileName());
    	         
    	            System.out.println(diffFiles(firstFileContent, secondFileContent));
    	            System.out.println(diffFiles(secondFileContent, firstFileContent));
	    	    	}
    	    	}
    }

    public static List<String> diffFiles(final List<String> firstFileContent,final List<String> secondFileContent) 
    {
        final List<String> diff = new ArrayList<String>();
        for (final String line : firstFileContent) {
            if (!secondFileContent.contains(line)) {
                diff.add(line);
            }
        }
        return diff;
    }
}