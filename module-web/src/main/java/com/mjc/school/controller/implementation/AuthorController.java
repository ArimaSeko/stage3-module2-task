package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class AuthorController implements BaseController<AuthorDtoRequest,AuthorDtoResponse, Long> {
    @Qualifier("authorService")
    private BaseService <AuthorDtoRequest, AuthorDtoResponse, Long> authorService;

    public AuthorController(BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService) {
        this.authorService = authorService;
    }

    @CommandHandler
    public List<AuthorDtoResponse> readAll() {
        return authorService.readAll();
    }

    @CommandHandler
    public AuthorDtoResponse readById(Long id) {
        return authorService.readById(id);
    }

    @CommandHandler
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        return authorService.create(createRequest);
    }

    @CommandHandler
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        return authorService.update(updateRequest);
    }

    @CommandHandler
    public boolean deleteById(Long id) {
        return authorService.deleteById(id);
    }
}
