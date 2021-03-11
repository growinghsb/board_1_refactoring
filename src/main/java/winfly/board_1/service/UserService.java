package winfly.board_1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import winfly.board_1.controller.dto.UserDto;
import winfly.board_1.entity.User;
import winfly.board_1.repository.JpaUserRepository;

import java.util.List;

import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

@Service
@RequiredArgsConstructor
@Transactional// 항상 엔티티매니저는 트랜잭션 단위 안에서 동작하기 때문에 트랜잭션이 있어야 한다.
public class UserService { // 테스트 코드 작성할 것

    //네이밍 할 때 기술이 먼저 오면 안된다. 기술을 의식하고 코딩하진 않기 때문이다.
    private final JpaUserRepository jpaUserRepository;

    public Long save(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setTitle(dto.getTitle());
        user.setContent(dto.getContent());
        user.setWriteTime(now().format(ofPattern("yyyy-MM-dd HH:mm:ss"))); // 이거 시간 출력할 때 원인은 알겠는데 방법을 모르겠네
        //일단 String으로 받아서 db에 저장.
        user.setViewCount(0);
        jpaUserRepository.save(user);
        return user.getId();
    }

    public User findOne(Long id) { // 바로 엔티티를 뷰로 보내지 말고, dto를 통해 진행, 리팩토링 할 것
        return jpaUserRepository.findOne(id);
    }

    public List<User> findAll() {
        return jpaUserRepository.findAll();
    }

    public void modify(UserDto userDto, Long id) {
        User user = jpaUserRepository.findOne(id);
        user.setName(userDto.getName());
        user.setTitle(userDto.getTitle());
        user.setContent(userDto.getContent());
        user.setModifyTime(now().format(ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    public User viewCountUp(Long id) {
        User user = jpaUserRepository.findOne(id);
        user.setViewCount(user.getViewCount() + 1);
        return user;
    }

    public void delete(Long id) {
        jpaUserRepository.delete(jpaUserRepository.findOne(id));
    }
}
