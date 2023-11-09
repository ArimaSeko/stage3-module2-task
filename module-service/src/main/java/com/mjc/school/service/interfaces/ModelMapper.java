package com.mjc.school.service.interfaces;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
@Mapper
public interface ModelMapper {

    List<NewsDtoResponse> modelListToDtoList(List<NewsModel> newsModelList);

    NewsDtoResponse modelToDto(NewsModel newsModel);

    @Mappings({
            @Mapping(target = "createDate", ignore = true),
            @Mapping(target = "lastUpdatedDate", ignore = true)
    })
    NewsModel dtoToModel(NewsDtoRequest newsModelRequest);
}
