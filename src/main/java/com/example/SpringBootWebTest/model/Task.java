package com.example.SpringBootWebTest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TASKS")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String name;
	private String description;
	private boolean completed;

	@ManyToOne
	@JoinColumn(name = "taskListId", referencedColumnName = "id", nullable = false)
	private TaskList taskList;

	@ManyToOne
	@JoinColumn(name = "parentTaskId", referencedColumnName = "id")
	private Task parentTask;

	@OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Task> childrenTask;
}
