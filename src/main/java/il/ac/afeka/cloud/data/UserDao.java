package il.ac.afeka.cloud.data;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User, String> {

}
