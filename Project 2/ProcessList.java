import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;


public class ProcessList {
	LinkedHashMap<Integer,Process> list = new LinkedHashMap<Integer,Process>();

	public ProcessList(String[] words) {
		for (int i = 0; i<words.length;i++){
			list.put(i/2, new Process(i/2,Integer.parseInt(words[i]),Integer.parseInt(words[i+1])));
			i++;
		}
	}
	
	public ProcessList(ProcessList copy) {
		this.list = copy.list;
	}

	@Override
	public String toString() {
		return "ProcessList [list=" + list + "]";
	}

	public boolean isEmpty(){
		return this.list.isEmpty();
	}

	public void remove(Process cur) {
		list.remove(cur.getPID());
	}
	
	public int size(){
		return this.list.size();
	}
	
	public Process returnFirst(){
		return list.entrySet().iterator().next().getValue();
	}
	
	public LinkedHashMap<Integer,Process> getList(){
		return list;
	}
	
}
