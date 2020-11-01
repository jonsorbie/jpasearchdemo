package sorbie.jon.searchdemo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import sorbie.jon.searchdemo.search.PetSpecificationsBuilder;
import sorbie.jon.searchdemo.search.SearchCriteria;
import sorbie.jon.searchdemo.search.GraphQLSpecifications;
import sorbie.jon.searchdemo.database.OwnerRepository;
import sorbie.jon.searchdemo.database.PetRepository;
import sorbie.jon.searchdemo.domain.Owner;
import sorbie.jon.searchdemo.domain.Pet;
import sorbie.jon.searchdemo.domain.Species;

import java.util.List;

@Service
public class GraphQLService {

    private final Logger log = LoggerFactory.getLogger(GraphQLService.class);

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private PetRepository petRepository;

    public List<Pet> petSearch(List<SearchCriteria> params) {
        PetSpecificationsBuilder builder = new PetSpecificationsBuilder();
        for (SearchCriteria param : params) {
            builder = builder.with(param);
        }
        Specification<Pet> spec = builder.andCombination();
        return petRepository.findAll(spec);
    }

    public Pet retrievePet(String name) {
        log.info("Retrieving pet with name {}", name);
        List<Pet> pets = petRepository.findAll(GraphQLSpecifications.isNamed(name));
        return pets.isEmpty() ? null : pets.get(0);
    }

    public List<Pet> retrievePets(Species species) {
        log.info("Retrieving pets of species {}", species);
        return petRepository.findAll(GraphQLSpecifications.isSpecies(species));
    }

    public List<Owner> retrieveOwnersNamed(String firstName) {
        log.info("Retrieving owners with first name {}", firstName);
        return ownerRepository.findAll(GraphQLSpecifications.hasFirstName(firstName));
    }

    public List<Pet> retrievePets(Owner owner) {
        log.info("Retrieving pets for owner {} {}", owner.getFirstName(), owner.getLastName());
        return petRepository.findAll(GraphQLSpecifications.hasOwner(owner));
    }
}
