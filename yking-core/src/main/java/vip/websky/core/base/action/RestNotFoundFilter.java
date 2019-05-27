package vip.websky.core.base.action;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vip.websky.core.base.model.dto.ResponseDTO;

/**
 * @author Yong.Yang
 * @since 2019/5/19 21:18
 */
@RestController
public class RestNotFoundFilter implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseDTO handleError() {
        return ResponseDTO.error("404","资源路径未找到");
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
