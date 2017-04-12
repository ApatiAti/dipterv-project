package hu.web.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;

import hu.model.document.DocumentFile;

public class DocumentUtil {

	public static final Logger logger = Logger.getLogger(DocumentUtil.class);
	
	public static void setFileInResponse(HttpServletResponse response, DocumentFile file) throws IOException {
		String mimeType = file.getContentType();
		
		if (mimeType == null){
			mimeType = URLConnection.guessContentTypeFromName(file.getFileName());
		}
		if(mimeType==null){
		    logger.warn("Nem felismerhető Mimetype. Default használata.");
		    mimeType = "application/octet-stream";
		}
		 
		logger.debug("mimetype : " + mimeType);
		 
		response.setContentType(mimeType);
		 
		/* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
		    while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
		response.setHeader("Content-Disposition", String.format("inline; filename=\"%s\"", file.getFileName()));
    
		 
		/* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
	//	response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFileName()));
		 
		byte[] content = file.getContentFile().getContent();
		response.setContentLength(content.length);
    
		InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(content));
    
		//Copy bytes from source to destination(outputstream in this example), closes both streams.
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
	
}
