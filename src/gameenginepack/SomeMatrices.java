package gameenginepack;

public class SomeMatrices {
    public double[] inside = new double[16];

    public SomeMatrices() {
        inside[15] = 1;
    }

    /*public void multVecMatrix(SomeMatrices Other) {
        double x = src.x * m[0][0] + src.y * m[1][0] + src.z * m[2][0] + m[3][0];
        double y= src.x * m[0][1] + src.y * m[1][1] + src.z * m[2][1] + m[3][1];
        double z = src.x * m[0][2] + src.y * m[1][2] + src.z * m[2][2] + m[3][2];
        double w = src.x * m[0][3] + src.y * m[1][3] + src.z * m[2][3] + m[3][3];
        if (w != 1 && w != 0) {
            dst.x = x / w;
            dst.y = y / w;
            dst.z = z / w;
        }
    }*/

    /*
     Vec3<T> multVecMatrix(const Vec3<T> &v)
    {
#ifdef ROWMAJOR
        return Vec3<T>(
            v.x * m[0][0] + v.y * m[1][0] + v.z * m[2][0],
            v.x * m[0][1] + v.y * m[1][1] + v.z * m[2][1],
            v.x * m[0][2] + v.y * m[1][2] + v.z * m[2][2]);
#else
        return Vec3<T>(
            v.x * m[0][0] + v.y * m[0][1] + v.z * m[0][2],
            v.x * m[1][0] + v.y * m[1][1] + v.z * m[1][2],
            v.x * m[2][0] + v.y * m[2][1] + v.z * m[2][2]);
#endif
    }
     */
}
