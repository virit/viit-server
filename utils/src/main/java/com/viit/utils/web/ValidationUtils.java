package com.viit.utils.web;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * 表单校验工具类
 *
 * @author chentao
 * @version 2019-10-29
 */
public class ValidationUtils {

    /**
     * 获取错误字段
     * @return 错误字段
     */
    public static ErrorFields getErrorFields(Errors errors) {
        if (!errors.hasErrors()) {
            return null;
        }
        ErrorFieldsImpl errorFields = new ErrorFieldsImpl();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            ErrorFieldImpl stField = new ErrorFieldImpl();
            stField.setFieldName(fieldError.getField());
            stField.setMessage(fieldError.getDefaultMessage());
            errorFields.add(stField);
        });
        return errorFields;
    }
}
