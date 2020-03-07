package me.test.howtotestjava.study;

import me.test.howtotestjava.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
}
