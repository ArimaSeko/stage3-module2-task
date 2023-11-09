package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class AuthorRepository implements BaseRepository <AuthorModel, Long> {
    @Qualifier("dataSource")
    public final DataSource dataSource;

    public AuthorRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<AuthorModel> readAll() {
        return dataSource.getAuthors();
    }

    @Override
    public AuthorModel readById(Long id) {
        return dataSource.getAuthors().stream()
                .filter(authors -> id.equals(authors.getId()))
                .findFirst()
                .get();
    }

    @Override
    public AuthorModel create(AuthorModel model) {
        List<AuthorModel> authorModels = dataSource.getAuthors();
        authorModels.sort(Comparator.comparing(AuthorModel::getId));
        if (!authorModels.isEmpty()) {
            model.setId(authorModels.get(authorModels.size() - 1).getId() + 1);
        } else {
            model.setId(1L);
        }
        authorModels.add(model);
        return model;
    }

    @Override
    public AuthorModel update(AuthorModel model) {
        AuthorModel authorModel = readById(model.getId());
        authorModel.setId(model.getId());
        authorModel.setName(model.getName());
        return authorModel;
    }

    @Override
    public boolean deleteById(Long id) {
        List<AuthorModel> deleteList = new ArrayList<>();
        deleteList.add(this.readById(id));
        return dataSource.getAuthors().removeAll(deleteList);
    }

    @Override
    public boolean existById(Long id) {
        return dataSource.getAuthors().stream().anyMatch(authors -> id.equals(authors.getId()));
    }
}
