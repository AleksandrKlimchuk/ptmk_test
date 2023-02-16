package ptmk.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ptmk.task.dto.HumanDTO;
import ptmk.task.repository.HumanRepository;
import ptmk.task.ui.AbstractTableDrawer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniqueHumanSelectorService implements AbstractTask{

    final HumanRepository repository;
    final AbstractTableDrawer<HumanDTO> drawer;

    @Override
    public void run(String... args) {
        List<HumanDTO> humans = repository.selectHumanWithUniqueFullnameAndBirthdayOrderByFullname();
        drawer.draw(humans);
    }
}
