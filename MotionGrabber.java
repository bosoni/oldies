/**
 * @file MotionGrabber.java
 * @author mjt, 2006-07
 * mixut@hotmail.com
 *
 * @created 31.10.2006
 * @edited 13.6.07 
 * 
 * Ohjelma ottaa talteen kaiken liikkuvan ja tallentaa ne tiedostoihin haluttuun hakemistoon.
 * rgb 254,254,254 pitäisi olla läpinäkyvä.
 *
 */
package motion;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class MotionGrabber
{
	static int minx = -1, maxx = -1;
	static int miny = -1, maxy = -1;

	static void MessageBox(String msg)
	{
		if (msg.equals(""))
			return;
		JOptionPane.showMessageDialog(null, msg, "MotionGrabber", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) throws Exception
	{
		System.out.println("MotionGrabber by mjt, 2006-07");
		System.out.println("mixut@hotmail.com");
		if(args.length<4)
		{
			System.out.println("1. parametri hakemisto tai tiedostonimi (esim pics/  tai pic) ");
			System.out.println("2. ja 3. parametri kaapattavan kuvan leveys ja korkeus.");
			System.out.println("4. parametri montako kuvaa otetaan.");
			System.out.println("Huom! Ikkuna josta liike halutaan, kannattaa viedä vasempaan ylänurkkaan. ");
			System.out.println("Esim: vanha peli, tila 320x200. Vasemmassa ylänurkassa. ");
			System.out.println(" java -jar motiongrabber pics 320 200 20   (tallentaa pics hakemistoon 20 kuvaa)");
			return;
		}
		String outFileName = args[0]+"/pic";

		int W=Integer.parseInt(args[1]), H=Integer.parseInt(args[2]), no=Integer.parseInt(args[3]);
		Rectangle size = new Rectangle(W, H);

		Robot robot = new Robot();

		MessageBox("Nyt otetaan taustakuvasta kuva, joten kaikki liikkuva piiloon, hiiri, ukko ym ja paina OK.");
		try
		{
			Thread.sleep(300);
		} catch (InterruptedException ie)
		{
		}
		BufferedImage image = robot.createScreenCapture(size);
		BufferedImage newImage = robot.createScreenCapture(size);

		MessageBox("Nyt kun painat ok, ohjelma alkaa napsimaan kuvia joten liikuta ukkoa, mitä lie.");
		try
		{
			Thread.sleep(200);
		} catch (InterruptedException ie)
		{
		}

		// ja kun painaa ok, aletaan ottaa kuvia.
		for(int q=0; q<no; q++)
		{
			// ota kuva
			newImage = robot.createScreenCapture(size);

			// ota liikkuvan kuvan reunat
			findMinMax(image, newImage);

			if(minx<0 || miny<0 || maxx<0 || maxy<0) continue;
			
			System.out.println(">"+q+"< minxx="+ minx+" "+miny+"  max_xy="+maxx+" "+maxy);
			
			// liikkuva objekti on nyt kohdassa minx,miny -> maxx, maxy
			// blittaa newImagen min -> max alueelta picciin.
			BufferedImage pic = newImage.getSubimage(minx, miny, maxx - minx, maxy - miny);

			makeAlpha(image, pic);
			

			// tallenna kuva
			BufferedImage bdest = new BufferedImage(pic.getWidth(), pic.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = bdest.createGraphics();
			AffineTransform at = AffineTransform.getScaleInstance((double)1, (double)1);
			g.drawRenderedImage(pic, at);
			ImageIO.write(bdest, "png", new File(outFileName + q + ".png"));

		}

	}
	
	/**
	 * käy pikseli pikseliltä pic läpi ja vertaa taustaan. muuta eriävät
	 * pikselit läpinäkyviksi
	 * 
	 * @param bg
	 * @param pic
	 */
	static void makeAlpha(BufferedImage bg, BufferedImage pic)
	{
		for(int x=0; x<pic.getWidth(); x++)
		{
			for(int y=0; y<pic.getHeight(); y++)
			{
				// jos sama väri alkup taustassa ja picissä
				if(bg.getRGB(minx+x, miny+y)==pic.getRGB(x, y))
				{
					pic.setRGB(x, y, (0<<24)+(254<<16)+(254<<8)+254);					
				}
			}
		}
		
	}
	/**
	 * etsi min ja max xy:t jossa muutosta tapahtunut
	 * 
	 * @param bg
	 * @param pic
	 */
	static void findMinMax(BufferedImage bg, BufferedImage pic)
	{
		minx = -1;
		maxx = -1;
		miny = -1;
		maxy = -1;

		int x, y;

		// ensin haetaan min_x ja max_x
		for (x = 0; x < pic.getWidth(); x++)
		{
			for (y = 0; y < pic.getHeight(); y++)
			{
				// erivärinen pixel
				if (pic.getRGB(x, y) != bg.getRGB(x, y))
				{
					minx = x;
					break;
				}
			}
			if (minx != -1)
				break;
		}
		for (x = pic.getWidth() - 1; x > 0; x--)
		{
			for (y = 0; y < pic.getHeight(); y++)
			{
				// erivärinen pixel
				if (pic.getRGB(x, y) != bg.getRGB(x, y))
				{
					maxx = x;
					break;
				}
			}
			if (maxx != -1)
				break;
		}

		// hae min_y ja max_y
		for (y = 0; y < pic.getHeight(); y++)
		{
			for (x = 0; x < pic.getWidth(); x++)
			{
				// erivärinen pixel
				if (pic.getRGB(x, y) != bg.getRGB(x, y))
				{
					miny = y;
					break;
				}
			}
			if (miny != -1)
				break;
		}
		for (y = pic.getHeight() - 1; y > 0; y--)
		{
			for (x = 0; x < pic.getWidth(); x++)
			{
				// erivärinen pixel
				if (pic.getRGB(x, y) != bg.getRGB(x, y))
				{
					maxy = y;
				}
			}
			if (maxy != -1)
				break;
		}
	}
}
