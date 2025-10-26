/*
 * Liron Himbert - 337731797
 * Yossef Simhon - 311217020*/
import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CyclicBarrier;

public interface SeaCreature {

	void drawCreature(Graphics g);
	String getAnimalName();
	String getColor();
	
	int getSize();
	int getX();
	int getY();
	int getHorSpeed();
	int getVerSpeed();
	int getEatCount();
	void setSuspend();
	void setResume();
	void setBarrier(CyclicBarrier barr);
	public void eatInc();
	void setSize(SeaCreature x );
	void setX(SeaCreature x);
	void setY(SeaCreature x);
	void setColor(SeaCreature x);
	void setVerSpeed(SeaCreature x);
	void setHorSpeed(SeaCreature x); 
	Color getActColor();
	Fish cloneFish();
	Jellyfish cloneJellyfish();
	
	
}