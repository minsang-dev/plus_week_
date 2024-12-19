package com.example.demo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderTest {

    @Test
    void encode() {
        // given
        String rawPassword = "qwer1234";

        // when
        String encodedPassword = PasswordEncoder.encode(rawPassword);

        // then
        assertNotEquals("qwer13224", encodedPassword);
    }

    @Test
    void matches() {
        // given
        String rawPassword = "qwer1234";
        String encodedPassword = PasswordEncoder.encode(rawPassword);

        // when
        boolean matches = PasswordEncoder.matches(rawPassword, encodedPassword);

        // then
        assertTrue(matches);
    }
}