package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterConversionDto;
import mate.academy.rickandmorty.exeption.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterModel;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterMapper characterMapper;
    private final CharacterRepository repository;

    @Override
    public CharacterConversionDto getRandomCharacter() {
        long count = repository.count();
        if (count == 0) {
            throw new EntityNotFoundException("No characters available");
        }
        CharacterModel model = repository.findById((long) (Math.random() * count))
                .orElseThrow(() -> new EntityNotFoundException("Character not found"));
        return characterMapper.toCharacterConversionDto(model);
    }

    @Override
    public List<CharacterConversionDto> searchCharacters(String searchParameter) {
        if (searchParameter.isBlank()) {
            throw new EntityNotFoundException("Search parameter is empty");
        }
        List<CharacterModel> characters =
                repository.findByNameContainingIgnoreCase(searchParameter);
        if (characters.isEmpty()) {
            throw new EntityNotFoundException("No characters found");
        }
        return characterMapper.toCharacterDtos(characters);
    }
}
