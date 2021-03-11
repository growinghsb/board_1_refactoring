package winfly.board_1.repository;


import winfly.board_1.entity.Content;

import java.util.List;

public interface ContentRepository {

    public abstract Long save(Content content);

    public abstract Content findOne(Long id);

    public abstract List<Content> findAll();

    public abstract void delete(Content content);

}
