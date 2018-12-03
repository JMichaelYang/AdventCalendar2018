package utilities;

public class Section {
  public final int id;
  public final int left;
  public final int top;
  public final int right;
  public final int bottom;

  public Section(int id, int left, int right, int top, int bottom) {
    this.id = id;
    this.left = left;
    this.right = right;
    this.top = top;
    this.bottom = bottom;
  }

  public static boolean setPoints(int[][] fabric, Section sec) {
    boolean alone = true;

    for(int i = sec.left; i <= sec.right; i++) {
      for(int j = sec.top; j <= sec.bottom; j++) {
        if(fabric[i][j] > 1) {
          alone = false;
        }
        fabric[i][j] += 1;
      }
    }

    return alone;
  }
}

