package org.sambasoft.login.programmerTasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ProTaksController {

    @Autowired
    private ProTaskService proTaskService;

    @RequestMapping("/proTasks")
    public String showAllProTasks(Model model){
        List<ProTaskModel> tasks = proTaskService.getAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("task", new ProTaskModel());
        return "programmerTaskViews/proTasks";
    }

    @RequestMapping(value = "/proTasks/new", method = RequestMethod.POST)
    public String saveTask(ProTaskModel task)
    {
        proTaskService.create(task);
        return "redirect:/proTasks";
    }

    @RequestMapping(value= "/proTasks/close/{id}")
    public String closeTask(@PathVariable(value = "id") Long id, Model model){
        ProTaskModel task = proTaskService.findById(id);
        proTaskService.close(task);
        return "redirect:/proTasks";
    }

    @RequestMapping(value= "/proTasks/open/{id}")
    public String openTask(@PathVariable(value = "id") Long id){
        ProTaskModel task = proTaskService.findById(id);
        proTaskService.open(task);
        return "redirect:/proTasks";
    }

    @RequestMapping(value= "/proTasks/delete/{id}")
    public String deleteProTask(@PathVariable(value = "id") Long id, Model model){
        proTaskService.delete(id);
        return "redirect:/proTasks";
    }
}
