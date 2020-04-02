import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ques8_13 {
    public static void main(String[] args) {
        Box[] boxList = { new Box(6, 4, 4), new Box(8, 6, 2), new Box(5, 3, 3), new Box(7, 8, 3), new Box(4, 2, 2), new Box(9, 7, 3)};
		ArrayList<Box> boxes = new ArrayList<Box>();
		for (Box b : boxList) {
			boxes.add(b);
		}
		
		int height = createStack(boxes);
		System.out.println(height);
    }

    private static int createStack(ArrayList<Box> boxes) {
        Collections.sort(boxes, new BoxComparator());
        int maxHeight = 0;
        int maxHeightMapFromIthBox[] = new int[boxes.size()];
        for (int i=0; i<boxes.size(); ++i) {
            int height = createStack(boxes, i, maxHeightMapFromIthBox);
            maxHeight = Math.max(height, maxHeight);
        }
        return maxHeight;
    }

    private static int createStack(ArrayList<Box> boxes, int bottomBoxIndex, int[] maxHeightMapFromIthBox) {
        if (bottomBoxIndex < boxes.size() && maxHeightMapFromIthBox[bottomBoxIndex] > 0)
            return maxHeightMapFromIthBox[bottomBoxIndex];
        Box bottom = boxes.get(bottomBoxIndex);
        int maxHeight = 0;
        for (int i=bottomBoxIndex+1; i<boxes.size(); ++i) {
            int height = createStack(boxes, i, maxHeightMapFromIthBox);
            maxHeight = Math.max(height, maxHeight);
        }
        maxHeight += bottom.height;
        maxHeightMapFromIthBox[bottomBoxIndex] = maxHeight;
        return maxHeight;
    }
}

class Box {
    int width, height, depth;

    public Box(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public boolean canBeAbove(Box b) {
        if (width < b.width && height < b.height && depth < b.depth)
            return true;
        return false;
    }
}

class BoxComparator implements Comparator<Box> {
    @Override
    public int compare(Box x, Box y) {
        return y.height - x.height;
    }
}