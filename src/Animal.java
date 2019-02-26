
/*******************************
 * Author: Alexander McDougall *
 * Class: Animal               *
 * Date: 18/01/19              *
 *******************************/

public class Animal {

	// Declare Variables.
	private String animalID;
	private String species;
	private String animalType;
	private int cageID;
	private int dangerRating;

	// Constructors
	public Animal() {
		
	}

	public Animal(String species, String animalID, String animalType, int dangerRating, int cageID) {
		
		this.cageID = cageID;
		this.animalID = animalID;
		this.species = species;
		this.animalType = animalType;
		this.dangerRating = dangerRating;
	}

	//*********************** SETTERS & GETTERS *********************** 
	public int getCageID() {
		return cageID;
	}
	public void setCageID(int ID) {
		this.cageID=ID;
	}
	
	public String getAnimalID() {
		return animalID;
	}
	public void setAnimalID(String animalID) {
		this.animalID = animalID;
	}
	
	public String getSpecies() {
		return species;
	}
	public void setAnimalType(String animalType){
		this.animalType = animalType;
	}
	public String getAnimalType() {
		return animalType;
	}
	public void setSpecies(String species) {
		this.species = species;
	}

	public int getDangerRating() {
		return dangerRating;
	}

	public void setDangerRating(int dangerRating) {
		this.dangerRating = dangerRating;
	}
	//*****************************************************************

	// animalDataEntry method will allow for the user to enter animal data in system.
	public void animalDataEntry(int numberOfAnimals, String enclosureTitle, int cageNumber,int iDNumber, int cageDangerRating) {

		String mySpecies;
		String animalType;
		int dangerRating;
		String tempAnimalIDNumber; //Attempt to create animal number automatically.
		tempAnimalIDNumber = Integer.toString(numberOfAnimals + 1);

		// do Statement for the user to allow user to enter an Animal into the system.
		do {
			System.out.print("Please enter the Species of the Animal " + (numberOfAnimals + 1) + " in Cage " +
					cageNumber + " ID[" + iDNumber + "] in Enclosure [" + enclosureTitle + "]:");
			mySpecies = Validate.validateString();

			// Animal ID, gets first two characters of species & adds a number to identify animal.
			if (mySpecies != null) {
				setAnimalID(mySpecies.substring(0, 2).toLowerCase() + tempAnimalIDNumber);
				setSpecies(mySpecies);
			}
			// Validates user input.
			if (mySpecies.equals("") || mySpecies.equals(null)) {
				System.out.println("You must enter a proper Species, please try again.");
			}

			// Asks user the type of animal being entered, input is validated by Validation.jar,
			// Using a while loop, I'm checking the users input to make sure that it equals to 
			// mammal, bird or reptile. No other entry should be accepted. It will continue to loop
			// until the user enters a valid type.
			System.out.println("Enter a Type for the Animal (Mammal, Bird, Reptile):");
			animalType = Validate.validateString();
			boolean check = false;
			while (check != true) {
				// If the user input is equal to mammal, bird or reptile, animalType is then set.
				if (animalType.equals("mammal") || animalType.equals("bird") || animalType.equals("reptile")) {
					check = true;
					setAnimalType(animalType); 
				}
				// If input isn't equal to mammal, bird or reptile, tells user.
				else {
					System.out.println("You must enter a proper Type (Mammal, Bird or Reptile) please try again.");
					animalType = Validate.validateString();
			}
		}
			
			// Asks user to danger rating of animal being entered, input is validated by Validation.jar,
			// Using a while loop, I'm checking the users input to make sure that it equals to 
			// cageDangerRating stored in the file. No other entry should be accepted so a suitable animal
			// is added to the Cage. It will continue to loop until the user enters a valid type.
			System.out.println("Enter a Danger Rating for the Animal, 1-5.");
			dangerRating = Validate.validateInteger();
			boolean input = false;
			while (input != true) {
				// If the user input is equal to the cageDangerRating read from the file, it will 
				// Successfully add the animal to the cage.
				if (dangerRating == cageDangerRating) {
					input = true;
					setDangerRating(dangerRating);
			} 
			else {
				// If input isn't equal to cage danger rating, tells user.
				System.out.println("You must enter the correct Danger Rating to match the cage, please try again.");
				dangerRating = Validate.validateInteger();
			}
		}
	} while (mySpecies.equals("") || mySpecies.equals(null));
		System.out.println("An Animal has been successfully added to the cage, press any key to continue.");
		// Increment numberOfAnimals since a new Animal has been added.
		numberOfAnimals++;
	}


	
	// Displaying Animal details to the user via console.
	public void displayAnimalDetails(int k, int tempCageID) {
		System.out.printf("\t\t" + (k + 1) + "%10s %10s %10s %10s %10s %n"," - Cage ID: " + (tempCageID),
				" Animal ID: " + animalID,
				" Species: " + species,
				" Animal Type: " + animalType,
				" Danger Rating: " + dangerRating);
	}


}