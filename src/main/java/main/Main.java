package main;

import dao.AddressDao;
import dao.CompanyDao;
import dao.UserDao;
import java.sql.SQLException;
import java.util.List;
import model.User;
import model.dto.UserDTO;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import service.UserService;

/**
 * @author 王雅琪
 * @description ${description}
 * @date 2020/6/11
 */
public class Main {

  public static void main(String[] args) throws SQLException {
    ClassPathResource classPathResource = new ClassPathResource("application-dev.xml");
    XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(classPathResource);
    UserService userService = (UserService) xmlBeanFactory.getBean("userService");

    userService.initDB();

    userService.addUserInfo(new UserDTO("zj","久爱","上海九亭"));

    userService.findUserInfo(0);



  }
}
