import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

/*******************************
 * Author: Alexander McDougall *
 * Class: Cage                 *
 * Date: 18/01/19              *
 *******************************/

public class Cage {
	
	// Declaring Variables
	private int IDNumber;
	private int numberOfAnimals;
	private int enclosureID;
	private int cageDR;
	private String category;
	final int maximumNumberOfAnimals = 8;
	
	// ArrayList
	private List<Animal> myAnimals = new ArrayList<Animal>();

	// Constructors
	public Cage() {

	}
	
	public Cage(int enclosureID,  int IDNumber, String category, int cageDR, List<Animal>tempAnimals) throws IOException {
		
		this.category = category;
		this.enclosureID = enclosureID;
		this.IDNumber = IDNumber;
		this.cageDR = cageDR;

		
		//Start the process of adding Animals.
		for (int i = 0; i < tempAnimals.size(); i++)
		{
			if (IDNumber == tempAnimals.get(i).getCageID()) {
				myAnimals.add(new Animal(tempAnimals.get(i).getSpecies(), 
						tempAnimals.get(i).getAnimalID(), 
						tempAnimals.get(i).getAnimalType(),
						tempAnimals.get(i).getDangerRating(), 
						tempAnimals.get(i).getCageID()));
				numberOfAnimals++; //increment the number of Animals by 1 at each run.
			}
		}
	}

	//*********************** SETTERS & GETTERS ***********************
	public int getEnclosureID() {
		return enclosureID;
	}

	public void setEnclosureID(int enclosureNumber) {
		this.enclosureID = enclosureNumber;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getCageDR() {
		return cageDR;
	}

	public void setCageDR(int cageDR) {
		this.cageDR = cageDR;
	}

	public int getIDNumber() {

		return IDNumber;
	}

	public void setIDNumber(int IDNumber) {
		this.IDNumber = IDNumber;
	}

	public int getNumberOfAnimals() {
		return numberOfAnimals;
	}

	public void setNumberOfAnimals(int numberOfAnimals) {
		this.numberOfAnimals = numberOfAnimals;
	}

	public List<Animal> getMyAnimals() {
		return myAnimals;
	}

	public void setMyAnimals(List<Animal> myAnimals) {
		this.myAnimals = myAnimals;
	}

	public int getMaximumNumberOfAnimals() {
		return maximumNumberOfAnimals;
	}
	//*****************************************************************

	//cageDataEntry method will allow the user to add a Cage with Category.
	public void cageDataEntry(int enclosureID2,  int numberOfCages) {
		String cageCategory;
		int dangerRating;

		do {
			int tempCageIDNumber; //Attempt to create Cage number automatically.
			
			// In future, possibly change the way an ID is generated.
			tempCageIDNumber = enclosureID2 + (numberOfCages + 1);
			
			setIDNumber(tempCageIDNumber);
			System.out.print("\nPlease enter the Category for the new Cage: ");
			cageCategory = Validate.validateString();
			setCategory(cageCategory);
			System.out.print("\nPlease enter a Danger Rating for the new Cage: ");
			dangerRating = Validate.validateInteger();
			setCageDR(dangerRating);
			// Validates user input.
			if (cageCategory.equals("") || cageCategory.equals(null)) {//nothing entered for the Category.
				System.out.println("You must enter a category, please try again.");
			}
		} while (cageCategory.equals("") || cageCategory.equals(null));//nothing entered for the ID
	}
	// Displaying Cage details to the user via console.
	public void displayCageDetails(int i) {
		System.out.printf((i + 1) + "-%10s %-10s %-10s %-10s %n",
				"\tEnclosure ID: "  +  getEnclosureID(),  
				"\tCage ID: "  +  IDNumber,
				"\tCategory: "  +  category,
				"\tCage Danger Rating: "  +  cageDR);
	}

	// addAnimal method allows the user via console. 
	public int addAnimal(String enclosureTitle,  int cageNumber) {
		int numberOfAnimalsAdded = 0; //the purpose of this variable to update the total number of Animals in the zoo
		String answer = "YES";
		System.out.println("Adding Animals.");
		System.out.println("This Cage has " + myAnimals.size() + " Animals.");
		System.out.println("This Cage has a Danger Rating of " + getCageDR() + '.');
		System.out.println("The maximum number of Animals in this Cage is " + maximumNumberOfAnimals + " Animals.");
		Validate.validateString();
		
		while(answer.equals("yes".toUpperCase())) {
			/*
			 * Validates the size of the myAnimals array to make sure Animals can be added to the cage,
			 * If cage is full, user will be told and will break.
			 * If the cage isn't full, the process to add an Animal will start.
			 */
			if (myAnimals.size() >= maximumNumberOfAnimals) {
				System.out.print("No more animals can be added to this Cage,  press Enter to go to menu.");
				Validate.validateString();
				break;
			}
			else if(answer.equals("no".toUpperCase())) {
				break;
			}
			else {
				Animal tempAnimal  = new Animal();
				//Pass the number of Animals to automate animalID in the Animals's dataEntry() method
				tempAnimal.animalDataEntry(numberOfAnimals, enclosureTitle, cageNumber, IDNumber, cageDR);
				tempAnimal.setCageID(IDNumber);
				myAnimals.add(tempAnimal);
				numberOfAnimals++;
				numberOfAnimalsAdded++;//Each time a book added increment the number of Animals by 1
				System.out.print("Would you like to add another Animal [Yes/No]: ");
				answer = Validate.validateString().toUpperCase();
			}
		}

		//returns the total number of Animals added to the Cage at each call to this method
		return numberOfAnimalsAdded;
	}
}