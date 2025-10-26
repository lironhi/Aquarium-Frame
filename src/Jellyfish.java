
/*
 * Liron Himbert - 337731797*/
import java.awt.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import javax.swing.*;

public class Jellyfish extends Swimmable implements MarineAnimal {

	final private int EAT_DISTANCE = 5;
	private int size;
	private int x_front;
	private int y_front;
	private int x_dir;
	private int y_dir;
	private Color col;
	private int eatCount;
	private AquaPanel pan;
	private boolean threadSuspended;
	private CyclicBarrier barrier;

	public Jellyfish(int s, int x, int y, int h, int v, Color c, AquaPanel p) {
		super(h, v);
		size = s;
		x_front = x;
		y_front = y;
		x_dir = 1;
		y_dir = 1;
		col = c;
		pan = p;
		barrier = null;
		eatCount = 0;
		threadSuspended = false;
	}

	public int getSize() {
		return size;
	}

	public void eatInc() {
		eatCount++;
	};

	public int getEatCount() {
		return eatCount;
	}

	synchronized public void setSuspend() {
		threadSuspended = true;
	}

	synchronized public void setResume() {
		threadSuspended = false;
		notify();
	}

	public void setBarrier(CyclicBarrier b) {
		barrier = b;
	}

	public String getAnimalName() {
		return "Jellyfish";
	}

	public String getColor() {
		if (col == Color.black)
			return "Black";
		if (col == Color.red)
			return "Red";
		if (col == Color.blue)
			return "Blue";
		if (col == Color.green)
			return "Green";
		if (col == Color.cyan)
			return "Cyan";
		if (col == Color.orange)
			return "Orange";
		if (col == Color.yellow)
			return "Yellow";
		if (col == Color.magenta)
			return "Magenta";
		if (col == Color.pink)
			return "Pink";
		return null;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(100);

				synchronized (this) {
					while (threadSuspended)
						wait();
				}
			} catch (InterruptedException e) {
				System.out.println("Sleep ERROR!");
			}

			if (pan.checkFood()) {
				if (barrier != null) {
					try {
						int k = barrier.await();
						System.out.println(getColor() + " jellyfish (" + k + ") is ready to eat!");
						barrier = null;
					} catch (InterruptedException ex) {
						return;
					} catch (BrokenBarrierException ex) {
						return;
					} catch (Exception ex) {
						return;
					}
				}
				double oldSpead = Math.sqrt(horSpeed * horSpeed + verSpeed * verSpeed);
				double newHorSpeed = oldSpead * (x_front - pan.getWidth() / 2) / (Math
						.sqrt(Math.pow(x_front - pan.getWidth() / 2, 2) + Math.pow(y_front - pan.getHeight() / 2, 2)));
				double newVerSpeed = oldSpead * (y_front - pan.getHeight() / 2) / (Math
						.sqrt(Math.pow(x_front - pan.getWidth() / 2, 2) + Math.pow(y_front - pan.getHeight() / 2, 2)));
				x_front -= newHorSpeed;
				y_front -= newVerSpeed;
				if (x_front < pan.getWidth() / 2)
					x_dir = 1;
				else
					x_dir = -1;
				if ((Math.abs(x_front - pan.getWidth() / 2) < EAT_DISTANCE)
						&& (Math.abs(y_front - pan.getHeight() / 2) < EAT_DISTANCE)) {
					// pan.repaint();
					pan.eatFood(this);
				}
			} else {
				x_front += horSpeed * x_dir;
				y_front += verSpeed * y_dir;
			}

			if (x_front + size / 2 > pan.getWidth()) {
				x_dir = -1;
			} else if (x_front - size / 2 < 0) {
				x_dir = 1;
			}

			if (y_front > (int) (pan.getHeight() - 30 - size * 0.25)) {
				y_dir = -1;
			} else if (y_front < (int) (size * 0.25)) {
				y_dir = 1;
			}

			pan.repaint();
		}
	}

	public void drawCreature(Graphics g) {
		int numLegs;
		if (size < 40)
			numLegs = 5;
		else if (size < 80)
			numLegs = 9;
		else
			numLegs = 12;
		g.setColor(col);
		g.fillArc(x_front - size / 2, y_front - size / 4, size, size / 2, 0, 180);
		for (int i = 0; i < numLegs; i++)
			g.drawLine(x_front - size / 2 + size / numLegs + size * i / (numLegs + 1), y_front,
					x_front - size / 2 + size / numLegs + size * i / (numLegs + 1), y_front + size / 3);
	}

	@Override
	public Jellyfish cloneJellyfish() {
		return this;
	}

	@Override
	public Fish cloneFish() {
		return null;
	}

	@Override
	public void setSize(SeaCreature x) {
		this.size = x.getSize();

	}

	@Override
	public void setX(SeaCreature x) {
		this.x_front = x.getX();

	}

	@Override
	public void setY(SeaCreature x) {
		this.x_front = x.getY();

	}

	public void setColor(SeaCreature x) {
		this.col = x.getActColor();

	}

	@Override
	public void setVerSpeed(SeaCreature x) {
		this.verSpeed = x.getVerSpeed();

	}

	@Override
	public void setHorSpeed(SeaCreature x) {
		this.horSpeed = x.getHorSpeed();

	}

	@Override
	public int getX() {
		return this.x_front;
	}

	@Override
	public int getY() {
		return this.y_front;
	}

	@Override
	public Color getActColor() {
		return this.col;
	}

	public void PaintFish() {
		Color col = null;
		col = JColorChooser.showDialog(null, "Choose your color:", this.col);
		if (col != null) {
			this.col = col;
		}
	}

}
