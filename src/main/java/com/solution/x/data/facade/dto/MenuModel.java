package com.solution.x.data.facade.dto;

import com.solution.x.dao.MenuCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

/**
 * @author Tharindu Aththanayake
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenuModel extends RepresentationModel<MenuModel> {
    private Long menuId;
    private String name;
    private String description;
    private Set<MenuCategory> menuCategories;
}
