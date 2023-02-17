package models.forms;

import exceptions.InvalidForm;

public abstract class Form<T>{
    public abstract T build() throws InvalidForm;
}
