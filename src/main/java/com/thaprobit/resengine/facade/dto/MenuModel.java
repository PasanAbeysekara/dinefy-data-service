package com.thaprobit.resengine.facade.dto;

import com.thaprobit.resengine.dao.MenuCategory;
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
@EqualsAndHashCode(callSuper = false)
public class MenuModel extends RepresentationModel<MenuModel> {
    private Long menuId;
    private String name;
    private String description;
    private Set<MenuCategory> menuCategories;
}
