/*
 * Liron Himbert - 337731797
 * Yossef Simhon - 311217020*/
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class AddPlantDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private SeaCreature Plant;
	private JLabel type,x_coord,y_coord,size;
	private JPanel DialogPanel=new JPanel();
	private AquaPanel panel;
	private JCheckBox laminaria= new JCheckBox("Laminaria");
	private JCheckBox zostera= new JCheckBox("Zostera");
	private String[] names = {"Laminaria","Zostera"};  
	private JComboBox<String> cmb_plants;
	private JButton ok,cl;
	private JSlider xc = new JSlider(0,1200);
	private JSlider yc = new JSlider(0,700);
	private JSlider sizeOfPlant= new JSlider(20,320);
    
    public AddPlantDialog(AquaPanel panel)
	{
		this.panel=panel;		
		setSize(500,500);
		setLocationRelativeTo(null);
		type = new JLabel("type of plant:");
		DialogPanel.setLayout(new GridLayout(6,2,0,0));
		DialogPanel.add(type);	
		//add the text
		cmb_plants=new JComboBox<String>(names);
		DialogPanel.add(cmb_plants);
	
		final JLabel sizePlant = new JLabel(" Size :");
		sizePlant.setFont(new Font(null,1,12));
		DialogPanel.add(sizePlant);
		sizeOfPlant.setMinorTickSpacing(1);
		sizeOfPlant.setMajorTickSpacing(1);
		sizeOfPlant.setPaintTicks(true);
		DialogPanel.add(sizeOfPlant);
		sizeOfPlant.addChangeListener(new ChangeListener()
		{														
			public void stateChanged(ChangeEvent event)
			{
				int current = sizeOfPlant.getValue();
				sizePlant.setText(" choose size of the plant:    "+ String.valueOf(current));
			}
		});	
		final JLabel x= new JLabel(" Coord X    350");
		sizePlant.setFont(new Font(null,1,12));
		DialogPanel.add(x);
		xc.setMinorTickSpacing(1);
		xc.setMajorTickSpacing(1);
		xc.setPaintTicks(true);
		DialogPanel.add(xc);
		xc.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				int current = xc.getValue();
				x.setText(" x Coordinate :    "+ String.valueOf(current));
			}
		});	
		
		final JLabel y= new JLabel(" Coord Y");
		sizePlant.setFont(new Font(null,1,12));
		DialogPanel.add(y);
		yc.setMinorTickSpacing(1);
		yc.setMajorTickSpacing(1);
		yc.setPaintTicks(true);
		DialogPanel.add(yc);
		yc.addChangeListener(new ChangeListener()	
		{													
			public void stateChanged(ChangeEvent event)
			{
				int current = yc.getValue();
				y.setText("y Coordinate :    "+ String.valueOf(current));
			}
		});	
		final JLabel label2 = new JLabel();
		DialogPanel.add(label2);
		final JLabel label3 = new JLabel();
		DialogPanel.add(label3);
		ok=new JButton("add");
		DialogPanel.add(ok);
		cl=new JButton("cancel");
		DialogPanel.add(cl);
		cl.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		ok.addActionListener(this);
		add(DialogPanel);
		DialogPanel.setVisible(true);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==ok)
		{
			setVisible(false);
			String plantType;
			int x,y,size;
			plantType=cmb_plants.getItemAt(cmb_plants.getSelectedIndex());
			size=sizeOfPlant.getValue();
			x=xc.getValue();
			y=yc.getValue();
			AbstractSeaFactory plant = new PlantFactory(size, x, y, Color.green, this.panel);
			SeaPlant swimer = plant.produceSeaPlant(plantType);
			panel.addToPlantsHashSet(swimer);
			repaint();
		}

	}

}
