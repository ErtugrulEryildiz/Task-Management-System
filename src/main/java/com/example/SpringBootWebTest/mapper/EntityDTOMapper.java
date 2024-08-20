package com.example.SpringBootWebTest.mapper;

public interface EntityDTOMapper<T, DTO> {

	DTO toDTO(T t);

	T toEntity(DTO dto);
}
