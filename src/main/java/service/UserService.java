package service;

import dao.AddressDao;
import dao.CompanyDao;
import dao.UserDao;
import java.sql.SQLException;
import java.util.List;
import model.Company;
import model.User;
import model.dto.AddressDTO;
import model.dto.UserDTO;

/**
 * @author 王雅琪
 * @description ${description}
 * @date 2020/6/12
 */
public class UserService {

  private UserDao userDao;
  private CompanyDao companyDao;
  private AddressDao addressDaoImpl;

  public UserService(UserDao userDao, CompanyDao companyDao, AddressDao addressDao) {
    this.userDao = userDao;
    this.companyDao = companyDao;
    this.addressDaoImpl = addressDao;
  }

  public void initDB() {
    try {
      userDao.initTable();
      companyDao.initTable();
      addressDaoImpl.initTable();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void addUserInfo(UserDTO userDTO) {
    try {
      int companyId = companyDao.insert(new Company(userDTO.getCompanyName()));
      userDao.insert(new UserDTO(userDTO.getName(), companyId));
      addressDaoImpl.insert(new AddressDTO(userDTO.getAddressContent(), companyId));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void findUserInfo(int start) {
    List<UserDTO> userDTOS = userDao.findUserDetailInfo(start);
    userDTOS.forEach(user -> {
      System.err.println(
          user.getId() + " " + user.getName() + " " + user.getCompanyName() + " " + user
              .getAddressContent());
    });

  }

  public void findUser(int start) {
    List<User> users = userDao.find(start);
    users.forEach(user -> {
      System.err.println(user.getId() + " " + user.getName());
    });
  }
}
