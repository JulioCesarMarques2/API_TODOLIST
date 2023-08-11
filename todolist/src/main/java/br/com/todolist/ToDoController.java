package br.com.todolist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin("*")
public class ToDoController {
    private final ToDoRepository todorepo;

    public ToDoController(ToDoRepository todorepo) {
        this.todorepo = todorepo;
    }
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ToDO> getAll() {
        return this.todorepo.findAll();
    }
}
