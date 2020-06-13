package dao;

import java.util.List;
import model.User;
import model.dto.UserDTO;

/**
 * @author 王雅琪
 * @description ${description}
 * @date 2020/6/13
 */
public interface UserDao {

  void initTable();

  void insert(UserDTO userDTO);


  void del(Integer id);


  void update(User user);

  List<User> find(int start);


  List<UserDTO> findUserDetailInfo(int start);

}
