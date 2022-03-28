import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class TestArquivoPDF {

	public static void main(String[] args) {

		// Transform file to bytes array
		File file = new File("C:/ArquivosTest/Defensive_Programming_Java_Certificate.pdf");
		FileInputStream fileInput = null;
		FileChannel fileChannel = null;
		try {
			fileInput = new FileInputStream(file);
			fileChannel = fileInput.getChannel();
			int size = (int) fileChannel.size();
			MappedByteBuffer buf = fileChannel.map(MapMode.READ_ONLY, 0, size);

			byte[] bytes = new byte[size];
			buf.get(bytes);

			System.out.println("Bytes do arquivo" + bytes);

			// Transform bytes array to pdf file

			try {
				OutputStream out = new FileOutputStream("C:/ArquivosTest/Automacao.pdf");

				out.write(bytes);
				out.close();

				// Read the file
				FileReader fileReader = new FileReader("C:/ArquivosTest/Automacao.pdf");
				// int line = fileReader.read();
				// while(line != -1) {
				// Sytem.out.print((char)line);
				// }

				if (fileReader.ready()) {
					System.out.print("O Arquivo existe");
				} else {
					System.out.print("O Arquivo não existe");
				}

			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileInput != null) {
					fileInput.close();
				}
				if (fileChannel != null) {
					fileChannel.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
