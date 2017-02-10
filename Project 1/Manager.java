package cs143b; 

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Manager {
	File inputFile;
	File outputFile;
	RL rl;
	boolean console;
	LinkedHashMap<String, RCB> RS;
	LinkedHashMap<String, PCB> PS;
	PCB cur;
	StringBuilder sb;
	String path;
	String outpath;
	BufferedWriter bw = null;
	FileWriter fw = null;

	
	public Manager() {
		console = true;
		sb = new StringBuilder();
	}
	public Manager(String string) {
		path = string;
		outpath = path.substring(0,path.lastIndexOf('\\'))+"\\80087808.txt";
		System.out.println("Output to: "+ outpath);
		console = false;
		sb = new StringBuilder();
	}
	public void run() {
		if (cur == null){
			reset();
			sb.append("init ");
		}
		Scanner sc = null;
		try {
			sc = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		boolean keepGoing = true;
		while(keepGoing&&sc != null &&sc.hasNextLine()){
			String line = sc.nextLine();
			String[] words = line.split(" ");
			if(words.length == 0) { sb.append("\n");continue;}
			switch (words[0]){
			case "init":
				reset();
				sb.append("\ninit ");
				break;
			case "cr":
				String nameCr = words[1];
				int priority = Integer.parseInt(words[2]);
				if(PS.containsKey(nameCr) || priority > 2){
					sb.append("error ");
				}else{
					createHelper(nameCr,priority);
					sb.append(cur.getPID()+" ");
				}
				break;
			case "de":
				String nameDe = words[1];
				deleteHelper(nameDe);
				break;
			case "req":
				if (cur.getPID().equals("init")){
					sb.append("error ");
				}else{
					String nameReq = words[1];
					int need = Integer.parseInt(words[2]);
					if(!request(nameReq,need)){
						sb.append("error ");
						cur = schedular(cur, rl);
					}else{
						cur = schedular(cur, rl);
						sb.append(cur.getPID()+" ");
					}
				}
				break;
			case "rel":
				if (cur.getPID().equals("init")){
					sb.append("error ");
				}else{
					String nameRel = words[1];
					int free = Integer.parseInt(words[2]);
					if(!release(nameRel,free)){
						sb.append("error ");
						cur = schedular(cur, rl);							
					}else{
						cur = schedular(cur, rl);
						sb.append(cur.getPID()+" ");
					}
				}
				break;
			case "to":
				timeout(cur,rl);
				break;
			case "sb":
				break;
			case "quit":
				keepGoing = false;
				break;
			default:
				break;
			}
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

	// create
	private void createHelper(String nameCr, int priority) {
		PCB child = new PCB(nameCr, cur, priority);
		cur.add(child);
		rl.insert(child);
		PS.put(nameCr, child);
		cur = schedular(cur,rl);
	}
	// delete
	private void deleteHelper(String nameDe) {
		if (isKid(nameDe,cur)){
			PCB root = PS.get(nameDe);
			recurKill(root);
			cur = schedular(cur,rl);
			sb.append(cur.getPID()+" ");
		}else{
			sb.append("error ");
		}
	}
	private boolean isKid(String nameDe, PCB root){
		if (!PS.containsKey(nameDe)) return false;
		if (root.getChildren().contains(PS.get(nameDe))||root.getPID().equals(nameDe)){
			return true;
		}else{
			for (PCB child:root.getChildren()){
				if (isKid(nameDe,child)) return true;
			}
			return false;
		}
	}
	private void recurKill(PCB root){
		PS.remove(root.getPID());
		for (PCB child:root.getChildren()){
			recurKill(child);
			}

		for(RCB resource: root.getResources().keySet()){
			releaseHelper(root,resource.getRID(),root.getResources().get(resource));
		}

		if (root.getStatus().equals("blocked")){
			root.getStatusList().remove(root);
		} else {
			root.setStatus("ready");
			rl.remove(root);
		}
		}
	// request
	private boolean request(String nameReq, int n) {
		if (nameReq.equals("R1")){
			if (n > 1 || n <= 0){
				return false;
			}else{
				requestHelper("R1",n);
				return true;
			}
		}else if (nameReq.equals("R2")){
			if (n > 2 ||n <= 0){
				return false;
			}else{
				requestHelper("R2",n);
				return true;
			}
		}else if (nameReq.equals("R3")){
			if (n > 3||n <= 0){
				return false;
			}else{
				requestHelper("R3",n);
				return true;
			}
		}else if (nameReq.equals("R4")){
			if (n > 4||n <= 0){
				return false;
			}else{
				requestHelper("R4",n);
				return true;
			}
		}else{
			return false;
		}
	}
	private void requestHelper(String RID, int n){
		RCB request = RS.get(RID);
		if (request.getCurrentAvailablity() >= n){
			request.decrement(n);
			cur.addResource(request,n);
		}else{
			//System.out.println("* Process "+ cur.getPID() + " is blocked;");
			request.addRequest(cur,n);
			cur.setStatus("blocked");
			cur.setStatusList(request);
			rl.remove(cur);
		}
	}
	// release
	private boolean release(String nameRel, int n){
		if (nameRel.equals("R1")){
			if (!cur.getResources().containsKey(RS.get("R1"))||n > cur.getResources().get(RS.get("R1")) || n <= 0){
				return false;
			}else{
				releaseHelper(cur,"R1",n);
				return true;
			}
		}else if (nameRel.equals("R2")){
			if (!cur.getResources().containsKey(RS.get("R2"))||n > cur.getResources().get(RS.get("R2")) || n <= 0){
				return false;
			}else{
				releaseHelper(cur,"R2",n);
				return true;
			}
		}else if (nameRel.equals("R3")){
			if (!cur.getResources().containsKey(RS.get("R3"))||n > cur.getResources().get(RS.get("R3")) || n <= 0){
				return false;
			}else{
				releaseHelper(cur,"R3",n);
				return true;
			}
		}else if (nameRel.equals("R4")){
			if (!cur.getResources().containsKey(RS.get("R4"))||n > cur.getResources().get(RS.get("R4")) || n <= 0){
				return false;
			}else{
				releaseHelper(cur,"R4",n);
				return true;
			}
		}else{
			return false;
		}
	}
	private void releaseHelper(PCB c, String nameRel, int n) {
		RCB request = RS.get(nameRel);
		if (request == null){
			sb.append("error ");
		}else{
			request.increment(n);
			c.rmResource(request, n);
			while (!request.getWaitingList().isEmpty() && request.hasFirst()&& request.getCurrentAvailablity() >= request.returnFirst().getValue()) {
				PCB pcb = request.returnFirst().getKey();
				int need = request.returnFirst().getValue();
				request.decrement(need);
				request.remove(pcb);
				pcb.setStatus("ready");
				pcb.getResources().put(request, need);
				rl.insert(pcb);
				}
		}
	}

	// timeout
	private void timeout(PCB oldCur, RL rl) {
		rl.remove(oldCur);
		oldCur.setStatus("ready");
		rl.insert(oldCur);
		PCB newCur = rl.findHighest();
		newCur.setStatus("running");
		cur = schedular(newCur,rl);
		sb.append(cur.getPID() + " ");
	}
	//Scheduler
	private PCB schedular(PCB cur,RL rl){
		PCB p = rl.findHighest();
		if (cur.getPriority() < p.getPriority() || cur.getStatus() != "running" || cur == null){
			p.setStatus("running");
			if(cur != null && !cur.getStatus().equals("blocked")){
				//System.out.println(cur.getStatus());
				cur.setStatus("ready");
			}
		}
		return p;
	}
	

	// reset
	public void reset(){
		rl = new RL();
		RS = new LinkedHashMap<String, RCB>();
		PS = new LinkedHashMap<String, PCB>();
		RCB R1 = new RCB("R1",1);
		RCB R2 = new RCB("R2",2);
		RCB R3 = new RCB("R3",3);
		RCB R4 = new RCB("R4",4);
		RS.put("R1", R1);
		RS.put("R2", R2);
		RS.put("R3", R3);
		RS.put("R4", R4);
		cur = rl.findHighest();
		PS.put(cur.getPID(),cur);
	}

}
