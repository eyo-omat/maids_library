package com.maids.maids_library.repository;

import com.maids.maids_library.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron, Long> {

    Patron findPatronByEmail(String email);
}
