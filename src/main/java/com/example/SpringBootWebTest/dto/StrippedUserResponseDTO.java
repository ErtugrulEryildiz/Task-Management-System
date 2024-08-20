package com.example.SpringBootWebTest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrippedUserResponseDTO {
	private long id;
	private String username;
}
