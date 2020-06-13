package dao.impl;

import dao.CompanyDao;
import model.Company;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author 王雅琪
 * @description ${description}
 * @date 2020/6/12
 */
public class CompanyDaoImpl implements CompanyDao {

  private JdbcTemplate jdbcTemplate;

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void initTable() {
    String sql = "CREATE TABLE `company`(id INTEGER PRIMARY KEY AUTOINCREMENT ,`name` VARCHAR(255) NOT NULL )";
    int update = jdbcTemplate.update(sql);
    System.err.println("[company]初始化结果：" + update);
  }

  @Override
  public synchronized int insert(Company company) {
    Object[] parameter = {company.getName()};
    String sql = "INSERT INTO `company`(`name`) VALUES (?)";
    int update = jdbcTemplate.update(sql, parameter);
    System.err.println("[company]insert结果：" + update);
    return findNewCompanyId();
  }

  @Override
  public void del(Integer id) {
    Object[] parameter = {id};
    String sql = "DELETE FROM `company`WHERE id=?";
    int update = jdbcTemplate.update(sql, parameter);
    System.err.println("[company]del结果：" + update);
  }

  @Override
  public void update(Company company) {
    Object[] parameter = {company.getName(), company.getId()};
    String sql = "UPDATE `company` SET `name`=? WHERE id=?";
    int update = jdbcTemplate.update(sql, parameter);
    System.err.println("[company]update结果：" + update);
  }

  @Override
  public Integer findNewCompanyId() {
    String sql = "SELECT id FROM `company` ORDER BY id DESC LIMIT 1";
    return jdbcTemplate.queryForObject(sql, Integer.class);
  }

}
