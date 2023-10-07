package br.got.vamosajudar.infra.validator.validators;

import android.widget.EditText;

import br.got.vamosajudar.infra.validator.Validator;

public class NotEmptyValidation implements Validator {
    @Override
    public boolean validate(EditText text) {
        return text.getText().toString().trim().length() > 0;
    }
}
