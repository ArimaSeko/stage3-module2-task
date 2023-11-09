package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exeptions.NotFoundException;
import com.mjc.school.service.interfaces.*;
import com.mjc.school.service.validator.AuthorValidator;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.mjc.school.service.exeptions.ServiceErrorCode.*;
@Service
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {
    @Qualifier("authorRepository")
    private BaseRepository <AuthorModel, Long> authorRepository;
    private AuthorModelMapper mapper = Mappers.getMapper(AuthorModelMapper.class);
    @Qualifier("authorValidator")
    private AuthorValidator authorValidator;
    public AuthorService(BaseRepository<AuthorModel, Long> authorRepository, AuthorValidator authorValidator) {
        this.authorRepository = authorRepository;
        this.authorValidator = authorValidator;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return mapper.modelListToDtoList(authorRepository.readAll());
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        if (authorRepository.existById(id)) {
            AuthorModel authorModel = authorRepository.readById(id);
            return mapper.modelToDto(authorModel);
        } else {
            throw new NotFoundException(
                    String.format(String.valueOf(AUTHOR_ID_DOES_NOT_EXIST.getMessage()), id));
        }
    }

    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        AuthorModel model = mapper.dtoToModel(createRequest);
        AuthorModel authorModel = authorRepository.create(model);
        return mapper.modelToDto(authorModel);
    }

    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        if (authorRepository.existById(updateRequest.id())) {
            AuthorModel model = mapper.dtoToModel(updateRequest);
            AuthorModel authorModel = authorRepository.update(model);
            return mapper.modelToDto(authorModel);
        } else {
            throw new NotFoundException(
                    String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), updateRequest.id()));
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (authorRepository.existById(id)) {
            return authorRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }
}
