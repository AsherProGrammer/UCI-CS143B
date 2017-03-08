import java.io.*;
import java.util.Scanner;

public class Initializer {
	StringBuilder sb1;
	StringBuilder sb2;
	BufferedWriter bw1;
	FileWriter fw1;
	BufferedWriter bw2;
	FileWriter fw2;
	PM PM1;
	PM PM2;
	String path1;
	String path2;
	String outpath1;
	String outpath2;
	
	public Initializer() {
		sb1 = new StringBuilder();
		sb2 = new StringBuilder();
	}
	
	public Initializer(String path1, String path2) {
		this.path1 = path1;
		this.path2 = path2;
		outpath1 = path1.substring(0,path1.lastIndexOf('\\'))+"\\800878081.txt";
		outpath2 = path2.substring(0,path2.lastIndexOf('\\'))+"\\800878082.txt";
		System.out.println("Output to: "+ outpath1);
		System.out.println("Output to: "+ outpath2);
		sb1 = new StringBuilder();
		sb2 = new StringBuilder();
	}

	public void run() {
		Scanner sc1 = null;
		Scanner sc2 = null;
		try {
			sc1 = new Scanner(new File(path1));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line1 = sc1.nextLine();
		PM1 = new PM(false); 
		PM2 = new PM(true);
		String line2 = sc1.nextLine();
		PM1.init(line1,line2);
		PM2.init(line1,line2);
		try {
			sc2 = new Scanner(new File(path2));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line3 = sc2.nextLine();
		sb1.append(PM1.translate(line3));
		//System.out.println(sb1);
		sb2.append(PM2.translate(line3));	
		//System.out.println(sb2);
		if (sc1 != null )sc1.close();
		if (sc2 != null )sc2.close();
		try {
			fw1 = new FileWriter(outpath1);
			bw1 = new BufferedWriter(fw1);
			bw1.write(sb1.toString());
			fw2 = new FileWriter(outpath2);
			bw2 = new BufferedWriter(fw2);
			bw2.write(sb2.toString());
			System.out.println("Write finished");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw1 != null)
					bw1.close();
				if (bw2 != null)
					bw2.close();
				if (fw1 != null)
					fw1.close();
				if (fw2 != null)
					fw2.close();
			}
				catch(Exception e){}
		}
	}
}
