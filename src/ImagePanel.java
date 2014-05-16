import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class ImagePanel extends JPanel{
	
	Image logo = null;
	Color bgcolor = null;
	
	public ImagePanel(String filename, Color bg){
		
		super();
		bgcolor = bg;
		this.setBackground(bgcolor);
		
		logo = null;
		
		URL imgloc = this.getClass().getResource(filename);
		try {
			logo = ImageIO.read(imgloc);
		} catch (IOException e) {
			System.err.println("Logo not found :(");
			e.printStackTrace();
		}
		
		
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(logo, 35,0,null);
	}
	
}
