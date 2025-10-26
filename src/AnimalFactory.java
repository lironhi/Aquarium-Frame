/*
 * Liron Himbert - 337731797
 * Yossef Simhon - 311217020*/
import java.awt.Color;
import java.awt.Panel;

public class AnimalFactory extends AbstractSeaFactory {
	
	
	int size;
	int x;
	int y;
	int hors;
	int vers;
	Color c;
	AquaPanel panel;
	
	public AnimalFactory(int s,int x, int y, int h, int v, Color col, AquaPanel p)
	{
		
		this.size = s;
		this.x = x;
		this.y = y;
		this.hors = h;
		this.vers = v;
		this.c = col;
		this.panel = p;
	}

	@Override
	public SeaCreature produceSeaCreature(String type)
	{
		if (type.equalsIgnoreCase("Fish")) {
			return new Fish(size, x, y, hors, vers, this.c, panel);
		}
		if (type.equalsIgnoreCase("Jelly-Fish")) {
			return new Jellyfish(size, x, y, hors, vers, this.c, panel);
		}
		return null;
	}

	@Override
	public SeaPlant produceSeaPlant(String type) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
