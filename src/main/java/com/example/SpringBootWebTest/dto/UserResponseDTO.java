package com.example.SpringBootWebTest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
	private long id;
	private String username;
	private List<TaskListResponseDTO> taskLists;
}
