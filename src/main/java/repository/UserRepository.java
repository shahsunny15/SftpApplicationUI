package harsh.SftpApplication.repository;


import harsh.SftpApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}