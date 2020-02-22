import java.util.*;

class MyHashTable
{
	private ArrayList<LinkedList<String>> ht;
	private int sz = 0;

	public MyHashTable(int size){
		sz = size;
        ht = new ArrayList<LinkedList<String>>(size);
        for (int i = 0; i < size; ++i)
            ht.add(new LinkedList<String>());
	}

	public final int hash(String s)
	{
		int charSum = 0;
		for (char c : s.toCharArray()) {
			charSum += c;
		}
		return Math.abs(s.hashCode() + s.length() + charSum) % sz;
	}

	public final int size() {
		return sz;
	}

	public final boolean containsKey(String key) {
		if (ht.get(hash(key)).size() > 0) {
			return true;
		}
		return false;
	}

	public final void print() {
		int i = 0;
		for (LinkedList<String> l : ht) {
			System.out.print(i + ") ");
			for (var s : l) {
				System.out.print((s + " -> "));
			}
			System.out.print("\n");
			++i;
		}
	}

	public final void put(String key) {
		ht.get(hash(key)).addLast(key);
	}
}

public class Ques7_12 {
	public static void main(String[] args) {
		MyHashTable mht = new MyHashTable(10);
		String[] sa = {"a", "ab", "abc", "abcd", "abcde", "abcdef"};
		for (String s : sa){
			mht.put(s);
		}
		mht.print();
	}
}
