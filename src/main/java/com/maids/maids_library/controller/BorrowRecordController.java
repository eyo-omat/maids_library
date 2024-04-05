package com.maids.maids_library.controller;

import com.maids.maids_library.model.BorrowingRecord;
import com.maids.maids_library.service.BorrowingRecordService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class BorrowRecordController {
    @NonNull
    BorrowingRecordService borrowingRecordService;

    @PostMapping(value = "/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecord borrowingRecord = borrowingRecordService.borrowBook(bookId, patronId);
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowingRecord);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecord returnedRecord = borrowingRecordService.returnBook(bookId, patronId);
        return ResponseEntity.ok(returnedRecord);
    }
}
