package vip.websky.core.base.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
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
public enum UserSexEnum {
    /**
     * 未知
     */
    unknown(0, "未知"),
    male(1, "男"),
    female(2, "女");
    @EnumValue
    @JsonValue
    private final Integer key;
    private final String title;

//    public static UserSexEnum genderOf(Integer genderValue){
//        for (UserSexEnum gender : UserSexEnum.values()) {
//            if (gender.value.equals(genderValue)) {
//                return gender;
//            }
//        }
//        return UserSexEnum.unknown;
//    }
}
