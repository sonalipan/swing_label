package swing_label;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class compare {
	String source,destination;int count=0;

	public compare(String source, String destination) throws NoSuchAlgorithmException  {
		
		this.source = source;
		this.destination = destination;
		File so = new File(source);
		File de = new File(destination);
		try {
			getDiff(so, de);
			System.out.println("the total number of new files in source is : "+ count);
			JOptionPane.showMessageDialog(null, "the total number of new files in source is :"+count);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
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
	//ultimately we need to check the contents of files only,
	//so once a new directory is found or source's directory name matches with destination's directory name
	//getDiff(File dirA, File dirB) and hence compareNow(File[] fileArr, HashMap<String, File> map) is called again to find the 
	//  matching / not matching files in source directory only with that of destination directory
	public void compareNow(File[] fileArr, HashMap<String, File> map) throws IOException, NoSuchAlgorithmException
	{
		for(int i=0;i<fileArr.length;i++)
		{
			String fName = fileArr[i].getName();//obtaining the names of each element of the source directory
			//use that obtained name of the file stored in fName variable as
			//key value for the destination directory whose elements are stored in a map(key name 
			//is same as the File object name
			File fComp = map.get(fName);
			map.remove(fName);
			if(fComp!=null)
			{
				if(fComp.isDirectory())
				{
					getDiff(fileArr[i], fComp);
				}
				else
				{
					String cSum1 = checksum(fileArr[i]);
					String cSum2 = checksum(fComp);
					if(!cSum1.equals(cSum2))
					{
						System.out.println(fileArr[i].getName()+"\t\t"+ "different");
						count++;
					}
					else
					{
						System.out.println(fileArr[i].getName()+"\t\t"+"identical");
					}
				}
			}
			/////////////////finding unique elements of the source directory section//////////////////
			else
				//if the obtained file is a directory then traverse through that directory to get the unique files 
				//that are present in the source directory only
			{
				if(fileArr[i].isDirectory())
				{
					traverseDirectory(fileArr[i]);
				}
				else// the obtained File object is a unique file
				{
					System.out.println(fileArr[i].getName()+"\t\t"+"only in "+fileArr[i].getParent());
					count++;
				}
			}
		}
		
	}
	
	public void traverseDirectory(File dir)
	{
		File[] list = dir.listFiles();
		for(int k=0;k<list.length;k++)
		{
			if(list[k].isDirectory())
			{
				traverseDirectory(list[k]);
			}
			else
			{
				System.out.println(list[k].getName() +"\t\t"+"only in "+ list[k].getParent());
				count++;
			}
		}
	}
	
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
