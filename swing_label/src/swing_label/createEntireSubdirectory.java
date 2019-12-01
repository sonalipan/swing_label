package swing_label;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.HashMap;

public class createEntireSubdirectory {
 String so,de;int countCopy=0;File source,destination;int c=0;
 String destPathName;
	//////////////////////////////////////////////////////////

		
	
	public createEntireSubdirectory(String so, String de) {
		
		this.so = so;
		this.de = de;
		 source = new File(so);
		 destination = new File(de);
		 destPathName= destination.getAbsolutePath();
		
		
		try {
			 listingFiles(source.getAbsolutePath());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

private  void listingFiles(String absolutePathdestination)throws IOException {
		
		File dfolder = new File(absolutePathdestination);
       
        File[] dfiles = dfolder.listFiles();
        if (dfolder.isDirectory()){
        	
       long l=dfiles.length;
       int i=0;
     
    
       
        for (File file : dfiles)
        {
        	 
        	  if (file.isDirectory())
            {   
        		  String gfg = new String(file.getAbsolutePath()); 
        		  int firstOccurence=gfg.indexOf(File.separator);
        	       
        	        System.out.println(gfg.indexOf(File.separator,firstOccurence+1)); 
        	        int secondOccurence =gfg.indexOf(File.separator,firstOccurence+1);
        	        long l1 =gfg.length();
        			String new_str = gfg.substring(secondOccurence+1);
        			System.out.println("the substring "+new_str);
        			Path path = Paths.get(destPathName+File.separator+new_str);
        			if (!Files.exists(path))
        			{
        	            
        	            Files.createDirectories(path);
        	         
        	            
        	        }
        		  
                
                listingFiles(file.getAbsolutePath());
               
                
                
            }
           
            
        
        
        }
       }
        }
	


//////////////////////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////////////////////////////////



}
	



