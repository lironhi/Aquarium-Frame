
/*
 * Liron Himbert - 337731797*/
import java.awt.Color;

public class PlantFactory extends AbstractSeaFactory {

	Color c;
	int size;
	int x;
	int y;
	AquaPanel panel;

	public PlantFactory(int size, int x, int y, Color col, AquaPanel p) {
		this.c = col;
		this.size = size;
		this.x = x;
		this.y = y;
		this.panel = p;
	}

	@Override
	public SeaPlant produceSeaPlant(String type) {
		if (type.equalsIgnoreCase("Zostera")) {
			return new Zostera(size, x, y, this.c, panel, "Zostera");
		}
		if (type.equalsIgnoreCase("Laminaria")) {
			return new Laminaria(size, x, y, this.c, panel, "Laminaria");
		}
		return null;
	}

	@Override
	public SeaCreature produceSeaCreature(String type) {
		return null;
	}

}
