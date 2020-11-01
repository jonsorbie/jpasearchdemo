package sorbie.jon.searchdemo.graphqlresolvers;

import graphql.kickstart.tools.GraphQLResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sorbie.jon.searchdemo.domain.Owner;
import sorbie.jon.searchdemo.domain.Pet;
import sorbie.jon.searchdemo.services.GraphQLService;

import java.util.List;

@Component
public class OwnerResolver implements GraphQLResolver<Owner> {

    private final Logger log = LoggerFactory.getLogger(OwnerResolver.class);

    @Autowired
    private GraphQLService service;

    public List<Pet> pets(Owner owner) {
        log.info("Resolving pets for owner {} {}", owner.getFirstName(), owner.getLastName());
        return service.retrievePets(owner);
    }
}
