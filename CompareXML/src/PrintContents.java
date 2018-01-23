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

public class PrintContents 
{    

	public static void main(final String[] args) throws IOException 
    {
        //Set Playlist folders
		String NewPlaylistFolder = "C://TEMP//New";
    	String OldPlaylistFolder = "C://TEMP//Old";
		
    	//Create File
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss") ;
		File file = new File("C://TEMP//PlaylistComparison"+dateFormat.format(date) + ".txt") ;
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		out.write("************************************************************************PlayLists Comparison Report************************************************************************");
		out.newLine();
    	
    	//Make a List of playlist names  
    	List<String> Playlists = new  ArrayList<String>(); 
    	File folder = new File(NewPlaylistFolder);
    	File[] listOfFiles = folder.listFiles();
    	List<String> Result = new  ArrayList<String>();

    	    for (int i = 0; i < 1; i++) 
    	    {
    	      if (listOfFiles[i].isFile()&&listOfFiles[i].getName().toLowerCase().endsWith(".xml")) 
    	        Playlists.add(listOfFiles[i].getName());
    	     }
    	
    	   //Compare contents of 2 playlists with same name 
    	    for(String str : Playlists)  
    	    {
    	    	if(str==null)
    	    		continue;
    	    	else
	    	    	{
    	    		Path firstFile = Paths.get(NewPlaylistFolder+"//"+str);
    	            Path secondFile = Paths.get(OldPlaylistFolder+"//"+str);
    	            
    	            List<String> firstFileContent = Files.readAllLines(firstFile,Charset.defaultCharset());
    	            List<String> secondFileContent = Files.readAllLines(secondFile,Charset.defaultCharset());
    	            
    	            //Run diff and print result
    	            out.write(firstFile.getName(0).toString()+"/"+firstFile.getName(1).toString()+"/"+firstFile.getFileName()+" COMPARED WITH "+secondFile.getName(0).toString()+"/"+secondFile.getName(1).toString()+"/"+secondFile.getFileName());
    	            out.newLine();
    	            System.out.println(firstFile.getName(0).toString()+"/"+firstFile.getName(1).toString()+"/"+firstFile.getFileName()+" COMPARED WITH "+secondFile.getName(0).toString()+"/"+secondFile.getName(1).toString()+"/"+secondFile.getFileName());    	         
    	            Result=diffFiles(firstFileContent, secondFileContent);
    	            	for (String res : Result)
	    	            {
	    	            	out.write(res);
	    	            	System.out.println(res);
	    	            	out.newLine();
	    	            }
    	            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
    	            out.write("-----------------------------------------------------------------------------------------------------------------------------------------------");
    	            out.newLine();
    	            Result=diffFiles(secondFileContent,firstFileContent);
	            	for (String res : Result)
    	            {
    	            	out.write(res);
    	            	System.out.println(res);
    	            	out.newLine();
    	            }
	            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
	            out.write("-----------------------------------------------------------------------------------------------------------------------------------------------");
	            out.newLine();
	    	    	}
    	    	
    	    	}
    	    out.close();
    }

    public static List<String> diffFiles(List<String> firstFileContent,List<String> secondFileContent) 
    {
    	List<String> diff = new ArrayList<String>();
    	 
    	firstFileContent.remove(3);
    	firstFileContent.remove(3);
    	
    	 
    		for (String line : firstFileContent) 
            {	
    			diff.add(line);
 	             
            }
    		 
    	return diff;
    }
  
    //IF contents are same but order of elements is different
    public static List<String> advancedDiffFiles(List<String> firstFileContent,List<String> secondFileContent) 
    {
    	List<String> diff = new ArrayList<String>();
    	Iterator<String> It1 = firstFileContent.iterator();
		Iterator<String> It2 = secondFileContent.iterator();
		while (It1.hasNext()&&It2.hasNext()) 
		{
			String L1 =It1.next();
			String L2 =It2.next();
			
			if(L1.isEmpty())
				continue;
			else
			{	 
				if(!L1.equals(L2))
					diff.add("DIFFERENCE in ordering detected - "+L1+" Not same as - "+L2);
			}
		}
    	return diff;
    }
       
}
