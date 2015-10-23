# perceptualHash

## Example
```Java
import java.math.BigInteger;
import perceptualHash.*;
```

```Java
// 各ピクセルが0~255で表されるグレースケール画像
int[][] img1 = ...
int[][] img2 = ...
// BufferedImage bimg1 = ... // BufferedImageを使う場合

// Hashの定義
PerceptualHash aHash = new AHash(8, 8); // AHash
// PerceptualHash dHash = new DHash(8, 8, DHash.BothDirections); // DHash
// PerceptualHash pHash = new PHash(); // PHash

// ハッシュ値計算
BigInteger hash1 = aHash.hash(img1);
BigInteger hash2 = aHash.hash(img2);
// BigInteger hash3 = aHash.hash(bimg); // BufferedImageを使う場合

// 距離
int d = aHash.distance(hash1, hash2);
// [0, 1]に正規化された距離
double nd = aHash.nDistance(hash1, hash2);
```

## Usage
---
### 共通
#### BigInteger PerceptualHash#hash(int[][] img)
ハッシュ値を作成
* img[x][y] : 座標(x, y)のピクセル値を0~255で表した値

#### BigInteger PerceptualHash#hash(BufferedImage bimg)
BufferedImageからハッシュ値を作成

#### int PerceptualHash#distance(BigInteger h1, BigInteger h2)
ハッシュ値h1, h2間の距離を返す．
距離の最大値は使用するPerceptualHashの種類に依存．
* h1, h2 : このPerceptualHashで計算したハッシュ値

#### double PerceptualHash#nDistance(BigInteger h1, BigInteger h2)
ハッシュ値h1, h2間の距離を[0, 1]に正規化して返す．

#### int PerceptualHash#hashLength()
このPerceptualHashで計算されるハッシュ値の長さ

---
### AHash
AverageHash
#### AHash(int w, int h)
w×hのAverageHashを作成

#### AHash()
AHash(8, 8)と同じ

---
### DHash
#### DHash(int w, int h , int direction)
direction方向に差分を取ったw×hのDHashを作成
##### direction
* Horizontal : 水平方向
* Vertical : 垂直方向
* BothDirections : 水平，垂直両方向．Hash長2倍

#### DHash(int w, int h)
DHash(w, h, DHash.Horizontal)と同じ
#### DHash()
DHash(8, 8)と同じ

---
### PHash

#### PHash(int size, int smallerSize)
size×sizeに縮小してDCTを取り，左上のsmallerSize×smallerSizeの部分をハッシュとするPHashを作成

#### PHash()
PHash(32, 8)と同じ

