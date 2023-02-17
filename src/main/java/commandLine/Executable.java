package commandLine;

import exceptions.CommandRuntimeError;
import exceptions.ExitObliged;
import exceptions.IllegalArguments;

public interface Executable {
    void execute(String args) throws CommandRuntimeError, ExitObliged, IllegalArguments;
}
