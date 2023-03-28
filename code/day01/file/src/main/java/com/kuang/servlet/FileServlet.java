package com.kuang.servlet;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断上传的文件是普通表单还是带文件的表单
        if (!ServletFileUpload.isMultipartContent(req)){
            return;//终止方法运行,说明这是一个普通表单，直接返回
        }

        //创建上传文件的保存路径，建议在WEB-INF路径下，安全，用户无法直接访问上传的文件
        String uploadPath = this.getServletContext().getRealPath("/WEB-INF/upload");
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()){
            uploadFile.mkdir();//创建这个目录
        }
        //缓存，临时文件
        //临时路径，假如文件超过了预期的大小，我们就把他放到一个临时文件中，过几天自动清除，或者提醒用户转存为永久
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        File file = new File(tempPath);
        if (!file.exists()){
            file.mkdir();//创建这个临时目录
        }

        //1.创建DiskFileItemFactory对象，处理文件上传路径或者大小限制的
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //通过这个工厂设置一个缓存区，当上传的文件大于这个缓冲区的时候，将他放入到临时文件中
        factory.setSizeThreshold(1024 * 1024);//缓冲区为1M
        factory.setRepository(file);//临时目录的保存目录，需要一个File

        //2.获取 ServletFileUpload
        ServletFileUpload upload = new ServletFileUpload(factory);

        //监听文件的上传进度
        upload.setProgressListener(new ProgressListener() {
            //pBytesRead：已经读取到的文件大小
            //pContentLength：文件大小
            public void update(long pBytesRead, long pContentLength, int pItems) {
                System.out.println("总大小：" + pContentLength + "已上传：" + pBytesRead);
            }
        });
        //处理乱码问题
        upload.setHeaderEncoding("UTF-8");
        //设置单个文件的最大值
        upload.setFileSizeMax(1024 * 1024 * 10);
        //设置总共能够上传文件的大小
        //1024 = 1kb * 1024 = 1M * 10 = 10M
        upload.setSizeMax(1024 * 1024 * 10);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
