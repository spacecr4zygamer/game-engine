package gameenginepack;

import org.jetbrains.annotations.Nullable;

public class CFrame {
    public Vector3 Position = new Vector3(0, 0, 0);
    public Vector3 BackVector = new Vector3(1, 0, 0);
    public Vector3 RightVector = new Vector3(0, 0, 1);
    public Vector3 UpVector = new Vector3(0, 1, 0);

    /*
    Representation Matrix:
    m11,    m12,    m13,    mx
    m21,    m22,    m23,    my
    m31,    m32,    m33,    mz
    0,      0,      0,      1
     */

    public CFrame(double x, double y, double z, double m11, double m12, double m13, double m21, double m22, double m23, double m31, double m32, double m33) {
        //System.out.println("This one???"+x+" "+y+" "+z);
        this.Position = new Vector3(x, y, z);
        this.RightVector = new Vector3(m11, m21, m31).normalize();
        this.UpVector = new Vector3(m12, m22, m32).normalize();
        this.BackVector = new Vector3(m13, m23, m33).normalize();
    }

    public CFrame() {
    }

    public CFrame(double x, double y, double z) {
        this.Position = new Vector3(x, y, z);
    }

    private static final Vector3
            DOWN = new Vector3(0,-1,0),
            FRONT = new Vector3(0,0,-1);

    /**
     * Creates a CFrame that is Positioned at the first Vector3 and looks at the second Vector3.
     *
     * @param a The first Vector3
     * @param b The second Vector3
     */
    public CFrame(Vector3 a, Vector3 b) {
        /*
        local eye, look = t[1], t[2];
		local eyeIsVector = type(eye) == "table" and eye.__type and eye.__type == "vector3";
		local lookIsVector = type(look) == "table" and look.__type and look.__type == "vector3";
		if (not eyeIsVector and not lookIsVector) then
			local t = type(eye);
			local cust = t == "table" and eye.__type or t;
			error("bad argument #1 to 'new' (Vector3 expected, got" .. cust .. ")");
		end;

		local zaxis = (eye - look).unit;
		local xaxis = top:Cross(zaxis).unit;
		local yaxis = zaxis:Cross(xaxis).unit;
		if (xaxis.magnitude == 0) then -- edge cases
			if zaxis.y < 0 then
				xaxis = Vector3.new(0, 0, -1);
				yaxis = Vector3.new(1, 0, 0);
				zaxis = Vector3.new(0, -1, 0);
			else
				xaxis = Vector3.new(0, 0, 1);
				yaxis = Vector3.new(1, 0, 0);
				zaxis = Vector3.new(0, 1, 0);
			end;
		end;
		self.proxy.p = Vector3.new(eye.x, eye.y, eye.z);
		self.proxy.m11, self.proxy.m12, self.proxy.m13 = xaxis.x, yaxis.x, zaxis.x;
		self.proxy.m21, self.proxy.m22, self.proxy.m23 = xaxis.y, yaxis.y, zaxis.y;
		self.proxy.m31, self.proxy.m32, self.proxy.m33 = xaxis.z, yaxis.z, zaxis.z;
         */
        Vector3 zaxis = (a.sub(b)).normalize(),
                xaxis = Vector3.Cross(Vector3.UP,zaxis).normalize(),
                yaxis = Vector3.Cross(zaxis,xaxis).normalize();
        if (xaxis.magnitude() == 0) {
            if (zaxis.y == 0) {
                xaxis = FRONT;
                yaxis = Vector3.RIGHT;
                zaxis = DOWN;
            } else {
                xaxis = Vector3.BACK;
                yaxis = Vector3.RIGHT;
                zaxis = Vector3.UP;
            }
        }
        //local right = Vector3.new(m11, m21, m31) -- This is the same as cf.rightVector
        //local up = Vector3.new(m12, m22, m32) -- This is the same as cf.upVector
        //local back = Vector3.new(m13, m23, m33) -- This is the same as -cf.lookVector
        this.Position = new Vector3(a.x, a.y, a.z);
        this.RightVector = new Vector3(xaxis.x, xaxis.y, xaxis.z);
        this.UpVector = new Vector3(yaxis.x, yaxis.y, yaxis.z);
        this.BackVector = new Vector3(zaxis.x, zaxis.y, zaxis.z);
    }

    private static Vector3 vectorAxisAngle(Vector3 n, Vector3 v, double t) {
        n = n.normalize();
        return v.mul(Math.cos(t)).add(n.mul(Vector3.Dot(v,n)).mul(1-Math.cos(t))).add(Vector3.Cross(n,v).mul(Math.sin(t)));
        //return v * (float)Math.Cos(t) + Vector3.Dot(v, n) * n * (1 - (float)Math.Cos(t)) + Vector3.Cross(n, v) * (float)Math.Sin(t);
    }

    public static CFrame fromAxisAngle(Vector3 axis,double angle) {
        Vector3 r = vectorAxisAngle(axis, Vector3.RIGHT, angle);
        Vector3 u = vectorAxisAngle(axis, Vector3.UP, angle);
        Vector3 b = vectorAxisAngle(axis, Vector3.BACK, angle);
        return new CFrame(0, 0, 0, r.x, u.x, b.x, r.y, u.y, b.y, r.z, u.z, b.z);
    }

    public static CFrame Angles(double x, double y, double z) {
        CFrame cfx = fromAxisAngle(Vector3.RIGHT, x);
        CFrame cfy = fromAxisAngle(Vector3.UP, y);
        CFrame cfz = fromAxisAngle(Vector3.BACK, z);
        return cfx.mul(cfy).mul(cfz);
    }

    /**
     * Multiplies two CFrame's together.
     *
     * @param other The other CFrame
     * @return The multiplied CFrame
     */
    public CFrame mul(CFrame other) {
        double[] a = this.getComponents();
        double[] b = other.getComponents();
        // The Components of this CFrame
        double ax = a[0], ay = a[1], az = a[2], a11 = a[3], a12 = a[4], a13 = a[5], a21 = a[6], a22 = a[7], a23 = a[8], a31 = a[9], a32 = a[10], a33 = a[11];
        // The Components of the other CFrame
        double bx = b[0], by = b[1], bz = b[2], b11 = b[3], b12 = b[4], b13 = b[5], b21 = b[6], b22 = b[7], b23 = b[8], b31 = b[9], b32 = b[10], b33 = b[11];
        //x,y,z,m11,m12,m13,m21,m22,m23,m31,m32,m33
        //0,1,2,3   ,4  ,5  ,6  ,7  ,8  ,9  ,10 ,11
        double m11 = a11 * b11 + a12 * b21 + a13 * b31;
        double m12 = a11 * b12 + a12 * b22 + a13 * b32;
        double m13 = a11 * b13 + a12 * b23 + a13 * b33;
        double x = a11 * bx + a12 * by + a13 * bz + ax;
        double m21 = a21 * b11 + a22 * b21 + a23 * b31;
        double m22 = a21 * b12 + a22 * b22 + a23 * b32;
        double m23 = a21 * b13 + a22 * b23 + a23 * b33;
        double y = a21 * bx + a22 * by + a23 * bz + ay;
        double m31 = a31 * b11 + a32 * b21 + a33 * b31;
        double m32 = a31 * b12 + a32 * b22 + a33 * b32;
        double m33 = a31 * b13 + a32 * b23 + a33 * b33;
        double z = a31 * bx + a32 * by + a33 * bz + az;
        return new CFrame(x, y, z, m11, m12, m13, m21, m22, m23, m31, m32, m33);
    }

    /**
     * Multiplies a CFrame with a Vector3.
     * @param other The Vector3 to multiply with
     * @return A Vector3 derived from the CFrame multiplication
     */
    public Vector3 mul(Vector3 other) {
        //TODO Check if this is how it works
        double[] a = this.getComponents();
        double nx = a[3] * other.x + a[4] * other.y + a[5] * other.x + a[0];
        double ny = a[6] * other.x + a[7] * other.y + a[8] * other.x + a[1];
        double nz = a[9] * other.x + a[10] * other.y + a[11] * other.x + a[2];
        return new Vector3(nx, ny, nz);
    }

    /**
     * Gets all the Components of the CFrame in the order of:
     *
     * @return x, y, z, R01, R02, R03, R04, R05, R06, R07, R08, R09, R10, R11
     */
    public double[] getComponents() {
        return new double[]{this.Position.x, this.Position.y, this.Position.z,
                this.BackVector.x, this.UpVector.x, this.RightVector.x,
                this.BackVector.y, this.UpVector.y, this.RightVector.y,
                this.BackVector.z, this.UpVector.z, this.RightVector.z};
    }
}
