package sorbie.jon.searchdemo.search;

import org.springframework.data.jpa.domain.Specification;
import sorbie.jon.searchdemo.domain.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PetSpecificationsBuilder {

    private final List<SearchCriteria> params;

    public PetSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public PetSpecificationsBuilder with(String key, String operation, Object value) {
        return with(new SearchCriteria(key, operation, value));
    }

    public PetSpecificationsBuilder with(SearchCriteria param) {
        params.add(param);
        return this;
    }

    public Specification<Pet> andCombination() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<Pet>> specs = params.stream()
                .map(PetSpecification::new)
                .collect(Collectors.toList());
        Specification<Pet> result = specs.get(0);
        for (int i = 1; i < params.size(); i++) {
            //result = Specification.where(result).and(specs.get(i));
            result = result.and(specs.get(i));
        }
        return result;
    }

    // One could write an orCombination() method here like the one above.
}
