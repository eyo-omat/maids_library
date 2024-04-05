package com.maids.maids_library.service;

import com.maids.maids_library.dto.PatronRequest;
import com.maids.maids_library.exceptions.DuplicateEmailException;
import com.maids.maids_library.exceptions.PatronNotFoundException;
import com.maids.maids_library.model.Patron;
import com.maids.maids_library.repository.PatronRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatronService {

    @NonNull
    PatronRepository patronRepository;

    public Patron addPatron(PatronRequest patronRequest) {
        Patron existingEmailPatron = patronRepository.findPatronByEmail(patronRequest.getEmail());

        if (existingEmailPatron != null) {
            throw new DuplicateEmailException("Patron with the provided email exists: " + patronRequest.getEmail());
        }

        Patron newPatron = Patron.builder()
                .name(patronRequest.getName())
                .email(patronRequest.getEmail())
                .phone(patronRequest.getPhone())
                .build();

        patronRepository.save(newPatron);
        return newPatron;
    }

    public List<Patron> fetchAllPatrons() {
        return patronRepository.findAll();
    }

    @Cacheable(value = "patrons")
    public Patron fetchPatron(Long patron_id) {
        return patronRepository.findById(patron_id)
                .orElseThrow(
                        () -> new PatronNotFoundException("Patron with the provided id does not exist: " + patron_id)
                );
    }

    @Transactional
    public Patron updatePatron(PatronRequest patronRequest, Long patron_id) {
        return patronRepository.findById(patron_id)
                .map(foundPatron -> {
                    foundPatron.setName(patronRequest.getName());
                    foundPatron.setEmail(patronRequest.getEmail());
                    foundPatron.setPhone(patronRequest.getPhone());
                    patronRepository.save(foundPatron);
                    return foundPatron;
                })
                .orElseThrow(
                        () -> new PatronNotFoundException("Patron with the provided id does not exist: " + patron_id)
                );
    }

    public String removePatron(Long patron_id) {
        return patronRepository.findById(patron_id)
                .map(patron -> {
                    patronRepository.delete(patron);
                    return "Patron successfully deleted for id: " + patron_id;
                }).orElseThrow(
                        () -> new PatronNotFoundException("Patron with the provided id does not exist: " + patron_id)
                );
    }
}
