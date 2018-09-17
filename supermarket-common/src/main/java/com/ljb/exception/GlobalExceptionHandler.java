package com.ljb.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ljb.util.R;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/*
	 * 处理控制器中发生的异常，采用json形式进行数据返回
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus()
	@ResponseBody
	public R handlerException(Exception e) {
		if(e instanceof AccountException){
			return R.error(403,e.getMessage());
		}
		logger.error(e.getMessage());
		logger.info(getExceptionAllInfo(e));
		return R.error(-1, "未知异常："+ e.getMessage());
	}
	
	/*
	 * 处理Service层中的业务操作异常，以json数据返回
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus()
	@ResponseBody
	public R handlerBusinessException(BusinessException e) {
		logger.error(e.getMessage());
		logger.info(getExceptionAllInfo(e));
		return R.error(-1,e.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus()
    @ResponseBody
    public R handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        logger.error(e.getMessage(), e);
        logger.info(getExceptionAllInfo(e));
        return R.error(-1,e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
	
	public static String getExceptionAllInfo(Exception ex) {
        ByteArrayOutputStream out = null;
        PrintStream pout = null;
        String ret = "";
        try {
        	out = new ByteArrayOutputStream();
        	pout = new PrintStream(out);
        	ex.printStackTrace(pout);
	        ret = new String(out.toByteArray());
	        out.close();
        }catch(Exception e){
        	return ex.getMessage();
        }finally{
        	if(pout!=null){
        		pout.close();
        	}
        }
        return ret;
}
}
