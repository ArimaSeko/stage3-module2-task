package com.mjc.school.service.implementation;

import com.mjc.school.repository.implementation.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exeptions.NotFoundException;
import com.mjc.school.service.interfaces.ModelMapper;
import com.mjc.school.service.validator.NewsValidator;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import com.mjc.school.repository.BaseRepository;

import static com.mjc.school.service.exeptions.ServiceErrorCode.NEWS_ID_DOES_NOT_EXIST;
@Service
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {
    @Qualifier("newsRepository")
    private final BaseRepository<NewsModel, Long> newsRepository;
    @Qualifier("newsValidator")
    private final NewsValidator newsValidator;
    private ModelMapper mapper = Mappers.getMapper(ModelMapper.class);

    public NewsService(NewsRepository newsRepository, NewsValidator newsValidator) {
        this.newsRepository = newsRepository;
        this.newsValidator = newsValidator;
    }
    @Override
    public List<NewsDtoResponse> readAll() {
        return mapper.modelListToDtoList(newsRepository.readAll());
    }

    @Override
    public NewsDtoResponse readById(Long newsId){
        newsValidator.validateNewsId(newsId);
        if (newsRepository.existById(newsId)) {
            NewsModel newsModel = newsRepository.readById(newsId);
            return mapper.modelToDto(newsModel);
        } else {
            throw new NotFoundException(
                    String.format(String.valueOf(NEWS_ID_DOES_NOT_EXIST.getMessage()), newsId));
        }
    }

    @Override
    public boolean deleteById(Long newsId) {
        newsValidator.validateNewsId(newsId);
        if (newsRepository.existById(newsId)) {
            return newsRepository.deleteById(newsId);
        } else {
            throw new NotFoundException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), newsId));
        }
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest dtoRequest) {
        newsValidator.validateNewsId(dtoRequest.id());
        newsValidator.validateNewsDto(dtoRequest);
        if (newsRepository.existById(dtoRequest.id())) {
            NewsModel model = mapper.dtoToModel(dtoRequest);
            LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            model.setLastUpdatedDate(date);
            NewsModel newsModel = newsRepository.update(model);
            return mapper.modelToDto(newsModel);
        } else {
            throw new NotFoundException(
                    String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), dtoRequest.id()));
        }
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest dtoRequest) {
        newsValidator.validateNewsDto(dtoRequest);
        NewsModel model = mapper.dtoToModel(dtoRequest);
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        model.setCreateDate(date);
        model.setLastUpdatedDate(date);
        NewsModel newsModel = newsRepository.create(model);
        return mapper.modelToDto(newsModel);
    }
}
