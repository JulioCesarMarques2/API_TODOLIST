package br.com.todolist;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ToDO create(@RequestBody ToDO Tarefa) {
        return this.todorepo.save(Tarefa);
    }
    @DeleteMapping("/{Tarefaid}")
    public ResponseEntity<Void> delete(@PathVariable Integer Tarefaid) {
        Optional<ToDO> todo = this.todorepo.findById(Tarefaid);
        if (todo.isPresent()) {
            this.todorepo.deleteById(Tarefaid);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @PutMapping("/{todoid}/start_task")
    public ResponseEntity<ToDO> starttask(@PathVariable Integer todoid) {
        ToDO todoDatabase = this.todorepo.findById(todoid).get();
        if (todoDatabase != null) {
            todoDatabase.setStatus(StatusEnum.IN_PROGRESS);
            this.todorepo.save(todoDatabase);
            return ResponseEntity.ok(todoDatabase);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{todoid}/end_task")
    public ResponseEntity<ToDO> endtask(@PathVariable Integer todoid) {
        ToDO todoDatabase = this.todorepo.findById(todoid).get();
        if (todoDatabase != null) {
            todoDatabase.setStatus(StatusEnum.FINISHED);
            this.todorepo.save(todoDatabase);
            return ResponseEntity.ok(todoDatabase);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{todoid}/update")
    public ResponseEntity<ToDO> update(@PathVariable Integer todoid, @RequestBody ToDO todo) {
        ToDO todoDatabase = this.todorepo.findById(todoid).get();
        if (todoDatabase != null) {
            todoDatabase.setTitle(todo.getTitle());
            todoDatabase.setDescription(todo.getDescription());
            this.todorepo.save(todoDatabase);
            return ResponseEntity.ok(todoDatabase);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}




