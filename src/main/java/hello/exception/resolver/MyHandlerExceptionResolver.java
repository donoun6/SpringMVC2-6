package hello.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                //IllegalArgumentException 예외가 발생해서 컨트롤러 내부에서 밖으로 나오면 400에러로 보낸다.
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                //새로운 ModelAndView를 반환
                //빈값으로 넘기면 정상적인 흐름으로 return되서 서블릿 컨테이너 WAS까지 정상적인 흐름으로 retrun된다. (예외를 삼켜버린다)
                return new ModelAndView();
            }
        } catch (IOException e) {
            log.error("resolver ex", e);
        }

        return null;
    }

}
