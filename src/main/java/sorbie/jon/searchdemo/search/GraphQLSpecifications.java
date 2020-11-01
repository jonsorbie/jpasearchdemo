package sorbie.jon.searchdemo.search;

import org.springframework.data.jpa.domain.Specification;
import sorbie.jon.searchdemo.domain.Owner;
import sorbie.jon.searchdemo.domain.Owner_;
import sorbie.jon.searchdemo.domain.Pet;
import sorbie.jon.searchdemo.domain.Pet_;
import sorbie.jon.searchdemo.domain.Species;

import javax.persistence.criteria.Path;

public class GraphQLSpecifications {

    public static Specification<Pet> isSpecies(Species species) {
        return (root, query, criteriaBuilder) -> {
            Path<Species> path = root.get(Pet_.species);
            return criteriaBuilder.equal(path, species);
        };
    }

    public static Specification<Pet> isNamed(String name) {
        return (root, query, criteriaBuilder) -> {
            Path<String> path = root.get(Pet_.name);
            return criteriaBuilder.equal(path, name);
        };
    }

    public static Specification<Pet> hasOwner(Owner owner) {
        return (root, query, criteriaBuilder) -> {
            Path<Owner> path = root.get(Pet_.owner);
            return criteriaBuilder.equal(path, owner);
        };
    }

    public static Specification<Owner> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) -> {
            Path<String> path = root.get(Owner_.firstName);
            return criteriaBuilder.equal(path, firstName);
        };
    }
}
