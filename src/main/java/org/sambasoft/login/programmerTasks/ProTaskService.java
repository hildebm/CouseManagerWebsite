package org.sambasoft.login.programmerTasks;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProTaskService {

    private final ProTaskRepository proTaskRepository;

    public ProTaskService(ProTaskRepository proTaskRepository) {
        this.proTaskRepository = proTaskRepository;
    }

    List<ProTaskModel> getAll(){
        List<ProTaskModel> taskList = new ArrayList<>();
        proTaskRepository.findAll().iterator().forEachRemaining(taskList::add);
        return taskList;
    }

    ProTaskModel findById(Long id){
        Optional<ProTaskModel> taskListOptional = proTaskRepository.findById(id);
        if(!taskListOptional.isPresent()){
            throw new RuntimeException("Task not found");
        }

        return taskListOptional.get();
    }

    void delete(Long id){
        proTaskRepository.deleteById(id);
    }

    Long create(ProTaskModel task){
        proTaskRepository.save(task);
        return task.getId();
    }

    public void close(ProTaskModel task){
        task.setStatus("closed");
        proTaskRepository.save(task);
    }

    public void open(ProTaskModel task){
        task.setStatus("reopened");
        proTaskRepository.save(task);
    }
}
