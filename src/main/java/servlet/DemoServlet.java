package servlet;


import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 王雅琪
 * @description ${description}
 * @date 2020/6/11
 */
public class DemoServlet extends HttpServlet {

  public DemoServlet(){
    System.err.println("init");
  }


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Enumeration<String> attributeNames = req.getAttributeNames();
  }

}
