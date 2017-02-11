
public class Process {
	private int PID;
	private int AT; //arrival time
	private int ST; //(remaining) service time
	private int RT; //run time
	
	public Process(int PID, int AT, int ST){
		this.PID = PID;
		this.AT = AT;
		this.ST = ST;
		this.RT = 0;
	}

	public int getPID() {
		return PID;
	}
	
	public boolean serve(){
		if (ST != 0){
			this.ST--;
			this.RT++;
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "Process [PID=" + PID + ", AT=" + AT + ", ST=" + ST + ", RT=" + RT + "]";
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
}
