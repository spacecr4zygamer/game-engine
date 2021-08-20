package gameenginepack;

public class Vector3 {
    public double x=0,y=0,z=0;

    public static final Vector3 RIGHT = new Vector3(1,0,0);
    public static final Vector3 UP = new Vector3(0,1,0);
    public static final Vector3 BACK = new Vector3(0,0,1);

    public Vector3() {}

    /**
     * Sets only X position
     * @param x The X position
     */
    public Vector3(double x) {
        this.x = x;
    }

    /**
     * Sets only X and Y position.
     * @param x The X position
     * @param y The Y position
     */
    public Vector3(double x,double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets all positions.
     * @param x The X position
     * @param y The Y position
     * @param z The z position
     */
    public Vector3(double x,double y,double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Makes a String version separated by , .
     * @return String version
     */
    public String tostring() {
        return this.x+","+this.y+","+this.z;
    }

    /**
     * Reads a Vector3 from a String seperated by , .
     * @param string The String to Read
     * @return A Vector3 read from the String
     */
    public static Vector3 fromString(String string) {
        String[] ree = string.split(",");
        return new Vector3(Double.parseDouble(ree[0]),Double.parseDouble(ree[1]),Double.parseDouble(ree[2]));
    }

    /**
     * Clones the Vector3.
     * @return The cloned Vector3
     */
    public Vector3 clone() {
        return new Vector3(this.x,this.y,this.z);
    }

    /**
     * Multiplies two Vector3's with eachother.
     * @param other The second Vector3
     * @return The multiplied Vector3
     */
    public Vector3 mul(Vector3 other) {
        return new Vector3(this.x * other.x, this.y * other.y, this.z * other.z);
    }

    /**
     * Multiplies a Vector3 with a Number.
     * @param a The number to multiply
     * @return The multiplied Vector3
     */
    public Vector3 mul(double a) {
        return new Vector3(this.x*a,this.y*a,this.z*a);
    }

    /**
     * Adds two Vector3's with eachother.
     * @param other The second Vector3
     * @return The added Vector3
     */
    public Vector3 add(Vector3 other) {
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    /**
     * Subtracts a Vector3 from another Vector3.
     * @param other The second Vector3
     * @return The subtracted Vector3
     */
    public Vector3 sub(Vector3 other) {
        return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    /**
     * Divides two Vector3's with eachother.
     * @param other The second Vector3
     * @return The divided Vector3
     */
    public Vector3 div(Vector3 other) {
        return new Vector3(this.x/other.x,this.y/other.y,this.z/other.z);
    }

    /**
     * Divides a Vector3 with a Number.
     * @param a The Number to divide by
     * @return The divided Vector3
     */
    public Vector3 div(double a) {
        return new Vector3(this.x/a,this.y/a,this.z/a);
    }

    /**
     * Calculates the Cross product of two Vector3's.
     * @param other The second Vector3
     * @return The Cross Product of the Vector3's
     */
    /*public Vector3 cross(Vector3 other) {
        Vector3 Result = new Vector3();
        double a_1 = this.x,a_2 = this.y,a_3 = this.z;
        double b_1 = other.x,b_2 = other.y,b_3 = other.z;

        double s_1 = a_2*b_3-a_3*b_2,
                s_2 = a_3*b_1-a_1*b_3,
                s_3 = a_1*b_2-a_2*b_1;

        Result.x = s_1;
        Result.y = s_2;
        Result.z = s_3;

        return Result;
    }*/

    public static Vector3 Cross(Vector3 a, Vector3 b) {
        return new Vector3(a.y*b.z-a.z*b.y,a.z*b.x-a.x*b.z,a.x*b.y-a.y*b.x);
    }

    /**
     * Calculates the Distance from (0,0,0) to the Position in Euclidean-Space.
     * @return The distance to Center of the World
     */
    public double magnitude() {
        return Math.sqrt(Math.pow(this.x,2)+Math.pow(this.y,2)+Math.pow(this.z,2));
    }

    /**
     * Calculates the Dot product of two Vector3's that describes how far two Vector3's go in the same Direction.
     * @param other The second Vector3
     * @return The Dot product
     */
    /*public double dot(Vector3 other) {
        return this.x*other.x+this.y*other.y+this.z*other.z;
    }*/

    public static double Dot(Vector3 a, Vector3 b) {
        return a.x*b.x+a.y*b.y+a.z*b.z;
    }
    /**
     * Returns a Normalized Vector3 that has a Magnitude of 1 into any Direction.
     * @return The normalized Vector3
     */
    public Vector3 normalize() {
        return this.clone().div(this.magnitude());
    }

    /**
     * Interpolates between two Vector3's by the Alpha Percentage.
     * (Lerping is the Position between the two Vector3's where Alpha 0 = the Beginning and Alpha 1 = the End.)
     * @param vector The second Vector3, the Goal
     * @param alpha The Alpha percentage on how far it Interpolates
     * @return The Interpolated Vector3 between the beginning and end by the Alpha
     */
    public Vector3 lerp(Vector3 vector,double alpha) {
        return this.add(vector.sub(this).mul(alpha));
    }


    public static Vector3 Lerp(Vector3 begin,Vector3 end, double alpha) {
        return begin.clone().add(end.sub(begin).mul(alpha));
    }

}
