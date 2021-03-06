package ir.maktab.project.repository;

import ir.maktab.project.domain.User;
import ir.maktab.project.domain.dto.UserSearchResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUserName(String userName);

    @Query("select u from  User as u where u.role.name='master' and u.registerState='CONFIRM' ")
    List<User> findAllMaster();


    @Modifying
    @Query(value = "update user as u set u.entity_name=:entityName where u.id=:id", nativeQuery = true)
    void updateEntityNameOfTable(@Param("entityName") String name, @Param("id") Long id);

    Optional<User> findByResetPasswordToken(String token);

    @Query(value = "select * from user as u where u.entity_name in :entityName",
            countQuery = "select count(id) from user as u where u.entity_name in :entityName",nativeQuery = true)
    Page<User> findAllByEntityNameContains(String[] entityName, Pageable pageable);
}
