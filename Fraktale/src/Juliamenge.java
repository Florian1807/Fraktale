import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel; //Importieren
public class Juliamenge extends JPanelMandelbrot {
	public void zeichneJuliamenge(Graphics g, int liRand, int obRand) {
		double xa = -1.6, xe = 1.6, ya = -1.43, ye = 1.43; // Werte variieren !
		double dx = (xe-xa)/(imageBreite-1), dy = (ye-ya)/(imageHoehe-1);
		double zx, zy, tmp;
		double cx = -0.7, cy = 0.1; // Werte variieren !
		int maxIt = 200; // Wert variieren !
		int zaehler;
		g.setColor(Color.BLACK);
		for (int sp = 0; sp < imageBreite; sp++) {
		 for (int ze = 0; ze < imageHoehe; ze++) {
		 zx = xa + sp * dx; // von links nach rechts
		 zy = ye - ze * dy; // von oben nach unten
		 zaehler = 0;
		 do {
		 tmp = zx*zx - zy*zy + cx;
		 zy = 2*zx*zy + cy;
		 zx = tmp;
		 zaehler = zaehler + 1;
		 } while (zx*zx + zy*zy <= 4.0 && zaehler < maxIt);
		 if (zaehler == maxIt)
		 g.drawLine(sp + liRand, ze + obRand, sp + liRand, ze + obRand);
		} // for ze
		} // for Sp
		}

}
