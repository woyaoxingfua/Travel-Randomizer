package com.tlh.travel_randomizer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.List;


public interface DestinationRepository extends JpaRepository<Destination, Long> {

    // 因为“随机查找”是一个比较特殊的操作，JPA没有现成的方法，
    // 所以我们用 @Query 注解自己写一句 SQL 语句来实现这个功能。
    @Query(value = "SELECT * FROM destination ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<Destination> findRandom();

    List<Destination> findByProvince(String province);

    List<Destination> findByCity(String city);
}