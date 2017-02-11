public class FIFO {
	ProcessList pl;
	public FIFO(ProcessList pl) {
		this.pl = new ProcessList(pl);
	}

	public String sim() {
		int sum = 0;
		String ret = "";
		int clock = 0;
		int size = pl.size();
		Process cur = findFirst(pl);
		while (true){
			if (!cur.serve()){
				pl.remove(cur);
				sum += cur.getRT();
				ret += " "+cur.getRT();
				if (pl.isEmpty()){
					break;
				}
				cur = findFirst(pl);
				cur.setRT(clock-cur.getAT());
				cur.serve();
			}
			if (clock == 400) break;
			clock++;
		}
		double avg = (double) sum/size;
		ret = String.format("%.2f",avg)+ ret +"\n";
		System.out.println(ret);
		return ret;
	}

	private Process findFirst(ProcessList pl) {
		return pl.returnFirst();
	}

}
