package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import model.User;
import model.dto.UserDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.sqlite.SQLiteConnection;

/**
 * @author 王雅琪
 * @description ${description}
 * @date 2020/6/11
 */
public class UserDao {

  private JdbcTemplate jdbcTemplate;

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void initTable() throws SQLException {
    SQLiteConnection connection = (SQLiteConnection) jdbcTemplate.getDataSource().getConnection();
    PreparedStatement preparedStatement = connection
        .prepareStatement(
            "CREATE TABLE `user`(id INTEGER PRIMARY KEY AUTOINCREMENT ,`name` VARCHAR(255) NOT NULL,`company_id` int(11) NOT NULL)  ");
    int execute = preparedStatement.executeUpdate();
    System.err.println("[user]初始化结果：" + execute);
  }

  public void insert(UserDTO userDTO) throws SQLException {
    DataSource dataSource = jdbcTemplate.getDataSource();
    SQLiteConnection connection = (SQLiteConnection) dataSource.getConnection();
    PreparedStatement preparedStatement = connection
        .prepareStatement(
            "INSERT INTO `user`(id,`name`,company_id) VALUES (" + null + ",'" + userDTO
                .getName() + "'," + userDTO.getCompanyId() + ")");
    int execute = preparedStatement.executeUpdate();
    System.err.println("[user]insert结果：" + execute);
  }


  public void del(Integer id) throws SQLException {
    DataSource dataSource = jdbcTemplate.getDataSource();
    SQLiteConnection connection = (SQLiteConnection) dataSource.getConnection();
    PreparedStatement preparedStatement = connection
        .prepareStatement("DELETE `user`WHERE id=" + id);
    int execute = preparedStatement.executeUpdate();
    System.err.println("[user]del结果：" + execute);

  }


  public void update(User user) throws SQLException {
    DataSource dataSource = jdbcTemplate.getDataSource();
    SQLiteConnection connection = (SQLiteConnection) dataSource.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(
        "UPDATE `user` SET `name`=" + user.getName() + " WHERE id=" + user.getId());
    int execute = preparedStatement.executeUpdate();
    System.err.println("[user]update结果：" + execute);

  }

  public List<User> find(int start) throws SQLException {
    List<User> users = new ArrayList<>(6);
    DataSource dataSource = jdbcTemplate.getDataSource();
    SQLiteConnection connection = (SQLiteConnection) dataSource.getConnection();
    String sql = "SELECT id,`name` FROM `user` LIMIT " + start + ",10";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      int id = resultSet.getInt(1);
      String name = resultSet.getString(2);
      users.add(new User(id, name));
    }
    resultSet.close();
    return users;
  }


  public List<UserDTO> findUserDetailInfo(int start) throws SQLException {
    List<UserDTO> users = new ArrayList<>(6);
    DataSource dataSource = jdbcTemplate.getDataSource();
    SQLiteConnection connection = (SQLiteConnection) dataSource.getConnection();
    String sql =
        "SELECT u.id,u.`name`,c.name,a.address_content  FROM `user` AS u LEFT JOIN company AS c ON c.id=u.company_id LEFT JOIN address AS a ON a.company_id=c.id LIMIT "
            + start + ",10";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      int id = resultSet.getInt(1);
      String name = resultSet.getString(2);
      String companyName = resultSet.getString(3);
      String addressContent = resultSet.getString(4);
      users.add(new UserDTO(id, name, companyName, addressContent));
    }
    resultSet.close();
    return users;
  }


}
