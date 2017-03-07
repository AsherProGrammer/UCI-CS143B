import java.util.Arrays;

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
//			System.out.println("I:="+mask[i]);
		}
		for (int i = 0; i < 32; i++) {
			mask2[i] = ~mask[i];
		}
//		System.out.println(mask[0]);
	}

	public void setOne(int i) {
//		System.out.println(bitmap[i/32]);
//		System.out.println(mask[i%32]);
		bitmap[i/32] = bitmap[i/32] | mask[i%32];
	}
	
	public void setZero(int i) {
		bitmap[i/32] = bitmap[i/32] | mask2[i%32];
	}
	
	public int search() {
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 32; j++) {
				long test = bitmap[i] & mask[j];
				if (test == 0) {
//					if (i == 0) {
//						return j;
//					} else {
//						return 32 + j;
//					}
				}
			}
		}
		return -1;
	}

	@Override
	public String toString() {
		return "BitMap [bitmap=" + Arrays.toString(bitmap) + "]";
	}
	
}
