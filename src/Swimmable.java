
/*
 * Liron Himbert - 337731797*/
import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CyclicBarrier;

public abstract class Swimmable extends Thread implements SeaCreature {

	protected int horSpeed;
	protected int verSpeed;

	public Swimmable() {
		horSpeed = 0;
		verSpeed = 0;
	}

	public Swimmable(int hor, int ver) {
		horSpeed = hor;
		verSpeed = ver;
	}

	public int getHorSpeed() {
		return horSpeed;
	}

	public int getVerSpeed() {
		return verSpeed;
	}

	public void setHorSpeed(int hor) {
		horSpeed = hor;
	}

	public void setVerSpeed(int ver) {
		verSpeed = ver;
	}

	abstract public String getAnimalName();

	abstract public void drawCreature(Graphics g);

	abstract public void setSuspend();

	abstract public void setResume();

	abstract public void setBarrier(CyclicBarrier b);

	abstract public int getSize();

	abstract public void eatInc();

	abstract public int getEatCount();

	abstract public String getColor();

	abstract public Fish cloneFish();

	abstract public Jellyfish cloneJellyfish();

	abstract public int getX();

	abstract public int getY();

	abstract public Color getActColor();

}
