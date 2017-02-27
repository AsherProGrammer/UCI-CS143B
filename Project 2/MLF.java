import java.util.Comparator;
import java.util.PriorityQueue;

public class MLF {
	ProcessList pl;
	int clock = 0;
	PriorityQueue<Process> queue =  null;
	
	
	
	public MLF(ProcessList pl) {
		this.pl = new ProcessList(pl);
		this.clock = pl.getList().get(0).getAT();
	}

	public String sim() {
		int sum = 0;
		String ret = "";
		int size = pl.size();
		int[] time = new int[size];
		int count = 0;
		MLFcomparator comparator = new MLFcomparator();
		queue = new PriorityQueue<Process>(size,comparator);
		
		while (true){
			offerQueue();
			Process cur = findFirst();
			if (cur == null && count == size){
				break;
			}
			else if (cur == null && count != size) {
				clock++;
				continue;
			}
			if (cur.serve()){
				if (cur.getST() != 0){
					if (downgrade(cur.getTime()+1))
						cur.setLevel(cur.getLevel()-1);
					queue.offer(cur);
				}
				else{
					sum += cur.getRT();
					time[cur.getPID()] = cur.getRT();
					count++;
				}
				clock++;
			}
		}
		
		double avg = (double) sum/size;
		for (int i = 0; i < size; i++){
			ret += String.format(" %d",time[i]);
		}
		ret = String.format("%.2f",avg)+ ret +"\n";

		return ret;
		
	}
	
	private boolean downgrade(int num){
		if (num == 0 || num == 1)
			return true;
		else if (num % 2 != 0)
			return false;
		else
			return downgrade(num/2);
	}

	private Process findFirst(){
		Process ret = queue.poll();
		if (ret != null && clock > ret.getAT()){
			ret.setRT(clock - ret.getAT());
		}
		return ret;
	}
	
	private void offerQueue(){
		for (Process p: pl.getList().values()){
        	if (p.getAT() == clock){
        		queue.offer(p);
        	}
        }
	}
	
	
	private class MLFcomparator implements Comparator<Process>{
		@Override
		public int compare(Process p1, Process p2) {
			if (p1.getLevel() > p2.getLevel()){
				return -1;
			}
			else if (p1.getLevel() == p2.getLevel()){
				if (p1.getAT() < p2.getAT()){
					return -1;
				}
				else if (p1.getAT() == p2.getAT()){
					if (p1.getPID() < p2.getPID())
						return -1;
					else
						return 1;
				}
				else{
					return 1;
				}
			}
			else{
				return 1;
			}
		}
		
	}

}
