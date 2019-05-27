package vip.websky.admin.sys.model.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
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
public enum PowerTypeEnum implements IEnum<String> {
    page("page", "页面"),
    operate("operate", "操作"),
    catalog("catalog", "目录"),
    button("button", "按钮"),
    menu("menu", "菜单");

    private String value;
    private String desc;

    @JsonValue
    public String getDesc(){
        return this.desc;
    }
}
