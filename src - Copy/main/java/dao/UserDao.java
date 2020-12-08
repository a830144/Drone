package dao;

import java.util.List;

public interface UserDao {
    
    public List<Object[]> findByIdAndPassword(Integer id,String password);

}
