
public class Process {
	int PID;
	int AT; //arrival time
	int ST; //remaining service time
	int RT; //run time
	int level;
	int time;
	
	public Process(int PID, int AT, int ST){
		this.PID = PID;
		this.AT = AT;
		this.ST = ST;
		this.RT = 0;
		this.level = 5;
		this.time = 0;
	}

	public int getPID() {
		return PID;
	}
	
	public boolean serve(){
		if (ST != 0){
			this.ST--;
			this.RT++;
			this.time++;
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "\nProcess [PID=" + PID + ", AT=" + AT + ", ST=" + ST + ", RT=" + RT + ", Level=" + level
				+ ", Time=" + time + "]";
	}

	public void setPID(int PID) {
		this.PID = PID;
	}

	public int getAT() {
		return AT;
	}

	public void setAT(int AT) {
		this.AT = AT;
	}

	public int getST() {
		return ST;
	}

	public void setST(int ST) {
		this.ST = ST;
	}
	
	public int getRT() {
		return RT;
	}
	
	public void setRT(int RT) {
		this.RT = RT;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
}
