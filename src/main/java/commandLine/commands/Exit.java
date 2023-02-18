package commandLine.commands;

import exceptions.CommandRuntimeError;
import exceptions.ExitObliged;
import exceptions.IllegalArguments;

public class Exit extends Command{
    public Exit(){
        super("exit", ": завершить программу (без сохранения в файл)");
    }

    @Override
    public void execute(String args) throws ExitObliged{
        throw new ExitObliged();
    }
}
