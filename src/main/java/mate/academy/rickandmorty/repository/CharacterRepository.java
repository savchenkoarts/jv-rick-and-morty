package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CharacterRepository
        extends JpaRepository<CharacterModel, Long>,
        JpaSpecificationExecutor<Character> {
    List<CharacterModel> findByNameContainingIgnoreCase(String name);
}
