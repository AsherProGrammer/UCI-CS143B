import java.util.LinkedHashMap;

public class ProcessList {
	private LinkedHashMap<Integer,Process> list = new LinkedHashMap<Integer,Process>();
	public ProcessList(String[] words) {
		for (int i = 0; i<words.length;i++){
			list.put(i/2, new Process(i/2,Integer.parseInt(words[i]),Integer.parseInt(words[i+1])));
			i++;
		}
	}
	
	public ProcessList(ProcessList copy) {
		this.list = copy.list;
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
	
}
