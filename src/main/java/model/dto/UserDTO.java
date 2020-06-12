package model.dto;

import model.User;

/**
 * @author 王雅琪
 * @description ${description}
 * @date 2020/6/12
 */
public class UserDTO extends User {

  private String companyName;
  private String addressContent;
  private Integer companyId;




  public UserDTO(String name, String companyName, String addressContent) {
    super(name);
    this.companyName = companyName;
    this.addressContent = addressContent;
  }

  public UserDTO(String name,Integer companyId) {
    super(name);
    this.companyId=companyId;
  }

  public UserDTO(Integer id, String name, String companyName, String addressContent) {
    super(id, name);
    this.companyName = companyName;
    this.addressContent = addressContent;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getAddressContent() {
    return addressContent;
  }

  public void setAddressContent(String addressContent) {
    this.addressContent = addressContent;
  }

  public Integer getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Integer companyId) {
    this.companyId = companyId;
  }
}
