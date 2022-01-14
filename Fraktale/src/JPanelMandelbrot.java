
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JPanelMandelbrot extends JFrame { // Ac 04-2018
	public static void main(String[] args) {
		new JPanelMandelbrot();
	}

	static int imageBreite = 459;
	static int imageHoehe = 405;
	int frameBreite = imageBreite + 30, frameHoehe = imageHoehe + 50;
	Leinwand malPanel = new Leinwand(imageBreite, imageHoehe);
	JPanel contentPane;

	public JPanelMandelbrot() { // Konstruktor
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(frameBreite, frameHoehe);
		setLocationRelativeTo(null);
		setTitle("Mandelbrotmenge mit JPanel");
		contentPane = new JPanel();
		setContentPane(contentPane);
		setVisible(true);
		malPanel.setPreferredSize(new Dimension(imageBreite, imageHoehe));
		contentPane.add(malPanel);
	}
}

class Leinwand extends JPanel {
	public Leinwand(int imageBreite, int imageHoehe) {
		setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		long zeit = System.currentTimeMillis();
		Mandelbrot.zeichneMandelbrotmenge(g, JPanelMandelbrot.imageBreite, JPanelMandelbrot.imageHoehe);
		System.out.println("benötigte Zeit = " + (System.currentTimeMillis() - zeit) + " ms");
	}
}

class Mandelbrot { // statische Klasse
	public static int iterZahl(final double cx, final double cy, int maxIt) {
		int zaehler = 0;
		double zx = 0.0, zy = 0.0, tmp;
		do {
			tmp = zx * zx - zy * zy + cx;
			zy = 2 * zx * zy + cy;
			zx = tmp;
			zaehler = zaehler + 1;
		} while (zx * zx + zy * zy <= 4.0 && zaehler < maxIt);
		return zaehler;
	}

	public static void zeichneMandelbrotmenge(Graphics g, int imageBreite, int imageHoehe) {
		double xa = -2.02, xe = 0.7, ya = -1.2, ye = 1.2; // Ratio 17:15
		final double dx = (xe - xa) / (imageBreite - 1), dy = (ye - ya) / (imageHoehe - 1);
		double cx, cy;
		int maxIt = 500;
		g.setColor(Color.BLACK);
		for (int sp = 0; sp < imageBreite; sp++) {
			cx = xa + sp * dx; // von links nach rechts
			for (int ze = 0; ze < imageHoehe; ze++) {
				cy = ye - ze * dy; // von oben nach unten
				if (iterZahl(cx, cy, maxIt) == maxIt)
					g.drawLine(sp, ze, sp, ze);
			}
		}
	}
}