package sorbie.jon.searchdemo.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sorbie.jon.searchdemo.domain.Pet;
import sorbie.jon.searchdemo.domain.Species;
import sorbie.jon.searchdemo.search.SearchCriteria;
import sorbie.jon.searchdemo.services.GraphQLService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController()
public class PetController {

    @Autowired
    private GraphQLService service;

    @GetMapping("/pet-search")
    @ResponseBody
    public List<Pet> petSearch(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "species", required = false) String species,
            @RequestParam(value = "minAge", required = false) Integer minAge,
            @RequestParam(value = "maxAge", required = false) Integer maxAge) {
        List<SearchCriteria> params = new ArrayList<>();
        if (name != null) {
            params.add(new SearchCriteria("name", ":", name));
        }
        if (species != null) {
            params.add(new SearchCriteria("species", ":", Species.valueOf(species)));
        }
        if (minAge != null) {
            params.add(new SearchCriteria("age", ">", minAge));
        }
        if (maxAge != null) {
            params.add(new SearchCriteria("age", "<", maxAge));
        }
        if (params.isEmpty()) {
            return Collections.emptyList();
        }
        return service.petSearch(params);
    }
}
