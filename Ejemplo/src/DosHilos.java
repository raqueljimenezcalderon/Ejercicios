import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DosHilos {

	// si un hilo y otro hilo estan escribiendo a la vez, no vale la version normal
	// del hasmap
	// necesitamos una collecion especial, en este caso una version especial del
	// arraylist.

	private static List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());

	// la lista sincronizada no permite que dos hilos escriban a la vez, pero si leer
	// hay que no hay problema

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Hilo numero 1
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					list.add(5);
					// System.out.println("Añadido 5 -" + list);

				}
			}
		});
		t1.start();

		// Hilo numero 2
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					if (!list.isEmpty()) {
						list.remove(0);
						System.out.println("Eliminado- " + list);
					}
				}
			}
		});
		t2.start();
	}

}
