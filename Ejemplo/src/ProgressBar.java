import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ProgressBar extends JFrame {

	private JPanel contentPane;
	private boolean stop = false;
	int i;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgressBar frame = new ProgressBar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProgressBar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton btnClick = new JButton("Click");
		panel.add(btnClick);

		JProgressBar progressBar = new JProgressBar(0, 1000000); // desde donde hasta donde queremos los valores
		contentPane.add(progressBar, BorderLayout.NORTH);

		btnClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//creamos un hilo para que no se nos buggee el boton de click
				//ya que el hilo de swing esta "ocupado" con el bucle
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						for ( i=0 ; i <= 1000000 && !stop; i++) {
							System.out.println(i);

							// porque la api dice que si hacemos cambios en swing tienen
							// que hacerse en el hilo de swing, no en el que hemos creado nosotros.
							SwingUtilities.invokeLater(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									progressBar.setValue(i);
								}
							});

						}
					}
				});
				t.start();

			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				stop = true;
			}

		});

		setTitle("Hola mundo desde Java");

	}

}
