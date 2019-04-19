package com.self.project.advice;

import com.self.project.core.Result;
import com.self.project.core.exception.BusinessException;
import com.self.project.util.ResultUtil;
import com.self.project.util.Tools;
import com.self.project.util.enumUtil.DBError;
import com.self.project.util.enumUtil.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.*;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @Description： SpringMVC异常统一处理
 * @Author will
 * @Date 2019/4/4 0004 下午 17:09
 */
@RestControllerAdvice
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    /**
     * 统一处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result handlerBusinessException(BusinessException e) {
        //记录业务异常
        logger.info(e.getMessage());
        return ResultUtil.error(e.getMessage());
    }

    /**
     * 统一处理接口不存在异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoHandlerFoundException(NoHandlerFoundException e) {

        return ResultUtil.error(ResultCode.NOT_FOUND.getCode(), Tools.format("接口 [{}] 不存在", e.getRequestURL()));

    }

    /**
     * 统一处理数据库访问错误
     */
    @ExceptionHandler(DataAccessException.class)
    public Result handleDataAccessException(DataAccessException e) {
        if (e instanceof DataIntegrityViolationException) {
            return DBError.DB_INTEGRITY_VIOLATION.result();
        }
        if (e instanceof DataAccessResourceFailureException) {
            return DBError.DB_ACCESS_FAILURE.result();
        }
        if (e instanceof BadSqlGrammarException) {
            return DBError.DB_BAD_SQL.result();
        }
        if (e instanceof TypeMismatchDataAccessException) {
            return DBError.DB_TYPE_MISMATCH.result();
        }
        if (e instanceof InvalidDataAccessResourceUsageException) {
            return DBError.DB_INVALID_ACCESS.result();
        }

        return DBError.DB_UNKNOW_ERROR.result();
    }

    /**
     * 统一处理运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result handlerRuntimeException(RuntimeException e) {
        StackTraceElement[] stacks = e.getStackTrace();
        StackTraceElement element = stacks[0];
        String message = Tools.format("接口出现异常，方法：{}.{}，异常摘要：{}",
                element.getClassName(), element.getMethodName(), e.getMessage());
        logger.error(message, e);
        return ResultUtil.error(ResultCode.INTERNAL_SERVER_ERROR.getCode(),
                Tools.format("方法：{}.{} 内部错误，请联系管理员",
                        element.getClassName(), element.getMethodName()));
    }

    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e) {
        return ResultUtil.error(ResultCode.UNKNOW_ERROR.getCode(), e.getMessage());
    }
}
