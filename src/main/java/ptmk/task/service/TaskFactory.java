package ptmk.task.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskFactory {

    private final CreateTableService createTableService;

    public AbstractTask createTask(String... commandLineArgs) {
        validateArgs(commandLineArgs);
        String taskNumber = commandLineArgs[0];
        switch (taskNumber) {
            case "1":
                return createTableService;
            default:
                throw new IllegalStateException("Unexpected task number: " + taskNumber + ". Available tasks: 1-5");
        }

    }

    private void validateArgs(String... args) {
        if (args.length == 0 || args[0].isBlank()) {
            throw new IllegalArgumentException("You need provide task number");
        }
    }
}
