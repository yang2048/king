package vip.websky.admin.sys.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Yong.Yang
 * @since 2019/5/27 17:04
 */
@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PowerTypeEnum{
    page(1, "页面"),
    catalog(2, "目录"),
    button(3, "按钮"),
    menu(4, "菜单");

    @EnumValue
    @JsonValue
    private Integer value;
    private String desc;

}
