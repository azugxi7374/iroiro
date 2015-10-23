package perceptualHash;

import java.math.BigInteger;

public class DHash extends PerceptualHash {
    int W, H, D;

    public static int Horizontal = 1;
    public static int Vertical = 2;
    public static int BothDirections = 3;

    public DHash() {
        this(8, 8, Horizontal);
    }

    public DHash(int w, int h) {
        this(w, h, Horizontal);
    }

    public DHash(int w, int h, int direction) {
        this.W = w;
        this.H = h;
        this.D = direction;
    }


    @Override
    public int hashLength() {
        if (D == BothDirections) {
            return this.W * this.H * 2;
        } else {
            return this.W * this.H;
        }
    }

    @Override
    public BigInteger hash(int[][] img) {
        int[][] a;

        if (D == Horizontal) {
            a = resize(img, W + 1, H);
        } else if (D == Vertical) {
            a = resize(img, W, H + 1);
        } else {
            a = resize(img, W + 1, H + 1);
        }

        String str = "";
        for (int w = 0; w < W; w++) {
            for (int h = 0; h < H; h++) {
                if (D == Horizontal || D == BothDirections) {
                    str += a[w][h] < a[w + 1][h] ? '0' : '1';
                }
                if (D == Vertical || D == BothDirections) {
                    str += a[w][h] < a[w][h + 1] ? '0' : '1';
                }
            }
        }
        return new BigInteger(str, 2);
    }
}