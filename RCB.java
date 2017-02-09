package cs143b;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class RCB {	
	private String RID;
	private int totalAvailablity;
	private int currentAvailablity;
	private LinkedHashMap<PCB,Integer> waitingList;
	
	public RCB(String RID, int availablity){
		this.RID = RID;
		this.totalAvailablity = this.currentAvailablity = availablity;
		this.waitingList = new LinkedHashMap<PCB,Integer>();
	}
	public void increment(int need){
		currentAvailablity += need;
	}
	public void decrement(int need){
		currentAvailablity -= need;
	}
	
	public void wait(PCB PCB, int need){
		waitingList.put(PCB,need);
	}
	
	public Entry<PCB,Integer> returnFirst(){
		return waitingList.entrySet().iterator().next();
	}
	
	public boolean hasFirst(){
		return waitingList.entrySet().iterator().hasNext();
	}
	
	public void addRequest(PCB PCB, int n){
		waitingList.put(PCB,n);
	}
	public void remove(PCB PCB) {
		waitingList.remove(PCB);
	}
	
	public String toString(){
		return RID+"cur"+currentAvailablity;
	}
	
	//getter and setter
	public String getRID() {
		return RID;
	}
	public void setRID(String rID) {
		RID = rID;
	}
	public int getTotalAvailablity() {
		return totalAvailablity;
	}
	public void setTotalAvailablity(int totalAvailablity) {
		this.totalAvailablity = totalAvailablity;
	}
	public int getCurrentAvailablity() {
		return currentAvailablity;
	}
	public void setCurrentAvailablity(int currentAvailablity) {
		this.currentAvailablity = currentAvailablity;
	}
	public LinkedHashMap<PCB,Integer> getWaitingList() {
		return waitingList;
	}
	public void setWaitingList(LinkedHashMap<PCB,Integer> waitingList) {
		this.waitingList = waitingList;
	}
	
}
