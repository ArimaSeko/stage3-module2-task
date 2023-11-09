package com.mjc.school.service.interfaces;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface AuthorModelMapper {
    List<AuthorDtoResponse> modelListToDtoList(List<AuthorModel> authorModels);

    AuthorDtoResponse modelToDto(AuthorModel authorModel);
    AuthorModel dtoToModel(AuthorDtoRequest authorModelRequest);
}
