package vip.websky.admin.sys.model.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
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
public enum StatusEnum implements IEnum<String> {
    normal("1", "正常"),
    disable("0", "禁用");

    private String value;
    private String desc;

    @JsonValue
    public String getDesc(){
        return this.desc;
    }

    public static StatusEnum statusOf(String statusValue){
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.value.equals(statusValue)) {
                return statusEnum;
            }
        }
        return StatusEnum.disable;
    }
}
