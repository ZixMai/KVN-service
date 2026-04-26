package ru.itpark.zix.kvnshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.zix.kvnshop.domain.User;
import ru.itpark.zix.kvnshop.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void upsert(User user) {
        userRepository.upsert(user.getTgId(), user.getUsername());
    }
}
