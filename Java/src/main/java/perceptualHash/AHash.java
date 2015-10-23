package perceptualHash;

import java.math.BigInteger;

public class AHash extends PerceptualHash {
    int W, H;

    public AHash() {
        this(8, 8);
    }

    public AHash(int w, int h) {
        this.W = w;
        this.H = h;
    }

    @Override
    public int hashLength() {
        return this.W * this.H;
    }

    @Override
    public BigInteger hash(int[][] img) {
        int[][] a = resize(img, W, H);

        double avg = 0;
        for (int i = 0; i < W; i++) {
            for (int jj = 0; jj < H; jj++) {
                avg += a[i][jj];
            }
        }
        avg /= hashLength();

        String str = "";
        for (int i = 0; i < W; i++) {
            for (int jj = 0; jj < H; jj++) {
                str += a[i][jj] < avg ? '0' : '1';
            }
        }
        return new BigInteger(str, 2);
    }
}
