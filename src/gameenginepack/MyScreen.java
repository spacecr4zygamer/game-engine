package gameenginepack;

import gameenginepack.Instances.BasePart;
import gameenginepack.Instances.Instance;
import gameenginepack.Instances.Square;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class MyScreen extends Screen {

    private final BasePart Player;

    public Vector2 Position = new Vector2();
    public MatrixTwoD OtherPosition = new MatrixTwoD();
    public CFrame Origin;

    private final ArrayList<Object> _Instances = new ArrayList<>();

    private final Game game = null;

    Vector3 a = new Vector3(-5, 5, -5),
            b = new Vector3(-5, 1, 5),
            c = new Vector3(-6, -1, -5);
    Vector3[] Verts = {a, b, c};

    public void ReadWorldData(String FILE) {
        try {
            // Instantiate the Factory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            // read from a project's resources folder
            Document doc = db.parse(new File(FILE));

            if (doc != null) {
                NodeList nodeList = doc.getChildNodes().item(0).getChildNodes();
                Node workspace = nodeList.item(1);
                NodeList workspacechilds = workspace.getChildNodes();
                for (int index = 0; index < workspacechilds.getLength(); index++) {
                    Node tempnode = workspacechilds.item(index);
                    if (tempnode.getNodeType() == Node.ELEMENT_NODE) {
                        //System.out.println(tempnode.getNodeName());
                        String Name = tempnode.getNodeName();
                        Class<?> myClass;
                        Object InstanceOfClass = null;
                        try {
                            myClass = Class.forName("gameenginepack.Instances." + Name);
                            //System.out.println(myClass.getName()+" REe");
                            Constructor<?> constructor = myClass.getConstructor();
                            InstanceOfClass = constructor.newInstance();
                        } catch (Exception a) {
                            a.printStackTrace();
                        }
                        Instance Inst = (Instance) InstanceOfClass;

                        if (Inst != null) {
                            NodeList Properties = tempnode.getChildNodes();
                            for (int c = 0; c < Properties.getLength(); c++) {
                                Node vtempNode = Properties.item(c);
                                String Prop_Name = vtempNode.getNodeName();
                                Inst.setProperty(Prop_Name, vtempNode.getTextContent());
                            }

                            _Instances.add((Instance) InstanceOfClass);
                            getScreenFactory().getGame().getWorkspace().AddChild((Instance) InstanceOfClass);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MyScreen(ScreenFactory screenFactory) {
        super(screenFactory);

        this.Origin = new CFrame(0, 0, 5);
        // String string = "Hey,However";

        //System.out.println(Vector2.Transform(new Vector2(1,0), 90).tostring());
        //System.out.println(new Vector2(1,0).mul(-0.5).tostring());

        System.out.println("Constructor Called");

        Player = new Square(new Vector2(0, 0), new Vector2(25, 25), "black");
    }

    /*private void bubblesort() {
        int[] tosort = {8, 6, 1, 5, 7, 3, 2, 4, 154, 81, 56, 1, 9784, 98, 41, 561, 56, 156, 489, 14568, 156, 1489, 498, 156, 19568};
        boolean stillsorting = true;
        while (stillsorting) {
            for (int e : tosort) {
                System.out.print(e + " ");
            }
            System.out.print("\n");
            boolean done = true;
            for (int i = 0; i < tosort.length - 1; i++) {
                int index1 = tosort[i];
                int index2 = tosort[i + 1];
                if (index2 < index1) {
                    tosort[i] = index2;
                    tosort[i + 1] = index1;
                    done = false;
                }

            }
            if (done == true) {
                stillsorting = false;
            }
        }
    }*/

    @Override
    public void onCreate() {
        //bubblesort();

        System.out.println("Creating!");


        ReadWorldData("WorldData/World.xml");

//		for (Instance child: getScreenFactory().getGame().getWorkspace().GetChildren()) {
//			System.out.println(child);
//		}

        //System.out.println(getScreenFactory().getGame().getWorkspace().FindFirstChildOfClass("Square"));
        //System.out.println(getScreenFactory().getGame().getWorkspace().FindFirstChild("Square"));

        System.out.println("Initialized");
        //ReadFile("WorldData/World.xml");
        //System.out.println(123456789 & 0);
        //System.out.println(checkPasscode(56));
        //Verändern sie die File Location falls nötig
        //_Instances.add(new Square(new Vector2(500, 20)));
        // E = (BasePart) _Instances.get(0);
        // System.out.println(E.Position.tostring());


		/*
		local Scale = 1

local x = 100
local y = 100

local Size = 1

local Runservice = game:GetService("RunService")

--// The good thing about types with autocomplete
local Pixels = {} :: {[number]: {userdata: Instance, x: number, y: number}}

for _x = 0,x,1 do
		Runservice.Heartbeat:Wait()
	for _y = 0,y,1 do
		local pixel = Instance.new("Frame",game.StarterGui.ScreenGui.Frame)
		pixel.Size = UDim2.new(0,Size,0,Size)
		pixel.Position = UDim2.new(0,_x*Size,0,_y*Size)
		pixel.BorderSizePixel = 0
		pixel.BackgroundColor = BrickColor.Black()
		table.insert(Pixels,{userdata = pixel, x = _x, y = _y})
	end
end

local function intersect_Line_Tri(Line,LineD,Plane,PlaneN,Verts)
	if PlaneN:Dot(LineD.unit) == 0 then return nil end
	if LineD.Magnitude == 0 then return nil end
	local d = (PlaneN:Dot(Plane) - PlaneN:Dot(Line)) / PlaneN:Dot(LineD)
	if d<=0 then return nil end
	--print(d)
	local Q = Line+LineD*d
	local ab = Verts[1].Position-Verts[2].Position
	if (Verts[2].Position-Verts[1].Position):Cross(Q-Verts[1].Position):Dot(PlaneN) >= 0 and
		(Verts[3].Position-Verts[2].Position):Cross(Q-Verts[2].Position):Dot(PlaneN) >= 0 and
		(Verts[1].Position-Verts[3].Position):Cross(Q-Verts[3].Position):Dot(PlaneN) >= 0
	then
		return d
	else
		--error"HUH"
		--warn("outside")
		return nil
	end
end

local function RayCast()

end

local Tris = workspace.Tris

local tab = {}

for i,v in pairs(Tris:GetChildren()) do
	local key = i
	tab[key] = {}
	for o,u in pairs(v:GetChildren()) do
		tab[key][o] = u.Value
	end
end

local FOV = 40
local Camera = workspace.Camera

local ImageWidth = x--Camera.ViewportSize.X
local ImageHeight = y--Camera.ViewportSize.Y
local ImageRatio = ImageWidth/ImageHeight



while wait() do
	for _,v in pairs(Pixels) do
		local Pixel = v.userdata
		--print(Pixel.Position.Offset.X/Scale-x/2)
		local OriginCF = Camera.CFrame--*CFrame.new(,,0)
		local Line = OriginCF.Position
		--local xoff = v.x-x/2
		--local yoff = v.y-y/2
		--local xpercentage = xoff/(x/2)
		--local ypercentage = yoff/(y/2)

		--local PixelNDC_X = (v.x+0.5)/ImageWidth
		--local PixelNDC_Y = (v.y+0.5)/ImageHeight

		--local PixelScreen_X = (2*PixelNDC_X-1)*ImageRatio
		--local PixelScreen_Y = 1-2*PixelNDC_Y

		--local alpha = math.pi/4 --//TODO Add the other thing?

		--local BC = math.tan(FOV/2)

		--local PixelCamera_X = (2*PixelScreen_X-1)*ImageRatio*BC
		--local PixelCamera_Y = (1-2*PixelScreen_Y)*BC

		local Px = (2 * ((v.x + 0.5) / ImageWidth) - 1) * math.tan(FOV / 2 * math.pi / 180) * ImageRatio
		local Py = (1 - 2 * ((v.y + 0.5) / ImageHeight)) * math.tan(FOV / 2 * math.pi /180)

		local rayDirection = CFrame.new(Px,Py,-1)
		local LineDirection = CFrame.new(OriginCF.Position,(OriginCF*rayDirection).Position).LookVector

		--local LineDirection = OriginCF*CFrame.Angles(math.rad(xpercentage*FOV),math.rad(ypercentage*FOV),0).LookVector
		local v = tab[1]
		--local CF1 = CFrame.lookAt(v[1].Position,v[2].Position,Vector3.new(0,1,0))
		--local CF2 = CFrame.lookAt(v[1].Position,v[3].Position,Vector3.new(0,1,0))
		local Normal = (v[2].Position-v[1].Position):Cross((v[3].Position-v[1].Position))
		local result = intersect_Line_Tri(Line,LineDirection,v[1].Position,Normal.Unit,v)
		if result then
			Pixel.BackgroundColor3 = Color3.fromRGB(255, 255, 255)--:Lerp(Color3.fromRGB(0, 0, 0),result/500)
		else
			Pixel.BackgroundColor3 = Color3.fromRGB(44, 0, 32)
		end
	end
end
		 */

    }

    @Override
    public void onUpdate() {
        int Speed = 10;
        Vector2 CurrentSpeed = new Vector2();
        MatrixTwoD OtherSpeed = new MatrixTwoD();
        CFrame CFSpeed = new CFrame();
        //System.out.println("Updating");
        // boolean moving = false;
        if (this.getScreenFactory().getGame().getKeyBoardListener().isKeyPressed(KeyEvent.VK_A)) {
            CurrentSpeed.x -= 1;
            CFSpeed = CFSpeed.mul(new CFrame(-1, 0, 0));
            OtherSpeed = OtherSpeed.mul(new MatrixTwoD(-Speed,0));
            // moving = true;
            // this.Position.x += 20;
            // this.Player.Position.x -= 2;
        }

        if (this.getScreenFactory().getGame().getKeyBoardListener().isKeyPressed(KeyEvent.VK_D)) {
            CurrentSpeed.x += 1;
            CFSpeed = CFSpeed.mul(new CFrame(1, 0, 0));
            OtherSpeed = OtherSpeed.mul(new MatrixTwoD(Speed,0));
            // moving = true;
            // this.Position.x -= 20;
            // this.Player.Position.x += 2;
        }

        if (this.getScreenFactory().getGame().getKeyBoardListener().isKeyPressed(KeyEvent.VK_W)) {
            CurrentSpeed.y += 1;
            CFSpeed = CFSpeed.mul(new CFrame(0, 0, -1));
            OtherSpeed = OtherSpeed.mul(new MatrixTwoD(0,Speed));
            // moving = true;
            // this.Position.y += 20;
            // this.Player.Position.y -= 2;
        }

        if (this.getScreenFactory().getGame().getKeyBoardListener().isKeyPressed(KeyEvent.VK_S)) {
            CurrentSpeed.y -= 1;
            CFSpeed = CFSpeed.mul(new CFrame(0, 0, 1));
            OtherSpeed = OtherSpeed.mul(new MatrixTwoD(0,-Speed));
            // moving = true;
            // this.Position.y -= 20;
            // this.Player.Position.y += 2;
        }
        if (this.getScreenFactory().getGame().getKeyBoardListener().isKeyPressed(KeyEvent.VK_E)) {
            OtherPosition = OtherPosition.mul(MatrixTwoD.Angles(-5));
        }
        if (this.getScreenFactory().getGame().getKeyBoardListener().isKeyPressed(KeyEvent.VK_Q)) {
            OtherPosition = OtherPosition.mul(MatrixTwoD.Angles(5));
        }
        //System.out.println(OtherPosition);
        if (CurrentSpeed.magnitude() > 0) {
            // System.out.println(CurrentSpeed.unit().tostring());
            //System.out.println("The Current speed is: " + CFSpeed.Position.tostring());
            Position = Position.add(CurrentSpeed.unit().mul(Speed));
            OtherPosition = OtherPosition.mul(OtherSpeed);
            this.Origin = this.Origin.mul(CFSpeed);
        }
    }

    Polygon RotateRect(Vector2 Position, Vector2 Size, double angle) {
        Vector2 v1 = Position.add(Vector2.Transform(Size.mul(-0.5), angle));
        Vector2 v3 = Position.add(Vector2.Transform(Size.mul(0.5), angle));
        Vector2 v2 = Position.add(Vector2.Transform(new Vector2(Size.x * 0.5, -Size.y * 0.5), angle));
        Vector2 v4 = Position.add(Vector2.Transform(new Vector2(-Size.x * 0.5, Size.y * 0.5), angle));

        int[] xpoints = {(int) v1.x, (int) v2.x, (int) v3.x, (int) v4.x};
        int[] ypoints = {(int) v1.y, (int) v2.y, (int) v3.y, (int) v4.y};

        return new Polygon(xpoints, ypoints, 4);
    }

    Polygon RotateTris(Vector2 Position, Vector2 Size, double angle) {
        Vector2 v1 = Position.add(Vector2.Transform(Size.mul(-0.5), angle));
        Vector2 v3 = Position.add(Vector2.Transform(Size.mul(0.5), angle));
        //Vector2 v2 = Position.add(Vector2.Transform(new Vector2(Size.x/2,-Size.y/2), angle));
        Vector2 v2 = Position.add(Vector2.Transform(new Vector2(-Size.x * 0.5, Size.y * 0.5), angle));

        int[] xpoints = {(int) v1.x, (int) v2.x, (int) v3.x};
        int[] ypoints = {(int) v1.y, (int) v2.y, (int) v3.y};

        return new Polygon(xpoints, ypoints, 3);
    }

    private double intersect_Line_Triangle(Vector3 Origin, Vector3 Direction, Vector3 PlaneNormal, Vector3 Plane, Vector3[] Verts) {
        /*
        if PlaneN:Dot(LineD.unit) == 0 then return nil end
        if LineD.Magnitude == 0 then return nil end
        local d = (PlaneN:Dot(Plane) - PlaneN:Dot(Line)) /PlaneN:Dot(LineD)
        if d <= 0 then return nil end
                -- print(d)
            local Q = Line + LineD * d
            local ab = Verts[1].Position - Verts[2].Position
            if (Verts[2].Position - Verts[1].Position):Cross(Q - Verts[1].Position):Dot(PlaneN) >= 0 and
                (Verts[3].Position - Verts[2].Position):Cross(Q - Verts[2].Position):Dot(PlaneN) >= 0 and
                (Verts[1].Position - Verts[3].Position):Cross(Q - Verts[3].Position):Dot(PlaneN) >= 0
            then
            return d
	    else
            --error "HUH"
            -- warn("outside")
            return nil
        end
        */
        if (Vector3.Dot(PlaneNormal,Direction) == 0) {
            return -1;
        }
        if (Direction.magnitude() == 0) {
            return -2;
        }
        //System.out.println(PlaneNormal.tostring() + "|" + Plane.tostring() + "|" + PlaneNormal.dot(Plane)+"|Ori|"+Origin.tostring()+"|P_O|"+PlaneNormal.dot(Origin)+"|D|"+Direction.tostring()+"|P_D|"+PlaneNormal.dot(Direction));
        double d = (Vector3.Dot(PlaneNormal,Plane) - Vector3.Dot(PlaneNormal,Origin)) / Vector3.Dot(PlaneNormal,Direction);
        //System.out.println(d);
        if (d < 0) {
            return -3;
        }



        Vector3 Q = Origin.add(Direction.mul(d));
        if (Vector3.Dot(Vector3.Cross(Verts[1].sub(Verts[0]),Q.sub(Verts[0])),PlaneNormal) >= 0 &&
                Vector3.Dot(Vector3.Cross(Verts[2].sub(Verts[1]),Q.sub(Verts[1])),PlaneNormal) >= 0 &&
                Vector3.Dot(Vector3.Cross(Verts[0].sub(Verts[2]),Q.sub(Verts[2])),PlaneNormal) >= 0) {
            //System.out.println("Hit");
            return d;
        } else {
            //System.out.println("Missed");
            return -4;
        }


    }


    private float oldtime = 0;

    @Override
    public void onDraw(Graphics2D g2d) {

        float newtime = System.nanoTime();
        float delta = newtime - oldtime;
        oldtime = newtime;
        //System.out.println("%.50f".formatted(newtime)+"|"+delta*0.0001);
        float FPS = 1 / (delta * 0.000000001f);

        if (getScreenFactory().getGame().RenderType == Gamestates.TwoD) {


            //System.out.println("Drawing");

            // Camera

            // Player Showing
            Rectangle e = g2d.getClipBounds();
            int x = e.width, y = e.height;
            g2d.setColor(Color.green);
            g2d.drawLine(0, y / 2, x, y / 2);
            g2d.drawLine(x / 2, 0, x / 2, y);
            // System.out.println(e.height+" "+e.width);
            Vector2 thisPosition = OtherPosition.getPosition();

            g2d.setColor(Color.getColor(this.Player.BrickColor));
            //g2d.fillRect(x / 2 - (int) this.Player.Size.x / 2, y / 2 - (int) this.Player.Size.y / 2,
              //      (int) this.Player.Size.x, (int) this.Player.Size.y);
            g2d.fillPolygon(
                    RotateRect(
                            new Vector2(x*0.5,y*0.5),
                            this.Player.Size,
                            -Math.toDegrees(OtherPosition.getLookVector().angle())
                    ));

            g2d.setColor(Color.cyan);
            g2d.fillRect(x / 2, y / 2, 1, 1);

            g2d.setColor(Color.gray);
            //Polygon poly = new Polygon(xpoints,ypoints,4);
            //g2d.setColor(Color.red);
            //g2d.fillPolygon(poly);

            //g2d.fillPolygon();
            // Walls around it

            //System.out.println(Position.tostring());



            for (int i = 0; i < _Instances.size(); i++) {
                BasePart Part = (BasePart) _Instances.get(i);
                Vector2 DistanceVec = Part.Position.sub(thisPosition).converttorender();
                // System.out.println(DistanceVec.tostring());
                // System.out.println(DistanceVec.tostring()+" "+Position.tostring()+"
                // "+Part.Position.tostring());

                //if (DistanceVec.x <= x*4 & DistanceVec.x >= -x*4 & DistanceVec.y <= y*4 & DistanceVec.y >= -y*4) {
                // Vector2 relative = Position.add(DistanceVec);
                // Vector2 relative = Position.add();

                Vector2 screenoffset = new Vector2(x / 2, y / 2);
                Polygon partrotated;
                if (Part instanceof Square) {
                    partrotated = RotateRect(screenoffset.add(DistanceVec), Part.Size, Part.Rotation);
                } else {
                    partrotated = RotateTris(screenoffset.add(DistanceVec), Part.Size, Part.Rotation);
                }
                Color color;
                try {
                    Field field = Color.class.getField(Part.BrickColor);
                    color = (Color) field.get(null);
                } catch (Exception em) {
                    color = Color.gray; // Not defined
                }
                //System.out.println(color);
                g2d.setColor(color);
                g2d.fillPolygon(partrotated);
                //g2d.drawPolygon(partrotated);
                //g2d.fillRect((int) DistanceVec.x + (int) screenoffset.x, (int) DistanceVec.y + (int) screenoffset.y,(int) Part.Size.x, (int) Part.Size.y);
                // g2d.rotate(10);
                //}
                // break;
            }

            g2d.setColor(Color.white);
            g2d.fillRect(3, y - 17, 165, 15);
            g2d.setColor(Color.black);
            g2d.drawString("Player Position: " + Math.round(OtherPosition.getPosition().x) + " / " + Math.round(OtherPosition.getPosition().y), 5, y - 5);
            g2d.drawRect(3, y - 17, 165, 15);
        } else {

            // 3D Rendering

            //CFrame Origin = new CFrame(0,0,0);
            //Origin = new Vector3(-1,0,0)
            int max_x = 800, max_y = 600;

            //this.Origin = new CFrame(this.Origin.Position,a);//Origin.mul(CFrame.Angles(0,Math.toRadians(10),0));
            Origin = Origin.mul(CFrame.Angles(0,Math.toRadians(10),0));


            double ImageWidth = 800, ImageHeight = 600, ImageRatio = 800f / 600f;

            float FOV = 70;

            //System.out.println(this.Origin.mul(new CFrame(0,0,-1)).Position.tostring());


            //g2d.setBackground(Color.BLACK);
            for (int x = 0; x < max_x; x++) {
                for (int y = 0; y < max_y; y++) {


                    float m = (float) x, l = (float) y;

                    double Px = (2 * ((m + 0.5) / ImageWidth) - 1) * Math.tan(FOV / 2 * Math.PI / 180) * ImageRatio;
                    double Py = (1 - 2 * ((l + 0.5) / ImageHeight)) * Math.tan(FOV / 2 * Math.PI / 180);


                    CFrame rayDirection = new CFrame(Px, Py, -1);//.sub(Origin);
                    Vector3 LineDirection = new CFrame(this.Origin.Position, this.Origin.mul(rayDirection).Position).BackVector.mul(-1);

                    //Vector3 PlaneOrigin = Verts[0].add(Verts[1]).add(Verts[2]).div(3);

                    Vector3 Normal = Vector3.Cross(Verts[1].sub(Verts[0]),Verts[2].sub(Verts[0]));
                    double result = intersect_Line_Triangle(this.Origin.Position, LineDirection.normalize(), Normal.normalize(), Verts[0], Verts);

                    //if (result < 0) {System.out.println(result);}
                    //System.out.println(result);

                    if (result >= 0) {
                        g2d.setColor(Color.decode(Color3.Lerp(Color3.White,Color3.Black,result * 0.1).encodetoHex()));
                        //g2d.setColor(Color.blue);
                        g2d.fillRect(x, y, 1, 1);
                    } else {
                        g2d.setColor(Color.pink);
                        g2d.fillRect(x, y, 1, 1);
                    }
                    //Pixel.BackgroundColor3 = Color3.fromRGB(255, 255, 255)//--:Lerp(Color3.fromRGB(0, 0, 0),result/500)
                    //Pixel.BackgroundColor3 = Color3.fromRGB(44, 0, 32)


                }
            }

            g2d.setColor(Color.white);
            g2d.fillRect(3, 400, 165, 15);
            g2d.setColor(Color.black);
            g2d.drawString("Player Position: " + Math.round(Origin.Position.x) + " / " + Math.round(Origin.Position.y)+ " / "+ Math.round(Origin.Position.z), 5, 412);
            g2d.drawRect(3, 400, 165, 15);
        }

        g2d.setColor(Color.white);
        g2d.fillRect(3, 100, 165, 15);
        g2d.setColor(Color.black);
        g2d.drawString(""+FPS,5,112);
        g2d.drawRect(3, 100, 165, 15);
    }
}
