package filter;
import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter{
    //初始化 web服务器启动他就初始化了，随时等待过滤对象出现！
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CharacterEncodingFilter初始化");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");

        System.out.println("CharacterEncodingFilter执行前");
        filterChain.doFilter(servletRequest,servletResponse);//让我们的请求继续走，如果不写，那么程序就被拦截停止了
        System.out.println("CharacterEncodingFilter执行后");
    }
//銷毀，web服务器关闭的时候，过滤会销毁
    public void destroy() {
        System.out.println("CharacterEncodingFilter销毁");
    }
}
