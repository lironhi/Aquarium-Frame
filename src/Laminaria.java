/*
 * Liron Himbert - 337731797
 * Yossef Simhon - 311217020*/
import java.awt.Color;
import java.util.concurrent.CyclicBarrier;
import java.awt.Graphics;

public class Laminaria extends Immobile {

	private String name;
	private int size;
	private int x;
	private int y;
	private Color colorr;
	private AquaPanel panel;
	 
	public Laminaria(int size,int x,int y, Color color,AquaPanel panel,String name)
	{
		super(name);
		this.colorr=color;
		this.size=size;
		this.x=x;
		this.y=y;
		this.panel=panel;
		this.name=name;
	}
	
	public void drawCreature(Graphics g) {
		g.setColor(colorr);
 
		g.fillArc(x-size/20, y-size, size/10, size*4/5, 0, 360);
		g.fillArc(x-size*3/20, y-size*13/15, size/10, size*2/3, 0, 360);
		g.fillArc(x+size/20, y-size*13/15, size/10, size*2/3, 0, 360);
		g.drawLine(x, y, x, y-size/5);
		g.drawLine(x, y, x-size/10, y-size/5);
		g.drawLine(x, y, x+size/10, y-size/5);
		panel.repaint();
		
	}

	public String getName() {
		
		return "Laminaria";
	}
	
	public int getSize() {
		
		return this.size;
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public void setSize(Immobile i) {
		this.size=i.getSize();
		
	}
	public void setX(Immobile i) {
		this.x=i.getX();
		
	}
	public void setY(Immobile i)
	{
		this.y=i.getY();
}

	
		
		
}
