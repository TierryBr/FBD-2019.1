package br.quixada.ufc.si.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class Loading extends JFrame {

	private JPanel contentPane;
	private final int largura_img = 600;
	private final int altura_img = 410;
	private final int tempo_espera = 5000;
	private final String caminho = "/imagens/loading.gif";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loading frame = new Loading();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Loading() {
		JWindow janela = new JWindow();
		janela.getContentPane()
				.add(new JLabel(" ", new ImageIcon(getClass().getResource(caminho)), SwingConstants.CENTER));

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		janela.setBounds((dimension.width - largura_img) / 2, (dimension.height - altura_img) / 2, largura_img,
				altura_img);
		janela.setVisible(true);

		try {
			Thread.sleep(tempo_espera);
			janela.dispose();
		} catch (InterruptedException ie) {

		}
	}

}
