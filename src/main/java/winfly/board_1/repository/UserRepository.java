package winfly.board_1.repository;


import winfly.board_1.entity.User;

import java.util.List;

public interface UserRepository {

    public abstract Long save(User user);

    public abstract User findOne(Long id);

    public abstract List<User> findAll();

    public abstract void delete(User user);

}
