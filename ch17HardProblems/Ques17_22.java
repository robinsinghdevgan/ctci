import java.util.*;

class PathNode {
	private String word = null;
	private PathNode previousNode = null;
	public PathNode(String word, PathNode previous) {
		this.word = word;
		previousNode = previous;
	}
	
	public String getWord() {
		return word;
	}
	
	/* Traverse path and return linked list of nodes. */
	public LinkedList<String> collapse(boolean startsWithRoot) {
		LinkedList<String> path = new LinkedList<String>();
		PathNode node = this;
		while (node != null) {
			if (startsWithRoot) {
				path.addLast(node.word);
			} else {
				path.addFirst(node.word);
			}
			node = node.previousNode;
		}
		return path;
	}
}

class BFSData {
	public Queue<PathNode> toVisit = new LinkedList<PathNode>();
	public HashMap<String, PathNode> visited = new HashMap<String, PathNode>();

	public BFSData(String root) {
		PathNode sourcePath = new PathNode(root, null);
		toVisit.add(sourcePath);
		visited.put(root, sourcePath);	
	}
	
	public boolean isFinished() {
		return toVisit.isEmpty();
	}
}

class HashMapList<T, E> {
	private HashMap<T, ArrayList<E>> map = new HashMap<T, ArrayList<E>>();
	
	/* Insert item into list at key. */
	public void put(T key, E item) {
		if (!map.containsKey(key)) {
			map.put(key, new ArrayList<E>());
		}
		map.get(key).add(item);
	}
	
	/* Insert list of items at key. */
	public void put(T key, ArrayList<E> items) {
		map.put(key, items);
	}
	
	/* Get list of items at key. */
	public ArrayList<E> get(T key) {
		return map.get(key);
	}
	
	/* Check if hashmaplist contains key. */
	public boolean containsKey(T key) {
		return map.containsKey(key);
	}
	
	/* Check if list at key contains value. */
	public boolean containsKeyValue(T key, E value) {
		ArrayList<E> list = get(key);
		if (list == null) return false;
		return list.contains(value);
	}
	
	/* Get the list of keys. */
	public Set<T> keySet() {
		return map.keySet();
	}
	
	@Override
	public String toString() {
		return map.toString();
	}
}

public class Ques17_22 {
    public static LinkedList<String> transform(String startWord, String stopWord, String[] words) {
		HashMapList<String, String> wildcardToWordList = getWildcardToWordList(words);
		
		BFSData sourceData = new BFSData(startWord);
		BFSData destData = new BFSData(stopWord);
		
		while (!sourceData.isFinished() && !destData.isFinished()) {
			/* Search out from source. */
			String collision = searchLevel(wildcardToWordList, sourceData, destData);
			if (collision != null) {
				return mergePaths(sourceData, destData, collision);
			}
			
			/* Search out from destination. */
			collision = searchLevel(wildcardToWordList, destData, sourceData);
			if (collision != null) {
				return mergePaths(sourceData, destData, collision);
			}
		}
		
		return null;
	}
	
	/* Search one level and return collision, if any. */
	public static String searchLevel(HashMapList<String, String> wildcardToWordList, BFSData primary, BFSData secondary) {
		/* We only want to search one level at a time. Count how many nodes are currently in the primary's
		 * level and only do that many nodes. We'll continue to add nodes to the end. */
		int count = primary.toVisit.size(); 
		for (int i = 0; i < count; i++) {
			/* Pull out first node. */
			PathNode pathNode = primary.toVisit.poll();
			String word = pathNode.getWord();
			
			/* Check if it's already been visited. */
			if (secondary.visited.containsKey(word)) {
				return pathNode.getWord();
			}				
			
			/* Add friends to queue. */
			ArrayList<String> words = getValidLinkedWords(word, wildcardToWordList);
			for (String w : words) {
				if (!primary.visited.containsKey(w)) {
					PathNode next = new PathNode(w, pathNode);
					primary.visited.put(w, next);
					primary.toVisit.add(next);
				}
			}
		}
		return null;
	}
	
	public static LinkedList<String> mergePaths(BFSData bfs1, BFSData bfs2, String connection) {
		PathNode end1 = bfs1.visited.get(connection); // end1 -> source
		PathNode end2 = bfs2.visited.get(connection); // end2 -> dest
		LinkedList<String> pathOne = end1.collapse(false); // forward: source -> connection
		LinkedList<String> pathTwo = end2.collapse(true); // reverse: connection -> dest
		pathTwo.removeFirst(); // remove connection
		pathOne.addAll(pathTwo); // add second path
		return pathOne;
	}
	
	public static ArrayList<String> getWildcardRoots(String word) {
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i < word.length(); i++) {
			String w = word.substring(0, i) + "_" + word.substring(i + 1);
			words.add(w);
		}
		return words;
	}	
	
	public static HashMapList<String, String> getWildcardToWordList(String[] words) {
		HashMapList<String, String> wildcardToWords = new HashMapList<String, String>();
		for (String word : words) {
			ArrayList<String> linked = getWildcardRoots(word);
			for (String linkedWord : linked) {
				wildcardToWords.put(linkedWord, word);
			}
		}
		return wildcardToWords;
	}
	
	public static ArrayList<String> getValidLinkedWords(String word, HashMapList<String, String> wildcardToWords) {
		ArrayList<String> wildcards = getWildcardRoots(word);
		ArrayList<String> linkedWords = new ArrayList<String>();
		for (String wildcard : wildcards) {
			ArrayList<String> words = wildcardToWords.get(wildcard);
			for (String linkedWord : words) {
				if (!linkedWord.equals(word)) {
					linkedWords.add(linkedWord);
				}
			}
		}
		return linkedWords;
	}	

	public static void main(String[] args) {
		String[] words = {"maps", "tan", "tree", "apple", "cans", "help", "aped", "pree", "pret", "apes", "flat", "trap", "fret", "trip", "trie", "frat", "fril"};		
		LinkedList<String> list = transform("tree", "flat", words);
		
		if (list == null) {
			System.out.println("No path.");
		} else {
			System.out.println(list.toString());
		}
	}
}