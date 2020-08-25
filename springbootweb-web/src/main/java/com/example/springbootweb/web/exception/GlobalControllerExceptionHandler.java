package com.example.springbootweb.web.exception;

import com.example.springbootweb.biz.execption.ServiceException;
import com.example.springbootweb.common.entity.Result;
import com.example.springbootweb.common.error.SpringBootWebErrors;
import com.example.springbootweb.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * @author william
 * @date 2020/4/21
 */
@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConnversion(RuntimeException ex) {
        log.warn("{} ---> {}", HttpStatus.BAD_REQUEST, ex.toString());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SpringBootWebNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleTimeMachineNotFound(RuntimeException ex) {
        log.warn("{} ---> {}", HttpStatus.NOT_FOUND, ex.toString());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public String handleServiceException(ServiceException e) {
        printLog(e);
        return JsonUtil.bean2Json(Result.wrapErrorResult(e.getError()));
    }

    /**
     * 参数校验
     */
    //TODO 怎么只返回注解上的value
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        printLog(e);
        return JsonUtil.bean2Json(Result.wrapErrorResult(10001, e.getMessage()));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public String handleDuplicateKeyException(DuplicateKeyException e) {
        printLog(e);
        return JsonUtil.bean2Json(Result.wrapErrorResult(611, "This record already exists."));
    }

    /**
     * 运行时异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String runtimeExceptionHandler(RuntimeException e) {
        printLog(e);
        return JsonUtil.bean2Json(Result.wrapErrorResult(SpringBootWebErrors.RuntimeException));
    }

    /**
     * 空指针异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public String nullPointerExceptionHandler(NullPointerException e) {
        printLog(e);
        return JsonUtil.bean2Json(Result.wrapErrorResult(SpringBootWebErrors.NullPointerException));
    }

    /**
     * 类型转换异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public String classCastExceptionHandler(ClassCastException e) {
        printLog(e);
        return JsonUtil.bean2Json(Result.wrapErrorResult(SpringBootWebErrors.ClassCastException));
    }

    /**
     * IO异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public String iOExceptionHandler(IOException e) {
        printLog(e);
        return JsonUtil.bean2Json(Result.wrapErrorResult(SpringBootWebErrors.IoException));
    }

    /**
     * 未知方法异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseBody
    public String noSuchMethodExceptionHandler(NoSuchMethodException e) {
        printLog(e);
        return JsonUtil.bean2Json(Result.wrapErrorResult(SpringBootWebErrors.NoSuchMethodException));
    }

    /**
     * 数组越界异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public String indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException e) {
        printLog(e);
        return JsonUtil.bean2Json(Result.wrapErrorResult(SpringBootWebErrors.IndexOutOfBoundsException));
    }

    /**
     * 400错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public String requestNotReadable(HttpMessageNotReadableException e) {
        printLog(e);
        return JsonUtil.bean2Json(Result.wrapErrorResult(SpringBootWebErrors.RequestNotReadable));
    }

    /**
     * 400错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public String requestTypeMismatch(TypeMismatchException e) {
        printLog(e);
        return JsonUtil.bean2Json(Result.wrapErrorResult(SpringBootWebErrors.BadRequest));
    }

    /**
     * 400错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public String requestMissingServletRequest(MissingServletRequestParameterException e) {
        printLog(e);
        return JsonUtil.bean2Json(Result.wrapErrorResult(SpringBootWebErrors.BadRequest));
    }

    /**
     * 405错误
     *
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public String request405(HttpRequestMethodNotSupportedException e) {
        printLog(e);
        return JsonUtil.bean2Json(Result.wrapErrorResult(SpringBootWebErrors.MethodNotAllowed, e.getMethod()));
    }

    /**
     * 406错误
     *
     * @return
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    public String request406(HttpMediaTypeNotAcceptableException e) {
        printLog(e);
        return JsonUtil.bean2Json(Result.wrapErrorResult(SpringBootWebErrors.NotAcceptable, e.getSupportedMediaTypes()));
    }

    /**
     * 500错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @ResponseBody
    public String server500(RuntimeException e) {
        printLog(e);
        return JsonUtil.bean2Json(Result.wrapErrorResult(SpringBootWebErrors.SystemError));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e) {
        printLog(e);
        return JsonUtil.bean2Json(Result.wrapErrorResult(SpringBootWebErrors.SystemError));
    }

    /**
     * 异常信息打印日志
     *
     * @param e
     */
    private void printLog(Exception e) {

        log.error("error >>> ", e);
    }
}
