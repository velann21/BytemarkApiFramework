package co.bytemark.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class AppZip {
	List<String> fileList;

	@SuppressWarnings("unused")
	private String OUTPUT_ZIP_FILE = null;
	private String SOURCE_FOLDER = null;

	public AppZip(String OUTPUT_ZIP_FILE, String SOURCE_FOLDER) {
		this.OUTPUT_ZIP_FILE = OUTPUT_ZIP_FILE;
		this.SOURCE_FOLDER = SOURCE_FOLDER;
		fileList = new ArrayList<String>();
	}

	public void zipIt(String zipFile) {
		byte[] buffer = new byte[1024];
		try {
			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			for (String file : this.fileList) {
				ZipEntry ze = new ZipEntry(file);
				zos.putNextEntry(ze);
				FileInputStream in = new FileInputStream(SOURCE_FOLDER + File.separator + file);
				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				in.close();
			}
			zos.closeEntry();
			// remember close it
			zos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void generateFileList(File node) {
		// add file only
		if (node.isFile()) {
			fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
		}
		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				generateFileList(new File(node, filename));
			}
		}

	}

	private String generateZipEntry(String file) {
		return file.substring(SOURCE_FOLDER.length() + 1, file.length());
	}

}
