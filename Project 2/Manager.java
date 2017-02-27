import java.io.*;
import java.util.Scanner;

public class Manager {
	StringBuilder sb;
	String path;
	String outpath;
	BufferedWriter bw = null;
	FileWriter fw = null;
	ProcessList pl = null;

	
	public Manager() {
		sb = new StringBuilder();
	}
	public Manager(String string) {
		path = string;
		outpath = path.substring(0,path.lastIndexOf('\\'))+"\\80087808.txt";
		System.out.println("Output to: "+ outpath);
		sb = new StringBuilder();
	}

	public void run() {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (sc != null &&sc.hasNextLine()){
			String line = sc.nextLine();
			String[] words = line.split(" ");
			pl = new ProcessList(words);
			sb.append(new FIFO(pl).sim());
			pl = new ProcessList(words);
			sb.append(new SJF(pl).sim());
			pl = new ProcessList(words);
			sb.append(new SRT(pl).sim());
			pl = new ProcessList(words);
			sb.append(new MLF(pl).sim());
		}
		
		if (sc != null )sc.close();

		try {
			fw = new FileWriter(outpath);
			bw = new BufferedWriter(fw);
			bw.write(sb.toString());
			System.out.println("Write finished");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			if (bw != null)
				bw.close();

			if (fw != null)
				fw.close();}
			catch(Exception e){}
			}
		}
}
