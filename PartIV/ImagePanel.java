package PartIV;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private Image img;

	private Image[] diceImages = new Image[6];
	private List<Integer> imageSequence;
	private final String file = "PartIV/";
	
	public ImagePanel() {

		for (int i=0;i<6;i++) {
			diceImages[i] = new ImageIcon(file + "die"+(i+1) +".png").getImage();
		}
		imageSequence = new ArrayList<Integer>();
	}
	
	public ImagePanel(String img) {
		this(new ImageIcon(img).getImage());
	}
	
	public ImagePanel(Image img) {
		this();
		this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        /*setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);*/
        setLayout(null);
	}
	
	public void setImage(Image img) {
		this.img = img;
    	System.out.println("repainting");
		repaint();
	}
	
	public void setImage(String img) {
		this.img = new ImageIcon(img).getImage();
		repaint();
	}
	
	public void setSequence(List<Integer> al) {
		imageSequence = al;
		repaint();
	}
	
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	Image nextImage = img;
    	Integer lastIndex = -1;
    	if (imageSequence.size() > 0 ) {
    		lastIndex = imageSequence.get(imageSequence.size() -1);
    	}
    	for (Integer i : imageSequence) {
    		//System.out.println("setting image to index " + i);
    		nextImage = diceImages[i];
    		g.drawImage(nextImage, 50, 0, this);
    		img = nextImage;
    		
    		try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	imageSequence.clear();
    	if (lastIndex != -1) {
    		imageSequence.add(lastIndex);
    	}
    	g.drawImage(img, 50, 0, this);
    }
}
