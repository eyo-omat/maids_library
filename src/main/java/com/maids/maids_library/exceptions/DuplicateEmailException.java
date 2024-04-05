package com.maids.maids_library.exceptions;

import javax.validation.constraints.NotBlank;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(@NotBlank String s) {
        super(s);
    }
}
