package com.fastrack.msorderproject.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fastrack.msorderproject.models.Orders;
import com.fastrack.msorderproject.models.StatusEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.NonNull;

/**
 * OrderDto
 */
@Validated
@Builder
public class OrderDto   {
  @JsonProperty("description")
  @NonNull
  private String description = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  @NonNull
  private String name = null;

  @JsonProperty("total")
  @NonNull
  private Double total = null;

  @JsonProperty("status")
  private StatusEnum status = null;
  
  public OrderDto() {
  }
  
  public OrderDto(Orders ordem) {
		this.id = ordem.getId();
		this.name = ordem.getName();
		this.total = ordem.getTotal();
		this.status = ordem.getStatus();
		this.description = ordem.getDescription();
				
	}
  

  public OrderDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public OrderDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OrderDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public OrderDto total(Double total) {
    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
  **/
  @ApiModelProperty(value = "")
  
    public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public OrderDto status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")
  
    public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderDto orderDto = (OrderDto) o;
    return Objects.equals(this.description, orderDto.description) &&
        Objects.equals(this.id, orderDto.id) &&
        Objects.equals(this.name, orderDto.name) &&
        Objects.equals(this.total, orderDto.total) &&
        Objects.equals(this.status, orderDto.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, id, name, total, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderDto {\n");
    
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
  public static List<OrderDto> converter(List<Orders> orders) {
		
	return orders.stream().map(OrderDto::new).collect(Collectors.toList());
  }
}
