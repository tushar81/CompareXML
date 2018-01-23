package Final2;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ComparePlaylists_SingleFile 
{    

	public static void main(final String[] args) throws IOException 
    {
        //Set Playlist folders
		String NewPlaylistFolder = "C://TEMP//New";
    	String OldPlaylistFolder = "C://TEMP//Old";
		
    	//Create File
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss") ;
		File file = new File("C://TEMP//SingleFileComparison"+dateFormat.format(date) + ".txt") ;
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		out.write("************************************************************************PlayLists Comparison Report************************************************************************");
		out.newLine();
    	
    	//Make a List of playlist names  
    	String Playlists = "750007.xml";
    	List<String> Result = new  ArrayList<String>();
    	
    	   //Compare contents of 2 playlists with same name 
    	    if(!Playlists.isEmpty())  
    	    {
    	    		Path firstFile = Paths.get(NewPlaylistFolder+"//"+Playlists);
    	            Path secondFile = Paths.get(OldPlaylistFolder+"//"+Playlists);
    	            
    	            List<String> firstFileContent = Files.readAllLines(firstFile,Charset.defaultCharset());
    	            List<String> secondFileContent = Files.readAllLines(secondFile,Charset.defaultCharset());
    	            
    	            //Run diff and print result
    	            out.write(firstFile.getName(0).toString()+"/"+firstFile.getName(1).toString()+"/"+firstFile.getFileName()+" COMPARED WITH "+secondFile.getName(0).toString()+"/"+secondFile.getName(1).toString()+"/"+secondFile.getFileName());
    	            out.newLine();
    	            //Compare 1 - 2
    	            Result=diffFiles(firstFileContent, secondFileContent);
    	            	for (String res : Result)
	    	            {
	    	            out.write(res);
	    	            out.newLine();
	    	            }    	            
    	            out.write("-----------------------------------------------------------------------------------------------------------------------------------------------");
    	            out.newLine();
    	          //Compare 2 - 1
    	            Result=diffFiles(secondFileContent,firstFileContent);
	            	for (String res : Result)
    	            {
    	            	out.write(res);
    	            	out.newLine();
    	            }		            
		            out.write("-----------------------------------------------------------------------------------------------------------------------------------------------");
		            out.newLine();
	    	    	}
	    	    out.close();
	    System.out.println("End of comparison");	
    }

    public static List<String> diffFiles(List<String> firstFileContent,List<String> secondFileContent) 
    {
    	List<String> diff = new ArrayList<String>(); 
    	
    	//Remove the Version and Timestamp as they wont be identical
    	firstFileContent.remove(3);
    	firstFileContent.remove(3);
    	
    	secondFileContent.remove(3);
    	secondFileContent.remove(3);
    	
    	
    	if(!firstFileContent.equals(secondFileContent))
    	{	
    		diff.add("===================================================Contents / sequence of new and old Playlist are not matching===================================================");    		
    	 
        	Iterator<String> It1 = firstFileContent.iterator();
    		Iterator<String> It2 = secondFileContent.iterator();
    		
    		while (It1.hasNext()&&It2.hasNext()) 
    		{
    			String L1 =It1.next();
    			String L2 =It2.next();
    			
    			if(L1.isEmpty())
    				continue;
    			else if(!L1.equals(L2))
    					diff.add("DIFFERENCE detected - "+L1+" Not same as - "+L2);
    		}	 		 
    	}
    	else
    		diff.add("===================================================Playlists are identical===================================================");    		 	
    	
    	return diff;
    }

}
