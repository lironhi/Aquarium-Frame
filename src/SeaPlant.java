/*
 * Liron Himbert - 337731797
 * Yossef Simhon - 311217020*/
import java.awt.Graphics;

public interface SeaPlant {

	void drawCreature(Graphics g);
	String getName();
	int getSize();
	int getX();
	int getY();
	void setSize(Immobile i);
	void setX(Immobile i);
	void setY(Immobile i);
}
