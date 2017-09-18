package pictureturncharacter0904;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imgtest {

	/** 此处设置灰度字符，此处只用十个字符，可以设置更多 */
	private static char[] cs = new char[] { '.', ',', '*', '+', '=', '&', '$', '@', '#', ' ' };
	//private static char[] cs = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', ' ' };
	//private static char[] cs = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ' };
//	public static void main(String[] arges) throws IOException{
//		BufferedImage bimage = ImageIO.read(new File("C:\\Users\\Administrator\\Pictures\\TIM图片20170904174351.jpg"));
//		int with = bimage.getWidth();
//		int height = bimage.getHeight();
//		char[][] css = new char[with][height];
//		for(int x = 0; x < with ;x++){
//			for(int y = 0; y < height ;y++){
//				int rgb = bimage.getRGB(x, y);
//				Color c = new Color(rgb);
//				// 得到灰度值
//			    int cc = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
//			    css[x][y] = cs[(int) ((cc * 10 - 1) / 255)];
//			}
//		}
//		int bei = 8;
//		with*=bei;
//		height*=bei;
//		BufferedImage newImage = new BufferedImage(with, height, BufferedImage.TYPE_INT_RGB);
//		Graphics g = newImage.getGraphics();
////		newImage = ((Graphics2D) g).getDeviceConfiguration().createCompatibleImage(with*10, height*10, Transparency.TRANSLUCENT); 
////		g.dispose(); 
//		g.fillRect(0, 0, with, height);
//		g = newImage.createGraphics(); 
//		g.setFont(new Font("", Font.BOLD, 10));
//		g.setColor(new Color(0,0,0));
//		for(int x = 0; x < with/bei ;x++){
//			for(int y = 0; y < height/bei ;y++){
//				System.out.print(css[x][y]);
//				g.drawString(String.valueOf(css[x][y]), x*bei, y*bei);
//			}
//			System.out.println();
//		}
//		ImageIO.write(newImage, "png", new File("C:\\Users\\Administrator\\Desktop\\TIM图片20170904174353.png"));
//		
//	}
	public static void main(String[] arges){
		int i =10;
		System.out.println(i++);
		System.out.println(++i);
		System.out.println(i=20);
		System.out.println(i==20);
	}
}
