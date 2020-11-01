package sorbie.jon.searchdemo.database;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import sorbie.jon.searchdemo.domain.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long>, JpaSpecificationExecutor<Owner> {

    // The following declarations become unnecessary with the use of Specifications
    // List<Owner> findByFirstName(String firstName);
}
