package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import model.dto.AddressDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.sqlite.SQLiteConnection;

/**
 * @author 王雅琪
 * @description ${description}
 * @date 2020/6/12
 */
public class AddressDao {

  private JdbcTemplate jdbcTemplate;

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void initTable() throws SQLException {
    SQLiteConnection connection = (SQLiteConnection) jdbcTemplate.getDataSource().getConnection();
    PreparedStatement preparedStatement = connection
        .prepareStatement(
            "CREATE TABLE `address`(id INTEGER PRIMARY KEY AUTOINCREMENT ,`company_id` int(11) NOT NULL ,`address_content` VARCHAR(255) NOT NULL)");
    int execute = preparedStatement.executeUpdate();
    System.err.println("[address]初始化结果：" + execute);
  }

  public void insert(AddressDTO addressDTO) throws SQLException {
    DataSource dataSource = jdbcTemplate.getDataSource();
    SQLiteConnection connection = (SQLiteConnection) dataSource.getConnection();
    PreparedStatement preparedStatement = connection
        .prepareStatement(
            "INSERT INTO `address`(id,`address_content`,company_id) VALUES (" + null + ",'"
                + addressDTO.getAddressContent() + "'," + addressDTO.getCompanyId() + ")");
    int execute = preparedStatement.executeUpdate();
    System.err.println("[address]insert结果：" + execute);
  }


  public void del(Integer id) throws SQLException {
    DataSource dataSource = jdbcTemplate.getDataSource();
    SQLiteConnection connection = (SQLiteConnection) dataSource.getConnection();
    PreparedStatement preparedStatement = connection
        .prepareStatement("DELETE FROM `address`WHERE id=" + id);
    int execute = preparedStatement.executeUpdate();
    System.err.println("[address]del结果：" + execute);

  }


  public void update(AddressDTO addressDTO) throws SQLException {
    DataSource dataSource = jdbcTemplate.getDataSource();
    SQLiteConnection connection = (SQLiteConnection) dataSource.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(
        "UPDATE `address` SET `address_content`=" + addressDTO.getAddressContent() + ",company_id="
            + addressDTO.getCompanyId() + " WHERE id=" + addressDTO.getId());
    boolean execute = preparedStatement.execute();
    System.err.println("[address]update结果：" + execute);

  }
}
