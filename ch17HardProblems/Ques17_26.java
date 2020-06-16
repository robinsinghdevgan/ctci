import java.util.*;

public class Ques17_26 {
    public static class Element implements Comparable<Element> {
		public int word;
		public int document;
		public Element(int w, int d) {
			word = w;
			document = d;
		}
		
		public int compareTo(Element e) {
			if (word == e.word) {
				return document - e.document;
			}
			return word - e.word;
		}
	}
	
	public static HashMap<DocPair, Double> computeSimilarities(HashMap<Integer, Document> documents) {
		ArrayList<Element> elements = sortWords(documents);
		HashMap<DocPair, Double> similarities = computeIntersections(elements);
		adjustToSimilarities(documents, similarities);
		return similarities;
	}	
	
	/* Throw all words into one list, sorting by the word then the document. */
	public static ArrayList<Element> sortWords(HashMap<Integer, Document> docs) {
		ArrayList<Element> elements = new ArrayList<Element>();
		for (Document doc : docs.values()) {
			ArrayList<Integer> words = doc.getWords();
			for (int word : words) {
				elements.add(new Element(word, doc.getId()));
			}
		}
		Collections.sort(elements);
		return elements;
	}
	
	/* Increment the intersection size of each document pair. */
	public static void increment(HashMap<DocPair, Double> similarities, int doc1, int doc2) {
		DocPair pair = new DocPair(doc1, doc2);
		if (!similarities.containsKey(pair)) {
			similarities.put(pair, 1.0);
		} else {
			similarities.put(pair, similarities.get(pair) + 1);		
		}
	}
	
	/* Adjust the intersection value to become the similarity. */
	public static HashMap<DocPair, Double> computeIntersections(ArrayList<Element> elements) {
		HashMap<DocPair, Double> similarities = new HashMap<DocPair, Double>();
		
		for (int i = 0; i < elements.size(); i++) {
			Element left = elements.get(i);
			for (int j = i + 1; j < elements.size(); j++) {
				Element right = elements.get(j);
				if (left.word != right.word) {
					break;
				}
				increment(similarities, left.document, right.document);	
			}
		}
		
		return similarities;
	}
	
	/* Adjust the intersection value to become the similarity. */
	public static void adjustToSimilarities(HashMap<Integer, Document> documents, HashMap<DocPair, Double> similarities) {
		for (var entry : similarities.entrySet()) {
			DocPair pair = entry.getKey();
			Double intersection = entry.getValue();
			Document doc1 = documents.get(pair.doc1);
			Document doc2 = documents.get(pair.doc2);
			double union = (double) doc1.size() + doc2.size() - intersection;
			entry.setValue(intersection / union);
		}
	}
	
	public static void main(String[] args) {
		int numDocuments = 10;
		int docSize = 5;
		HashMap<Integer, Document> documents = new HashMap<Integer, Document>();
		for (int i = 0; i < numDocuments; i++) {
			int[] words = randomArray(docSize, 0, 10);
			ArrayList<Integer> w = removeDups(words);
			System.out.println(i + ": " + w.toString());
			Document doc = new Document(i, w);
			documents.put(i, doc);
		}
		
		HashMap<DocPair, Double> similarities = computeSimilarities(documents);
		printSim(similarities);
    }
    
    public static int randomInt(int n) {
		return (int) (Math.random() * n);
	}

	public static int randomIntInRange(int min, int max) {
		return randomInt(max + 1 - min) + min;
	}

	public static int[] randomArray(int N, int min, int max) {
		int[] array = new int[N];
		for (int j = 0; j < N; j++) {
			array[j] = randomIntInRange(min, max);
		}
		return array;
    }
    
    public static ArrayList<Integer> removeDups(int[] array) {
		HashSet<Integer> set = new HashSet<Integer>();
		for (int a : array) {
			set.add(a);
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.addAll(set);
		return list;
    }
    
    public static void printSim(HashMap<DocPair, Double> similarities) {
		for (var result : similarities.entrySet()) {
			DocPair pair = result.getKey();
			Double sim = result.getValue();
			System.out.println(pair.doc1 + ", " + pair.doc2 + " : " + sim);
		}
	}

}

class Document {
	private ArrayList<Integer> words;
	private int docId;
	
	public Document(int id, ArrayList<Integer> w) {
		docId = id;
		words = w;
	}
	
	public ArrayList<Integer> getWords() {
		return words;
	}
	
	public int getId() {
		return docId;
	}
	
	public int size() {
		return words == null ? 0 : words.size();
	}
}

class DocPair {
	public int doc1;
	public int doc2;
	
	public DocPair(int d1, int d2) {
		doc1 = d1;
		doc2 = d2;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof DocPair) { 
			DocPair p = (DocPair) o;
			return p.doc1 == doc1 && p.doc2 == doc2;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (doc1 * 31) ^ doc2;
	}
}