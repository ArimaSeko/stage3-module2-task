package com.mjc.school.repository.model.data;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mjc.school.repository.utils.Utils.getRandomContentByFilePath;
import static com.mjc.school.repository.utils.Utils.getRandomDate;
@PropertySource("classpath:authors.properties")
public class NewsData {
    private static final String CONTENT_FILE_NAME = "content.properties";
    private static final String NEWS_FILE_NAME = "news.properties";
    private static NewsData newsData;
    private List<NewsModel> newsList;
    public List<NewsModel> getNewsList() {
        return newsList;
    }
    public static NewsData getNewsData(List<AuthorModel> authorModelList) {
        if (newsData == null) {
            newsData = new NewsData(authorModelList);
        }
        return newsData;
    }

    private NewsData(List<AuthorModel> authorModelList) {
        init(authorModelList);

    }
    private void init(List<AuthorModel> authorModelList) {
        newsList = new ArrayList<>();
        Random random = new Random();
        for (long i = 1; i <= 20; i++) {
            LocalDateTime date = getRandomDate();
            newsList.add(
                    new NewsModel(
                            i,
                            getRandomContentByFilePath(NEWS_FILE_NAME),
                            getRandomContentByFilePath(CONTENT_FILE_NAME),
                            date,
                            date,
                            authorModelList.get(random.nextInt(authorModelList.size())).getId()));
        }
    }
}
