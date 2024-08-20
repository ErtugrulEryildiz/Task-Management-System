package com.example.SpringBootWebTest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrippedTaskResponseDTO {
	private long id;
	private String name;
	private String description;
	private Boolean completed = false;
}
