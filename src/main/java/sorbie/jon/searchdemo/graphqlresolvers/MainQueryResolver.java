package sorbie.jon.searchdemo.graphqlresolvers;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sorbie.jon.searchdemo.domain.Owner;
import sorbie.jon.searchdemo.domain.Pet;
import sorbie.jon.searchdemo.domain.Species;
import sorbie.jon.searchdemo.services.GraphQLService;

import java.util.List;

@Component
public class MainQueryResolver implements GraphQLQueryResolver {

    private final Logger log = LoggerFactory.getLogger(MainQueryResolver.class);

    @Autowired
    private GraphQLService service;

    public Pet petNamed(String name) {
        log.info("Query for pet named {}", name);
        return service.retrievePet(name);
    }

    public List<Pet> petsOfSpecies(Species species) {
        log.info("Query for pets of species {}", species);
        return service.retrievePets(species);
    }

    public List<Owner> ownersNamed(String firstName) {
        log.info("Query for owners with first name {}", firstName);
        return service.retrieveOwnersNamed(firstName);
    }
}
