package hu.web.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;

import org.apache.log4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import hu.model.document.DocumentFile;

public class DocumentUtil {

	private static final String FILE_EXTENSIO_REGEXP = "(.)*\\.[\\w]{3,4}$";
	public static final Logger logger = Logger.getLogger(DocumentUtil.class);
	
	/**
	 * Megnyitja a böngészőben ha lehetséges vagy letölti a kiválaszott fájlt  
	 * @param response
	 * @param file
	 * @param openInBrowser Ha true akkor a böngészőben nyitja meg a fájlt. Ha false akkor letölti. 
	 * @throws IOException
	 */
	public static ResponseEntity<InputStreamResource> setFileIntoResponseEntity(DocumentFile file, boolean openInBrowser) throws IOException {
		String mimeType = file.getContentType();
		
		if (mimeType == null){
			mimeType = URLConnection.guessContentTypeFromName(file.getFileName());
		}
		if(mimeType == null){
		    logger.warn("Nem felismerhető Mimetype. Default használata.");
		    mimeType = "application/octet-stream";
		}
		 
		logger.debug("Végleges mimetype : " + mimeType);
		 
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.parseMediaType(mimeType));
		
		if (openInBrowser){
//			"Content-Disposition : inline" segítségével megnyitja a böngészőben is megtekinthető típusokat. Például : images/text/pdf  
//			 Ha nem nyithatóak meg (pl.: zip) akkor a böngésző a fájl letöltését kezdeményezi
			header.setContentDispositionFormData("inline", file.getFileName());
			
		} else {
//			"Content-Disposition : attachment" segítségével a böngésző a fájl letöltését kezdeményezi
			header.setContentDispositionFormData("attachment", file.getFileName());
		}
		
		byte[] content = file.getContentFile().getContent();
		header.setContentLength(content.length);
    
		InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(content));
		
		return new ResponseEntity<InputStreamResource>(inputStreamResource, header, HttpStatus.OK);
	}
	
	/**
	 * Validálja a paraméterként kapott fájl nevet. 
	 * @param fileName
	 * @return
	 */
	public static String validateFileName(String fileName){
		if (fileName == null || (fileName.trim()).isEmpty()){
			return "File név megadása kötelező";
		}
		if (!fileName.matches(FILE_EXTENSIO_REGEXP)){
			return "A megadott fájl névnek tartalmaznia kell valid fájl kiterjesztést";
		}
		if ( fileName.length() >= 255){
			return "A megadott fájl név túl hosszú";
		}
			
		return null;
	}
	
	/**
	 * Ellenőrzi, hogy a paraméterként kapott fájl név tartalmaz-e kiterjesztést.  
	 * @param fileName
	 * @return
	 */
	public static boolean validateFileNameHasExtension(String fileName){
		return fileName != null && !(fileName.trim()).isEmpty() && fileName.matches(FILE_EXTENSIO_REGEXP);		
	}
	
}
