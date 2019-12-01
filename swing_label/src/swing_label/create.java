package swing_label;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

public class create {
	String so,de;int c=0;
	File source,destination;
 HashMap<String, String> map_dest =new HashMap<String, String>();
	public create(String so, String de) throws NoSuchAlgorithmException {
		
		this.so = so;
		this.de = de;
		source = new File(so);
	    destination = new File(de);
	    try {
			listsubDirectoriesD(destination.getAbsolutePath());
			getDiff(source,destination);
			JOptionPane.showMessageDialog(null, "the total number of files copied is :"+c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Adding all the sub directories of s folder with the sub directory name as key and its absolute path as value
	private  void listsubDirectoriesD(String absolutePathdestination)throws IOException {
		
		File dfolder = new File(absolutePathdestination);
	   
	    File[] dfiles = dfolder.listFiles();
	    if (dfolder.isDirectory()){
	    	
	    	 //Get all files from source directory
	       // String sourcefiles1[] = sfolder.list();
	   long l=dfiles.length;
	   int i=0;
	   //System.out.println("the length :"+l);

	   
	    for (File file : dfiles)
	    {
	    	 
	    	  if (file.isDirectory())
	        {   
	    		 
	    		  map_dest.put(file.getName(),file.getAbsolutePath());
	    		  listsubDirectoriesD(file.getAbsolutePath());
	            
	            
	            
	        }
	       
	         }
	   }
	}



//////////////////////////////////////////////////////////////////////////////////////////////////////

public void getDiff(File dirA, File dirB) throws IOException, NoSuchAlgorithmException
{
File[] sourceList = dirA.listFiles();
File[] destinationList = dirB.listFiles();
Arrays.sort(sourceList);
Arrays.sort(destinationList);
HashMap<String, File> map1;

	map1 = new HashMap<String, File>();
	for(int i=0;i<destinationList.length;i++)
	{
		map1.put(destinationList[i].getName(),destinationList[i]);
	}
	
	compareNow(sourceList, map1);
	


}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//ultimately we need to check the contents of files only,
//so once a new directory is found or source's directory name matches with destination's directory name
//getDiff(File dirA, File dirB) and hence compareNow(File[] fileArr, HashMap<String, File> map) is called again to find the 
//  matching / not matching files in source directory only with that of destination directory
public void compareNow(File[] srcfileArr, HashMap<String, File> map) throws IOException, NoSuchAlgorithmException
{
for(int i=0;i<srcfileArr.length;i++)
{
	String fName = srcfileArr[i].getName();//obtaining the names of each element of the source directory
	//use that obtained name of the file stored in fName variable as
	//key value for the destination directory whose elements are stored in a map(key name 
	//is same as the File object name
	File fComp = map.get(fName);
	
	map.remove(fName);
	if(fComp!=null)
	{
		if(fComp.isDirectory())
		{
			getDiff(srcfileArr[i], fComp);
		}
		else
		{
			String cSum1 = checksum(srcfileArr[i]);
			String cSum2 = checksum(fComp);
			if(!cSum1.equals(cSum2))
			{
				System.out.println(srcfileArr[i].getName()+"\t\t"+ "different");
				 	File srcFile = new File(srcfileArr[i].getParent());
	                File destFile = new File(fComp.getParent());
	                File srcFile_f = new File(srcFile, fComp.getName());
	                File destFile_f = new File(destFile, fComp.getName());
	              //Copy the file content from one place to another
	                Files.copy(srcFile_f.toPath(), destFile_f.toPath(),StandardCopyOption.REPLACE_EXISTING);
	                System.out.println("File copied :: " + destFile_f);
	                c++;
			}
			else
			{
				System.out.println(srcfileArr[i].getName()+"\t\t"+"identical");
			}
		}
	}
	/////////////////finding unique elements of the source directory section//////////////////
	
		//if the obtained file is a directory then traverse through that directory to get the unique files 
		//that are present in the source directory only
	
	
		else if(srcfileArr[i].isFile())// the obtained File object is a unique file
		{	File srcFile = new File(srcfileArr[i].getParent());
        	File srcFile_f = new File(srcFile, srcfileArr[i].getName());	
        	String a=srcfileArr[i].getParent();
   		 	String b=source.getAbsolutePath();
   		 	System.out.println(a);
   		 
   		 	System.out.println(b);
   		 	boolean t=a.equals(b);
			 if(t)
			 {
				 
	                File destFile_f = new File(destination, srcfileArr[i].getName());
	                Files.copy(srcFile_f.toPath(), destFile_f.toPath(),StandardCopyOption.REPLACE_EXISTING);
	                System.out.println("File copied :: " + destFile_f);
					System.out.println(srcfileArr[i].getName()+"\t\t"+"only in "+srcfileArr[i].getParent());
					c++;
			 }
			 
			 else
			 {
			 
				 System.out.println("------------------------------------------------");
					File sFolder1= new File(srcfileArr[i].getAbsolutePath());
					String k= sFolder1.getName();
					System.out.println("the name of the file is \n"+k);
					String d=sFolder1.getParent();
					System.out.println("the parent of "+k +" is\n"+d);
					int l =d.length();
					System.out.println("the length "+l);
					
					int index1=d.lastIndexOf(File.separator);//returns last index of 's' char value  
					System.out.println(index1);//6  
					String new_str = d.substring(index1+1, l);
					System.out.println("the substring "+new_str);
					String pa=map_dest.get(new_str);
					System.out.println("the absolute path is "+pa);
					
					File s= new File(sFolder1.getParent());
					File s1= new File(s,sFolder1.getName());
					File dd=new File(map_dest.get(new_str));
					File d1 = new File(dd,sFolder1.getName());
					System.out.println("Files copied in \n"+dd);
					Files.copy(s1.toPath(), d1.toPath(),StandardCopyOption.REPLACE_EXISTING);
					c++;
                   
			 }
			 } 
			 }
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public String checksum(File file) throws NoSuchAlgorithmException, IOException 
{
	InputStream fin = null;


	try 
	{
	    fin = new FileInputStream(file);
	    java.security.MessageDigest md5er = MessageDigest.getInstance("MD5");
	    byte[] buffer = new byte[1024];
	    int read;
	    do 
	    {
	    	read = fin.read(buffer);
	    	if (read > 0)
	    		md5er.update(buffer, 0, read);
	    } while (read != -1);
	    fin.close();
	    byte[] digest = md5er.digest();
	    if (digest == null)
	      return null;
	    String strDigest = "0x";
	    for (int i = 0; i < digest.length; i++) 
	    {
	    	strDigest += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1).toUpperCase();
	    }
	    return strDigest;
	} 
	finally
    {
        if (fin != null)
        {
        	fin.close();
        }
        
    }
}
}
