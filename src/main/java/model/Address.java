package model;  /**
   * @author 王雅琪
   * @description ${description}
   * @date 2020/6/12
   */
public class Address {
  private Integer id;

  private String addressContent;

  public Address(String addressContent) {
    this.addressContent = addressContent;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAddressContent() {
    return addressContent;
  }

  public void setAddressContent(String addressContent) {
    this.addressContent = addressContent;
  }
}
