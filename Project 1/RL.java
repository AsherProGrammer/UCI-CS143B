package cs143b;
//RL stands for ready list
import java.util.LinkedList;

public class RL {
	private LinkedList<PCB> p0;
	private LinkedList<PCB> p1;
	private LinkedList<PCB> p2;
	
	public RL(){
		p0 = new LinkedList<PCB>();
		PCB init = new PCB();
		p0.add(init);
		p1 = new LinkedList<PCB>();
		p2 = new LinkedList<PCB>();
	}
	
	public void insert(PCB child) {
		if (child.getPriority() == 2) {
			p2.add(child);
		} else if (child.getPriority() == 1){
			p1.add(child);
		} else if (child.getPriority() == 0){
			p0.add(child);
		}
	}
	
	public PCB findHighest() {
		PCB highest = p0.get(0);
		if (!p2.isEmpty()){
			for (PCB pcb : p2){
				if (!pcb.getStatus().equals("blocked")){
					highest = pcb;
					return highest;
				}
			}
		}
		if (!p1.isEmpty()){
			for (PCB pcb : p1){
				if (!pcb.getStatus().equals("blocked")){
					highest = pcb;
					return highest;
				}
			}
		}
		return highest;
	}

	public void remove(PCB root) {
		if (root.getPriority() == 2) {
			p2.remove(root);
		} else if (root.getPriority() == 1){
			p1.remove(root);
		} else if (root.getPriority() == 0){
			p0.remove(root);
		}
	}
	
	public String toString(){
		String ret = "";
		ret += p2.toString();
		ret += p1.toString();
		ret += p0.toString();
		return ret;
	}
}
