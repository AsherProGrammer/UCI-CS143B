import java.util.Arrays;

public class TLB {
	TLBtuple[] LRU;
	
	public TLB(){
		LRU = new TLBtuple[4];
		for (int i = 0; i < 4; i++){
			LRU[i] = new TLBtuple();
		}
	}
	
	public boolean search(int sp){
		int index = 0;
		for (int i = 0; i < 4; i++){
			if (LRU[i].getSp() == sp){
				index = i;
				for (int j = 0; j< 4; j++){
					if (LRU[j].getFreq() > LRU[index].getFreq()){
						LRU[j].dec();
					}
				}
				LRU[index].setFreq(3);
				return true;
			}
		}
		return false;
	}
	
	public int highest(){
		for (int i = 0; i < 4; i++){
			if (LRU[i].getFreq() == 3){
				return LRU[i].getFrame();
			}
		}
		return 0;
	}
	public void update(int sp, int frame){
		for (int i = 0; i < 4; i++){
			LRU[i].dec();
		}
		for (int i = 0; i < 4; i++){
			if (LRU[i].getFreq() == -1){
				LRU[i].setFreq(3);
				LRU[i].setSp(sp);
				LRU[i].setFrame(frame);
				return;
			}
		}
	}

	@Override
	public String toString() {
		return "TLB [LRU=" + Arrays.toString(LRU) + "]";
	}
	
	
}
