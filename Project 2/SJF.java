import java.util.Comparator;
import java.util.PriorityQueue;

public class SJF {
	ProcessList pl;
	int clock = 0;
	PriorityQueue<Process> queue =  null;
	
	
	
	public SJF(ProcessList pl) {
		this.pl = new ProcessList(pl);
		this.clock = pl.getList().get(0).getAT();
	}

	public String sim() {
		int sum = 0;
		String ret = "";
		int size = pl.size();
		int[] time = new int[size];
		int count = 0;
		
		SJFcomparator comparator = new SJFcomparator();
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
			while (cur.serve()){
				clock++;
				offerQueue();
			}
			sum += cur.getRT();
			time[cur.getPID()] = cur.getRT();
			count++;
		}
		
		double avg = (double) sum/size;
		for (int i = 0; i < size; i++){
			ret += String.format(" %d",time[i]);
		}
		ret = String.format("%.2f",avg)+ ret +"\n";

		return ret;
		
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
	
	
	private class SJFcomparator implements Comparator<Process>{
		@Override
		public int compare(Process p1, Process p2) {
			if (p1.getST() < p2.getST()){
				return -1;
			}
			else if (p1.getST() == p2.getST()){
				if (p1.getAT() < p2.getAT())
					return -1;
				else
					return 1;
			}
			else{
				return 1;
			}
		}
		
	}

}
