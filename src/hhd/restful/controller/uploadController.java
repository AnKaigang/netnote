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
public class uploadController {

	@RequestMapping(value = "upload")
	public String upload(String communityid) {
		
		return "fileUpload";
	}
	/**
	 * @author liukai
	 * 获取存放在session里面的用户上传的头像文件名
	 * @param session
	 * @return
	 */
	@RequestMapping(value="HeadFileName")
	@ResponseBody
	public String  getHeadFileName(HttpSession session)
	{
		
		return (String)session.getAttribute("Imagename");
	}
	@RequestMapping(value = "upload2")
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
					session.setAttribute("Imagename",tfilename);
					// 写文件到本地
					file.transferTo(localFile);
				}
			}
		}
		
        return tfilename ;
	}

	@RequestMapping(value = "testupload")
	@ResponseBody
	public void test(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String serverRealPath = request.getSession().getServletContext()
				.getRealPath("/headImage");
		response.getWriter().write(serverRealPath);
		
	}

	@RequestMapping("download")
	@ResponseBody
	public String download(String filename, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("utf-8");
		System.out.println(filename);

		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ filename);
		
		String serverRealPath = request.getSession().getServletContext()
				.getRealPath("/headImage");
		serverRealPath += File.separator;
		String filepath = serverRealPath + filename;
		
		try {

			InputStream inputStream = new FileInputStream(new File(filepath));

			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			// 这里主要关闭。
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 返回值要注意，要不然就出现下面这句错误！
		// java+getOutputStream() has already been called for this response
		return null;
	}
}
