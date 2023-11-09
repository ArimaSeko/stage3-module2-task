package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long>  {
    @Qualifier("dataSource")
    public DataSource dataSource;
    public NewsRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNews();
    }

    @Override
    public NewsModel readById(Long newsId) {
        return dataSource.getNews().stream()
                .filter(news -> newsId.equals(news.getId()))
                .findFirst()
                .get();
    }

    @Override
    public boolean deleteById(Long newsId) {
        List<NewsModel> deleteList = new ArrayList<>();
        deleteList.add(this.readById(newsId));
        return dataSource.getNews().removeAll(deleteList);
    }

    @Override
    public boolean existById(Long newsId) {
        return dataSource.getNews().stream().anyMatch(news -> newsId.equals(news.getId()));
    }

    @Override
    public NewsModel update(NewsModel model) {
        NewsModel newsModel = readById(model.getId());
        newsModel.setTitle(model.getTitle());
        newsModel.setContent(model.getContent());
        newsModel.setLastUpdatedDate(model.getLastUpdatedDate());
        newsModel.setAuthorId(model.getAuthorId());
        return newsModel;
    }

    @Override
    public NewsModel create(NewsModel model) {
        List<NewsModel> newsModel = dataSource.getNews();
        newsModel.sort(Comparator.comparing(NewsModel::getId));
        if (!newsModel.isEmpty()) {
            model.setId(newsModel.get(newsModel.size() - 1).getId() + 1);
        } else {
            model.setId(1L);
        }
        newsModel.add(model);
        return model;
    }
}
