
public class PM {
	int[] frame;
	int size = 1024*512;
	boolean TLB;
	BitMap bm;

	public PM(int S, int F, boolean b) {
		this.TLB = b;
		bm = new BitMap();
		frame = new int[size];
		for (int i = 0; i < 512; i++){
			frame[i] = 0;
		}
		for (int i = 512; i < size; i++){
			frame[i] = -1;
		}
//		System.out.println(bm);
		bm.setOne(0);
		frame[S] = F;
//		System.out.println(bm);
		if (F != -1){
			for (int i = F; i < F+1024; i++){
				frame[i] = 0;
			}
			int i = F/512;
			bm.setOne(i);
			bm.setOne(i+1);
		}
	}

	public void init(String line) {
		String[] param = line.split(" ");
		for (int i = 0; i < param.length; i+=3){
			int index = frame[Integer.parseInt(param[i+1])];
			int page = Integer.parseInt(param[i]);
			int addr = Integer.parseInt(param[i+2]);
			if (index > 0){
				frame[index + page] = addr;
				if (addr != -1){
					for (int j = addr; j < addr + 512; j++){
						frame[j] = 0;
					}
					bm.setOne(addr/512);
				}
			}
		}
//		System.out.println(bm);

	}

	@Override
	public String toString() {
		return "PM [bm=" + bm + "]";
	}

	
	
}
