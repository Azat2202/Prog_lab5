package managers.commandLine;

import managers.commandLine.commands.Command;

import java.util.*;

public class CommandManager {
    private final List<Command> commands = new ArrayList<>();
    private final List<String> commandHistory = new ArrayList<>();

    public void addCommand(Command command){
        this.commands.add(command);
    }
    public void addCommand(Collection<Command> commands){
        this.commands.addAll(commands);
    }
    public List<Command> getCommands(){
        return commands;
    }
    public void addToHistory(String line){
        this.commandHistory.add(line);
    }
}
