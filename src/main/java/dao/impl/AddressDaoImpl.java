package dao.impl;

import dao.AddressDao;
import java.sql.SQLException;
import model.dto.AddressDTO;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author 王雅琪
 * @description ${description}
 * @date 2020/6/12
 */
public class AddressDaoImpl implements AddressDao {

  private JdbcTemplate jdbcTemplate;

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void initTable() throws SQLException {
    String sql = "CREATE TABLE `address`(id INTEGER PRIMARY KEY AUTOINCREMENT ,`company_id` int(11) NOT NULL ,`address_content` VARCHAR(255) NOT NULL)";
    int update = jdbcTemplate.update(sql);
    System.err.println("[address]初始化结果：" + update);
  }
  @Override
  public void insert(AddressDTO addressDTO) throws SQLException {
    Object[] parameter = {addressDTO.getAddressContent(), addressDTO.getCompanyId()};
    String sql = "INSERT INTO `address`(`address_content`,company_id) VALUES (?,?)";
    int update = jdbcTemplate.update(sql, parameter);
    System.err.println("[address]insert结果：" + update);
  }

  @Override
  public void del(Integer id) throws SQLException {
    Object[] parameter = {id};
    String sql = "DELETE FROM `address`WHERE id=?";
    int update = jdbcTemplate.update(sql, parameter);
    System.err.println("[address]del结果：" + update);
  }

  @Override
  public void update(AddressDTO addressDTO) throws SQLException {
    Object[] parameter = {addressDTO.getAddressContent(),addressDTO.getCompanyId(),addressDTO.getId()};
    String sql = "UPDATE `address` SET `address_content`=?,company_id=? WHERE id=?";
    int update = jdbcTemplate.update(sql, parameter);
    System.err.println("[address]update结果：" + update);

  }
}
