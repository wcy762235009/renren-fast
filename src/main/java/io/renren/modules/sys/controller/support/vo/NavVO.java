package io.renren.modules.sys.controller.support.vo;


import io.renren.modules.sys.entity.SysMenuEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * @Author: wcy
 * @CreateTime: 2025-03-26
 * @Description:
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NavVO {
    List<SysMenuEntity> menuList;
    Set<String> permissions;
}
