import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ComparePlaylists2 
{
     
	public static void main(final String[] args) throws IOException 
    {
        //Set Playlist folders
		String NewPlaylistFolder = "C://TEMP//A";
    	String OldPlaylistFolder = "C://TEMP//AA";
		
    	//Get list of files OR playlist names
    	String Playlists[] = new String[10]; 
    	File folder = new File(NewPlaylistFolder);
    	File[] listOfFiles = folder.listFiles();

    	    for (int i = 0; i < listOfFiles.length; i++) 
    	    {
    	      if (listOfFiles[i].isFile()&&listOfFiles[i].getName().toLowerCase().endsWith(".xml")) 
    	        Playlists[i]=listOfFiles[i].getName();
    	     }
    	
    	   //Compare contents of 2 playlists with same name 
    	    for(String str : Playlists)  {
    	    	if(str==null)
    	    		continue;
    	    	else
	    	    	{
    	    		Path firstFile = Paths.get(NewPlaylistFolder+"//"+str);
    	            Path secondFile = Paths.get(OldPlaylistFolder+"//"+str);
    	            
    	            List<String> firstFileContent = Files.readAllLines(firstFile,Charset.defaultCharset());
    	            List<String> secondFileContent = Files.readAllLines(secondFile,Charset.defaultCharset());
    	            
    	            System.out.println(firstFile.getName(0).toString()+"/"+firstFile.getName(1).toString()+"/"+firstFile.getFileName()+" COMPARED WITH "+secondFile.getName(0).toString()+"/"+secondFile.getName(1).toString()+"/"+secondFile.getFileName());    	         
    	            System.out.println(diffFiles(firstFileContent, secondFileContent));
	    	    	}
    	    	}
    }

    public static List<String> diffFiles(List<String> firstFileContent,List<String> secondFileContent) 
    {
    	List<String> diff = new ArrayList<String>();
    	if(!firstFileContent.equals(secondFileContent))
    	{	 
    		diff=firstFileContent;
    	}
    	else
    		diff.add("No difference in 2 files");
    	return diff;
    }
}