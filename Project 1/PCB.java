package cs143b;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class PCB {
	private String PID;
	private LinkedHashMap<RCB,Integer> resources;
	private String status;
	private RCB statusList;
	private PCB parent;
	private LinkedList<PCB> children;
	private int priority;

	public PCB(){
		this.PID = "init";
		this.resources = new LinkedHashMap<RCB,Integer>();
		this.status = "ready";
		this.statusList = null;
		this.parent = null;
		this.children = new LinkedList<PCB>();
		this.priority = 0;
	}
	
	public PCB(String PID, PCB parent, int priority){
		this.PID = PID;
		this.resources = new LinkedHashMap<RCB,Integer>();
		this.status = "ready";
		this.statusList = null;
		this.parent = parent;
		this.children = new LinkedList<PCB>();
		this.priority = priority;
	}
	
	public boolean add(PCB child){		
		return this.children.add(child);
	}
	
	public void addResource(RCB resource, int n){
		resources.put(resource, n+resources.getOrDefault(resource, 0));
	}
	
	public void rmResource(RCB resource, int n) {
		if (n == resources.get(resource)){
			resources.remove(resource);
		}else{
			resources.put(resource, resources.get(resource)-n);
		}
	}
	
	public String toString(){

		String ret =  "PID:= " + PID + "\nStatus:= " + status + "\nPriority:= " + priority;
		if (parent != null)
			ret += "\nParent:= " + parent.getPID();
		for (Map.Entry<RCB, Integer> entry: resources.entrySet()){
			ret += "\n Resouce details: " + entry.getKey().toString() + " " +entry.getValue();
		}
		ret += statusList;
		ret += "\n ------- Children: -------";
		for (PCB child:children){
			ret += "\n"+child.getPID()+" AND ";
		}

		return ret;
	}
	
	

	//getter and setter

	public String getPID() {
		return PID;
	}
	public void setPID(String PID) {
		this.PID = PID;
	}
	public LinkedHashMap<RCB, Integer> getResources() {
		return resources;
	}
	public void setResources(LinkedHashMap<RCB, Integer> resources) {
		this.resources = resources;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public PCB getParent() {
		return parent;
	}
	public void setParent(PCB parent) {
		this.parent = parent;
	}
	public LinkedList<PCB> getChildren() {
		return children;
	}
	public void setChildren(LinkedList<PCB> children) {
		this.children = children;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public RCB getStatusList() {
		return statusList;
	}
	public void setStatusList(RCB statusList) {
		this.statusList = statusList;
	}

}
