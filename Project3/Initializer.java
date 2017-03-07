import java.io.*;
import java.util.Scanner;

public class Initializer {
	StringBuilder sb;
	BufferedWriter bw;
	FileWriter fw;
	PM PM1;
	PM PM2;
	VA VA;
	String path1;
	String path2;
	String outpath1;
	String outpath2;
	
	public Initializer() {
		sb = new StringBuilder();
	}
	
	public Initializer(String path1, String path2) {
		this.path1 = path1;
		this.path2 = path2;
		outpath1 = path1.substring(0,path1.lastIndexOf('\\'))+"\\800878081.txt";
		outpath2 = path2.substring(0,path2.lastIndexOf('\\'))+"\\800878082.txt";
		System.out.println("Output to: "+ outpath1);
		System.out.println("Output to: "+ outpath2);
		sb = new StringBuilder();
	}

	public void run() {
		Scanner sc1 = null;
		Scanner sc2 = null;
		try {
			sc1 = new Scanner(new File(path1));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = sc1.nextLine();
		String[] words = line.split(" ");
		PM1 = new PM(Integer.parseInt(words[0]),Integer.parseInt(words[1]),false); 
		//PM2 = new PM(Integer.parseInt(words[0]),Integer.parseInt(words[1]),true);
		line = sc1.nextLine();
		PM1.init(line);
		//PM2.init(line);
		try {
			sc2 = new Scanner(new File(path2));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		line = sc2.nextLine();
//		VA.init(line);
		//
		//
		//
		//
		//
		//
//		System.out.println(line);
//
//		if (sc1 != null )sc1.close();
//		if (sc2 != null )sc2.close();
//		try {
//			fw = new FileWriter(outpath1);
//			bw = new BufferedWriter(fw);
//			bw.write(sb.toString());
//			fw = new FileWriter(outpath2);
//			bw = new BufferedWriter(fw);
//			bw.write(sb.toString());
//			System.out.println("Write finished");
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (bw != null)
//					bw.close();
//				if (fw != null)
//					fw.close();}
//				catch(Exception e){}
//		}
	}
}
