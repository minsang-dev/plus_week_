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

@DataJpaTest //DataJpa와 관련이 있는 것들만 초기화
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(QueryDslConfig.class)
class ItemTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testStatusCheckedNull() {
        /**
         * 1. nullable = false 동작하는지 확인
         * 2. nullable = false -> itemEntity의 reservationStatus 필드
         * 3. reservationStatus 필드를 null로 설정해서 itemEntity를 생성
         * 4. itemEntity를 save해야 데이터베이스의 sql이 실행됨
         *
         * null일때 exception이 발생한다.
         *
         * 특정 exception 발생 확인 위해 assertThrows(1,2)
         * 1번 매개변수 = 발생할 exception
         * 2번 매개변수 = 실행될 로직
         * 1번 매개변수 = 2번의 로직에서 발생할 Exception
          */

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

    // --> 데이터베이스가 동작하기 전에 발생하는 예외

}