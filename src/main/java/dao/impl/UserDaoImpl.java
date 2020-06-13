package dao.impl;

import dao.UserDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.User;
import model.dto.UserDTO;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author 王雅琪
 * @description ${description}
 * @date 2020/6/11
 */
public class UserDaoImpl implements UserDao {

  private JdbcTemplate jdbcTemplate;

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void initTable() {
    String sql = "CREATE TABLE `user`(id INTEGER PRIMARY KEY AUTOINCREMENT ,`name` VARCHAR(255) NOT NULL,`company_id` int(11) NOT NULL)";
    int update = jdbcTemplate.update(sql);
    System.err.println("[user]初始化结果：" + update);
  }

  @Override
  public void insert(UserDTO userDTO) {
    String sql = "INSERT INTO `user`(`name`,company_id) VALUES (?,?)";
    Object[] parameters = {userDTO.getName(), userDTO.getCompanyId()};
    int update = jdbcTemplate.update(sql, parameters);
    System.err.println("[user]insert结果：" + update);
  }

  @Override
  public void del(Integer id) {
    String sql = "DELETE `user`WHERE id=?";
    int update = jdbcTemplate.update(sql, id);
    System.err.println("[user]del结果：" + update);

  }

  @Override
  public void update(User user) {
    String sql = "UPDATE `user` SET `name`=? WHERE id=?";
    Object[] parameters = {user.getName(), user.getId()};
    int update = jdbcTemplate.update(sql, parameters);
    System.err.println("[user]update结果：" + update);

  }

  @Override
  public List<User> find(int start) {
    List<User> users = new ArrayList<>(6);
    String sql = "SELECT id,`name` FROM `user` LIMIT ?,10";
    List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, new Object[]{start});
    maps.forEach(map -> {
      Object id = map.get("id");
      Object name = map.get("name");
      users.add(new User((Integer) id, name.toString()));
    });
    return users;
  }


  @Override
  public List<UserDTO> findUserDetailInfo(int start) {
    List<UserDTO> users = new ArrayList<>(6);
    String sql = "SELECT u.id,u.`name` AS userName,c.name,a.address_content AS addressContent  FROM `user` AS u LEFT JOIN company AS c ON c.id=u.company_id LEFT JOIN address AS a ON a.company_id=c.id LIMIT ?,10";
    List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, new Object[]{start});
    maps.forEach(map -> {
      Object id = map.get("id");
      String userName = map.get("userName") == null ? null : map.get("userName").toString();
      String name = map.get("name") == null ? null : map.get("name").toString();
      String addressContent =
          map.get("addressContent") == null ? null : map.get("addressContent").toString();
      users.add(new UserDTO((Integer) id, userName, name, addressContent));
    });
    return users;
  }


}
