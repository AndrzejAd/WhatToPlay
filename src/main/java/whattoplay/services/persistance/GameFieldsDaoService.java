package whattoplay.services.persistance;

import whattoplay.domain.entities.Collection;
import whattoplay.domain.entities.Developer;
import whattoplay.domain.entities.Franchise;

public interface GameFieldsDaoService {
    void saveSetOfDevelopers(Iterable<Developer> developers);
    void saveSetOfFranchises(Iterable<Franchise> franchises);
    void saveSetOfCollections(Iterable<Collection> collections);
}
