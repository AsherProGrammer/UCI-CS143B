
public class TLBtuple {
	int freq;
	int sp = -1;
	int frame = -1;
	
	public void inc(){
		freq++;
		if (freq > 3)
			freq = 3;
	}
	
	public void dec(){
		freq--;
		if (freq < -1)
			freq = -1;
	}
	
	public int getFreq() {
		return freq;
	}
	public int getSp() {
		return sp;
	}
	public int getFrame() {
		return frame;
	}
	public void setFreq(int freq) {
		this.freq = freq;
	}
	public void setSp(int sp) {
		this.sp = sp;
	}
	public void setFrame(int frame) {
		this.frame = frame;
	}

	@Override
	public String toString() {
		return "TLBtuple [freq=" + freq + ", sp=" + sp + ", frame=" + frame + "]";
	}
}
