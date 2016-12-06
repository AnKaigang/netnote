<%@ page contentType="image/jpeg" language="java" import="java.util.*,java.awt.*,java.awt.image.*,javax.imageio.*" pageEncoding="utf-8"%>

<%!  
   //生成随机颜色并获取
    Color getRandColor(int fc,int bc){  
        Random random = new Random();  
        if(fc > 255){  
            fc = 255;  
        }  
        if(bc > 255){  
            bc = 255;  
        }  
        int r = fc +random.nextInt(bc-fc);  
        int g = fc +random.nextInt(bc-fc);  
        int b = fc +random.nextInt(bc-fc);  
          
          
        return new Color(r,g,b);  
    }  
%>  
<%   
    //设置页面不缓存  
    response.setHeader("Pragma","no-cache");  
    response.setHeader("Cache-Control","no-catch");  
    response.setDateHeader("Expires",0);  
      
    //在内存中创建图象  
    int width = 70;  
    int height = 25;  
    BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);  
      
    //创建图象  
    Graphics g = image.getGraphics();  
    //生成随机对象  
    Random random = new Random();  
    //设置背景色  
    g.setColor(getRandColor(200,250));  
    g.fillRect(0,0,width,height);  
    //设置字体  	
    g.setFont(new Font("Tines Nev Roman",Font.PLAIN,22));  
    //随机产生干扰线  
     
    for(int i = 0; i < 255; i++){  
          g.setColor(getRandColor(160,200)); 
        int xl = random.nextInt(12)+1;  
        int yl = random.nextInt(6)+1; 
        int x = random.nextInt(width-1);  
        int y = random.nextInt(height-1);
        g.drawLine(x, y,i,i+1);
    }  
    //随机产生认证码,4位数字  
    char[] cs = new char[] { '0','1','H','I', '4', '5', '6','N','O','P', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j','k','l', 'A',
			'B', 'M','C','D','E','F','G','2', '3', 'J','K','L','Q','R','S','T','U','V','W','X','Y','Z' };
      String  sRand = "";  
    for(int i = 0; i < 4; i++){  
        char c =cs[random.nextInt(cs.length)];
        String rand = String.valueOf(c); 
        sRand  += rand;  
        //将认证码显示到图象中  
        g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));  
        g.drawString(rand,14*i+6,22);  
    }    
    //图像生效  
    g.dispose();  
    //输出图像到页面  
     session.setAttribute("rCode",sRand);
    try {
		ImageIO.write(image, "JPEG", response.getOutputStream());
	} catch (Exception e) {
		e.getMessage();
	} finally {

		//解决以调用错误
		out.clear();
		out = pageContext.pushBody();
	} 
%> 
