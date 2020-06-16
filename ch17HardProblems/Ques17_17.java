import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Ques17_17 {
    public static Trie createTreeFromStrings(String[] smalls, int maxSize) {
        Trie tree = new Trie();
        for (String s : smalls) {
            if (s.length() <= maxSize) {
                tree.insertString(s, 0);
            }
        }
        return tree;
    }

    public static ArrayList<String> findStringsAtLoc(TrieNode root, String big, int start) {
        ArrayList<String> strings = new ArrayList<String>();
        int index = start;
        while (index < big.length()) {
            root = root.getChild(big.charAt(index));
            if (root == null)
                break;
            if (root.terminates()) {
                strings.add(big.substring(start, index + 1));
            }
            index++;

        }
        return strings;
    }

    public static void insertIntoHashMap(ArrayList<String> strings, HashMapList<String, Integer> map, int index) {
        for (String s : strings) {
            map.put(s, index);
        }
    }

    public static HashMapList<String, Integer> searchAll(String big, String[] smalls) {
        HashMapList<String, Integer> lookup = new HashMapList<String, Integer>();
        TrieNode root = createTreeFromStrings(smalls, big.length()).getRoot();

        for (int i = 0; i < big.length(); i++) {
            ArrayList<String> strings = findStringsAtLoc(root, big, i);
            insertIntoHashMap(strings, lookup, i);
        }

        return lookup;
    }

    public static void main(String[] args) {
        String big = "mississippi";
        String[] smalls = { "is", "ppi", "hi", "sis", "i", "mississippi" };
        HashMapList<String, Integer> locations = searchAll(big, smalls);
        System.out.println(locations.toString());
    }
}

class Trie {
    private TrieNode root = new TrieNode();

    public ArrayList<Integer> search(String s) {
        return root.search(s);
    }

    public void insertString(String str, int location) {
        root.insertString(str, location);
    }

    public TrieNode getRoot() {
        return root;
    }
}

class TrieNode {
    private HashMap<Character, TrieNode> children;
    private ArrayList<Integer> indexes;

    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
        indexes = new ArrayList<Integer>();
    }

    public void insertString(String s, int index) {
        if (s == null)
            return;
        indexes.add(index);
        if (s.length() > 0) {
            char value = s.charAt(0);
            TrieNode child = null;
            if (children.containsKey(value)) {
                child = children.get(value);
            } else {
                child = new TrieNode();
                children.put(value, child);
            }
            String remainder = s.substring(1);
            child.insertString(remainder, index + 1);
        } else {
            children.put('\0', null);
        }
    }

    public ArrayList<Integer> search(String s) {
        if (s == null || s.length() == 0) {
            return indexes;
        } else {
            char first = s.charAt(0);
            if (children.containsKey(first)) {
                String remainder = s.substring(1);
                return children.get(first).search(remainder);
            }
        }
        return null;
    }

    public boolean terminates() {
        return children.containsKey('\0');
    }

    public TrieNode getChild(char c) {
        return children.get(c);
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
        if (list == null)
            return false;
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
