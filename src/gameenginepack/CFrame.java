package gameenginepack;

public class CFrame {
    public Vector3 Position = new Vector3();
    public Vector3 BackVector = new Vector3();
    public Vector3 RightVector = new Vector3();
    public Vector3 UpVector = new Vector3();

    public CFrame(double x, double y, double z, double m11, double m12, double m13, double m21, double m22, double m23, double m31, double m32, double m33) {
        this.Position = new Vector3(x, y, z);
        this.BackVector = new Vector3(m13, m23, m33);
        this.UpVector = new Vector3(m12, m22, m32);
        this.RightVector = new Vector3(m11, m21, m31);
    }

    public CFrame() {}

    public CFrame(double x,double y,double z) {
        this.Position = new Vector3(x,y,z);
    }

    public CFrame (Vector3 a, Vector3 b) {
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
                xaxis = new Vector3(0,1,0).cross(zaxis).normalize(),
                yaxis = zaxis.cross((xaxis)).normalize();
        if (xaxis.magnitude() == 0 ) {
            if (zaxis.y == 0) {
                xaxis = new Vector3(0, 0, -1);
                yaxis = new Vector3(1, 0, 0);
                zaxis = new Vector3(0, -1, 0);
            } else {
                xaxis = new Vector3(0, 0, 1);
                yaxis = new Vector3(1, 0, 0);
                zaxis = new Vector3(0, 1, 0);
            }
        }
        //local right = Vector3.new(m11, m21, m31) -- This is the same as cf.rightVector
        //local up = Vector3.new(m12, m22, m32) -- This is the same as cf.upVector
        //local back = Vector3.new(m13, m23, m33) -- This is the same as -cf.lookVector
        this.Position = new Vector3(a.x,a.y,a.z);
        this.RightVector = new Vector3(xaxis.x,xaxis.y, xaxis.z);
        this.UpVector = new Vector3(yaxis.x,yaxis.y, yaxis.z);
        this.BackVector = new Vector3(zaxis.x,zaxis.y, zaxis.z);
    }

    public CFrame mul(CFrame other) {
        double[] a = this.getComponents();
        double[] b = this.getComponents();
        //x,y,z,m11,m12,m13,m21,m22,m23,m31,m32,m33
        //0,1,2,3   ,4  ,5  ,6  ,7  ,8  ,9  ,10 ,11
        //double m11 = a[3]*b[3]+a[4]
        double m11 = a[3] * b[3] + a[4] * b[6] + a[5] * b[9];
        double m12 = a[3] * b[4] + a[4] * b[7] + a[5] * b[10];
        double m13 = a[3] * b[5] + a[4] * b[8] + a[5] * b[11];
        double x = a[3] * b[0] + a[4] * b[1] + a[5] * b[2] + a[1];
        double m21 = a[6] * b[3] + a[7] * b[6] + a[8] * b[9];
        double m22 = a[6] * b[4] + a[7] * b[7] + a[8] * b[10];
        double m23 = a[6] * b[5] + a[7] * b[8] + a[8] * b[11];
        double y = a[6] * b[0] + a[7] * b[1] + a[8] * b[2] + a[1];
        double m31 = a[9] * b[3] + a[10] * b[6] + a[11] * b[9];
        double m32 = a[9] * b[4] + a[10] * b[7] + a[11] * b[10];
        double m33 = a[9] * b[5] + a[10] * b[8] + a[11] * b[11];
        double z = a[9] * b[0] + a[10] * b[1] + a[11] * b[2] + a[2];
        return new CFrame(x, y, z, m11, m12, m13, m21, m22, m23, m31, m32, m33);
    }

    public Vector3 mul(Vector3 other) {
        double[] a = this.getComponents();
        double nx = a[3] * other.x + a[4] * other.y + a[5] * other.x + a[0];
        double ny = a[6] * other.x + a[7] * other.y + a[8] * other.x + a[1];
        double nz = a[9] * other.x + a[10] * other.y + a[11] * other.x + a[2];
        return new Vector3(nx, ny, nz);
    }


    public double[] getComponents() {
        //-- m11, m12, m13,
        //-- m21, m22, m23,
        //-- m31, m32, m33
        //
        //local right = Vector3.new(m11, m21, m31) -- This is the same as cf.rightVector
        //local up = Vector3.new(m12, m22, m32) -- This is the same as cf.upVector
        //local back = Vector3.new(m13, m23, m33) -- This is the same as -cf.lookVector
        return new double[]{this.Position.x, this.Position.y, this.Position.z,
                this.RightVector.x, this.UpVector.x, this.BackVector.x,
                this.RightVector.y, this.UpVector.y, this.BackVector.y,
                this.RightVector.z, this.UpVector.z, this.BackVector.z};
    }
}
