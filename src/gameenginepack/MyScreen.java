package gameenginepack;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class MyScreen extends Screen {

	private final BasePart Player;

	public Vector2 Position = new Vector2();
	
	private ArrayList<Object> _Instances = new ArrayList<Object>();
	
	

	// private static final String filelink = "D:/"

	public void ReadFile(String FILENAME) {
		// Instantiate the Factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {

			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();

			// read from a project's resources folder
			Document doc = db.parse(new File(FILENAME));
			
			//System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
			//System.out.println("------");

			if (doc.hasChildNodes()) {
				printNote(doc.getChildNodes());
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	public void ReadWorldData(String FILE) {
		try {
			// Instantiate the Factory
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();

			// read from a project's resources folder
			Document doc = db.parse(new File(FILE));

			if (doc!=null) {
				NodeList nodeList = doc.getChildNodes().item(0).getChildNodes();
				Node workspace = nodeList.item(1);
				NodeList workspacechilds = workspace.getChildNodes();
				for (int index=0;index<workspacechilds.getLength();index++) {
					Node tempnode = workspacechilds.item(index);
					if (tempnode.getNodeType() == Node.ELEMENT_NODE) {
						System.out.println(tempnode.getNodeName());
						String Name = tempnode.getNodeName();
						Class<?> myClass = null;
						Object InstanceOfClass = null;
						try {
							myClass = Class.forName(Name);
							Constructor<?> constructor = myClass.getConstructor();
							InstanceOfClass = constructor.newInstance();
						} catch (Exception a) {
							a.printStackTrace();
						}
						Instance Inst = (Instance) InstanceOfClass;

						NodeList Properties = tempnode.getChildNodes();
						for (int c=0;c<Properties.getLength();c++) {
							Node vtempNode = Properties.item(c);
							String Prop_Name = vtempNode.getNodeName();
							Inst.setProperty(Prop_Name,vtempNode.getTextContent());
						}

						_Instances.add(InstanceOfClass);
					}
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	private void printNote(NodeList nodeList) {

		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				
				// get node name and value
				//System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
				//System.out.println("Node Value =" + tempNode.getTextContent());
				String Name = tempNode.getNodeName();
				if (Name == "Square" || Name == "Triangle") {
					//System.out.println("This is a basepart so make a instance");
					
					
					//Class<?> myClass = null;
					
					Class<?> myClass = null;
					Object instanceOfMyClass = null;
						try {
							
							//System.out.println("Hey1");
							myClass = Class.forName("gameenginepack."+Name);
							//System.out.println("Hey2");
							//Class[] types = {Double.TYPE, this.getClass()};
							Constructor<?> constructor = myClass.getConstructor();
							//System.out.println("Hey3");
							//Object[] parameters = {new Double(0), this};
							
							instanceOfMyClass = (Instance) constructor.newInstance();
							System.out.println("Passed Generating Class: "+Name);
						} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | 
								InstantiationException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException  e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("Failed Generating Class: "+Name);
						}
						Instance Inst = (Instance) instanceOfMyClass;
					
					
					//Instance Inst = clazz.newInstance();
					NodeList Properties = tempNode.getChildNodes();
					for (int c=0;c<Properties.getLength();c++) {
						Node vtempNode = Properties.item(c);
						String Prop_Name = vtempNode.getNodeName();
						Inst.setProperty(Prop_Name,vtempNode.getTextContent());
					}
					//System.out.println("This is the Position: "+Inst.Position.tostring());
					_Instances.add(Inst);
				}

				/*if (tempNode.hasAttributes()) {
					
					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node node = nodeMap.item(i);
						System.out.println("attr name : " + node.getNodeName());
						System.out.println("attr value : " + node.getNodeValue());
					}

				}*/

				if (tempNode.hasChildNodes()) {
					// loop again if has child nodes
					printNote(tempNode.getChildNodes());
				}

				//System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

			}
		}
	}

	public MyScreen(ScreenFactory screenFactory) {
		super(screenFactory);
		// TODO Auto-generated constructor stub
		// String string = "Hey,However";
		
		//System.out.println(Vector2.Transform(new Vector2(1,0), 90).tostring());
		//System.out.println(new Vector2(1,0).mul(-0.5).tostring());
		
		Player = new Square(new Vector2(0, 0), new Vector2(25, 25), "black");
	}

	
	public static boolean checkPasscode(int test) {
		// returns true if passcode is valid
		boolean result = false;
		for (int i = 42; i <= 88; i += 12) {
			//i += 1 erst, den
			int e = i;
			System.out.println(((++e | e + 1) ^ (1 + test)) == 0);
			if (result = ((++i | i + 1) ^ (1 + test)) == (0))
				break;
		}
		return result;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		System.out.println("Creating!");

		ReadWorldData("WorldData/World.xml");

		//ReadFile("WorldData/World.xml");
		//System.out.println(123456789 & 0);
		System.out.println(checkPasscode(56));
		//Verändern sie die File Location falls nötig
		//_Instances.add(new BasePart(new Vector2(500, 20)));
		// E = (BasePart) _Instances.get(0);
		// System.out.println(E.Position.tostring());
	}

	public int Speed = 10;

	@Override
	public void onUpdate() {
		Vector2 CurrentSpeed = new Vector2();
		// boolean moving = false;
		// TODO Auto-generated method stub
		if (this.getScreenFactory().getGame().getKeyBoardListener().isKeyPressed(KeyEvent.VK_A)) {
			CurrentSpeed.x -= 1;
			// moving = true;
			// this.Position.x += 20;
			// this.Player.Position.x -= 2;
		}

		if (this.getScreenFactory().getGame().getKeyBoardListener().isKeyPressed(KeyEvent.VK_D)) {
			CurrentSpeed.x += 1;
			// moving = true;
			// this.Position.x -= 20;
			// this.Player.Position.x += 2;
		}

		if (this.getScreenFactory().getGame().getKeyBoardListener().isKeyPressed(KeyEvent.VK_W)) {
			CurrentSpeed.y += 1;
			// moving = true;
			// this.Position.y += 20;
			// this.Player.Position.y -= 2;
		}

		if (this.getScreenFactory().getGame().getKeyBoardListener().isKeyPressed(KeyEvent.VK_S)) {
			CurrentSpeed.y -= 1;
			// moving = true;
			// this.Position.y -= 20;
			// this.Player.Position.y += 2;
		}
		if (CurrentSpeed.magnitude() > 0) {
			// System.out.println(CurrentSpeed.unit().tostring());
			Position = Position.add(CurrentSpeed.unit().mul(Speed));
		}
	}
	
	Polygon RotateRect(Vector2 Position,Vector2 Size, double angle) {
		Vector2 v1 = Position.add(Vector2.Transform(Size.mul(-0.5), angle));
		Vector2 v3 = Position.add(Vector2.Transform(Size.mul(0.5), angle));
		Vector2 v2 = Position.add(Vector2.Transform(new Vector2(Size.x/2,-Size.y/2), angle));
		Vector2 v4 = Position.add(Vector2.Transform(new Vector2(-Size.x/2,Size.y/2), angle));
		
		int[] xpoints = {(int) v1.x,(int) v2.x,(int) v3.x,(int) v4.x};
		int[] ypoints = {(int) v1.y,(int) v2.y,(int) v3.y,(int) v4.y};
		
		Polygon poly = new Polygon(xpoints,ypoints,4);
		return poly;
	}
	
	Polygon RotateTris(Vector2 Position,Vector2 Size, double angle) {
		Vector2 v1 = Position.add(Vector2.Transform(Size.mul(-0.5), angle));
		Vector2 v3 = Position.add(Vector2.Transform(Size.mul(0.5), angle));
		//Vector2 v2 = Position.add(Vector2.Transform(new Vector2(Size.x/2,-Size.y/2), angle));
		Vector2 v2 = Position.add(Vector2.Transform(new Vector2(-Size.x/2,Size.y/2), angle));
		
		int[] xpoints = {(int) v1.x,(int) v2.x,(int) v3.x};
		int[] ypoints = {(int) v1.y,(int) v2.y,(int) v3.y};
		
		Polygon poly = new Polygon(xpoints,ypoints,3);
		return poly;
	}

	@Override
	public void onDraw(Graphics2D g2d) {
		// TODO Auto-generated method stub

		// Camera
		
		// Player Showing
		Rectangle e = g2d.getClipBounds();
		int x = e.width, y = e.height;
		g2d.setColor(Color.green);
		g2d.drawLine(0, y/2, x, y/2);
		g2d.drawLine(x/2, 0, x/2, y);
		// System.out.println(e.height+" "+e.width);
		g2d.setColor(Color.black);
		g2d.drawString("Player Position: " + Math.round(Position.x) + " / " + Math.round(Position.y), 5, y - 5);
		g2d.drawRect(3, y-17, 165, 15);
		
		
		g2d.setColor(Color.getColor(this.Player.BrickColor));
		g2d.fillRect(x / 2 - (int) this.Player.Size.x / 2, y / 2 - (int) this.Player.Size.y / 2,
				(int) this.Player.Size.x, (int) this.Player.Size.y);

		g2d.setColor(Color.cyan);
		g2d.fillRect(x / 2, y / 2, 1, 1);
	
		g2d.setColor(Color.gray);
		//Polygon poly = new Polygon(xpoints,ypoints,4);
		//g2d.setColor(Color.red);
		//g2d.fillPolygon(poly);
		
		//g2d.fillPolygon();
		// Walls around it

		// System.out.println(Position.tostring());

		for (int i = 0; i<_Instances.size();i++) {
			BasePart Part = (BasePart) _Instances.get(i);
			// System.out.println(Part!=null);
			Vector2 DistanceVec = Part.Position.sub(Position).converttorender();
			// System.out.println(DistanceVec.tostring());
			// System.out.println(DistanceVec.tostring()+" "+Position.tostring()+"
			// "+Part.Position.tostring());

			//if (DistanceVec.x <= x*4 & DistanceVec.x >= -x*4 & DistanceVec.y <= y*4 & DistanceVec.y >= -y*4) {
				// Vector2 relative = Position.add(DistanceVec);
				// Vector2 relative = Position.add();
				
				Vector2 screenoffset = new Vector2(x / 2 , y / 2 );
				Polygon partrotated;
				if (Part instanceof Square) {
					partrotated = RotateRect(screenoffset.add(DistanceVec),Part.Size,Part.Rotation);
				} else {
					partrotated = RotateTris(screenoffset.add(DistanceVec),Part.Size,Part.Rotation);
				}
				Color color;
				try {
				    Field field = Color.class.getField(Part.BrickColor);
				    color = (Color)field.get(null);
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

	}

}
