import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class App {
	
	public boolean isSame(String file1 , String file2){
		boolean isSame = false;
		try {
			byte[] b1 = new byte[204800];
			byte[] b2 = new byte[204800];
			File f1 = new File(file1);
			File f2 = new File(file2);
			//File f2 = new File("C:\\Users\\Azhar\\Downloads\\ghi.mp3");
			if(f1.isFile() && f2.isFile()){
				FileInputStream fis1 = new FileInputStream(f1);
				FileInputStream fis2 = new FileInputStream(f2);
				fis1.read(b1);
				fis2.read(b2);
				isSame  = Arrays.equals(b1, b2);
			}
			else
				System.out.println(file1 +" or "+file2+" are not files");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isSame;
	}
	
	public boolean isSameNew(File f1 , File f2){
		boolean isSame = false;
		try {
			byte[] b1 = new byte[204800];
			byte[] b2 = new byte[204800];
			if(f1.isFile() && f2.isFile()){
				FileInputStream fis1 = new FileInputStream(f1);
				FileInputStream fis2 = new FileInputStream(f2);
				fis1.read(b1);
				fis2.read(b2);
				isSame  = Arrays.equals(b1, b2);
			}
			else
				System.out.println(f1 +" or "+f2+" are not files");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isSame;
	}

	public ArrayList<String> getFileNames(String dir) throws Exception{
		ArrayList<String> fileNames = new ArrayList<String>();
		File f=new File(dir);
		if(f.isDirectory()){
			String[] list =  f.list();
			for(String name : list){
				if(name.substring(name.length()-3).equals("mp3")){
					//System.out.println(name);
					fileNames.add(name);
				}
			}
		}
		else
			throw new Exception("Path selected is not a directory");
		return fileNames;
	}
	
	public static void main(String... s) throws Exception{
		//TreeSet<String> duplicates
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSSZ");
		Date d1 = new Date();
		System.out.println("Start Time : "+dateFormat.format(d1));
		int x=-1,y=-1;
		App app = new App();
		String path=s[0];
		ArrayList<String> names = app.getFileNames(path);
		for(int i=0;i<names.size();i++){
			for(int j=0;j<names.size();j++){
				if(i!=j && i!=y && j!=x){
					//System.out.println(path+"\\"+names[i]+"::"+path+"\\"+names[j]);
					boolean same = app.isSame(path+"\\"+names.get(i),path+"\\"+names.get(j));
					//System.out.println(same);
					if(same){
						System.out.println(names.get(i)+"::"+names.get(j));
						x=i;
						y=j;
					}
				}
			}
		}
		Date d2 = new Date();
		System.out.println("End Time : "+dateFormat.format(d2));
	}
}
