package ir.maktab.project.repository;

import ir.maktab.project.domain.Master;
import ir.maktab.project.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    private static final String ENTITY_NAME = "Manager";
    private User user;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        user = Master.builder().firstName("javad")
                .lastName("zare").userName("javad1234").build();

        userRepository.save(user);
    }

    @Test
    void updateEntityNameOfTable() {
        userRepository.updateEntityNameOfTable(ENTITY_NAME, user.getId());


        boolean result = (boolean) entityManager.createNativeQuery("select case  when u.entity_name='Manager' then true else false end from user as u")
                .getSingleResult();
        assertThat(result).isTrue();

    }
}