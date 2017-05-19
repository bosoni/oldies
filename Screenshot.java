import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.geom.*;

public class Screenshot {
	public static void main(String[] args) throws Exception {

		String outFileName = "pics/out";

		// determine current screen size
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		Rectangle screenRect = new Rectangle(screenSize);

		// create screen shot
		Robot robot = new Robot();
		// scale
		int lev = 1280, kor = 1024;
		float scaleX = 1.0f, scaleY = scaleX;
		int newW = (int) (scaleX * lev);
		int newH = (int) (scaleY * kor);

		BufferedImage bdest = new BufferedImage(newW, newH,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bdest.createGraphics();
		AffineTransform at = AffineTransform.getScaleInstance((double) scaleX,
				(double) scaleY);

		for (int a = 0; a < 100; a++) {
			System.out.println("Kuva " + a);

			BufferedImage image = robot.createScreenCapture(screenRect);

			g.drawRenderedImage(image, at);
			ImageIO.write(bdest, "jpg", new File(outFileName + a + ".jpg"));

			try {
				Thread.sleep(100);
			} catch (InterruptedException ie) {
				//
			}

		}

		System.exit(0);
	}
}
