package vip.websky.admin.sys.model.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: YangYong
 * @Date: 2018/8/18 23:11
 * @Copyright yang2048@qq.com @沐之永
 * @Company
 * @Description:
 */
@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserSexEnum implements IEnum<String> {
    /**
     * 未知
     */
    unknown("0", "未知"),
    male("1", "男"),
    female("2", "女");

    private String value;
    private String desc;

    @JsonValue
    public String getDesc(){
        return this.desc;
    }

    public static UserSexEnum genderOf(String genderValue){
        for (UserSexEnum gender : UserSexEnum.values()) {
            if (gender.value.equals(genderValue)) {
                return gender;
            }
        }
        return UserSexEnum.unknown;
    }
}
