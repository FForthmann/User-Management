package de.nordakademie.service;

import de.nordakademie.model.Postcode;
import de.nordakademie.repository.PostcodeRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class PostcodeServiceImpl implements PostcodeService{

    private PostcodeRepository repository;

    @Inject
    public void setRepository(PostcodeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Postcode createPostcode(Postcode postcode) {
        return repository.save(postcode);
    }

    @Override
    public void deletePostcodeById(Long postcodeId) {
        repository.deleteById(postcodeId);
    }

    @Override
    public void updatePostcode(Long id, Postcode postcodeUpdate) {
      Optional<Postcode> postcodePersistent = repository.findById(id);
      postcodePersistent.get().setPostcode(postcodeUpdate.getPostcode());
      postcodePersistent.get().setLocation(postcodeUpdate.getLocation());
    }

    @Override
    public List<Postcode> findAllPostcodes() {

        return (List<Postcode>) repository.findAll();
    }

    @Override
    public Optional<Postcode> findPostcodeById(Long postcodeId) {
        return repository.findById(postcodeId);
    }


}
