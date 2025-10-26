/*
 * Liron Himbert - 337731797
 * Yossef Simhon - 311217020*/
import java.awt.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import javax.swing.*;

public class Fish extends Swimmable implements MarineAnimal {
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
	 
	 public Fish(int s,int x, int y, int h, int v, Color c, AquaPanel p) {
		 super(h,v);
		 size = s;
		 x_front = x;
		 y_front = y;
		 x_dir = 1;
		 y_dir = 1;
		 col = c;
		 pan = p;
		 barrier = null;
		 eatCount=0;
		 threadSuspended = false;
	 }
	 
	 public void eatInc() {
		 eatCount++;
	 }
	 
	 public int getSize() {
		 return size;
	 }
	 

	 public int getEatCount() {
		 return eatCount;
	 }
	 
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.x_front;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.y_front;
	}
	 
	 synchronized public void setSuspend() {
		 threadSuspended = true;
	 }
	 
	 synchronized public void setResume() {
		 threadSuspended = false; notify();
	 }
	 
	 public void setBarrier(CyclicBarrier b) { 
		 barrier = b;
	 }
	 
	 public String getAnimalName() {
		 return "Fish";
	 }
	 
	 public Color getActColor()
		{
			return this.col;
		}
	 
	 public String getColor() { 
		 if(col==Color.black)
			 return "Black";
		 if(col==Color.red)
			 return "Red";
		 if(col==Color.blue)
			 return "Blue";
		 if(col==Color.green)
			 return "Green";
		 if(col==Color.cyan)
			 return "Cyan";
		 if(col==Color.orange)
			 return "Orange";
		 if(col==Color.yellow)
			 return "Yellow";
		 if(col==Color.magenta)
			 return "Magenta";
		 if(col==Color.pink)
			 return "Pink";
		 return null;
	 }
	 
	
	public void setColor(SeaCreature x) {
		this.col=x.getActColor();
		
	}
	 
	@Override
	public void setX(SeaCreature x) {
		this.x_front=x.getX();
	}

	@Override
	public void setY(SeaCreature x) {
		this.y_front=x.getY();
		
	}

	public void setColor(Color color) {
		this.col=color;
	}

	@Override
	public void setSize(SeaCreature x) {
		this.size=x.getSize();
		
	}
	
	public void setVerSpeed(SeaCreature x)
	{
		// TODO Auto-generated method stub
		this.verSpeed=x.getVerSpeed();
		
	}

	public void setHorSpeed(SeaCreature x)
	{
		// TODO Auto-generated method stub
		this.horSpeed=x.getHorSpeed();
		
	}
		
		
     public void run() 
     {
        while (true) 
        {
            try 
            {
                Thread.sleep(100);
                
                synchronized(this) {
                    while (threadSuspended)
    						wait();
    				}  
           } 
            catch (InterruptedException e) 
            {
            	System.out.println("Sleep ERROR!");
            }
                       
            if(pan.checkFood())
            {
            	if(barrier!=null)
            	{
            		try {
	                    int k = barrier.await();
	                    System.out.println(getColor()+" fish ("+k+") is ready to eat!");
	                    barrier = null;
	                  } catch (InterruptedException ex) {
	                    return;
	                  } catch (BrokenBarrierException ex) {
		                    return;
                	  } catch (Exception ex) {
                		  return;
                	  }
            	}
            	double oldSpead = Math.sqrt(horSpeed*horSpeed+verSpeed*verSpeed);
               	double newHorSpeed = oldSpead*(x_front - pan.getWidth()/2)/(Math.sqrt(Math.pow(x_front - pan.getWidth()/2,2)+Math.pow(y_front - pan.getHeight()/2,2)));
               	double newVerSpeed = oldSpead*(y_front - pan.getHeight()/2)/(Math.sqrt(Math.pow(x_front - pan.getWidth()/2,2)+Math.pow(y_front - pan.getHeight()/2,2)));
               	x_front -= newHorSpeed;
               	y_front -= newVerSpeed;
               	if(x_front<pan.getWidth()/2)
               		x_dir = 1;
               	else
               		x_dir = -1;
               	if((Math.abs(x_front-pan.getWidth()/2)<EAT_DISTANCE) && (Math.abs(y_front-pan.getHeight()/2)<EAT_DISTANCE))
               	{
               		//pan.repaint();
               		pan.eatFood(this);
               	}
            }
            else
            {
			    x_front += horSpeed*x_dir;
			    y_front += verSpeed*y_dir;
            }

		    if(x_front > pan.getWidth())
		    {
		    	x_dir = -1;
		    	x_front = (int) (pan.getWidth()-size*1.25);
		    }
		    else if(x_front < 0)
		    {
		    	x_dir = 1;
		    	x_front = (int) (size*1.25);
		    }
	
		    if(y_front > (int) (pan.getHeight()-30-size*0.25))
		    {
		    	y_dir = -1;
		    }
		    else if(y_front < (int) (size*0.25))
		    {
		    	y_dir = 1;
		    }

            pan.repaint();
       }
    }
    public void drawCreature(Graphics g)
    {
		g.setColor(col);
		if(x_dir==1) // fish swims to right side
		{
			// Body of fish
			g.fillOval(x_front - size, y_front - size/4, size, size/2);

			// Tail of fish
			int [] x_t = {x_front - size - size/4, x_front - size - size/4, x_front - size};
			int [] y_t = {y_front - size/4, y_front + size/4, y_front};
			Polygon t = new Polygon(x_t,y_t,3);		
			g.fillPolygon(t);

			// Eye of fish
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(255-col.getRed(),255-col.getGreen(),255-col.getBlue()));
			g2.fillOval(x_front - size/5, y_front - size/10, size/10, size/10);
			
			// Mouth of fish
			if(size>70)
				g2.setStroke(new BasicStroke(3));
			else if(size>30)
				g2.setStroke(new BasicStroke(2));
			else
				g2.setStroke(new BasicStroke(1));
            g2.drawLine(x_front, y_front, x_front-size/10, y_front+size/10);
            g2.setStroke(new BasicStroke(1));
		}
		else // fish swims to left side
		{
			// Body of fish
			g.fillOval(x_front, y_front - size/4, size, size/2);

			// Tail of fish
			int [] x_t = {x_front + size + size/4, x_front + size + size/4, x_front + size};
			int [] y_t = {y_front - size/4, y_front + size/4, y_front};
			Polygon t = new Polygon(x_t,y_t,3);		
			g.fillPolygon(t);

			// Eye of fish
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(255-col.getRed(),255-col.getGreen(),255-col.getBlue()));
			g2.fillOval(x_front + size/10, y_front - size/10, size/10, size/10);
			
			// Mouth of fish
			if(size>70)
				g2.setStroke(new BasicStroke(3));
			else if(size>30)
				g2.setStroke(new BasicStroke(2));
			else
				g2.setStroke(new BasicStroke(1));
           	g2.drawLine(x_front, y_front, x_front+size/10, y_front+size/10);
            g2.setStroke(new BasicStroke(1));
		}
    }

	

	@Override
	public Fish cloneFish() {
		return this;
	}

	@Override
	public Jellyfish cloneJellyfish() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void PaintFish()
	{
		Color col = null;
		col= JColorChooser.showDialog(null,"Choose your color: ", this.col);
		if(col!=null)
		{
			this.col=col;
		}
	}
	
}

