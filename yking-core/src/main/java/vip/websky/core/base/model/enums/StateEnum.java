package vip.websky.core.base.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Yong.Yang
 * @since 2019/5/27 16:59
 */
@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StateEnum {
    disable(0, "禁用"),
    normal(1, "正常");

    @EnumValue
    @JsonValue
    private final Integer key;
    private final String title;
}
