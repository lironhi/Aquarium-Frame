
/*
 * Liron Himbert - 337731797*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DuplicateAnimal extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JCheckBox Fish, Jellyfish;
	private JCheckBox red, green, black, orange, yellow;
	private JComboBox<String> veSpeedBox, hoSpeedBox;
	private JSlider sizeOfFish = new JSlider(20, 320);
	private AquaPanel Panel;
	private JPanel DuplicatePanel = new JPanel();
	private JPanel ChooseDuplicate = new JPanel();/////////////////////

	private JButton Next = new JButton("Next");
	private JButton Choose = new JButton("Choose");
	private int counter = 0, counter2 = 0;

	private Boolean Fishflag, Jellyflag, FishChoose = false, JellyChoose = false;
	private ArrayList<SeaCreature> MyList = new ArrayList<SeaCreature>();
	private ArrayList<JCheckBox> CheckList = new ArrayList<JCheckBox>();
	private SeaCreature[] arr;
	private JComboBox<String> Cmb_Index;

	public DuplicateAnimal(AquaPanel panel) {
		this.Panel = panel;
		this.setSize(400, 400); // size of the new panel
		setLocationRelativeTo(null);
		settingPanel();
		add(DuplicatePanel);// add the panel to north side of the frame

		panel.setVisible(true);
		setVisible(true);
	}

	public void settingPanel() {

		JLabel type = new JLabel(" choose type of fish you wont to Duplicate:");
		DuplicatePanel.setLayout(new GridLayout(4, 1, 0, 0));

		Fish = new JCheckBox("Fish");
		Jellyfish = new JCheckBox("JellyFish");
		DuplicatePanel.add(type);

		DuplicatePanel.add(Fish);
		DuplicatePanel.add(Jellyfish);
		DuplicatePanel.add(Next);
		Fishflag = false;
		Jellyflag = false;
		for (SeaCreature s : this.Panel.setAnimals) // draw all the animals
		{
			if (s.getAnimalName() == "Fish") {
				Fishflag = true;
			}
			if (s.getAnimalName() == "Jellyfish") {
				Jellyflag = true;
			}
		}
		if (Fishflag == false) {
			Fish.setText("Not Fish!");

		}
		if (Jellyflag == false) {
			Jellyfish.setText("Not Jellyfish!");

		}
		if (Fishflag == false && Jellyflag == false) {
			JOptionPane.showMessageDialog(this, "Type", "Error!", JOptionPane.ERROR_MESSAGE);

		} else {

			if (Fishflag == false) {
				Fish.setEnabled(false);
			}
			if (Jellyflag == false) {
				Jellyfish.setEnabled(false);
			}
			setVisible(true);
			DuplicatePanel.setVisible(true);

		}
		Fish.addActionListener(this);
		Jellyfish.addActionListener(this);
		Next.addActionListener(this);
	}

	public void ChooseOneFish() {
		JLabel type1 = new JLabel(" choose the fish you wont to Duplicate:");
		ChooseDuplicate.setLayout(new GridLayout(8, 4, 0, 0));
		add(ChooseDuplicate);
		ChooseDuplicate.add(type1);
		for (SeaCreature s : this.Panel.setAnimals) // draw all the animals
		{
			if (s.getAnimalName() == "Fish") {
				counter++;
			}
		}

		PrintFishItem();
		String[] index;
		index = new String[counter];
		for (int i = 0; i < counter; i++)
			index[i] = Integer.toString(i + 1);
		Cmb_Index = new JComboBox<String>(index);

		ChooseDuplicate.add(Cmb_Index);
		ChooseDuplicate.add(Choose);
		ChooseDuplicate.setVisible(true);

		Choose.addActionListener(this);

	}

	public void ChooseOneJelly() {

		JLabel type1 = new JLabel(" choose the jelly fish you wont to Duplicate:");
		ChooseDuplicate.setLayout(new GridLayout(8, 4, 0, 0));
		add(ChooseDuplicate);
		ChooseDuplicate.add(type1);
		for (SeaCreature s : this.Panel.setAnimals) // draw all the animals
		{
			if (s.getAnimalName() == "Jelly-Fish") {
				counter++;
			}
		}
		PrintJellyItem();
		String[] index;
		index = new String[counter];
		for (int i = 0; i < counter; i++)
			index[i] = Integer.toString(i + 1);
		Cmb_Index = new JComboBox<String>(index);

		ChooseDuplicate.add(Cmb_Index);
		ChooseDuplicate.add(Choose);
		ChooseDuplicate.setVisible(true);
		Choose.addActionListener(this);

	}

	public void PrintFishItem() {
		arr = new SeaCreature[counter];
		int i = 0;
		for (SeaCreature s : this.Panel.setAnimals) // draw all the animals
		{
			if (s.getAnimalName() == "Fish") {
				JLabel type3 = new JLabel(i + 1 + ") Fish - size:" + s.getSize() + ", color:" + s.getColor()
						+ ", verSpeed: " + s.getVerSpeed() + ", horSpeed: " + s.getHorSpeed());
				ChooseDuplicate.add(type3);
				arr[i] = s;
				i++;
			}
		}
	}

	public void PrintJellyItem() {
		arr = new SeaCreature[counter];
		int i = 0;
		for (SeaCreature s : this.Panel.setAnimals) // draw all the animals
		{
			if (s.getAnimalName() == "Jelly-Fish") {
				JLabel type3 = new JLabel(i + 1 + ") JellyFish - size:" + s.getSize() + ", color:" + s.getColor()
						+ ", verSpeed: " + s.getVerSpeed() + ", horSpeed: " + s.getHorSpeed());
				ChooseDuplicate.add(type3);
				arr[i] = s;
				i++;
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Fish) {
			if (Fish.isSelected()) {
				Jellyfish.setEnabled(false);

			} else if (Jellyflag == true) {
				Jellyfish.setEnabled(true);

			}
		}
		if (e.getSource() == Jellyfish) {
			if (Jellyfish.isSelected()) {
				Fish.setEnabled(false);

			} else if (Fishflag == true) {
				Fish.setEnabled(true);
			}

		}
		if (e.getSource() == Next) {
			DuplicatePanel.setVisible(false);
			if (!(Fish.isSelected()) && (!Jellyfish.isSelected())) {
				JOptionPane.showMessageDialog(this, "You need type to duplicate !", "Error!",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			else {
				if (Fish.isSelected()) {
					FishChoose = true;

					ChooseOneFish();

				} else {
					JellyChoose = true;

					ChooseOneJelly();
				}
			}

		}
		if (e.getSource() == Choose) {
			if (FishChoose == true) {
				SeaCreature temp = arr[Cmb_Index.getSelectedIndex()];
				temp.cloneFish(); // return the details
				Panel.addAnimal("Fish", temp.getSize(), temp.getHorSpeed(), temp.getVerSpeed(), temp.getActColor());
				ChooseDuplicate.setVisible(false);
				dispose();
			} else {
				SeaCreature temp = arr[Cmb_Index.getSelectedIndex()];
				temp.cloneJellyfish(); // return the details
				Panel.addAnimal("Jellyfish", temp.getSize(), temp.getHorSpeed(), temp.getVerSpeed(),
						temp.getActColor());
				ChooseDuplicate.setVisible(false);
				dispose();
			}

		}

	}
}
