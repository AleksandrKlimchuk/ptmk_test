package ptmk.task.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskFactory {

    private final CreateTableService createTableService;
    private final InsertHumanService insertHumanService;
    private final UniqueHumanSelectorService uniqueHumanSelectorService;

    public AbstractTask createTask(String... commandLineArgs) {
        validateArgs(commandLineArgs);
        String taskNumber = commandLineArgs[0];
        return switch (taskNumber) {
            case "1" -> createTableService;
            case "2" -> insertHumanService;
            case "3" -> uniqueHumanSelectorService;
            default ->
                    throw new IllegalStateException("Unexpected task number: " + taskNumber + ". Available tasks: 1-5");
        };

    }

    private void validateArgs(String... args) {
        if (args.length == 0 || args[0].isBlank()) {
            throw new IllegalArgumentException("You need provide task number");
        }
    }
}
