package dao;

import model.Company;

/**
 * @author 王雅琪
 * @description ${description}
 * @date 2020/6/13
 */
public interface CompanyDao {

  void initTable();

  int insert(Company company);

  void del(Integer id);

  void update(Company company);

  Integer findNewCompanyId();
}
