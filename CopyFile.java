package IOWork;

import java.io.*;

public class CopyFile {
	public static void copyFile(String srcFile, String destFile){
		File src = new File(srcFile);
		File dest = new File(destFile);
		try(FileInputStream fsrc=new FileInputStream(src);FileOutputStream fdest=new FileOutputStream(dest)){
			byte all[]=new byte[(int)src.length()];
			fsrc.read(all);
			fdest.write(all);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void copyFolder(String srcFolder, String destFolder){
		File src = new File(srcFolder);
		//验证是否存在这个这个路径，如果不存在就创建该路径
		File dest = new File(destFolder);
		if(!dest.exists() && !dest.isDirectory()) {
			dest.mkdirs();
		}
		
		File list[]=src.listFiles();
		String Fname[]=src.list();
		for(int i=0;i<list.length;i++) {
			copyFile(list[i].getAbsolutePath(), destFolder+"/"+Fname[i]);
		}
	}
	
	public static void search(File folder, String search) {
		File list[]=folder.listFiles();
		for(int i=0;i<list.length;i++) {
			try(FileInputStream f=new FileInputStream(list[i])){
				byte all[]=new byte[(int)list[i].length()];
				f.read(all);
				String str = new String(all);
				if(str.indexOf(search)!=-1) {
					System.out.println("找到子目标串"+search+",在文件："+list[i].getAbsolutePath());
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		//File f1=new File("d:/lol.txt");
		//File f2=new File("d:/lol2.txt");
		File folder=new File("d:/test/testFile01");
		copyFile("d:/lol.txt","d:/lol2.txt");
		copyFolder("d:/test/testFile01","d:/test/testFile02");
		search(folder,"abc");
	}
}
