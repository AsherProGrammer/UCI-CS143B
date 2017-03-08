public class BitMap {
	long[] bitmap;
	long[] mask;
	long[] mask2;
	public BitMap() {
		bitmap = new long[32];
		mask = new long[32];
		mask2 = new long[32];
		mask[31] = 1;
		for (int i = 30; i >= 0; i--){
			mask[i] = mask[i+1]<<1;
		}
		for (int i = 0; i < 32; i++) {
			mask2[i] = ~mask[i];
		}
	}

	public void setOne(int i) {
		bitmap[i/32] = bitmap[i/32] | mask[i%32];
	}
	
	public void setZero(int i) {
		bitmap[i/32] = bitmap[i/32] | mask2[i%32];
	}
	

	@Override
	public String toString() {
		String ret  = "";
		for (int i = 0; i < 32;i++){
			ret += Integer.toBinaryString((int) bitmap[i]) + " ";
		}
		return ret;
	}

	public int findOne() {
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 32; j++) {
				long test = bitmap[i] & mask[j];
				if (test == 0) {
					return (i*32 ) + j;
				}
			}
		}
		return -1;
	}

	public int findTwo() {
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 31; j++) {
				long test1 = bitmap[i] & mask[j];
				long test2 = bitmap[i] & mask[j+1];
				if (test1 == 0 && test2 == 0) {
					return (i*32 ) + j;
				}
			}
		}
		return -1;
	}
	
}
