package perceptualHash;

import java.math.BigInteger;

// PHash : http://pastebin.com/Pj9d8jt5#
// DCT : http://stackoverflow.com/questions/4240490/problems-with-dct-and-idct-algorithm-in-java
public class PHash extends PerceptualHash {

    private int size, smallerSize;

    public PHash() {
        this(32, 8);
    }

    public PHash(int size, int smallerSize) {
        this.size = size;
        this.smallerSize = smallerSize;
    }


    @Override
    public int hashLength() {
        return smallerSize * smallerSize - 1;
    }

    @Override
    public BigInteger hash(int[][] img) {

        double[][] dctVals = applyDCT(resize(img, size, size));

        double total = 0;
        for (int x = 0; x < smallerSize; x++) {
            for (int y = 0; y < smallerSize; y++) {
                total += dctVals[x][y];
            }
        }
        total -= dctVals[0][0];

        double avg = total / (double) ((smallerSize * smallerSize) - 1);

        String str = "";

        for (int x = 0; x < smallerSize; x++) {
            for (int y = 0; y < smallerSize; y++) {
                if (x != 0 && y != 0) {
                    str += (dctVals[x][y] > avg ? "1" : "0");
                }
            }
        }
        return new BigInteger(str, 2);
    }


    private double getCoefficients(int v) {
        return v == 0 ? 1 / Math.sqrt(2.0) : 1;
    }

    private double[][] applyDCT(int[][] f) {
        int N = size;

        double[][] F = new double[N][N];
        for (int u = 0; u < N; u++) {
            for (int v = 0; v < N; v++) {
                double sum = 0.0;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        sum += Math.cos(((2 * i + 1) / (2.0 * N)) * u * Math.PI) * Math.cos(((2 * j + 1) / (2.0 * N)) * v * Math.PI) * (f[i][j]);
                    }
                }
                sum *= (getCoefficients(u) * getCoefficients(v)) / 4.0;
                F[u][v] = sum;
            }
        }
        return F;
    }

}