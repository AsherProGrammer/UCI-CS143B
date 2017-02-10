
public class Process {
	int PID;
	int AT;
	int ST;
	
	public Process(int PID, int AT, int ST){
		this.PID = PID;
		this.AT = AT;
		this.ST = ST;
	}

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public int getAT() {
		return AT;
	}

	public void setAT(int aT) {
		AT = aT;
	}

	public int getST() {
		return ST;
	}

	public void setST(int sT) {
		ST = sT;
	}
}
