package ptmk.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ptmk.task.dto.HumanDTO;
import ptmk.task.repository.HumanRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FMaleSelectorService implements AbstractTask{

    private final HumanRepository repository;

    @Override
    public void run(String... args) {
        final long startTime = System.nanoTime();
        List<HumanDTO> fMales = repository.findFMales();
        final long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000 ;
        System.out.println("Select time: " + duration + "ms.");
        System.out.println("Size of select: " + fMales.size());
    }
}
