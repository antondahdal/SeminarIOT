
import java.util.ArrayList;

public class SlidingWindow {
private ArrayList<String> newList;
private int maxSize;

public SlidingWindow(int maxSize) {
	this.maxSize=maxSize;
	newList=new ArrayList<String>(maxSize);
}

public ArrayList<String> getNewList() {
	return newList;
}

//bm7a mn l [0] wbzed l3er5 ljded 3l [0]
public void add(String e) {
	newList.add(e);
	if(newList.size()>=maxSize) {
		newList.remove(0);
	}
	
}
public int getSize() {
	return maxSize;
}

public void clear() {
	if(newList!=null) {
		newList.clear();
	}
}







}
