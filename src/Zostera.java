/*
 * Liron Himbert - 337731797
 * Yossef Simhon - 311217020*/
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.concurrent.CyclicBarrier;

public class Zostera extends Immobile {


	private String name;
	private int size;
	private int x;
	private int y;
	private Color colorr;
	private AquaPanel panel;
	
	public Zostera(int size,int x,int y, Color color,AquaPanel panel,String name)
	{
		super(name);
		this.colorr=color;
		this.size=size;
		this.x=x;
		this.y=y;
		this.panel=panel;

	}
	
	@Override
	public void drawCreature(Graphics g) {
		
		Graphics2D g2=(Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(colorr);
		g.drawLine(x, y, x, y-size);
		g.drawLine(x-2, y, x-10, y-size*9/10);
		g.drawLine(x+2, y, x+10, y-size*9/10);
		g.drawLine(x-4, y, x-20, y-size*4/5);
		g.drawLine(x+4, y, x+20, y-size*4/5);
		g.drawLine(x-6, y, x-30, y-size*7/10);
		g.drawLine(x+6, y, x+30, y-size*7/10);
		g.drawLine(x-8, y, x-40, y-size*4/7);
		g.drawLine(x+8, y, x+40, y-size*4/7);
		g2.setStroke(new BasicStroke(1));

		panel.repaint();
		 
	 }
	
	public String getName() {
		
		return "Zostera";
	}
	
	public int getSize() {
		
		return this.size;
	}
	
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}
	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}
	public void setSize(Immobile i)
	{
		// TODO Auto-generated method stub
		this.size=i.getSize();
		
	}
	public void setX(Immobile i)
	{
		// TODO Auto-generated method stub
		this.x=i.getX();
		
	}
	public void setY(Immobile i)
	{
		// TODO Auto-generated method stub
		this.y=i.getY();
		
	}

}
