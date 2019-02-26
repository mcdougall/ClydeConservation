import java.util.List;
import java.io.IOException;
import java.util.LinkedList;

/*******************************
 * Author: Alexander McDougall *
 * Class: Enclosure            *
 * Date: 18/01/19              *
 *******************************/

public class Enclosure {

	// Declaring Variables.
	private List<Cage> enclosureCages = new LinkedList<>();
	private int enclosureID;
	private String category;
	private String description;
	private int numberOfCages;//to hold number of added Cages to the Enclosure.
	private int numberOfAnimalsInEnclosure;
	final int maximumNumberOfCages  =  4;

	// Constructors
	public Enclosure() {
		
	}
	
	public Enclosure(int enclosureID, String category, String description, int numberOfCages, List<Cage>tempCages, List<Animal>tempAnimal) throws IOException {

		this.enclosureID = enclosureID;
		this.category = category;
		this.description = description;
		this.numberOfCages = numberOfCages;
		for (int i = 0; i < tempCages.size(); i++)
		{
			if (enclosureID == tempCages.get(i).getEnclosureID())
			{
				enclosureCages.add(new Cage(tempCages.get(i).getEnclosureID(), tempCages.get(i).getIDNumber(),
						tempCages.get(i).getCategory(), tempCages.get(i).getCageDR(), tempAnimal));
				numberOfCages++; //increment the number of Cages by 1 at each run.
			}
		}
	}

	//*********************** SETTERS & GETTERS *********************** 
	
	public void setEnclosureID(int enclosureID) {
		this.enclosureID = enclosureID;
	}

	public int getEnclosureID() {
		return enclosureID;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getNumberOfCages() {
		return numberOfCages;
	}
	
	public void setEnclosureCages(List<Cage> enclosureCages) {
		this.enclosureCages = enclosureCages;
	}

	public List<Cage> getEnclosureCages() {
		return enclosureCages;
	}

	public int getNumberOfAnimalsInEnclosure() {
		return numberOfAnimalsInEnclosure;
	}

	public void setNumberOfAnimalsInEnclosure() {
		// Calculate the total number of animals in the enclosure by looping through the cages 
		// and adding the number of animals in each cage to the total number of animals in each enclosure.
		numberOfAnimalsInEnclosure = 0;//Must be set to 0 otherwise it will be doubled at each run.
		for(int i = 0;i<getEnclosureCages().size();i++) {
			numberOfAnimalsInEnclosure = numberOfAnimalsInEnclosure + getEnclosureCages().get(i).getNumberOfAnimals();
		}
	}

	public int getMaximumNumberOfCages() {
		return maximumNumberOfCages;
	}

	public void setNumberOfAnimalsInEnclosure(int numberOfAnimalsInEnclosure) {
		this.numberOfAnimalsInEnclosure = numberOfAnimalsInEnclosure;
	}
	//*****************************************************************
	
	public boolean checkCageAvailability() { //check if there is at least one cage available in the current enclosure.
	
		boolean cageAvailable = false;
		
		if(!enclosureCages.isEmpty()) {
			cageAvailable = true;
		}
		else {
			cageAvailable = false;
		}
		return cageAvailable;
	}

	// enclosureDataEntry method will allow for the user to enter enclosure data in system. 
	public void enclosureDataEntry() {
		do {
			System.out.print("\nPlease enter a Category for the new Enclosure: ");
			category = Validate.validateString();
			setCategory(category);
			System.out.print("\nPlease enter a description for the new Enclosure: ");
			description = Validate.validateString();
			setDescription(description);
			if(category.equals("") || category.equals(null)|| description.equals("") || description.equals(null)) {
				System.out.println("You must enter a proper category and description, please try again");
			}
		} while(category.equals("") || category.equals(null)||description.equals("") || description.equals(null));
	}

	//Display the details of the Enclosure.
	public void displayEnclosureDetails(int i) {
		System.out.printf((i + 1) + "%18s %-30s %-30s %n", "Enclosure ID: " + getEnclosureID(), "\tCategory:  "
				+ category, "\tDescription: "+getDescription());//"-" for left indentation
	}

	//Adding cages to the specific enclosure, it calls a method in the Cage class to enter Cage details.
	public void addCages(int enclosureNumber) {
		if (enclosureCages.size() == maximumNumberOfCages) {//maximum number of Cages reached.
			System.out.println("You cannot add any more cages to enclosure "
					+ "("+(enclosureNumber)+") ["+getDescription()+"], please "
					+ "press any key to return to the Main Menu");
			Validate.validateString();
		}
		else {
			//Adding cages
			Cage tempCage = new Cage();
			tempCage.cageDataEntry(enclosureID, numberOfCages);//populate the essential data to the new Cage object.
			tempCage.setEnclosureID(enclosureNumber);
			enclosureCages.add(tempCage); //Now add the cage.
			System.out.println("A Cage category " + tempCage.getCategory() +
					" was successfully added to Enclosure "
					+ getEnclosureID());
			numberOfCages++; // Increment the number of Cages in current enclosure by one.
		}

	}

	//Report the cage details for every cage in the enclosure.
	public void listCagesInEnclosure() {
		
		// Loop through all the available cages and display the Cage details via console.
		// try catch statement has been used if an NullPointerException is encountered, 
		// will exit if encountered.
		
		try {
			for(int i = 0; i < enclosureCages.size(); i++) {
				enclosureCages.get(i).displayCageDetails(i);
			}
		}

		catch(NullPointerException e) {
			//Nothing here - just exit gracefully
		}
	}
}
