package service;

import domain.Tag;
import org.springframework.stereotype.Service;
import repository.TagRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAllTags() {
        try {
            return tagRepository.findAll();

        } catch (Exception e) {
            return null;
        }
    }
}
