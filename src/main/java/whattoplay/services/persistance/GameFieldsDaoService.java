package whattoplay.services.persistance;

import whattoplay.domain.entities.*;

public interface GameFieldsDaoService {
    void saveSetOfDevelopers(Iterable<Developer> developers);
    void saveSetOfFranchises(Iterable<Franchise> franchises);
    void saveSetOfCollections(Iterable<Collection> collections);
    void saveGameDeveloper(GameDeveloper gameDeveloper);
    void saveGameGenres(GameGenres gameGenres);
    void saveGameGameModes(GameGameModes gameGameModes);
    void saveGamePlayerPerspectives(GamePlayerPerspectives gamePlayerPerspectives);
    void saveGameWebsite(GameWebsites website);
    void saveField(Object genre);
    void saveGameMode(GameMode gameMode);
    void savePlayerPerspective(PlayerPerspective playerPerspective);
    void saveDeveloper(Developer developer);
    void saveGenre(Genre genre);
    void saveFranchise(Franchise franchise);
    void saveCollection(Collection collection);
}
