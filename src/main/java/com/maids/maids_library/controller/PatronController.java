package com.maids.maids_library.controller;

import com.maids.maids_library.dto.PatronRequest;
import com.maids.maids_library.model.Patron;
import com.maids.maids_library.model.Patron;
import com.maids.maids_library.service.PatronService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/patrons")
@RequiredArgsConstructor
public class PatronController {

    @NonNull
    PatronService patronService;

    @GetMapping
    public ResponseEntity<List<Patron>> fetchAllPatrons() {
        List<Patron> allPatrons = patronService.fetchAllPatrons();
        return ResponseEntity.status(HttpStatus.OK).body(allPatrons);
    }

    @PostMapping
    public ResponseEntity<Patron> addPatron(@Valid @RequestBody PatronRequest patronRequest) {
        Patron newPatron = patronService.addPatron(patronRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPatron);
    }

    @PutMapping(value = "/{patron_id}")
    public ResponseEntity<Patron> updatePatron(@Valid @RequestBody PatronRequest patronRequest, @PathVariable Long patron_id) {
        Patron updatedPatron = patronService.updatePatron(patronRequest, patron_id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPatron);
    }

    @DeleteMapping(value = "/{patron_id}")
    public ResponseEntity<String> removePatron(@Valid @PathVariable Long patron_id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(patronService.removePatron(patron_id));
    }
}
