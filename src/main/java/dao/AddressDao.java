package dao;

import java.sql.SQLException;
import model.dto.AddressDTO;

/**
 * @author 王雅琪
 * @description ${description}
 * @date 2020/6/13
 */
public interface AddressDao {

  void initTable() throws SQLException;

  void insert(AddressDTO addressDTO) throws SQLException;

  void del(Integer id) throws SQLException;

  void update(AddressDTO addressDTO) throws SQLException;
}
