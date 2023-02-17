package managers;

import exceptions.CommandRuntimeError;
import exceptions.ExitObliged;
import exceptions.IllegalArguments;
import exceptions.NoSuchCommand;
import commandLine.commands.Command;

import java.util.*;
import java.util.stream.Collectors;

public class CommandManager{
    private final HashMap<String, Command> commands = new HashMap<>();
    private final List<String> commandHistory = new ArrayList<>();

    public void addCommand(Command command){
        this.commands.put(command.getName(), command);
    }
    public void addCommand(Collection<Command> commands){
        this.commands.putAll(commands.stream()
                .collect(Collectors.toMap(Command::getName, s -> s)));
    }
    public Collection<Command> getCommands(){
        return commands.values();
    }
    public void addToHistory(String line){
        this.commandHistory.add(line);
    }

    public void execute(String name, String args) throws NoSuchCommand, IllegalArguments, CommandRuntimeError, ExitObliged {
        Command command = commands.get(name);
        if (command == null) throw new NoSuchCommand();
        command.execute(args);
    }
}
