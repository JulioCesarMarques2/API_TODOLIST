package br.com.todolist;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}
@Bean
	CommandLineRunner criarTarefasFake(ToDoRepository todorepository) {
		return args -> {
			ToDO todo1 = new ToDO("Estudar PHP",
					"Estudar PHP para criar Site", StatusEnum.NOT_STARTED);
			ToDO todo2 = new ToDO("Estudar JAVA",
					"Estudar JAVA para aprender Programação", StatusEnum.IN_PROGRESS);
			ToDO todo3 = new ToDO("Estudar HTML",
					"Estudar HTML para criar Site", StatusEnum.FINISHED);
			todorepository.save(todo1);
			todorepository.save(todo2);
			todorepository.save(todo3);
		};
}
}
