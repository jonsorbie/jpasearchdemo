package sorbie.jon.searchdemo.database;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import sorbie.jon.searchdemo.domain.Pet;

public interface PetRepository extends CrudRepository<Pet, Long>, JpaSpecificationExecutor<Pet> {

    // The following declarations become unnecessary with the use of Specifications
    // List<Pet> findByName(String name);
    // List<Pet> findBySpecies(Species species);
    // List<Pet> findByOwner(Owner owner);
}
