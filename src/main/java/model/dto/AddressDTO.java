package model.dto;

import model.Address;

/**
 * @author 王雅琪
 * @description ${description}
 * @date 2020/6/12
 */
public class AddressDTO extends Address {

  private Integer companyId;

  public AddressDTO(String addressContent, Integer companyId) {
    super(addressContent);
    this.companyId = companyId;
  }

  public Integer getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Integer companyId) {
    this.companyId = companyId;
  }
}
