package com.mjc.school.repository.utils;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.mjc.school.repository.model.data.AuthorData.getAuthorData;
import static com.mjc.school.repository.model.data.NewsData.getNewsData;
@Component
public class DataSource {
    private final List<NewsModel> newsData;
    private final List<AuthorModel> authorData;
    private DataSource() {
        authorData = getAuthorData().getAuthorList();
        newsData = getNewsData(authorData).getNewsList();
    }

    public static DataSource getInstance() {
        return LazyDataSource.INSTANCE;
    }

    public List<NewsModel> getNews() {
        return newsData;
    }
    public List<AuthorModel> getAuthors(){ return authorData;}
    private static class LazyDataSource {
        static final DataSource INSTANCE = new DataSource();
    }
}
