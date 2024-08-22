package uz.broker.dataanalaysergrpcmicroservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.broker.dataanalaysergrpcmicroservice.model.Data;

import java.util.List;

public interface DataRepository extends JpaRepository<Data, Long> {

    @Query(value = """
            SELECT *
            FROM data
            OFFSET (SELECT current_offset FROM offsets LIMIT 1)
            LIMIT :batchSize
            """, nativeQuery = true)
    List<Data> findAllWithOffset(@Param("batchSize") long batchSize);

    @Modifying
    @Query(value = """
            UPDATE offsets SET current_offset = current_offset + :batchSize
            """, nativeQuery = true)
    void incrementOffset(@Param("batchSize") long batchSize);
}
