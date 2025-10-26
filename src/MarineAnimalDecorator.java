/*
 * Liron Himbert - 337731797*/
public class MarineAnimalDecorator implements MarineAnimal {
	public MarineAnimal animal;

	public MarineAnimalDecorator(MarineAnimal animal) {
		this.animal = animal;
	}

	public void PaintFish() {
		this.animal.PaintFish();
	}

}