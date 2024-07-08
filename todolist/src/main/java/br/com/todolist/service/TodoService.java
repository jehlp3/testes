package br.com.todolist.service;

import br.com.todolist.entity.Todo;
import br.com.todolist.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo todo){
        todoRepository.save(todo);
        return read();
    }

    public List<Todo> read(){
        Sort sort = Sort.by("prioridade").descending().and(
                Sort.by("nome").ascending());
        return todoRepository.findAll(sort);
    }

    public List<Todo> update(Todo todo){
        todoRepository.save(todo);
        return read();
    }

    public List<Todo> delete(Long id){
        todoRepository.deleteById(id);
        return read();
    }
}
