package com.example.demo.repository;

import com.example.demo.entity.QItem;
import com.example.demo.entity.QReservation;
import com.example.demo.entity.QUser;
import com.example.demo.entity.Reservation;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

public class ReservationRepositoryCustomImpl implements ReservationRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public ReservationRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Reservation> search(Long userId, Long itemId) {
        QReservation reservation = QReservation.reservation;
        QUser user = QUser.user;
        QItem  item = QItem.item;

        return jpaQueryFactory
                .selectFrom(reservation)
                .leftJoin(reservation.user, user).fetchJoin()
                .leftJoin(reservation.item, item).fetchJoin()
                .where(
                        userId != null ? reservation.user.id.eq(userId) : null,
                        itemId != null ? reservation.item.id.eq(itemId) : null
                )
                .fetch();
    }
}
