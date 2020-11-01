package sorbie.jon.searchdemo.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sorbie.jon.searchdemo.domain.Owner;
import sorbie.jon.searchdemo.domain.Pet;
import sorbie.jon.searchdemo.domain.Species;

/**
 * This component will run once, initializing a few entities in the H2 database
 */
@Component
public class H2Initializer implements CommandLineRunner {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private PetRepository petRepository;

    @Override
    public void run(String... args) {
        populateDB();
    }

    /**
     * Populate the in-memory DB with 3 owners, 4 pets
     */
    private void populateDB() {
        // A few owners
        Owner sean = createOwner("Sean", "Connery");
        Owner roger = createOwner("Roger", "Moore");
        Owner ursula = createOwner("Ursula", "Andress");

        // A few pets
        createPet("Fido", Species.DOG, 12, sean);
        createPet("Spike", Species.DOG, 4, roger);
        createPet("Whiskers", Species.CAT, 11, roger);
        createPet("Bubbles", Species.FISH, 1, ursula);
    }

    private Owner createOwner(String firstName, String lastName) {
        Owner owner = new Owner(firstName, lastName);
        ownerRepository.save(owner);
        return owner;
    }

    private Pet createPet(String name, Species species, int age, Owner owner) {
        Pet pet = new Pet(name, species, age);
        pet.setOwner(owner);
        petRepository.save(pet);
        return pet;
    }
}
