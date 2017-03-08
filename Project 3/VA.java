
public class VA {
	int va;
	int s;
	int p;
	int w;
	int sp;
	
	public VA(int addr) {
		va = addr;
		String bi = Integer.toBinaryString(addr);
		int length = bi.length();
		for (int i = length; i < 32; i++){
			bi = "0"+bi;
		}
		s = Integer.parseInt(bi.substring(4, 13),2);
		p = Integer.parseInt(bi.substring(13,23),2);
		w = Integer.parseInt(bi.substring(23),2);
		sp = Integer.parseInt(bi.substring(4,23),2);
	}

	public int getVa() {
		return va;
	}

	public int getS() {
		return s;
	}

	public int getP() {
		return p;
	}

	public int getW() {
		return w;
	}

	public int getSp() {
		return sp;
	}


}
