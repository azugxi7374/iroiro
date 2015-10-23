package perceptualHash;

import java.awt.image.BufferedImage;
import java.math.BigInteger;

public abstract class PerceptualHash {

    public abstract int hashLength();

    public abstract BigInteger hash(int[][] img);

    public BigInteger hash(BufferedImage bimg) {
        return hash(biToArray(bimg));
    }


    public int distance(BigInteger h1, BigInteger h2) {
        return h1.xor(h2).bitCount();
    }
    public double nDistance(BigInteger h1, BigInteger h2) {
        return distance(h1, h2) / (double) hashLength();
    }

    protected int[][] resize(int[][] img, int w, int h) {
        int[][] ret = new int[w][h];
        double xScale = (double) img.length / w;
        double yScale = (double) img[0].length / h;
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                ret[x][y] = (int) Math.round(
                        resize_getValue(img, xScale * x, yScale * y, xScale * (x + 1), yScale * (y + 1)));
            }
        }
        return ret;
    }

    private double resize_getValue(int[][] img, double x1, double y1, double x2, double y2) {
        double ret = 0;
        for (int x = (int) Math.floor(x1); x <= (int) Math.floor(x2 - 1e-9); x++) {
            for (int y = (int) Math.floor(y1); y <= (int) Math.floor(y2 - 1e-9); y++) {
                double dx = (Math.min(x2, x + 1) - Math.max(x1, x));
                double dy = (Math.min(y2, y + 1) - Math.max(y1, y));
                ret += dx * dy * img[x][y];
            }
        }
        return ret / ((x2 - x1) * (y2 - y1));
    }

    protected int[][] biToArray(BufferedImage bi) {
        int W = bi.getWidth();
        int H = bi.getHeight();
        int[][] img = new int[W][H];
        for (int w = 0; w < W; w++) {
            for (int h = 0; h < H; h++) {
                int rgb = bi.getRGB(w, h);
                img[w][h] = Math.round(
                        ((rgb >> 16 & 0xff) + (rgb >> 8 & 0xff) + (rgb & 0xff)) / 3
                );
            }
        }
        return img;
    }

    protected BufferedImage arrayToBi(int[][] img) {
        int W = img.length;
        int H = img[0].length;
        BufferedImage bi;
        bi = new BufferedImage(W, H, BufferedImage.TYPE_BYTE_GRAY);
        for (int x = 0; x < W; x++) {
            for (int y = 0; y < H; y++) {
                int v = Math.max(0, Math.min(255, img[x][y]));
                bi.setRGB(x, y, v << 16 | v << 8 | v << 0);
            }
        }
        return bi;
    }
}

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//