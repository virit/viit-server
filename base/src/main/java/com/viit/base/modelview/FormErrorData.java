package com.viit.base.modelview;

import com.viit.utils.web.ErrorFields;
import com.viit.utils.web.ValidationUtils;
import org.springframework.validation.Errors;

/**
 * 表单错误返回数据
 *
 * @author virit
 * @version 2019-10-29
 */
public class FormErrorData extends SimpleRestData<ErrorFields> {

    public FormErrorData(Errors errors) {
        this.data = ValidationUtils.getErrorFields(errors);
        this.resultCode = ResultCode.FORM_VALID_FAILED;
    }
}
