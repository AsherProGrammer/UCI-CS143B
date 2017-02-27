import java.util.Comparator;
import java.util.PriorityQueue;

public class FIFO {
	ProcessList pl;
	int clock;
	PriorityQueue<Process> queue =  null;

	public FIFO(ProcessList pl) {
		this.pl = new ProcessList(pl);
		this.clock = pl.getList().get(0).getAT();
	}

	
	public String sim() {
		int sum = 0;
		String ret = "";
		int size = pl.size();
		int[] time = new int[size];
		
		FIFOcomparator comparator = new FIFOcomparator();
		queue = new PriorityQueue<Process>(size,comparator);
		fillQueue();

		while (true){
			Process cur = findFirst();
			if (cur == null){
				break;
			}
			if (cur.getAT() > clock){
				queue.offer(cur);
				clock++;
				continue;
			}
			else if (!cur.serve()){
				sum += cur.getRT();
				time[cur.getPID()] = cur.getRT();
				continue;
			}
			else{
				queue.offer(cur);
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
	
	private Process findFirst(){
		Process ret = queue.poll();
		if (ret != null && clock > ret.getAT()){
			ret.setRT(clock - ret.getAT());
		}
		return ret;
	}
	
	private void fillQueue(){
		for (Process p: pl.getList().values()){
        	queue.offer(p);
        }
	}
	
	
	private class FIFOcomparator implements Comparator<Process>{
		@Override
		public int compare(Process p1, Process p2) {
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
		
	}



}
