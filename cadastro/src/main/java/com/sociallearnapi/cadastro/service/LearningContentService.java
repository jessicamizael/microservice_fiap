package com.sociallearnapi.cadastro.service;


import com.sociallearnapi.cadastro.exceptions.ApiErrorException;
import com.sociallearnapi.cadastro.model.LearningContent;
import com.sociallearnapi.cadastro.repository.LearningContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LearningContentService {
    @Autowired
    LearningContentRepository learningContentRepository;

    public LearningContent save(LearningContent learningContent) {
        return learningContentRepository.save(learningContent);
    }

    public LearningContent findById(String id) throws ApiErrorException {
        Optional<LearningContent> optionalLearningContent = learningContentRepository.findById(id);
        return optionalLearningContent.orElseThrow(() -> new ApiErrorException(HttpStatus.NOT_FOUND,
                "The informed learning content doesn't exists"));
    }

    public List<LearningContent> findAll() {
        return (List<LearningContent>) learningContentRepository.findAll();
    }

    public LearningContent update(String learningContentId, LearningContent updatedLearningContent)
            throws ApiErrorException {
        var learningContent = updateSavedLearningContent(learningContentId, updatedLearningContent);
        return learningContentRepository.save(learningContent);
    }

    private LearningContent updateSavedLearningContent(final String learningContentId,
                                                       final LearningContent updatedLearningContent) throws ApiErrorException {
        var savedLearningContent = findById(learningContentId);
        savedLearningContent.setName(updatedLearningContent.getName());
        savedLearningContent.setActive(updatedLearningContent.isActive());
        savedLearningContent.setLastModifiedDate(Date.from(Instant.now()));
        return savedLearningContent;
    }

    public LearningContent inactivate(String learningContentId) throws ApiErrorException {
        var savedLearningContent = findById(learningContentId);
        savedLearningContent.setActive(false);
        return learningContentRepository.save(savedLearningContent);
    }

    public void deleteById(String id) {
        learningContentRepository.deleteById(id);
    }
}
