import java.util.*;

class HtWt implements Comparable<HtWt> {
	private int height;
	private int weight;
	public HtWt(int h, int w) { height = h; weight = w; }
	
	public int compareTo(HtWt second) {
		if (this.height != second.height) { 
			return ((Integer)this.height).compareTo(second.height);
		} else {
			return ((Integer)this.weight).compareTo(second.weight);
		}
	}
	
	public String toString() {
		return "(" + height + ", " + weight + ")";
	}
	
	/* Returns true if "this" should be lined up before "other". Note 
	 * that it's possible that this.isBefore(other) and 
	 * other.isBefore(this) are both false. This is different from the 
	 * compareTo method, where if a < b then b > a. */
	public boolean isBefore(HtWt other) {
		if (height < other.height && weight < other.weight) {
			return true;
		} else {
			return false;
		}
	}
}

public class Ques17_8 {
    // Returns longer sequence
	private static ArrayList<HtWt> max(ArrayList<HtWt> seq1, ArrayList<HtWt> seq2) {
		if (seq1 == null) {
			return seq2;
		} else if (seq2 == null) {
			return seq1;
		}
		return seq1.size() > seq2.size() ? seq1 : seq2;
	}
	
	private static boolean canAppend(ArrayList<HtWt> solution, HtWt value) {
		if (solution == null) {
			return false;
		}
		if (solution.size() == 0) {
			return true;
		}
		HtWt last = solution.get(solution.size() - 1);
		return last.isBefore(value);
	}
	
	public static ArrayList<HtWt> longestIncreasingSeq(ArrayList<HtWt> array) {
		Collections.sort(array);
		
		ArrayList<ArrayList<HtWt>> solutions = new ArrayList<ArrayList<HtWt>>();
		ArrayList<HtWt> bestSequence = null;
		for (int i = 0; i < array.size(); i++) {
			ArrayList<HtWt> longestAtIndex = bestSeqAtIndex(array, solutions, i);
			solutions.add(i, longestAtIndex);
			bestSequence = max(bestSequence, longestAtIndex);
		}
		
		return bestSequence;
	}
	
	private static ArrayList<HtWt> bestSeqAtIndex(ArrayList<HtWt> array, ArrayList<ArrayList<HtWt>> solutions, int index) {
		HtWt value = array.get(index);
		
		ArrayList<HtWt> bestSequence = new ArrayList<HtWt>();
		
		for (int i = 0; i < index; i++) {
			ArrayList<HtWt> solution = solutions.get(i);
			if (canAppend(solution, value)) {
				bestSequence = max(solution, bestSequence);
			}
		}
		
		ArrayList<HtWt> best = (ArrayList<HtWt>) bestSequence.clone();
		best.add(value);
		
		return best;
	}
	
	public static ArrayList<HtWt> initialize() {
		ArrayList<HtWt> items = new ArrayList<HtWt>();
		
		HtWt item = new HtWt(65, 60);
		items.add(item);
		
		item = new HtWt(70, 150);
		items.add(item);
		
		item = new HtWt(56, 90);
		items.add(item);
		
		item = new HtWt(75, 190);
		items.add(item);
		
		item = new HtWt(60, 95);
		items.add(item);
		
		item = new HtWt(68, 110);
		items.add(item);
		
		item = new HtWt(35, 65);
		items.add(item);
		
		item = new HtWt(40, 60);
		items.add(item);
		
		item = new HtWt(45, 63);
		items.add(item);
		
		return items;
	}
	
	public static void printList(ArrayList<HtWt> list) {
		for (HtWt item : list) {
			System.out.println(item.toString() + " ");
		}
	}
	
	public static void main(String[] args) {
		ArrayList<HtWt> items = initialize();
		ArrayList<HtWt> solution = longestIncreasingSeq(items);
		printList(solution);
	}
}