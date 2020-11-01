package sorbie.jon.searchdemo.search;

import org.springframework.data.jpa.domain.Specification;
import sorbie.jon.searchdemo.domain.Pet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PetSpecification implements Specification<Pet> {

    private SearchCriteria criteria;

    public PetSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Pet> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        String key = criteria.getKey();
        String op = criteria.getOperation();
        Object value = criteria.getValue();
        if (op.equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get(key), value.toString());
        }
        else if (op.equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(root.get(key), value.toString());
        }
        else if (op.equalsIgnoreCase(":")) {
            return criteriaBuilder.equal(root.get(key), value);
        }
        else if (op.equalsIgnoreCase("~")) {
            return criteriaBuilder.like(root.get(key), "%" + value + "%");
        }
        return null;
    }
}
