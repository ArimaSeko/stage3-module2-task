package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.implementation.NewsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Component
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {
    @Qualifier("newsService")
    private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }
    @CommandHandler
    public List<NewsDtoResponse> readAll() {
        return newsService.readAll();
    }
    @CommandHandler
    public NewsDtoResponse readById(Long newsId) {
        return newsService.readById(newsId);
    }
    @CommandHandler
    public NewsDtoResponse create(NewsDtoRequest dtoRequest) {
        return newsService.create(dtoRequest);
    }
    @CommandHandler
    public NewsDtoResponse update(NewsDtoRequest dtoRequest) {
        return newsService.update(dtoRequest);
    }
    @CommandHandler
    public boolean deleteById(Long newsId) {
        return newsService.deleteById(newsId);
    }
}
