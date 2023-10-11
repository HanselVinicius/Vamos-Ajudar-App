package br.got.vamosajudar.infra.validator.validators;

import android.widget.EditText;

import br.got.vamosajudar.infra.validator.Validator;

public class MaxCharsValidation implements Validator {

    private int maxChars;

    public MaxCharsValidation(int maxChars) {
        this.maxChars = maxChars;
    }

    @Override
    public boolean validate(EditText text) {
        return text.getText().toString().trim().length() <= maxChars;
    }
}
