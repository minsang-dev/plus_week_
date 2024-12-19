package com.example.demo.entity;

import com.example.demo.config.QueryDslConfig;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QueryDslConfig.class)
class ItemTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testStatusCheckedNull() {

        // given
        User owner = new User("user", "qwer@gmail.com", "zzanggu", "qwer1234");
        User manager = new User("admin", "asdf@gmail.com", "zzangA", "asdf1234");

        userRepository.save(owner);
        userRepository.save(manager);

        Item item = new Item("초코비", "초코과자", manager, owner);

        // when, then
        assertThrows(ConstraintViolationException.class, () ->
                itemRepository.saveAndFlush(item)
        );
    }
}