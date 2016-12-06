package hhd.restful.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
public class EditheaderController {




	@RequestMapping(value = "editHeadImg")
	@ResponseBody
	public String upLoad2(HttpSession session,HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {

		String path = null;
		String tfilename = null;
		//response.setHeader("Content-type", "text/html;charset=UTF-8");
		// 解析器解析request的上下文
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 先判断request中是否包涵multipart类型的数据，
		if (multipartResolver.isMultipart(request)) {
			// 再将request中的数据转化成multipart类型的数据
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile((String) iter.next());
				if (file != null) {
					String fileName = file.getOriginalFilename();
					// 下面的加的日期是为了防止上传的名字一样

					String serverRealPath = request.getSession()
							.getServletContext().getRealPath("/headImage");
					serverRealPath += File.separator;
					tfilename = new SimpleDateFormat("yyyyMMddHHmmss")
							.format(new Date()) + fileName;
					path = serverRealPath + tfilename;
					File localFile = new File(path);
					// 写文件到本地
					file.transferTo(localFile);
				}
			}
		}
		
        return tfilename ;
	}

}
