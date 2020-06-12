package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import model.Company;
import org.springframework.jdbc.core.JdbcTemplate;
import org.sqlite.SQLiteConnection;

/**
 * @author 王雅琪
 * @description ${description}
 * @date 2020/6/12
 */
public class CompanyDao {

  private JdbcTemplate jdbcTemplate;

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void initTable() throws SQLException {
    SQLiteConnection connection = (SQLiteConnection) jdbcTemplate.getDataSource().getConnection();
    PreparedStatement preparedStatement = connection
        .prepareStatement(
            "CREATE TABLE `company`(id INTEGER PRIMARY KEY AUTOINCREMENT ,`name` VARCHAR(255) NOT NULL )");
    int execute = preparedStatement.executeUpdate();
    System.err.println("[company]初始化结果：" + execute);
  }

  public synchronized int insert(Company company) throws SQLException {
    DataSource dataSource = jdbcTemplate.getDataSource();
    SQLiteConnection connection = (SQLiteConnection) dataSource.getConnection();
    PreparedStatement preparedStatement = connection
        .prepareStatement(
            "INSERT INTO `company`(id,`name`) VALUES (" + null + ",'" + company.getName() + "')");
    int execute = preparedStatement.executeUpdate();
    System.err.println("[company]insert结果：" + execute);
    return findNewCompanyId();
  }


  public void del(Integer id) throws SQLException {
    DataSource dataSource = jdbcTemplate.getDataSource();
    SQLiteConnection connection = (SQLiteConnection) dataSource.getConnection();
    PreparedStatement preparedStatement = connection
        .prepareStatement("DELETE FROM `company`WHERE id=" + id);
    int execute = preparedStatement.executeUpdate();
    System.err.println("[company]del结果：" + execute);
  }

  public void update(Company company) throws SQLException {
    DataSource dataSource = jdbcTemplate.getDataSource();
    SQLiteConnection connection = (SQLiteConnection) dataSource.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(
        "UPDATE `company` SET `name`=" + company.getName() + " WHERE id=" + company.getId());
    int execute = preparedStatement.executeUpdate();
    System.err.println("[company]update结果：" + execute);
  }

  public Integer findNewCompanyId() throws SQLException {
    DataSource dataSource = jdbcTemplate.getDataSource();
    SQLiteConnection connection = (SQLiteConnection) dataSource.getConnection();
    String sql = "SELECT id FROM `company` ORDER BY id DESC LIMIT 1";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();
    int id = resultSet.getInt(1);
    resultSet.close();
    return id;
  }

}
