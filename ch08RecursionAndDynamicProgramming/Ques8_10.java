public class Ques8_10 {
    public static void main(String[] args) {
        int x = 100, y = 50;
        Color[][] screen = new Color[x][y];
        for (Color[] colors : screen) {
            for (int i = 0; i < colors.length; ++i) {
                colors[i] = Color.BLACK;
            }
        }
        for (Color[] colors : screen) {
            for (var c : colors)
                System.out.print(c);
            System.out.println();
        }
        paintFill(screen, 0, 0, Color.WHITE);
        for (Color[] colors : screen) {
            for (var c : colors)
                System.out.print(c);
            System.out.println();
        }
    }

    enum Color {
        RED, GREEN, BLUE, WHITE, BLACK
    }

    static void paintFill (Color[][] screen, int r, int c, Color newColor) {
        if (screen[r][c] == newColor)
            return;
        paintFill(screen, r, c, newColor, screen[r][c]);
    }

    private static void paintFill(Color[][] screen, int r, int c, Color newColor, Color oldColor) {
        if (r < 0 || r >= screen.length || c < 0 || c >= screen[0].length)
            return;
        if (screen[r][c] == oldColor) {
            screen[r][c] = newColor;
            paintFill(screen, r-1, c, newColor, oldColor); //up
            paintFill(screen, r+1, c, newColor, oldColor); //down
            paintFill(screen, r, c-1, newColor, oldColor); //left
            paintFill(screen, r, c+1, newColor, oldColor); //right
        }

    }
}