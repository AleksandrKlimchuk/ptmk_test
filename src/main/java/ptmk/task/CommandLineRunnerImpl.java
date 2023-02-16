package ptmk.task;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ptmk.task.service.TaskFactory;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final TaskFactory taskFactory;

    @Override
    public void run(String... args) {
        taskFactory.createTask(args).run();
    }
}
