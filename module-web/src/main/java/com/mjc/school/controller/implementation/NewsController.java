package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.implementation.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Component
public class NewsController implements BaseController <NewsDtoRequest, NewsDtoResponse, Long> {
    @Qualifier("newsService")
    private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }
    @Override
    public List<NewsDtoResponse> readAll() {
        return newsService.readAll();
    }
    @Override
    public NewsDtoResponse readById(Long newsId) {
        return newsService.readById(newsId);
    }
    @Override
    public NewsDtoResponse create(NewsDtoRequest dtoRequest) {
        return newsService.create(dtoRequest);
    }
    @Override
    public NewsDtoResponse update(NewsDtoRequest dtoRequest) {
        return newsService.update(dtoRequest);
    }
    @Override
    public boolean deleteById(Long newsId) {
        return newsService.deleteById(newsId);
    }
}
