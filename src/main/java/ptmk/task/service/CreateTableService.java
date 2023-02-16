package ptmk.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ptmk.task.repository.HumanRepository;

@Service
@RequiredArgsConstructor
public class CreateTableService implements AbstractTask {

    private final HumanRepository repository;

    @Override
    public void run(String... args) {
        repository.createTable();
    }
}
