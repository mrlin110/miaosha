package com.mrlin.miaosha.exception;

import com.mrlin.miaosha.common.CodeMsg;
import com.mrlin.miaosha.common.Result;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import java.util.List;

/**
 * @Description: 异常全局处理器
 * @Author: ljm
 * @Date: 2020/10/14 14:25
 * @Version: 1.0
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
   public Result<String> exceptionHandler(HttpServletRequest request,Exception e){
        if((e instanceof GlobalException)){
            GlobalException ex = (GlobalException)e;
            return  Result.error(ex.getCodeMsg());
        }else if(e instanceof BindException){
           BindException ex = (BindException)e;
           List<FieldError> lt = ex.getBindingResult().getFieldErrors();
           StringBuffer sBuffer =  new StringBuffer();
           for(FieldError fieldError:lt) {
               sBuffer.append(fieldError.getDefaultMessage()+",");
           }
           if(sBuffer.length()>0) {
               sBuffer.deleteCharAt(sBuffer.length() - 1);
           }
           return  Result.error(CodeMsg.BIND_ERROR.fillArgs(sBuffer));
       }else{
           return  Result.error(CodeMsg.SERVER_ERROR);
       }
   }
}
