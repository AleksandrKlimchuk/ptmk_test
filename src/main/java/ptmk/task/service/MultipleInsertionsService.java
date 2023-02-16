package ptmk.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ptmk.task.model.Human;
import ptmk.task.repository.HumanRepository;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MultipleInsertionsService implements AbstractTask {

    private final HumanRepository repository;
    private final HumanGeneratorService humanGenerator;

    @Override
    public void run(String... args) {
        List<Human> humans = generateHumans();
        List<Human> males = generateMales();
        repository.insertMultipleHumans(humans);
        repository.insertMultipleHumans(males);
    }

    private List<Human> generateMales() {
        return generate(humanGenerator::generateMales, 100);
    }

    private List<Human> generateHumans() {
        return generate(humanGenerator, 1_000_000);
    }

    private List<Human> generate(Supplier<Human> generator, int size) {
        return Stream.generate(generator)
                .limit(size)
                .toList();
    }


}
