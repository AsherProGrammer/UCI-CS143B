
public class PM {
	int[] frame;
	int size = 1024*512;
	boolean TLB;
	BitMap bm;
	TLB tlb;

	public PM(boolean b) {
		this.TLB = b;
		this.tlb = new TLB();
		bm = new BitMap();
		frame = new int[size];
		for (int i = 0; i < 512; i++){
			frame[i] = 0;
		}
		for (int i = 512; i < size; i++){
			frame[i] = -1;
		}
		bm.setOne(0);
	}

	public void init(String line1, String line2) {
		String[] param1 = line1.split(" ");
		for (int i = 0; i < param1.length; i+=2){
			int segment = Integer.parseInt(param1[i]);
			int addr = Integer.parseInt(param1[i+1]);
			frame[segment] = addr;
			if (addr != -1) {
				for (int j = addr; j < addr + 1024; j++) {
					frame[j] = 0;
				}
				int k = addr/512;
				bm.setOne(k);
				bm.setOne(k+1);
			}
		}
		
		String[] param2 = line2.split(" ");
		for (int i = 0; i < param2.length; i+=3){
			int index = frame[Integer.parseInt(param2[i+1])];
			int page = Integer.parseInt(param2[i]);
			int addr = Integer.parseInt(param2[i+2]);
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
	}

	@Override
	public String toString() {
		return "PM [bm=" + bm + "]";
	}

	public String translate(String line) {
		String ret = "";
		String[] words = line.split(" ");
		VA va = null;
		if (TLB == false){
			for (int i = 0; i < words.length; i+=2){
				va = new VA(Integer.parseInt(words[i+1]));
				if (Integer.parseInt(words[i]) == 0)
					ret += read(va)+" ";
				else
					ret += write(va) + " ";
			}
		}
		else{
			for (int i = 0; i < words.length; i+=2){
				va = new VA(Integer.parseInt(words[i+1]));
//				System.out.println("@Before");
//				System.out.println("va: "+va.getVa());
//				System.out.println("va binary: "+Integer.toBinaryString(va.getVa()));
//				System.out.println("tlb:"+tlb);
				if (Integer.parseInt(words[i]) == 0)
					ret += readTLB(va)+" ";
				else
					ret += writeTLB(va) + " ";
//				System.out.println("@After");
//				System.out.println("tlb:"+tlb);

			}
		}
		return ret;
	}

	private String writeTLB(VA va) {
		int sp = va.getSp();
		if (tlb.search(sp)){
			return "h "+(tlb.highest()+va.getW());
		}
		int s = frame[va.getS()];
		int p = frame[s+va.getP()];
		String F = write(va);
//		System.out.println("result is "+F);
		if (!F.equals("pf")&&!F.equals("err")){
			int f = Integer.parseInt(F);
			tlb.update(sp,f);
		}
		return "m "+F;
	}

	private String readTLB(VA va) {
		int sp = va.getSp();
		if (tlb.search(sp)){
			return "h "+(tlb.highest()+va.getW());
		}
		int s = frame[va.getS()];
		int p = frame[s+va.getP()];
		String F = read(va);
		if (!F.equals("pf")&&!F.equals("err")){
			if (s != -1 && p != -1)
				tlb.update(sp,p);	
		}
//		System.out.println(va.getVa());
//		System.out.println(sp);
//		System.out.println(p);
//		System.out.println(tlb);
		return "m "+read(va);
	}
	

	private String read(VA va) {
		int s = frame[va.getS()];
		if (s == -1){
			return "pf";
		}
		if (s == 0){
			return "err";
		}
		int p = frame[s+va.getP()];
		if (p == -1){
			return "pf";
		}
		if (p == 0){
			return "err";
		}
		int w = p+va.getW();
		return ""+w;
	}
	
	private String write(VA va) {
		int s = frame[va.getS()];
		if (s == -1){
			return "pf";
		}
		int p = frame[s+va.getP()];
		if (p == -1){
			return "pf";
		}
		if (s == 0){
			int i = bm.findTwo();
			bm.setOne(i);
			bm.setOne(i+1);
			int F = i * 512;
			frame[va.getS()] = F;
			for ( int j = F; j < F+1024;j++){
				frame[j] = 0;
			}
			int j = bm.findOne();
			bm.setOne(j);
			int P = j*512;
			frame[F+va.getP()] = P;
			for (int k = P; k < P + 512; k++){
				frame[k] = 0;
			}
		} else if (p == 0){
			int i = bm.findOne();
			bm.setOne(i);
			int F = i * 512;
			frame[frame[va.getS()]+va.getP()] = F;
			for (int k = F; k < F + 512; k++){
				frame[k] = 0;
			}
		}
		int w = frame[frame[va.getS()]+va.getP()] + va.getW();
		return ""+w;
	}
}